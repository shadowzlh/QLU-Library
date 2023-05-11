package com.shadowzlh.lib.utils;

import com.shadowzlh.lib.domain.DateSegment.SeatMap;
import org.springframework.stereotype.Component;

@Component
public class AreaInfo {
    final static StringBuilder result = new StringBuilder();
    public static String getAreaName(int seatId) {

        result.delete(0,result.length());
        SeatMap.seatMaps.forEach((k,v) -> {
           if (seatId >= v.getStartNo() && seatId <= v.getEndNo()){
               result.append(k);
           }
       });
       if (result.toString().equals("")) return "机房";
       return result.toString();
   }

   public static int getSeatNo(int seatId) {
       String areaName = getAreaName(seatId);
      return seatId - (SeatMap.seatMaps.get(areaName).getStartNo()) + 1;
   }
}
