package com.emperor_tracare.emperortracked.model;

public class WeightRecord {

    private String day;
    private String month;
    private String value;

    public WeightRecord() {
    }

    public WeightRecord(String day, String month, String value) {
        this.day = day;
        this.month = month;
        this.value = value;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
