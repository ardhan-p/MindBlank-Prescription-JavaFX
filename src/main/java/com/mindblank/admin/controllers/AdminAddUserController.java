package com.mindblank.admin.controllers;

import com.mindblank.entities.Admin;
import com.mindblank.entities.User;

public class AdminAddUserController {
    private Admin admin;

    public AdminAddUserController(Admin admin) {
       this.admin = admin;
    }

    // checks nric if there is a duplicate
    public boolean validateNRIC(String NRIC) {
        return admin.checkNRIC(NRIC, false);
    }

    // adds new user to database
    public boolean addUserToDB(User u) {
        return admin.insertUser(u);
    }
}
