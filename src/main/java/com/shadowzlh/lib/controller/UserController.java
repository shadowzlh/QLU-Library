package com.shadowzlh.lib.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadowzlh.lib.domain.User;
import com.shadowzlh.lib.pojo.po.UserArea;
import com.shadowzlh.lib.pojo.po.UserDate;
import com.shadowzlh.lib.pojo.po.UserSeat;
import com.shadowzlh.lib.pojo.po.UserSeatstr;
import com.shadowzlh.lib.service.*;
import com.shadowzlh.lib.service.Process;
import com.shadowzlh.lib.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDateService userDateService;
    @Autowired
    private UserAreaService userAreaService;
    @Autowired
    private UserSeatService userSeatService;
    @Autowired
    private UserSeatstrService userSeatstrService;

    @GetMapping
    public String loginPage()  {
        return "static/login.html";
    }

    @PostMapping("login")
    @ResponseBody
    public Map<String, String> login(String username, String password, String email, HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        String result = Login.SuccessLogin(user);
        if (result.equals("Success")) {
            Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());

            cookie.setMaxAge(60 * 60 * 24 * 7);
            request.getSession().setMaxInactiveInterval(60 * 60 * 24 * 7);
            request.getSession().setAttribute("user",user);
            response.addCookie(cookie);
        }

        Map<String,String> map = new HashMap<>();
        map.put("code","1");
        map.put("msg",result);
        return map;
    }
    @GetMapping("cfg")
    public String cfg() {
        return "/static/index.html";
    }

    @PostMapping("save")
    @ResponseBody
    public  Map<String, String> save(@RequestParam(value = "area[]",required = false) String[] areas, @RequestParam(value = "seat[]",required = false) Integer[] seats, String date,String seatStr,HttpServletRequest request)  {

        User user = (User) request.getSession().getAttribute("user");

        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setDates(Collections.singletonList(simpleDateFormat.parse(date)));

            com.shadowzlh.lib.pojo.po.User userPo = Convert.serviceUser2poUser(user);
            userService.saveOrUpdateByMultiId(userPo);
            com.shadowzlh.lib.pojo.po.User DbUserPo = userService.selectByMultiId(userPo);
            Integer userId = DbUserPo.getId();


            userDateService.remove(new QueryWrapper<UserDate>().eq("user_id",userId));
            userDateService.saveOrUpdateByMultiId(new UserDate(simpleDateFormat.parse(date),userId));


            if (areas != null) {
                user.setAreas(Arrays.asList(areas));
                    userAreaService.remove(new QueryWrapper<UserArea>().eq("user_id",userId));
                for (String s : areas) {
                    userAreaService.saveOrUpdateByMultiId(new UserArea(userId,s));
                }
            }else {
                userAreaService.remove(new QueryWrapper<UserArea>().eq("user_id",userId));
            }

            if (seats != null) {
                userSeatService.remove(new QueryWrapper<UserSeat>().eq("user_id",userId));
                user.setSeats(Arrays.asList(seats));
                new Thread(()->{
                    for (Integer s : seats) {
                        userSeatService.saveOrUpdateByMultiId(new UserSeat(userId,s));
                    }
                }).start();
            }else {
                userSeatService.remove(new QueryWrapper<UserSeat>().eq("user_id",userId));
            }


            if (!StringUtils.isEmpty(seatStr)) {
                userSeatstrService.remove(new LambdaQueryWrapper<UserSeatstr>().eq(UserSeatstr::getUserId,userId));
                userSeatstrService.saveOrUpdateByMultiId(new UserSeatstr(userId,seatStr));
            } else {
                userSeatstrService.remove(new LambdaQueryWrapper<UserSeatstr>().eq(UserSeatstr::getUserId,userId));
            }

        }catch (Exception e) {
            e.printStackTrace();
            Map<String,String> map = new HashMap<>();
            map.put("code","0");
            map.put("msg","");
            return map;
        }




        Map<String,String> map = new HashMap<>();
        map.put("code","1");
        map.put("msg","");
        return map;
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {


        Map<String,Object> map = new HashMap<>();
        map.put("msg","");
        map.put("code","1");

        User user = (User) request.getSession().getAttribute("user");

//        System.out.println(request.getSession().getId());

        List<com.shadowzlh.lib.pojo.po.User> list = userService.list(new LambdaQueryWrapper<com.shadowzlh.lib.pojo.po.User>().eq(com.shadowzlh.lib.pojo.po.User::getUsername, user.getUsername()));
        if (list == null || list.isEmpty()) {
            map.put("code","0");
            return map;
        }
        Integer userId = list.get(0).getUserId();

        List<UserDate> dateList = userDateService.list(new LambdaQueryWrapper<UserDate>().eq(UserDate::getUserId, userId));

        if (dateList != null && !dateList.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            map.put("date",simpleDateFormat.format(dateList.get(0).getDate()));
        }


        List<UserArea> areaList = userAreaService.list(new LambdaQueryWrapper<UserArea>().eq(UserArea::getUserId, userId));
        if (areaList != null && !areaList.isEmpty()) {
            StringBuilder areaStr = new StringBuilder();
           areaList.forEach((area)-> areaStr.append(area.getArea()).append(","));
           map.put("area",areaStr);
        }


        List<UserSeatstr> seatstrs = userSeatstrService.list(new LambdaQueryWrapper<UserSeatstr>().eq(UserSeatstr::getUserId, userId));
        if (seatstrs != null && seatstrs.size() > 0) {
            map.put("seat",seatstrs.get(0).getSeatstr());
        }


        return map;

    }

//    @RequestMapping("test")
//    public void test(){
//        Process process = new Process();
//        process.setUserSeatService(userSeatService);
//        process.setUserService(userService);
//        process.setUserAreaService(userAreaService);
//        process.setUserDateService(userDateService);
//
//        process.Init();
//        process.process();
//    }
}
