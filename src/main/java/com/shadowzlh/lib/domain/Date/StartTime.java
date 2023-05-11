package com.shadowzlh.lib.domain.Date;


import java.util.Date;

public class StartTime {

    private Date date;
    private int timezone_type;
    private String timezone;
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }

    public void setTimezone_type(int timezone_type) {
        this.timezone_type = timezone_type;
    }
    public int getTimezone_type() {
        return timezone_type;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public String getTimezone() {
        return timezone;
    }

}