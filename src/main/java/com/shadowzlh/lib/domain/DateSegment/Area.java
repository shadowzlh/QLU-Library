package com.shadowzlh.lib.domain.DateSegment;

import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class Area {

    private Integer areaId;
    private Date date;
    private Integer segment;
    private Integer startNo;
    private Integer endNo;


    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSegment() {
        return segment;
    }

    public void setSegment(Integer segment) {
        this.segment = segment;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }


    public Integer getStartNo() {
        return startNo;
    }

    public Integer getEndNo() {
        return endNo;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", date=" + date +
                ", segment=" + segment +
                ", startNo=" + startNo +
                ", endNo=" + endNo +
                '}';
    }
}
