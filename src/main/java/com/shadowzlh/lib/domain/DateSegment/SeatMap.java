package com.shadowzlh.lib.domain.DateSegment;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties("segment")
public class SeatMap {
   public static Map<String,Area> seatMaps;
    public Map<String, Area> getSeatMaps() {
        return seatMaps;
    }

    public void setSeatMaps(Map<String, Area> seatMaps) {
        SeatMap.seatMaps = seatMaps;
    }
}
