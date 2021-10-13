package com.mindblank.login;
import com.mindblank.entities.*;

public class LoginController {
    public static boolean validateUser(String uName, String uPass) {
        User newLogin = new User(uName, uPass);
        boolean loginState = newLogin.login(newLogin.getuName(), newLogin.getuPass());
        return loginState;
    }
}
