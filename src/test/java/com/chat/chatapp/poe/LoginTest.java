package com.chat.chatapp.poe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    @Test
    public void testCheckUserName_Valid() {
        Login login = new Login();

        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        Login login = new Login();

        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPassword_Valid() {
        Login login = new Login();

        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99"));
    }

    @Test
    public void testCheckPassword_Invalid() {
        Login login = new Login();

        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhone_Valid() {
        Login login = new Login();

        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhone_Invalid() {
        Login login = new Login();

        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testLoginUser_Success() {
        Login login = new Login();

        assertTrue(
                login.loginUser(
                        "kyl_1",
                        "Ch&sec@ke99",
                        "kyl_1",
                        "Ch&sec@ke99"
                )
        );
    }

    @Test
    public void testLoginUser_Failure() {
        Login login = new Login();

        assertFalse(
                login.loginUser(
                        "wrong_user",
                        "wrongPassword",
                        "kyl_1",
                        "Ch&sec@ke99"
                )
        );
    }
}
