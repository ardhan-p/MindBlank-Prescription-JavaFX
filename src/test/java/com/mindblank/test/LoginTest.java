package com.mindblank.test;

import com.mindblank.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    @DisplayName("TC-1-2")
    void loginEmptyUsername() {
        User testUser = new User("", "sofat123");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-3")
    void loginEmptyPassword() {
        User testUser = new User("YoMama", "");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-4")
    void loginIncorrectPassword() {
        User testUser = new User("YoMama", "sofat234");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-5")
    void loginIncorrectUsername() {
        User testUser = new User("yomama", "sofat123");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-6")
    void loginCorrectUser() {
        User testUser = new User("YoMama", "sofat123");
        assertEquals(true, testUser.login(testUser.getuName(), testUser.getuPass()));
    }
}