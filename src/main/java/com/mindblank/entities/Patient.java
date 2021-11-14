package com.mindblank.entities;

import com.mindblank.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Patient extends User {
    DatabaseConnection connectSQL = new DatabaseConnection();
    Connection connectDB = connectSQL.getConnection();

    public Patient(User user) {
        super(user.uName, user.uPass, user.realName, user.email, user.phoneNum, user.address, user.userType);
    }

    public Patient() {

    }

    // checks whether or not nric is already in database
    public boolean getPatientIC(String patientIC) {
        String validatePatient = "SELECT * FROM USER WHERE BINARY NRIC = '" + patientIC + "' AND TYPE = 'PATIENT';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(validatePatient);

            while (queryResult.next()) {
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

    // checks whether or not prescription token is already in database
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

    // returns an arraylist of prescriptions associated with the inputted nric
    public ArrayList<Prescription> viewAllPastPrescriptions(String patientIC) {
        String selectPrescriptions = "SELECT * FROM PRESCRIPTION WHERE NRIC = '" + patientIC + "' AND COLLECTEDSTATUS = 1" + ";";
        ArrayList<Prescription> prescriptionArrayList = new ArrayList<Prescription>();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectPrescriptions);

            while (queryResult.next()) {
                Prescription newPres = new Prescription(queryResult.getString(1), queryResult.getString(3));
                prescriptionArrayList.add(newPres);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return prescriptionArrayList;
    }

    // returns an arraylist of medication associated with the inputted prescription token
    public ArrayList<Medication> viewMedicationsInPrescription(String tokenString) {
        String selectCurrentPrescription = "SELECT * FROM MEDICATION WHERE tokenString = '" + tokenString + "';";
        ArrayList<Medication> medicationArrayList = new ArrayList<Medication>();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectCurrentPrescription);

            while (queryResult.next()) {
                // create medicine object
                Medicine newMedicine = new Medicine(queryResult.getInt(2));

                // create medication object
                Medication newMedication = new Medication(newMedicine, queryResult.getInt(3), queryResult.getString(4), queryResult.getString(5));
                medicationArrayList.add(newMedication);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return medicationArrayList;
    }

    // returns patient object associated with the inputted prescription token
    public Patient viewPatientData(String tokenString) {
        String selectUsernameFromToken = "SELECT NRIC FROM PRESCRIPTION WHERE tokenString = '" + tokenString + "';";
        Patient currentPatient = new Patient();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectUsernameFromToken);

            while (queryResult.next()) {
                currentPatient = currentPatient.setPatientInfoFromDB(queryResult.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return currentPatient;
    }

    // returns the date of a prescription from database
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

    // returns a patient object with the corresponding attributes fetched from database
    public Patient setPatientInfoFromDB(String NRIC) {
        String getPatientInfo = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";
        Patient pat = new Patient();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getPatientInfo);

            while (queryResult.next()) {
                pat.uName = queryResult.getString(1);
                pat.realName = queryResult.getString(3);
                pat.email = queryResult.getString(4);
                pat.phoneNum = queryResult.getString(5);
                pat.address = queryResult.getString(6);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return pat;
    }
}




