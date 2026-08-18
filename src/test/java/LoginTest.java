import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    Login login = new Login();

    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPassword_Valid() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99"));
    }

    @Test
    public void testCheckPassword_Invalid() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhone_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhone_Invalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testLoginUser_Success() {

        String storedUsername = "kyl_1";
        String storedPassword = "Ch&sec@ke99";

        String enteredUsername = "kyl_1";
        String enteredPassword = "Ch&sec@ke99";

        assertTrue(login.loginUser(
                enteredUsername,
                enteredPassword,
                storedUsername,
                storedPassword
        ));
    }

    @Test
    public void testLoginUser_Failure() {

        String storedUsername = "kyl_1";
        String storedPassword = "Ch&sec@ke99";

        String enteredUsername = "wrong";
        String enteredPassword = "wrong123";

        assertFalse(login.loginUser(
                enteredUsername,
                enteredPassword,
                storedUsername,
                storedPassword
        ));
    }
}