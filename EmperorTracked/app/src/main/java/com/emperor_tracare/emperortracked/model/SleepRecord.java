package com.emperor_tracare.emperortracked.model;

public class SleepRecord {
    private String day;
    private String month;
    private String description;
    private String hours;
    private String minutes;

    public SleepRecord() {
    }

    public SleepRecord(String day, String month, String description, String hours, String minutes) {
        this.day = day;
        this.month = month;
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
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
}
