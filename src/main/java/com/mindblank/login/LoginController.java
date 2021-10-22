package com.mindblank.login;
import com.mindblank.entities.*;

public class LoginController {
    // TODO: change BCE to passing user object instead 2 strings
    public static boolean validateUser(User user) {
        boolean loginState = user.login(user.getuName(), user.getuPass());
        return loginState;
    }

    // TODO: update BCE to add new function to get user type
    public static String getUserType(User user) {
        return user.getUserType();
    }
}
