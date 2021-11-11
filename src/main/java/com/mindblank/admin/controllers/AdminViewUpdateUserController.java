package com.mindblank.admin.controllers;

import com.mindblank.entities.Admin;
import com.mindblank.entities.User;

public class AdminViewUpdateUserController {
    private Admin admin;

    public AdminViewUpdateUserController(Admin admin) {
        this.admin = admin;
    }

    public User getUserFromDB(String NRIC) {
        return admin.getUserFromDB(NRIC);
    }

    public boolean updateUserInfo(User u) {
        return admin.editUser(u);
    }
}
