package com.shadowzlh.lib.utils;

import com.shadowzlh.lib.common.Constants;
import com.shadowzlh.lib.domain.DateSegment.SeatMap;
import com.shadowzlh.lib.domain.User;
import com.shadowzlh.lib.service.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


public class OrderThread extends Thread{
    private User user;
    @Override
    public void run() {
        Set<Integer> bookedSet = Constants.getBookedSet();
        if (user.getSeats() != null) {
            for (Integer seat : user.getSeats()) {
                if (bookedSet.contains(seat)) continue;
                String areaName = AreaInfo.getAreaName(seat);
                String url = Constants.getBookUrl(seat);
                String segment = User.getSegment(areaName, user.getDates().get(0));
                if (Book.successBook(user, url, segment)) {
                    SendEmail.sendEmail(
                            "你已成功预定" + user.getDates().get(0) + "_" + areaName + "_" + AreaInfo.getSeatNo(seat) + "(座位号为系统算出, 不准确, 建议查看预约系统确认)"
                            , user.getEmail());
                    return;
                } else {
                    bookedSet.add(seat);
                }
            }
        }
            if (user.getAreas() != null) {
            for (String area : user.getAreas()) {
                Integer startNo = SeatMap.seatMaps.get(area).getStartNo();
                Integer endNo = SeatMap.seatMaps.get(area).getEndNo();
                for (int i = startNo; i <= endNo; i++) {
                    if (bookedSet.contains(i)) continue;
                    String url = Constants.getBookUrl(i);
                    String segment = User.getSegment(area,user.getDates().get(0));
                    if (Book.successBook(user,url,segment)) {
                        SendEmail.sendEmail(
                                "你已成功预定"  + user.getDates().get(0) + "_" + area + "_" +String.format("%03d",(i - startNo + 1)) + "(座位号为系统算出, 不准确, 建议查看预约系统确认)"
                                ,user.getEmail());
                        return;
                    } else {
                        bookedSet.add(i);
                    }
                }
            }
        }


        for (String area : SeatMap.seatMaps.keySet()) {
            Integer startNo = SeatMap.seatMaps.get(area).getStartNo();
            Integer endNo = SeatMap.seatMaps.get(area).getEndNo();
            for (int i = startNo; i <= endNo; i++) {
                if (bookedSet.contains(i)) continue;
                String url = Constants.getBookUrl(i);
                String segment = User.getSegment(area,user.getDates().get(0));
                if (Book.successBook(user,url,segment)) {
                    SendEmail.sendEmail(
                            "你已成功预定"  + user.getDates().get(0)  + "_" + area + "_" +String.format("%03d",(i - startNo + 1)) + "(座位号为系统算出, 不准确, 建议查看预约系统确认)"
                            ,user.getEmail());
                    return;
                } else {
                    bookedSet.add(i);
                }
            }
        }


    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderThread(String name, User user){
        this.setName(name);
        this.setUser(user);
    }
}
