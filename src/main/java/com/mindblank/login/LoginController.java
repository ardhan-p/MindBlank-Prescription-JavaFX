package com.mindblank.login;
import com.mindblank.entities.*;

public class LoginController {
    private User user;

    public LoginController(User user) {
       this.user = user;
    }

    // TODO: change BCE to passing user object instead 2 strings
    public boolean validateUser() {
        boolean loginState = user.login(user.getuName(), user.getuPass());
        return loginState;
    }

    // TODO: update BCE to add new function to get user type
    public String getUserType() {
        return user.getUserType();
    }
}
