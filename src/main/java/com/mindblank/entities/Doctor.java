package com.mindblank.entities;

public class Doctor extends User {
    public Doctor(User user) {
        super(user.uName, user.uPass);
    }
}
