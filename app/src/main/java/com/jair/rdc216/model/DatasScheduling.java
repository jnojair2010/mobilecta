package com.jair.rdc216.model;

import com.google.gson.annotations.SerializedName;

public class DatasScheduling {

    @SerializedName("day")
    private int day;

    @SerializedName("month")
    private int month;
    @SerializedName("year")
    private int year;

    @SerializedName("idEmpresa")
    private int idCompany;
    @SerializedName("nameEmpresa")
    private String nameCompany;


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }
}
