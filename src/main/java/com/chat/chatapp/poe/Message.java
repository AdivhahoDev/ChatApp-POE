package com.chat.chatapp.poe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;
    
    private static final String JSON_FILE = "messages.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Message() {
        // Default constructor for JSON parsing
    }

    public Message(String recipient, String message, int numMessagesSent) {
        this.messageID = generateMessageID();
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    // --- Validation Methods ---

    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && (recipient.startsWith("+") || recipient.startsWith("0")) && recipient.length() <= 10) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // --- Generation Methods ---

    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    public String createMessageHash() {
        if (message == null || message.trim().isEmpty()) {
            return "";
        }
        String firstTwo = messageID.substring(0, 2);
        String msgNum = String.valueOf(numMessagesSent);
        
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "").toUpperCase();
        
        return firstTwo + ":" + msgNum + ":" + firstWord + lastWord;
    }

    // --- User Choice / Storage Methods ---

    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                storeMessage();
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }
    
    private void storeMessage() {
        List<Message> messages = new ArrayList<>();
        Type listType = new TypeToken<List<Message>>() {}.getType();

        try (FileReader reader = new FileReader(JSON_FILE)) {
            messages = gson.fromJson(reader, listType);
            if (messages == null) messages = new ArrayList<>();
        } catch (IOException e) {
            // File doesn't exist yet
        }

        messages.add(this);

        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            gson.toJson(messages, writer);
        } catch (IOException e) {
            System.out.println("Error saving message to JSON: " + e.getMessage());
        }
    }

    public static int returnTotalMessages() {
        Type listType = new TypeToken<List<Message>>() {}.getType();
        try (FileReader reader = new FileReader(JSON_FILE)) {
            List<Message> messages = gson.fromJson(reader, listType);
            return messages != null ? messages.size() : 0;
        } catch (IOException e) {
            return 0;
        }
    }

    public static void printMessages() {
        Type listType = new TypeToken<List<Message>>() {}.getType();
        try (FileReader reader = new FileReader(JSON_FILE)) {
            List<Message> messages = gson.fromJson(reader, listType);
            if (messages == null || messages.isEmpty()) {
                System.out.println("No messages found.");
                return;
            }
            for (Message m : messages) {
                System.out.println("-----------------------------------");
                System.out.println("Message ID: " + m.messageID);
                System.out.println("Message Hash: " + m.messageHash);
                System.out.println("Recipient: " + m.recipient);
                System.out.println("Message: " + m.message);
            }
            System.out.println("-----------------------------------");
        } catch (IOException e) {
            System.out.println("No messages found. (File does not exist yet)");
        }
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
}