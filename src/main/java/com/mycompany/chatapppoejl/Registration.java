package com.mycompany.chatapppoejl;

import java.util.Scanner;

public class Registration {
    private Login loginValidator = new Login();
    private Scanner scanner = new Scanner(System.in);

    public void startRegistration() {
        System.out.println("=== USER REGISTRATION ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Create username (must contain '_' and max 5 chars): ");
        String username = scanner.nextLine();
        System.out.print("Create password (>=8 chars, 1 capital, 1 number, 1 special): ");
        String password = scanner.nextLine();
        System.out.print("Enter cell phone number (e.g., +27838968976): ");
        String phone = scanner.nextLine();

        String result = loginValidator.registerUser(username, password, phone, firstName, lastName);
        System.out.println(result);
    }

    public Login getLoginValidator() {
        return loginValidator;
    }
}