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
        System.out.print("How many messages do you wish to send? ");
        int totalMessages = scanner.nextInt();
        scanner.nextLine();

        int sentCount = 0;
        for (int i = 1; i <= totalMessages; i++) {
            System.out.println("\n--- Message " + i + " ---");
            System.out.print("Enter recipient number (e.g., +27718693002): ");
            String recipient = scanner.nextLine();
            System.out.print("Enter your message (max 250 characters): ");
            String text = scanner.nextLine();

            if (!isValidPhone(recipient)) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                continue;
            }
            if (text.length() > 250) {
                int excess = text.length() - 250;
                System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
                continue;
            }

            Message msg = new Message(recipient, text);

            System.out.println("Choose action:");
            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message to send later");
            System.out.print("Your choice: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    System.out.println("Message successfully sent.");
                    sentCount++;
                    displayMessageDetails(msg);
                    storeMessageInJson(msg, "sent");
                    break;
                case 2:
                    System.out.println("Press 0 to delete the message.");
                    break;
                case 3:
                    System.out.println("Message successfully stored.");
                    storeMessageInJson(msg, "stored");
                    break;
                default:
                    System.out.println("Invalid action. Message discarded.");
            }
        }
        System.out.println("\nTotal number of messages sent: " + sentCount);
    }

    private static boolean isValidPhone(String phone) {
        String regex = "^\\+[1-9][0-9]{0,2}[0-9]{7,10}$";
        return Pattern.matches(regex, phone);
    }

    private static void displayMessageDetails(Message msg) {
        System.out.println("\n--- Message Details ---");
        System.out.println("Message ID: " + msg.getMessageID());
        System.out.println("Message Hash: " + msg.getMessageHash());
        System.out.println("Recipient: " + msg.getRecipient());
        System.out.println("Message: " + msg.getText());
    }

    private static void storeMessageInJson(Message msg, String status) {
        try (java.io.FileWriter fw = new java.io.FileWriter("messages.json", true);
             java.io.PrintWriter pw = new java.io.PrintWriter(fw)) {
            pw.println("{");
            pw.println("  \"messageID\": \"" + msg.getMessageID() + "\",");
            pw.println("  \"numSent\": " + msg.getNumSent() + ",");
            pw.println("  \"recipient\": \"" + msg.getRecipient() + "\",");
            pw.println("  \"text\": \"" + msg.getText() + "\",");
            pw.println("  \"hash\": \"" + msg.getMessageHash() + "\",");
            pw.println("  \"status\": \"" + status + "\"");
            pw.println("},");
        } catch (Exception e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
}