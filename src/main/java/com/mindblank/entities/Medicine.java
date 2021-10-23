package com.mindblank.entities;

public class Medicine {
    private int medicineID;
    private String name;

    public Medicine() {
        medicineID = 0;
        name = "";
    }

    public Medicine(int medicineID, String name) {
        this.medicineID = medicineID;
        this.name = name;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public String getName() {
        return name;
    }
}
