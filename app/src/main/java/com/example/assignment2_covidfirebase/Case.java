package com.example.assignment2_covidfirebase;

import java.util.Date;

public class Case {

    private String Age_Group;
    private String Classification_Reported;
    private String HA;
    private Date reported_Date;
    private char sex;

    public String getAge_Group() {
        return Age_Group;
    }

    public String getClassification_Reported() {
        return Classification_Reported;
    }

    public String getHA() {
        return HA;
    }

    public Date getReported_Date() {
        return reported_Date;
    }

    public char getSex() {
        return sex;
    }
}
