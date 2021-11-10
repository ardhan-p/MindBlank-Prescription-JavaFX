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

//    public ArrayList<Medication> getMedication(String token) {
//        String selectMedication = "SELECT medicine.name, medication.dosage, medication.expiry, medication.instructions " +
//                "FROM MEDICATION INNER JOIN MEDICINE ON medication.medicineID = medicine.medicineID INNER JOIN PRESCRIPTION ON medication.tokenString=prescription.tokenString" +
//                " WHERE medication.tokenString = '" + token + "' AND prescription.collectedStatus = 0" + ";";
//        ArrayList<Medication> medicationArrayList = new ArrayList<Medication>();
//
//        try {
//            Statement statement = connectDB.createStatement();
//            ResultSet queryResult = statement.executeQuery(selectMedication);
//
//            while (queryResult.next()) {
//                Medication newMed = new Medication(queryResult.getString(1), queryResult.getInt(2), queryResult.getString(3), queryResult.getString(4));
//                medicationArrayList.add(newMed);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            e.getCause();
//        }
//
//        return medicationArrayList;
//    }

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

    public void setPatientInfoFromDB(String NRIC) {
        String getPatientInfo = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getPatientInfo);

            while (queryResult.next()) {
                this.uName = queryResult.getString(1);
                this.realName = queryResult.getString(3);
                this.email = queryResult.getString(4);
                this.phoneNum = queryResult.getString(5);
                this.address = queryResult.getString(6);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}




