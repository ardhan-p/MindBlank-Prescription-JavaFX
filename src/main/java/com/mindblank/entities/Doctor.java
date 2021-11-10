package com.mindblank.entities;

import com.mindblank.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends User {

    public Doctor(User user) {
        super(user.uName, user.uPass, user.realName, user.email, user.phoneNum, user.address, user.userType);
    }

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

    public ArrayList<Prescription> viewAllPrescriptions(String patientIC) {
        String selectPrescriptions = "SELECT * FROM PRESCRIPTION WHERE NRIC = '" + patientIC + "';";
        ArrayList<Prescription> presArrayList = new ArrayList<Prescription>();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectPrescriptions);

            while (queryResult.next()) {
                Prescription newPres = new Prescription(queryResult.getString(1), queryResult.getString(3));
                presArrayList.add(newPres);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return presArrayList;
    }

    public ArrayList<Medication> viewMedicationsInPrescription(String tokenString) {
        String selectCurrentPrescription = "SELECT * FROM MEDICATION WHERE tokenString = '" + tokenString + "';";
        ArrayList<Medication> medArrayList = new ArrayList<Medication>();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectCurrentPrescription);

            while (queryResult.next()) {
                // create medicine object
                Medicine newMedicine = new Medicine(queryResult.getInt(2));

                // create medication object
                Medication newMedication = new Medication(newMedicine, queryResult.getInt(3), queryResult.getString(4), queryResult.getString(5));
                medArrayList.add(newMedication);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return medArrayList;
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

    public String getEmail(String tokenString) {
        String email = "";
        String selectEmail = "SELECT USER.email FROM USER INNER JOIN PRESCRIPTION ON USER.NRIC = PRESCRIPTION.NRIC " +
                             "WHERE tokenString = '" + tokenString + "';";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(selectEmail);

            while (queryResult.next()) {
                email = queryResult.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return email;
    }

    public void setDoctorInfoFromDB(String NRIC) {
        String getDoctorInfo = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getDoctorInfo);

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
