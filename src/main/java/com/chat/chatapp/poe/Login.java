package com.chat.chatapp.poe;

/**
 * Login class for the Chat App registration and login system.
 *
 * This class handles user registration, validation,
 * login authentication, and login status messages.
 */
public class Login {

    /**
     * Checks if the username is valid.
     *
     * A valid username:
     * - Contains an underscore (_)
     * - Is no more than 5 characters long
     *
     * @param username the username to check
     * @return true if valid, false otherwise
     */
    public boolean checkUserName(String username) {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /**
     * Checks if the password meets the required complexity.
     *
     * A valid password must have:
     * - At least 8 characters
     * - At least one capital letter
     * - At least one number
     * - At least one special character
     *
     * @param password the password to check
     * @return true if valid, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Check each character in the password.
        for (char character : password.toCharArray()) {

            if (Character.isUpperCase(character)) {
                hasCapital = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Checks if a South African cell phone number is valid.
     *
     * Required format:
     * +27 followed by exactly 10 digits.
     *
     * Regex pattern:
     * ^\+27\d{10}$
     *
     * The regex syntax was learned from Oracle's Java
     * Regular Expressions documentation:
     * https://docs.oracle.com/javase/tutorial/essential/regex/
     *
     * @param phoneNumber the phone number to check
     * @return true if valid, false otherwise
     */
    public boolean checkCellPhoneNumber(String phoneNumber) {

        if (phoneNumber == null) {
            return false;
        }

        String regex = "^\\+27\\d{10}$";

        return phoneNumber.matches(regex);
    }

    /**
     * Registers a user after checking all required information.
     *
     * @param username the username
     * @param password the password
     * @param phoneNumber the phone number
     * @return the appropriate registration message
     */
    public String registerUser(String username,
                               String password,
                               String phoneNumber) {

        // Check the username first.
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure "
                    + "that your username contains an underscore and is "
                    + "no more than five characters in length.";
        }

        // Check the password.
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure "
                    + "that the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.";
        }

        // Check the cell phone number.
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell number is not correctly formatted; please ensure "
                    + "that it starts with +27 and is followed by 10 digits.";
        }

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell number successfully captured.";
    }

    /**
     * Checks whether the entered username and password
     * match the stored username and password.
     *
     * @param enteredUsername username entered during login
     * @param enteredPassword password entered during login
     * @param storedUsername registered username
     * @param storedPassword registered password
     * @return true if both credentials match
     */
    public boolean loginUser(String enteredUsername,
                             String enteredPassword,
                             String storedUsername,
                             String storedPassword) {

        return enteredUsername != null
                && enteredPassword != null
                && enteredUsername.equals(storedUsername)
                && enteredPassword.equals(storedPassword);
    }

    /**
     * Returns the appropriate login status message.
     *
     * @param isLoggedIn whether the login was successful
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @return the appropriate login status message
     */
    public String returnLoginStatus(boolean isLoggedIn,
                                    String firstName,
                                    String lastName) {

        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}