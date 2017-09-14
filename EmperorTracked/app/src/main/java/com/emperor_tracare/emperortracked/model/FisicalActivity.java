package com.emperor_tracare.emperortracked.model;

public class FisicalActivity {

    private String day;
    private String month;
    private String type;
    private String time;
    private String calories;

    public FisicalActivity(String day, String month, String type, String time, String calories) {
        this.day = day;
        this.month = month;
        this.type = type;
        this.time = time;
        this.calories = calories;
    }

    public FisicalActivity() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
