package com.mindblank.login;
import com.mindblank.entities.*;

public class LoginController {
    private User user;

    public LoginController(User user) {
       this.user = user;
    }

    public boolean validateUser() {
        return user.login(user.getuName(), user.getuPass());
    }

    public String getUserType() {
        return user.getUserType();
    }
}
