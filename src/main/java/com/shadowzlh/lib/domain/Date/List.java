package com.shadowzlh.lib.domain.Date;

import lombok.Data;

import java.util.Date;

@Data
public class List {
    private Date day;
    private long id;
    private StartTime startTime;
    private EndTime endTime;
    private int area;

}