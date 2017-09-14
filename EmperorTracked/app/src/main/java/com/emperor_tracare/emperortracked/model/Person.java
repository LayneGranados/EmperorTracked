package com.emperor_tracare.emperortracked.model;


import com.emperor_tracare.emperortracked.R;

import java.util.ArrayList;

public class Person {

    private String nombre;
    private String usuario;
    private String password;
    private String email;
    private String foto;
    private int edad;
    private String fechaNacimiento;
    private String altura;
    private int kilos;
    private String hasCode;
    private boolean selected;
    private ArrayList<Person> tracked;


    public Person(String nombre, String usuario, String password, String email, String foto, int edad, String fechaNacimiento, String altura, int kilos, String hasCode) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.foto = foto;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.altura = altura;
        this.kilos = kilos;
        this.hasCode = hasCode;
        this.tracked = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public int getKilos() {
        return kilos;
    }

    public void setKilos(int kilos) {
        this.kilos = kilos;
    }

    public String getHasCode() {
        return hasCode;
    }

    public void setHasCode(String hasCode) {
        this.hasCode = hasCode;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static int findImageByUsername(String username){
        if(username.equalsIgnoreCase("marie.adams")){
            return R.drawable.marie_adams;
        } else if (username.equalsIgnoreCase("anna.adams")) {
            return R.drawable.anna_adams;
        } else if (username.equalsIgnoreCase("jhon.bennet")) {
            return R.drawable.jhon_bennet;
        } else if (username.equalsIgnoreCase("claire.adams")) {
            return R.drawable.claire_adams;
        } else if (username.equalsIgnoreCase("christ.mchill")) {
            return R.drawable.christ_mchill;
        } else if (username.equalsIgnoreCase("walter.adams")) {
            return R.drawable.walter_adams;
        } else if (username.equalsIgnoreCase("jules.adams")) {
            return R.drawable.jules_adams;
        } else {
            return R.drawable.profile;
        }
    }

    public ArrayList<Person> getTracked() {
        return tracked;
    }

    public void setTracked(ArrayList<Person> tracked) {
        this.tracked = tracked;
    }
}
