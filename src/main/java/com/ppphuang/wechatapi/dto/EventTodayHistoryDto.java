package com.ppphuang.wechatapi.dto;

import lombok.Data;

@Data
public class EventTodayHistoryDto {

    private String title;
    private int year;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

}