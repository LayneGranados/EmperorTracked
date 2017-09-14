package com.emperor_tracare.emperortracked.model;

public class StepRecord {

    private String day;
    private String month;
    private String numberSteps;
    private String caloriesBurned;

    public StepRecord() {
    }

    public StepRecord(String day, String month, String numberSteps, String caloriesBurned) {
        this.day = day;
        this.month = month;
        this.numberSteps = numberSteps;
        this.caloriesBurned = caloriesBurned;
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

    public String getNumberSteps() {
        return numberSteps;
    }

    public void setNumberSteps(String numberSteps) {
        this.numberSteps = numberSteps;
    }

    public String getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(String caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
