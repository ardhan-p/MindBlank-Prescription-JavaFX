package com.mindblank.entities;

import java.util.ArrayList;

public class Prescription {
    private String tokenString;
    private String NRIC;
    private String date;
    private String dosage;
    private String expiry;
    private String instructions;
    private boolean collectedStatus;
    private ArrayList<Medication> medicationList;

    public Prescription() {
        tokenString = "";
        NRIC = "";
        date = "";
        collectedStatus = false;
        medicationList = new ArrayList<Medication>();
    }

    public Prescription(String tokenString, String date) {
        this.tokenString = tokenString;
        this.date = date;
        medicationList = new ArrayList<Medication>();
    }

    public Prescription(String tokenString, String NRIC, String date, boolean collectedStatus) {
        this.tokenString = tokenString;
        this.NRIC = NRIC;
        this.date = date;
        this.collectedStatus = collectedStatus;
        medicationList = new ArrayList<Medication>();
    }

    public String getTokenString() {
        return tokenString;
    }

    public String getNRIC() {
        return NRIC;
    }

    public String getDate() {
        return date;
    }

    public boolean getCollectedStatus() {
        return collectedStatus;
    }

    public ArrayList<Medication> getMedicationList() {
        return medicationList;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCollectedStatus(boolean collectedStatus) {
        this.collectedStatus = collectedStatus;
    }

    public void setMedicationList(ArrayList<Medication> medicationList) {
        this.medicationList = medicationList;
    }
}
