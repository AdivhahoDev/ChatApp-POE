package com.chat.chatapp.poe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageManager {
    
    // 1. The 5 Arrays required by the assignment
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    
    // Keeps track of who is logged in so we know the "Sender"
    public static String currentUser = "Developer"; 

    // 2. Method to add a message to the correct array based on user choice
    public static void addMessage(String recipient, String text, String id, String hash, int action) {
        // We format the message so we can easily extract the sender/recipient later
        String formattedMessage = currentUser + " -> " + recipient + ": " + text;
        
        if (action == 1) { // Send
            sentMessages.add(formattedMessage);
            messageIDs.add(id);
            messageHashes.add(hash);
        } else if (action == 2) { // Disregard
            disregardedMessages.add(formattedMessage);
        } else if (action == 3) { // Store
            storedMessages.add(formattedMessage);
            messageIDs.add(id);
            messageHashes.add(hash);
        }
    }

    // 3. Read from JSON file (Satisfies the JSON requirement in Part 3)
    public static void loadFromJSON() {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Message>>() {}.getType();
        
        try (FileReader reader = new FileReader("messages.json")) {
            List<Message> messagesFromFile = gson.fromJson(reader, listType);
            
            if (messagesFromFile != null) {
                for (Message m : messagesFromFile) {
                    String formatted = "Developer -> " + m.getRecipient() + ": " + m.getMessage();
                    // Only add if it's not already in the array to prevent duplicates
                    if(!messageIDs.contains(m.getMessageID())) {
                        storedMessages.add(formatted);
                        messageIDs.add(m.getMessageID());
                        messageHashes.add(m.getMessageHash());
                    }
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, which is fine
        }
    }

    // 4. The Sub-Menu (Options a through f)
    public static void showStoredMessagesMenu(Scanner scanner) {
        // Load from JSON when opening the menu
        loadFromJSON(); 

        boolean running = true;
        while (running) {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID");
            System.out.println("d. Search messages for a particular recipient");
            System.out.println("e. Delete a message using the message hash");
            System.out.println("f. Display full report");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "a": displaySenderRecipient(); break;
                case "b": displayLongestMessage(); break;
                case "c": searchByMessageID(scanner); break;
                case "d": searchByRecipient(scanner); break;
                case "e": deleteByHash(scanner); break;
                case "f": displayReport(); break;
                case "0": running = false; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // --- OPTION A ---
    public static void displaySenderRecipient() {
        System.out.println("\n--- Sender and Recipient ---");
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        for (String msg : storedMessages) {
            String[] parts = msg.split(": ");
            System.out.println(parts[0]); // Prints "Sender -> Recipient"
        }
    }

    // --- OPTION B ---
    public static void displayLongestMessage() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        String longest = "";
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        System.out.println("\nLongest Stored Message:\n" + longest);
    }

    // --- OPTION C ---
    public static void searchByMessageID(Scanner scanner) {
        System.out.print("Enter Message ID to search: ");
        String searchId = scanner.nextLine();
        
        int index = messageIDs.indexOf(searchId);
        
        if (index != -1) {
            // We only want to show the Recipient and Message
            String fullMessage = storedMessages.get(index);
            String[] parts = fullMessage.split(": ");
            System.out.println("Recipient & Message: " + parts[1]);
        } else {
            System.out.println("Message ID not found.");
        }
    }

    // --- OPTION D ---
    public static void searchByRecipient(Scanner scanner) {
        System.out.print("Enter recipient phone number: ");
        String recipient = scanner.nextLine();
        boolean found = false;
        
        for (String msg : storedMessages) {
            if (msg.contains(recipient)) {
                System.out.println(msg);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No messages found for " + recipient);
        }
    }

    // --- OPTION E ---
    public static void deleteByHash(Scanner scanner) {
        System.out.print("Enter Message Hash to delete: ");
        String hash = scanner.nextLine();
        
        int index = messageHashes.indexOf(hash);
        
        if (index != -1) {
            String deletedMsg = storedMessages.get(index);
            // Remove from all three parallel arrays at the same index
            messageHashes.remove(index);
            messageIDs.remove(index);
            storedMessages.remove(index);
            
            System.out.println("Message: \"" + deletedMsg + "\" successfully deleted.");
        } else {
            System.out.println("Hash not found.");
        }
    }

    // --- OPTION F ---
    public static void displayReport() {
        System.out.println("\n--- FULL MESSAGE REPORT ---");
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println("Message Hash: " + messageHashes.get(i));
            System.out.println(storedMessages.get(i));
            System.out.println("-------------------------");
        }
    }
    
    // --- HELPER METHOD FOR UNIT TESTS ---
    public static void addTestMessage(String sender, String recipient, String text, String id, String hash) {
        String formattedMessage = sender + " -> " + recipient + ": " + text;
        storedMessages.add(formattedMessage);
        messageIDs.add(id);
        messageHashes.add(hash);
    }
}