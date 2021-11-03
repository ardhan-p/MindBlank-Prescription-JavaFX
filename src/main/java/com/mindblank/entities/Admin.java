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

    public boolean checkNRIC(String NRIC) {
        String validate = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";

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

    public boolean insertUser(User u) {
        String addUser = "INSERT INTO USER (NRIC, pass, name, email, phoneNum, address, type) " +
                "VALUES ('" + u.getuName() + "', '" + u.getuPass() + "', '" + u.getRealName() + "', '" + u.getEmail() +
                "', '" + u.getPhoneNum() + "', '" + u.getAddress() + "', '" + u.getUserType() + "');";

        try {
            Statement statement = connectDB.createStatement();
            statement.execute(addUser);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return false;
        }
    }
}
