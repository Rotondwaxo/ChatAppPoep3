package com.mycompany.chatapppoejl;

import java.util.regex.Pattern;

/**
 * Login class provides validation and authentication.
 * Regex reference: https://stackoverflow.com/questions/42104546/
 */
public class Login {
    private static String storedFirstName;
    private static String storedLastName;
    private static String storedUsername;
    private static String storedPassword;
    private static String storedPhone;

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        boolean hasCapital = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");
        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        // International format: + followed by country code and 7-10 digits
        String regex = "^\\+[1-9][0-9]{0,2}[0-9]{7,10}$";
        return Pattern.matches(regex, phone);
    }

    public String registerUser(String username, String password, String phone,
                               String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(phone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        storedFirstName = firstName;
        storedLastName = lastName;
        storedUsername = username;
        storedPassword = password;
        storedPhone = phone;
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        return storedUsername != null && storedUsername.equals(username) &&
               storedPassword != null && storedPassword.equals(password);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + storedFirstName + " " + storedLastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}