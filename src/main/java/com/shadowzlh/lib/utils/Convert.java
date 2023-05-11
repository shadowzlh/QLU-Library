package com.shadowzlh.lib.utils;

import com.shadowzlh.lib.common.Constants;
import com.shadowzlh.lib.domain.DateSegment.Area;
import com.shadowzlh.lib.domain.DateSegment.SeatMap;
import com.shadowzlh.lib.pojo.po.User;

import java.util.*;

public class Convert {
    public static User serviceUser2poUser(com.shadowzlh.lib.domain.User serviceUser) {
        String username = serviceUser.getUsername();
        String password = serviceUser.getPassword();
        String email = serviceUser.getEmail();
        return new User(username,password,email);
    }

    public static com.shadowzlh.lib.domain.User poUser2serviceUser(User userPo, Date date) {
        String username = userPo.getUsername();
        String password = userPo.getPassword();
        String email = userPo.getEmail();
        return new com.shadowzlh.lib.domain.User(username,password,email, Collections.singletonList(date));
    }


//    public static String seatList2seatExpress(List<Integer> list) {
//        Map<String, Area> seatMaps = SeatMap.seatMaps;
//        Map<String,List<Integer>> maps = new HashMap<>();
//
//        String result = "";
//        list.sort((o1,o2)->{
//            return o1 - o2;
//        });
//
//        seatMaps.forEach((k,v)->{
//            for (Integer seat : list) {
//                if (seat >= v.getStartNo() && seat <= v.getEndNo()) {
//                    List<Integer> idList = maps.get(k);
//                    idList
//                }
//            }
//        });
//        return "";
//    }
}
