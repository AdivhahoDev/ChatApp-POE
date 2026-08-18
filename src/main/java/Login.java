public class Login {

    // Checks that the username contains an underscore
    // and is no more than five characters long.
    public boolean checkUserName(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    // Checks that the password:
    // - Has at least 8 characters
    // - Has a capital letter
    // - Has a number
    // - Has a special character
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

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

    // Checks whether the phone number starts with +27 and is followed
    // by exactly 10 digits.
    //
    // Regex pattern learned from:
    // https://www.regular-expressions.info/
  public boolean checkCellPhoneNumber(String phoneNumber) {

    // Regex source: Regular-Expressions.info
    // Checks for +27 followed by exactly 9 digits.
    String phonePattern = "^\\+27\\d{9}$";

    return phoneNumber != null && phoneNumber.matches(phonePattern);
}

    // Registers the user by checking the username, password and phone number.
    public String registerUser(String username, String password, String phoneNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell number is not correctly formatted; please ensure that it starts with +27 and is followed by exactly 10 digits.";
        }

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell number successfully captured.";
    }

    // Checks whether the entered username and password match
    // the username and password stored during registration.
    public boolean loginUser(
            String enteredUsername,
            String enteredPassword,
            String storedUsername,
            String storedPassword) {

        return enteredUsername != null
                && enteredPassword != null
                && enteredUsername.equals(storedUsername)
                && enteredPassword.equals(storedPassword);
    }

    // Returns a message depending on whether the login was successful.
    public String returnLoginStatus(
            boolean isLoggedIn,
            String firstName,
            String lastName) {

        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}