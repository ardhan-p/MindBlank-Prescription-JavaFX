package com.mindblank.entities;

import com.mindblank.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    private String uName;
    private String uPass;
    private String email;
    private String phoneNum;
    private String address;

    public User() {
        uName = "Default";
        uPass = "Default";
    }

    public User(String uName, String uPass) {
        this.uName = uName;
        this.uPass = uPass;
    }

    public String getuName() {
        return uName;
    }

    public String getuPass() {
        return uPass;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean login(String uName, String uPass) {
        // TODO: connect to MySQL database to verify login details

        DatabaseConnection connectSQL = new DatabaseConnection();
        Connection connectDB = connectSQL.getConnection();

        String verifyLogin = "SELECT * FROM user WHERE BINARY username = '" + uName
                           + "' AND BINARY password = '" + uPass + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult == null) {
                    return false;
                } else {
                    return true;
                }
            }


        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }
}
