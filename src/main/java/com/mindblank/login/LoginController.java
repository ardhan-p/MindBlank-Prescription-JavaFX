package com.mindblank.login;
import com.mindblank.entities.*;

public class LoginController {
    private static User newUser = new User();

    public static boolean validateUser(String uName, String uPass) {
        newUser.setuName(uName);
        newUser.setuPass(uPass);
        boolean loginState = newUser.login(newUser.getuName(), newUser.getuPass());
        return loginState;
    }

    // TODO: update BCE to add new function to get user type
    public static String getUserType() {
        return newUser.getUserType();
    }
}
