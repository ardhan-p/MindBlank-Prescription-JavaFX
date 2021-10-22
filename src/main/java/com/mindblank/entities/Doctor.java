package com.mindblank.entities;

import com.mindblank.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor extends User {
    public Doctor(User user) {
        super(user.uName, user.uPass, user.realName, user.email, user.phoneNum, user.address, user.userType);
    }

    public boolean getPatientIC(String patientIC) {
        DatabaseConnection connectSQL = new DatabaseConnection();
        Connection connectDB = connectSQL.getConnection();

        String validatePatient = "SELECT * FROM USER WHERE BINARY NRIC = '" + patientIC + "';";

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
}
