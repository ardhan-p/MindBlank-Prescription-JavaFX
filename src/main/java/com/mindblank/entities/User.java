package com.mindblank.entities;

import com.mindblank.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    protected String uName;
    protected String uPass;
    protected String realName;
    protected String email;
    protected String phoneNum;
    protected String address;
    protected String userType;
    protected DatabaseConnection connectSQL = new DatabaseConnection();
    protected Connection connectDB = connectSQL.getConnection();

    public User() {
        uName = "Default";
        uPass = "Default";
    }

    public User(User u) {
        this.uName = u.uName;
        this.uPass = u.uPass;
        this.realName = u.realName;
        this.email = u.email;
        this.phoneNum = u.phoneNum;
        this.address = u.address;
        this.userType = u.userType;
    }

    public User(String uName, String uPass) {
        this.uName = uName;
        this.uPass = uPass;
    }

    public User(String uName, String uPass, String realName, String email, String phoneNum, String address, String userType) {
        this.uName = uName;
        this.uPass = uPass;
        this.realName = realName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
        this.userType = userType;
    }

    public String getuName() {
        return uName;
    }

    public String getuPass() {
        return uPass;
    }

    public String getRealName() {
        return realName;
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

    public String getUserType() {
        return userType;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void print() {
        System.out.println(uName);
        System.out.println(realName);
        System.out.println(email);
        System.out.println(phoneNum);
        System.out.println(address);
        System.out.println(userType);
    }

    public boolean login(String uName, String uPass) {
        String verifyLogin = "SELECT * FROM USER WHERE BINARY NRIC = '" + uName
                           + "' AND BINARY pass = '" + uPass + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult == null) {
                    return false;
                } else {
                    // sets user type from sql result
                    this.realName = queryResult.getString(3);
                    this.email = queryResult.getString(4);
                    this.phoneNum = queryResult.getString(5);
                    this.address = queryResult.getString(6);
                    this.userType = queryResult.getString(7);
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return false;
    }

    public void setUserInfoFromDB(String NRIC) {
        String getDoctorInfo = "SELECT * FROM USER WHERE NRIC = '" + NRIC + "';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getDoctorInfo);

            while (queryResult.next()) {
                this.uName = queryResult.getString(1);
                this.uPass = queryResult.getString(2);
                this.realName = queryResult.getString(3);
                this.email = queryResult.getString(4);
                this.phoneNum = queryResult.getString(5);
                this.address = queryResult.getString(6);
                this.userType = queryResult.getString(7);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
