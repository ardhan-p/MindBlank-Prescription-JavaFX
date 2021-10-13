package com.mindblank.login;
import com.mindblank.entities.*;

public class LoginController {
    public static boolean validateUser(String uName, String uPass) {
        User newUser = new User(uName, uPass);
        boolean loginState = newUser.login(newUser.getuName(), newUser.getuPass());
        return loginState;
    }
}
