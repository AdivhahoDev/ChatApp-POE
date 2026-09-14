package com.chat.chatapp.poe;

import java.util.Scanner;

/**
 * Main class for the Chat App.
 *
 * This class provides the console menu for
 * user registration and login.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        String storedUsername = "";
        String storedPassword = "";
        String firstName = "";
        String lastName = "";

        boolean registered = false;
        boolean running = true;

        System.out.println("====================================");
        System.out.println("       CHAT APP REGISTRATION        ");
        System.out.println("====================================");

        while (running) {

            System.out.println("\nPlease select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.println("\n========== REGISTRATION ==========");

                    System.out.print("Enter your first name: ");
                    firstName = scanner.nextLine();

                    System.out.print("Enter your last name: ");
                    lastName = scanner.nextLine();

                    System.out.print("Enter your username: ");
                    String newUsername = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String newPassword = scanner.nextLine();

                    System.out.print("Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();

                    String registrationMessage =
                            login.registerUser(
                                    newUsername,
                                    newPassword,
                                    phoneNumber
                            );

                    System.out.println("\n" + registrationMessage);

                    if (login.checkUserName(newUsername)
                            && login.checkPasswordComplexity(newPassword)
                            && login.checkCellPhoneNumber(phoneNumber)) {

                        storedUsername = newUsername;
                        storedPassword = newPassword;
                        registered = true;

                        System.out.println("Registration successful!");
                    }

                    break;

                case "2":

                    System.out.println("\n========== LOGIN ==========");

                    if (!registered) {
                        System.out.println(
                                "Please register an account before logging in."
                        );
                        break;
                    }

                    System.out.print("Enter your username: ");
                    String enteredUsername = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String enteredPassword = scanner.nextLine();

                    boolean isLoggedIn = login.loginUser(
                            enteredUsername,
                            enteredPassword,
                            storedUsername,
                            storedPassword
                    );

                    System.out.println(
                            login.returnLoginStatus(
                                    isLoggedIn,
                                    firstName,
                                    lastName
                            )
                    );

                    break;

                case "3":

                    System.out.println(
                            "\nThank you for using the Chat App."
                    );

                    running = false;
                    break;

                default:

                    System.out.println(
                            "Invalid option. Please choose 1, 2, or 3."
                    );
            }
        }

        scanner.close();
    }
}