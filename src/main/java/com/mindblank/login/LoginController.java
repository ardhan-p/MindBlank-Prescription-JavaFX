package com.mindblank.login;
import com.mindblank.entities.*;

public class LoginController {
    private User user;

    public LoginController(User user) {
       this.user = user;
    }

    // returns true if login credentials are correct
    public boolean validateUser() {
        return user.login(user.getuName(), user.getuPass());
    }

    // returns the user type
    public String getUserType() {
        return user.getUserType();
    }
}
