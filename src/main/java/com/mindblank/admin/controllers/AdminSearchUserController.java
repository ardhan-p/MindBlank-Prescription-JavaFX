package com.mindblank.admin.controllers;

import com.mindblank.entities.Admin;
import com.mindblank.entities.User;

public class AdminSearchUserController {
    private Admin admin;

    public AdminSearchUserController(Admin admin) {
        this.admin = admin;
    }

    // validates nric if valid and is in database
    public boolean validateNRIC(String NRIC) {
        return admin.checkNRIC(NRIC, true);
    }
}
