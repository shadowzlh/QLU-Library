package com.shadowzlh.lib.domain;

import com.shadowzlh.lib.domain.Date.DateJson;
import com.shadowzlh.lib.domain.DateSegment.SeatMap;
import com.shadowzlh.lib.domain.Seat.SeatStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Data
@NoArgsConstructor
public class User {


    /*用户数据*/
    private String username = "";
    private String password = "";
    private String email = "";
    private List<String> areas = new ArrayList<>();

    private List<Date> dates = new ArrayList<>();

    private List<Integer> seats = new ArrayList<>();
    /*png 验证码字节数组*/
    private byte[] pngBytes;
    private DefaultHttpClient httpClient = new DefaultHttpClient();
    /*具体楼层座位对象*/
    private SeatStatus seatStatus;
    /*网站时间段信息*/
    private   DateJson dateJson;


    public User(String username,String password,String eamil, List<Date> dates){
        this.username = username;
        this.password = password;
        this.dates = dates;
        this.email = eamil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }

    public byte[] getPngBytes() {
        return pngBytes;
    }

    public void setPngBytes(byte[] pngBytes) {
        this.pngBytes = pngBytes;
    }

    public DefaultHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(DefaultHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public DateJson getDateJson() {
        return dateJson;
    }

    public void setDateJson(DateJson dateJson) {
        this.dateJson = dateJson;
    }

    public static String getSegment(String area, Date date) {
        final long ONE_DAY_MILLIS = 1000L * 60 * 60 * 24;
        if (SeatMap.seatMaps == null)  log.info("seatMaps null ------------------------------");
        if (SeatMap.seatMaps.get(area) == null)  log.info(area + " null ------------------------------");
        Date base = SeatMap.seatMaps.get(area).getDate();
        long initSegment = SeatMap.seatMaps.get(area).getSegment();
        long interval = (date.getTime() - base.getTime()) / ONE_DAY_MILLIS;
        return String.valueOf(interval + initSegment);
    }

    public String getAccessToken() {
       return httpClient.getCookieStore().getCookies().stream().filter((k) -> k.getName().equals("access_token")).collect(Collectors.toList()).get(0).getValue();
    }
}
