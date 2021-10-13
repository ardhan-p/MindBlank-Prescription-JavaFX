package com.mindblank.entities;

public class User {
    private String uName;
    private String uPass;

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

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    public boolean login(String uName, String uPass) {
        // TODO: connect to MySQL database to verify login details
        return false;
    }
}
