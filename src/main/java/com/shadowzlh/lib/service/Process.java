package com.shadowzlh.lib.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadowzlh.lib.common.Constants;
import com.shadowzlh.lib.domain.User;
import com.shadowzlh.lib.pojo.po.UserArea;
import com.shadowzlh.lib.pojo.po.UserDate;
import com.shadowzlh.lib.pojo.po.UserSeat;
import com.shadowzlh.lib.utils.Convert;
import com.shadowzlh.lib.utils.Day;
import com.shadowzlh.lib.utils.OrderThread;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@Data
public class Process {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDateService userDateService;
    @Autowired
    private UserAreaService userAreaService;
    @Autowired
    private UserSeatService userSeatService;


    private List<User> serviceUsers = new ArrayList<>();

    //    读取文件参数,创建线程
    @Scheduled(cron = "0 30 5 * * *")
    public void Init() {
        serviceUsers.clear();
        Constants.getBookedSet().clear();
        // 查询数据库用户
        List<com.shadowzlh.lib.pojo.po.User> userPos = userService.list();

        for (com.shadowzlh.lib.pojo.po.User userPo : userPos) {
            // 获得id
            Integer userId = userPo.getId();
            // 获得user的date 并 按照date排序
            List<UserDate> userDates = userDateService.list(new QueryWrapper<UserDate>().eq("user_id",userId));
            userDates.sort((o1, o2) -> (int) (o1.getDate().getTime() - o2.getDate().getTime()));

            // 选择最新的date
            if (userDates == null || userDates.isEmpty()) continue;
            UserDate userDate = userDates.get(0);
            Date date = userDate.getDate();
            // 如果今天抢, 那么就加入列表 生成serviceUser
            User user = Convert.poUser2serviceUser(userPo, date);
            if (Day.inSameDay(Day.getBeforeDay(date),new Date())) {
                serviceUsers.add(user);
            } else {
                continue;
            }
            // 在数据库中删除掉当前的任务, 这样是因为当前方法的运行时间充裕
            userDateService.remove(new QueryWrapper<UserDate>().eq("user_id",userId).eq("date",date));

            // 查询区域,流处理转换对象,设置区域
            List<UserArea> userAreas = userAreaService.list(new LambdaQueryWrapper<UserArea>().eq(UserArea::getUserId,userId));
            if (userAreas != null && !userAreas.isEmpty()) {
                List<String> areas = userAreas.stream().map(UserArea::getArea).collect(Collectors.toList());
                user.setAreas(areas);
            }

            // 查询座位,流处理转换对象,设置区域
            List<UserSeat> userSeats = userSeatService.list(new LambdaQueryWrapper<UserSeat>().eq(UserSeat::getUserId,userId));
            if (userSeats != null && !userSeats.isEmpty()) {
                List<Integer> seats = userSeats.stream().map(UserSeat::getSeat).collect(Collectors.toList());
                user.setSeats(seats);
            }

        }

        for (User loginUser : serviceUsers) {
            new Thread(() -> Login.SuccessLogin(loginUser),loginUser.getUsername()).start();
//            Login.SuccessLogin(loginUser);
        }
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void process() {
        if (serviceUsers == null) return;
        for (User user : serviceUsers) {
            new OrderThread(user.getUsername() + "book", user).start();
//           new OrderThread(user.getUsername() + "book", user).run();

        }
    }

    @Scheduled(cron = "0 20 6 * * *")
    public void end() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread[] threads = new Thread[Thread.activeCount()];
        threadGroup.enumerate(threads);

        for (Thread thread : threads) {
            if (thread.getName().contains("book")) {
                thread.interrupt();
            }
        }
    }
}
