package com.mindblank.test;

import com.mindblank.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestLogin {

    @Test
    @DisplayName("TC-1-2")
    void loginEmptyUsername() {
        User testUser = new User("", "pass002");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-3")
    void loginEmptyPassword() {
        User testUser = new User("doctor", "");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-4")
    void loginIncorrectPassword() {
        User testUser = new User("doctor", "pass001");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-5")
    void loginIncorrectUsername() {
        User testUser = new User("docter", "pass002");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-6")
    void loginCorrectUser() {
        User testUser = new User("doctor", "pass002");
        assertEquals(true, testUser.login(testUser.getuName(), testUser.getuPass()));
    }

    @Test
    @DisplayName("TC-1-6 but expected false")
    void loginIncorrectUser() {
        User testUser = new User("doctor", "pass002");
        assertEquals(false, testUser.login(testUser.getuName(), testUser.getuPass()));
    }
}