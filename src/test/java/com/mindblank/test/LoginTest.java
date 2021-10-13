package com.mindblank.test;

import com.mindblank.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    @DisplayName("Login user YoMama")
    void login() {
        User testUser = new User("YoMama", "sofat123");
        assertEquals(true, testUser.login(testUser.getuName(), testUser.getuPass()));
    }
}