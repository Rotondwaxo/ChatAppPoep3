package com.mycompany.chatapppoep1;

import com.mycompany.chatapppoejl.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private Login login;


    // assertEquals tests
    @Test
    void testUsernameCorrectlyFormatted() {
        String msg = login.registerUser("kyl_1", "Ch&sec@ke99!", "+27838968976", "John", "Doe");
        assertEquals("User registered successfully.", msg);
        
        String loginMsg = login.returnLoginStatus("kyl_1", "Ch&sec@ke99!");
        assertEquals("Welcome John Doe, it is great to see you again.", loginMsg);
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        String msg = login.registerUser("kyle!!!!!!!", "Valid1@pass", "+27831234567", "Jane", "Smith");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", msg);
    }

    @Test
    void testPasswordMeetsComplexity() {
        String msg = login.registerUser("john_1", "Ch&sec@ke99!", "+27831234567", "John", "Doe");
        assertEquals("User registered successfully.", msg);
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        String msg = login.registerUser("jane_1", "password", "+27831234567", "Jane", "Smith");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", msg);
    }

    @Test
    void testCellPhoneCorrectlyFormatted() {
        String msg = login.registerUser("mark_1", "Valid1@pass", "+27838968976", "Mark", "Lee");
        assertEquals("User registered successfully.", msg);
    }

    @Test
    void testCellPhoneIncorrectlyFormatted() {
        String msg = login.registerUser("ann_1", "Valid1@pass", "08966553", "Ann", "White");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", msg);
    }

    // assertTrue/False tests
    @Test
    void testLoginSuccessful() {
        login.registerUser("test_1", "Test@1234", "+27831234567", "Test", "User");
        assertTrue(login.loginUser("test_1", "Test@1234"));
    }

    @Test
    void testLoginFailed() {
        login.registerUser("test_1", "Test@1234", "+27831234567", "Test", "User");
        assertFalse(login.loginUser("wrong", "password"));
    }

    @Test
    void testUsernameCorrectlyFormattedTrue() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void testUsernameIncorrectlyFormattedFalse() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    void testPasswordMeetsComplexityTrue() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
    }

    @Test
    void testPasswordDoesNotMeetComplexityFalse() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    void testCellPhoneCorrectlyFormattedTrue() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void testCellPhoneIncorrectlyFormattedFalse() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}
// Commit #5