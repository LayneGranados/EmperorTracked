package com.emperor_tracare.emperortracked.model;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {

    private ArrayList<Alarm> alarms = new ArrayList<>();
    private ArrayList<Person> people = new ArrayList<>();
    private Person personSigned;
    private Person personSelected;
    private String fragment;

    public ArrayList<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public static Person findPersonByUsername(ArrayList<Person> people, String username){
        for(Person p: people) {
            if(p.getUsuario().equalsIgnoreCase(username))
                return p;
        }
        return null;
    }

    public static Person findPersonByHashCode(ArrayList<Person> people, String hashCode){
        for(Person p: people) {
            if(p.getHasCode().equals(hashCode))
                return p;
        }
        return null;
    }

    public Person getPersonSigned() {
        return personSigned;
    }

    public void setPersonSigned(Person personSigned) {
        this.personSigned = personSigned;
    }

    public Person getPersonSelected() {
        return personSelected;
    }

    public void setPersonSelected(Person personSelected) {
        this.personSelected = personSelected;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}
