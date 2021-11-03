package com.mindblank.entities;

import java.sql.ResultSet;
import java.sql.Statement;

public class Admin extends User {
    public Admin(User user) {
        super(user.uName, user.uPass, user.realName, user.email, user.phoneNum, user.address, user.userType);
    }

    public void setAdminInfoFromDB(String NRIC) {
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
