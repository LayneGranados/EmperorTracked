package com.emperor_tracare.emperortracked.model;

public class HeartRateRecord {

    private String day;
    private String month;
    private String description;
    private String value;

    public HeartRateRecord() {
    }

    public HeartRateRecord(String day, String month, String description, String value) {
        this.day = day;
        this.month = month;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
