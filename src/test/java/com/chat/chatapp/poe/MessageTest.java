package com.chat.chatapp.poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        // Message ID is auto-generated, numMessagesSent = 1 for test cases
        message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
    }

    @Test
    public void testMessageIDCreated() {
        assertNotNull(message.getMessageID());
        assertTrue(message.checkMessageID(), "Message ID should be max 10 characters and not null");
    }

    @Test
    public void testRecipientCellSuccess() {
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message invalidMessage = new Message("083555", "Hello", 1); // Invalid length/code
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", invalidMessage.checkRecipientCell());
    }

    @Test
    public void testMessageLengthSuccess() {
        assertEquals("Message ready to send.", message.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Create a message that exceeds 250 characters
        String longText = "A".repeat(255);
        Message longMessage = new Message("+27718693002", longText, 1);
        assertEquals("Message exceeds 250 characters by 5; please reduce the size.", longMessage.checkMessageLength());
    }

    @Test
    public void testMessageHashCorrect() {
        // Example: ID starts with X, msg number is 1, first word HI, last word TONIGHT
        // Note: Since ID is auto-generated, we can't assert the exact first two digits, 
        // but we can assert the format and the words.
        String hash = message.getMessageHash();
        assertTrue(hash.matches("\\d{2}:1:HITONIGHT"), "Hash format should be XX:1:HITONIGHT");
    }

    @Test
    public void testSentMessage() {
        assertEquals("Message successfully sent.", message.sentMessage(1));
        assertEquals("Press 0 to delete the message.", message.sentMessage(2));
        assertEquals("Message successfully stored.", message.sentMessage(3));
    }
}