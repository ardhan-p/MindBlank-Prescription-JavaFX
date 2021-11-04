package com.mindblank.entities;

import com.mindblank.DatabaseConnection;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Pharmacist extends User {
    DatabaseConnection connectSQL = new DatabaseConnection();
    Connection connectDB = connectSQL.getConnection();

    public Pharmacist(User user) {
        super(user.uName, user.uPass, user.realName, user.email, user.phoneNum, user.address, user.userType);
    }

    public boolean getToken(String token) {
        String validate = "SELECT * FROM PRESCRIPTION WHERE tokenString = '" + token + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(validate);

            while(queryResult.next()) {
                return queryResult != null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public void getMedication(String token, ObservableList<Medication> medicationObservableList){
        String selectMedication = "SELECT medicine.name, medication.dosage, medication.expiry, medication.instructions " +
                "FROM MEDICATION INNER JOIN MEDICINE ON medication.medicineID = medicine.medicineID WHERE medication.tokenString = '" + token + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectMedication);

            while (queryResult.next()) {
                Medication newMed = new Medication(queryResult.getString(1), queryResult.getInt(2), queryResult.getString(3), queryResult.getString(4));
                medicationObservableList.add(newMed);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public Patient viewPatientData(String tokenString) {
        String selectUsernameFromToken = "SELECT NRIC FROM PRESCRIPTION WHERE tokenString = '" + tokenString + "';";
        Patient currentPatient = new Patient();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectUsernameFromToken);

            while (queryResult.next()) {
                currentPatient.setPatientInfoFromDB(queryResult.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return currentPatient;
    }

    public String getPrescriptionDate(String tokenString) {
        String date = "";
        String selectDate = "SELECT date FROM PRESCRIPTION WHERE tokenString = '" + tokenString + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectDate);

            while (queryResult.next()) {
                date = queryResult.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return date;
    }

    public void updateStatus(String tokenString) {
        String updateSt = " UPDATE PRESCRIPTION " +
                          "SET collectedStatus = 1 " +
                          "WHERE tokenString = '" + tokenString + "';";

        try {
            Statement statement = connectDB.createStatement();
            statement.execute(updateSt);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public int updateStatusBool(String tokenString) {
        int hold = 0;
        String updateBool = " SELECT collectedStatus " +
                "FROM PRESCRIPTION " +
                "WHERE tokenString = '" + tokenString + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(updateBool);

            while(queryResult.next()) {
                hold = queryResult.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return hold;

    }


}
