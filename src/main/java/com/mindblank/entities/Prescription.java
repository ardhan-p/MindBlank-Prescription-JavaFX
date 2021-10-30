package com.mindblank.entities;

public class Prescription {
    private String tokenString;
    private String NRIC;
    private String date;
    private String dosage;
    private String expiry;
    private String instructions;
    private boolean collectedStatus;

    public Prescription() {
        tokenString = "";
        NRIC = "";
        date = "";
        collectedStatus = false;
    }

    public Prescription(String tokenString, String date) {
        this.tokenString = tokenString;
        this.date = date;
    }

    public Prescription(String tokenString, String NRIC, String date, boolean collectedStatus) {
        this.tokenString = tokenString;
        this.NRIC = NRIC;
        this.date = date;
        this.collectedStatus = collectedStatus;
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
}
