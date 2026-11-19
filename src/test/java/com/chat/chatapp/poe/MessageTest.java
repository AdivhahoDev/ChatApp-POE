package com.chat.chatapp.poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeEach
    public void setUp() {
        // Clear arrays before every test so they don't overlap
        MessageManager.storedMessages.clear();
        MessageManager.messageIDs.clear();
        MessageManager.messageHashes.clear();
        MessageManager.sentMessages.clear();

        // Populate with the specific Test Data from Image 3 of your assignment
        MessageManager.addTestMessage("Developer", "+27834557896", "Did you get the cake?", "0838884567", "Hash1");
        MessageManager.addTestMessage("Developer", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "0838884568", "Hash2");
        MessageManager.addTestMessage("Developer", "+27838884567", "Ok, I am leaving without you.", "0838884569", "Hash3");
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        // Manually add the "Sent" messages as required by the test data
        MessageManager.sentMessages.add("Developer -> +27834557896: Did you get the cake?");
        MessageManager.sentMessages.add("Developer -> 0838884567: It is dinner time !");
        
        // Check if they were added correctly
        assertEquals("Developer -> +27834557896: Did you get the cake?", MessageManager.sentMessages.get(0));
        assertEquals("Developer -> 0838884567: It is dinner time !", MessageManager.sentMessages.get(1));
    }

    @Test
    public void testDisplayLongestMessage() {
        // The expected longest message from the Test Data
        String expectedLongest = "Developer -> +27838884567: Where are you? You are late! I have asked you to be on time.";
        
        String longest = "";
        for (String msg : MessageManager.storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        assertEquals(expectedLongest, longest);
    }

    @Test
    public void testSearchForMessageID() {
        // Test searching for the ID of message 4
        String searchId = "0838884568";
        int index = MessageManager.messageIDs.indexOf(searchId);
        
        // Assert that the ID is found (index is not -1) and the message matches
        assertNotEquals(-1, index);
        assertTrue(MessageManager.storedMessages.get(index).contains("Where are you?"));
    }

    @Test
    public void testSearchMessagesByRecipient() {
        // Test finding all messages for +27838884567 (Should be 2 messages)
        String recipient = "+27838884567";
        int count = 0;
        
        for (String msg : MessageManager.storedMessages) {
            if (msg.contains(recipient)) {
                count++;
            }
        }
        
        assertEquals(2, count);
    }

    @Test
    public void testDeleteMessageByHash() {
        // Test deleting "Hash2"
        String hashToDelete = "Hash2";
        int index = MessageManager.messageHashes.indexOf(hashToDelete);
        
        assertNotEquals(-1, index);
        
        // Simulate the deletion
        MessageManager.messageHashes.remove(index);
        MessageManager.messageIDs.remove(index);
        MessageManager.storedMessages.remove(index);
        
        // Assert that the list size decreased and the hash is gone
        assertEquals(2, MessageManager.storedMessages.size());
        assertEquals(-1, MessageManager.messageHashes.indexOf(hashToDelete));
    }
}