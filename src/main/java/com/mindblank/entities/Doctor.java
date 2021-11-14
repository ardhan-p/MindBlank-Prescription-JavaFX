package com.mindblank.entities;

import com.mindblank.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends User {

    public Doctor() {
        super();
    }

    public Doctor(User user) {
        super(user.uName, user.uPass, user.realName, user.email, user.phoneNum, user.address, user.userType);
    }

    // checks whether nric is a valid patient in the database
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

    // returns an arraylist containing prescriptions that has the same inputted nric
    public ArrayList<Prescription> viewPatientPrescriptions(String patientIC) {
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

    // returns an arraylist containing medications from a prescription
    public ArrayList<Medication> viewMedicationsInPatientPrescription(String tokenString) {
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

    // returns a patient object associated with the unique prescription token
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

    // returns date from inputted token from database
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

    // returns an email associated with the prescription token from database
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

    // returns a doctor object that contains the info from database corresponding to the nric
    public Doctor setDoctorInfoFromDB(String NRIC) {
        String getDoctorInfo = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";
        Doctor doc = new Doctor();

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getDoctorInfo);

            while (queryResult.next()) {
                doc.uName = queryResult.getString(1);
                doc.realName = queryResult.getString(3);
                doc.email = queryResult.getString(4);
                doc.phoneNum = queryResult.getString(5);
                doc.address = queryResult.getString(6);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return doc;
    }
}
