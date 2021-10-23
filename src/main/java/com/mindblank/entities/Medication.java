package com.mindblank.entities;

// TODO: add medicine as private attribute in class diagram
public class Medication {
    private String tokenString;
    private Medicine medicine;
    private int dosage;
    private String instructions;
    private String expiry;

    public Medication() {
        tokenString = "";
        medicine = new Medicine();
        dosage = 0;
        instructions = "";
        expiry = "";
    }

    public Medication(Medicine medicine, int dosage, String instructions, String expiry) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.instructions = instructions;
        this.expiry = expiry;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getTokenString() {
        return tokenString;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public String getMedicineName() {
        return medicine.getName();
    }

    public int getMedicineID() {
        return medicine.getMedicineID();
    }

    public int getDosage() {
        return dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getExpiry() {
        return expiry;
    }

}
