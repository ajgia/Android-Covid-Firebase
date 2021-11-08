package com.example.assignment2_covidfirebase;

import java.util.Date;

public class Case {

    private String Age_Group;
    private String Classification_Reported;
    private String HA;
    private String Reported_Date;
    private String Sex;

    public Case(){}

    public Case(String age_Group, String classification_Reported, String ha, String date, String sex) {
        this.Age_Group = age_Group;
        this.Classification_Reported = classification_Reported;
        this.HA = ha;
        this.Reported_Date = date;
        this.Sex = sex;
    }


    public String getAge_Group() { return Age_Group; }

    public String getClassification_Reported() {
        return Classification_Reported;
    }

    public String getHA() {
        return HA;
    }

    public String getReported_Date() {
        return Reported_Date;
    }

    public String getSex() {
        return Sex;
    }
}
