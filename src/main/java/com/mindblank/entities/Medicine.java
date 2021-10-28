package com.mindblank.entities;

import com.mindblank.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Medicine {
    private int medicineID;
    private String name;
    DatabaseConnection connectSQL = new DatabaseConnection();
    Connection connectDB = connectSQL.getConnection();

    public Medicine() {
        medicineID = 0;
        name = "";
    }

    public Medicine(int medicineID, String name) {
        this.medicineID = medicineID;
        this.name = name;
    }

    public Medicine(int medicineID) {
        this.medicineID = medicineID;
        setMedicineNameFromDB();
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

    private void setMedicineNameFromDB() {
        String selectName = "SELECT name FROM MEDICINE WHERE medicineID = " + medicineID + ";";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectName);

            while (queryResult.next()) {
                this.name = queryResult.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
