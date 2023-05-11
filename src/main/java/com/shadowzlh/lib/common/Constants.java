package com.shadowzlh.lib.common;


import com.shadowzlh.lib.domain.Area.ChildArea;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Slf4j
public class Constants {
    public static Set<Integer> getBookedSet() {
        return bookedSet;
    }

    public static void setBookedSet(Set<Integer> booked) {
        bookedSet = booked;
    }

    private static Set<Integer>  bookedSet = new HashSet<>();

    public String get = "";

    public static String languagePath ;
    public static String userPropertiesPath ;

    static {
        if (System.getProperty("os.name").toLowerCase().contains("windows")){
            languagePath  = "D:\\Desktop\\tessdata";
//            userPropertiesPath  = "D:\\Software\\IntelliJ IDEA 2021.3.1\\JavaEE_Experiment\\LibrarySeat\\src\\main\\resources";
        } else {
            log.info("Linux ----------------------");
//            userPropertiesPath  = "/usr/local/library";
            languagePath  = "/usr/local/library/tessdata";
        }
    }

    /*网站请求url*/
    private static String cookieUrl = "http://yuyue.lib.qlu.edu.cn/";
    private static String loginUrl = "http://yuyue.lib.qlu.edu.cn/api.php/login/";
    private static String captchaUrl = "http://yuyue.lib.qlu.edu.cn/api.php/check/";
    private static String seatUrl = "http://yuyue.lib.qlu.edu.cn/api.php/spaces_old/";
    private static String areaUrl = "http://yuyue.lib.qlu.edu.cn/api.php/areas/0/date/";
    private static String bookUrl = "http://yuyue.lib.qlu.edu.cn/api.php/spaces/#/book";
    private static String segmentUrl = "http://yuyue.lib.qlu.edu.cn/api.php/areadays/#";
    private static String loginReferer = "http://yuyue.lib.qlu.edu.cn/home/web/seat/area/1/";
    private static String areaReferer = "http://yuyue.lib.qlu.edu.cn/home/web/seat/area/1/";
    private static String seatReferer = " http://yuyue.lib.qlu.edu.cn/web/seat3";

//
    public static String getSeatUrl(ChildArea childArea, Boolean Reserve,String orderDate) {
        seatUrl += "?";
        seatUrl = seatUrl + "area=" + childArea.getId();

        if (Reserve){
            String day = (orderDate);
            String endTime = "22:00";
            seatUrl = seatUrl + "&day=" + day;
            seatUrl = seatUrl + "&startTime=" + "8:30" + "&endTime=" + endTime;
            return seatUrl;
        }else {
            Date date = new Date();
            String day = (date.getYear() + 1900) +  "-" + (date.getMonth() + 1) + "-" + date.getDate();
            String minutes = String.valueOf(date.getMinutes());
            if (minutes.length() < 2) minutes = "0" + minutes;
            String startTime = date.getHours() + ":" + minutes;
            String endTime = "22:00";
            seatUrl = seatUrl + "&day=" + day;
            seatUrl = seatUrl + "&startTime=" + startTime + "&endTime=" + endTime;
            return seatUrl;
        }

    }

    public static String getSeatReferer(ChildArea childArea, Boolean Reserve) {

        seatReferer += "?";
        seatReferer = seatReferer + "area=" + childArea.getId();

        if (Reserve){

            return null;
        }else {
            Date date = new Date();
            String day = (date.getYear() + 1900) +  "-" + (date.getMonth() + 1) + "-" + date.getDate();
            String minutes = String.valueOf(date.getMinutes());
            if (minutes.length() < 2) minutes = "0" + minutes;
            String startTime = date.getHours() + ":" + minutes;
            String endTime = "22:00";
            seatReferer = seatReferer + "&day=" + day;
            seatReferer = seatReferer + "&startTime=" + startTime + "&endTime=" + endTime;
            return seatReferer;
        }

    }

    public static String getBookUrl(int seatId) {
        return bookUrl.replace("#",String.valueOf(seatId));
    }

    public static String getSegmentUrl(int areaId) {
        return segmentUrl.replace("#",String.valueOf(areaId));
    }

    public static String getAreaUrl(String date) {
        return areaUrl + date ;
    }

    public String getGet() {
        return get;
    }

    public static String getLanguagePath() {
        return languagePath;
    }

    public static String getUserPropertiesPath() {
        return userPropertiesPath;
    }

    public static String getCookieUrl() {
        return cookieUrl;
    }

    public static String getLoginUrl() {
        return loginUrl;
    }

    public static String getCaptchaUrl() {
        return captchaUrl;
    }

//    public static String getSeatUrl() {
//        return seatUrl;
//    }

    public static String getAreaUrl() {
        return areaUrl;
    }

    public static String getLoginReferer() {
        return loginReferer;
    }

    public static String getAreaReferer() {
        return areaReferer;
    }

    public static String getSeatReferer() {
        return seatReferer;
    }
}
