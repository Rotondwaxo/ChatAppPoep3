package com.mycompany.chatapppoejl;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = new Registration();
        Login login = new Login();

        boolean exitApp = false;
        while (!exitApp) {
            System.out.println("\n--- CHAT APP POE ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registration.startRegistration();
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String uname = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String pwd = scanner.nextLine();
                    boolean success = login.loginUser(uname, pwd);
                    System.out.println(login.returnLoginStatus(uname, pwd));
                    if (success) {
                        showMessagingMenu(scanner, login);
                    }
                    break;
                case 3:
                    exitApp = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void showMessagingMenu(Scanner scanner, Login login) {
        System.out.println("\nWelcome to QuickChat");
        boolean exitMessaging = false;
        while (!exitMessaging) {
            System.out.println("\n--- MESSAGING MENU ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages (Coming Soon)");
            System.out.println("3. Quit to main menu");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    sendMessages(scanner);
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    exitMessaging = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void sendMessages(Scanner scanner) {
        // We will implement this in Step 5
        System.out.println("Send messages feature - to be implemented.");
        
    }
}