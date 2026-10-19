package com.chat.chatapp.poe;

import java.util.Scanner;

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

                    String registrationMessage = login.registerUser(newUsername, newPassword, phoneNumber);
                    System.out.println("\n" + registrationMessage);

                    if (login.checkUserName(newUsername) && login.checkPasswordComplexity(newPassword) && login.checkCellPhoneNumber(phoneNumber)) {
                        storedUsername = newUsername;
                        storedPassword = newPassword;
                        registered = true;
                        System.out.println("Registration successful!");
                    }
                    break;

                case "2":
                    System.out.println("\n========== LOGIN ==========");
                    if (!registered) {
                        System.out.println("Please register an account before logging in.");
                        break;
                    }

                    System.out.print("Enter your username: ");
                    String enteredUsername = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String enteredPassword = scanner.nextLine();

                    boolean isLoggedIn = login.loginUser(enteredUsername, enteredPassword, storedUsername, storedPassword);
                    System.out.println(login.returnLoginStatus(isLoggedIn, firstName, lastName));

                    // --- THIS IS THE NEW PART ---
                    // If login is successful, start the QuickChat menu!
                    if (isLoggedIn) {
                        startQuickChatMenu(scanner);
                    }
                    // ----------------------------
                    break;

                case "3":
                    System.out.println("\nThank you for using the Chat App.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
        scanner.close();
    }

    // ==========================================
    // THIS IS THE NEW PART 2 MENU
    // ==========================================
    public static void startQuickChatMenu(Scanner scanner) {
        int numMessages = 0;
        boolean running = true;

        System.out.println("\nWelcome to QuickChat.");
        
        while (running) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            
            if (choice.equals("1")) {
                System.out.print("How many messages do you want to send? ");
                int total = Integer.parseInt(scanner.nextLine());
                
                for (int i = 0; i < total; i++) {
                    numMessages++;
                    System.out.println("\n--- Message " + numMessages + " ---");
                    
                    // Get Phone Number
                    String recipient = "";
                    boolean validPhone = false;
                    while (!validPhone) {
                        System.out.print("Enter phone number (e.g. +27718693002): ");
                        recipient = scanner.nextLine();
                        Message temp = new Message(recipient, "test", numMessages);
                        if (temp.checkRecipientCell().equals("Cell phone number successfully captured.")) {
                            validPhone = true;
                        } else {
                            System.out.println(temp.checkRecipientCell());
                        }
                    }
                    
                    // Get Message Text
                    String text = "";
                    boolean validText = false;
                    while (!validText) {
                        System.out.print("Enter message (max 250 chars): ");
                        text = scanner.nextLine();
                        Message temp = new Message(recipient, text, numMessages);
                        if (temp.checkMessageLength().equals("Message ready to send.")) {
                            validText = true;
                        } else {
                            System.out.println(temp.checkMessageLength());
                        }
                    }
                    
                    // Create message
                    Message newMessage = new Message(recipient, text, numMessages);
                    System.out.println("Message ID: " + newMessage.getMessageID());
                    System.out.println("Message Hash: " + newMessage.getMessageHash());
                    
                    // Ask to send/store/disregard
                    System.out.println("\n1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message");
                    System.out.print("Choice: ");
                    int action = Integer.parseInt(scanner.nextLine());
                    System.out.println(newMessage.sentMessage(action));
                }
                
            } else if (choice.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (choice.equals("3")) {
                running = false;
                System.out.println("Total messages sent: " + Message.returnTotalMessages());
                Message.printMessages();
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }
}