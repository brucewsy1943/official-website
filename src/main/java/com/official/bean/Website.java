package com.official.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Website {

    private Integer id;

    private String ip;

    private Integer visitcount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp visitdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(Integer visitcount) {
        this.visitcount = visitcount;
    }


    public Timestamp getVisitdate() {
        return visitdate;
    }


    public void setVisitdate(Timestamp visitdate) {
        this.visitdate = visitdate;
    }
}