package com.mindblank.entities;

import com.mindblank.DatabaseConnection;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends User {
    DatabaseConnection connectSQL = new DatabaseConnection();
    Connection connectDB = connectSQL.getConnection();

    public Doctor(User user) {
        super(user.uName, user.uPass, user.realName, user.email, user.phoneNum, user.address, user.userType);
    }

    // TODO: change BCE to refer to doctor entity
    public boolean getPatientIC(String patientIC) {
        String validatePatient = "SELECT * FROM USER WHERE BINARY NRIC = '" + patientIC + "' AND TYPE = 'PATIENT';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(validatePatient);

            while(queryResult.next()) {
                if (queryResult == null) {
                    return false;
                } else {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    // adds prescription details to prescription sql table
    // adds all medicine in arraylist that is associated with said prescription to medication sql table
    public boolean addPrescription(String patientIC, String token, String date, ArrayList<Medication> medList) {
        String insertPrescription = "INSERT INTO PRESCRIPTION (tokenString, NRIC, date, collectedStatus) " +
                                    "VALUES ('" + token + "', '" + patientIC + "', '" + date + "', 0);";
        String insertMedication;
        boolean addPrescriptionValid = false;

        try {
            Statement statement = connectDB.createStatement();
            statement.execute(insertPrescription);

            for (Medication med : medList) {
                insertMedication = "INSERT INTO MEDICATION (tokenString, medicineID, dosage, instructions, expiry) " +
                                   "VALUES ('" + token + "', " + med.getMedicineID() + ", " + med.getDosage() + ", '" +
                                   med.getInstructions() + "', '" + med.getExpiry() + "');";
                statement.execute(insertMedication);
            }

            addPrescriptionValid = true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return addPrescriptionValid;
    }

    // checks if generated token is already found in database
    public boolean validateToken(String token) {
        String validate = "SELECT * FROM PRESCRIPTION WHERE tokenString = '" + token + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(validate);

            if (queryResult.next() == false) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public void viewPrescriptions(String patientIC, ObservableList<Prescription> presObservableList) {
        String selectPrescriptions = "SELECT * FROM PRESCRIPTION WHERE NRIC = '" + patientIC + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectPrescriptions);

            while (queryResult.next()) {
                Prescription newPres = new Prescription(queryResult.getString(1), queryResult.getString(3));
                presObservableList.add(newPres);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
