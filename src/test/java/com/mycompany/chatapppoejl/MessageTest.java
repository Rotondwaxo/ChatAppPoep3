package com.mycompany.chatapppoejl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeEach
    void setUp() {
        Message.resetCounter();
    }

    @Test
    void testMessageLengthSuccess() {
        String shortMsg = "Hi Mike, can you join us for dinner tonight?";
        // Added "sent" as status
        Message msg = new Message("+27718693002", shortMsg, "sent");
        assertTrue(msg.getText().length() <= 250);
    }

    @Test
    void testMessageLengthFailure() {
        String longMsg = "A".repeat(260);
        assertTrue(longMsg.length() > 250);
        int excess = longMsg.length() - 250;
        assertEquals(10, excess);
    }

    @Test
    void testRecipientValid() {
        Message msg = new Message("+27718693002", "Hello", "sent");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    void testRecipientInvalid() {
        Message msg = new Message("08575975889", "Hello", "sent");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    void testMessageHashExact() {
        // Using test-only constructor (fixed ID and counter)
        Message msg = new Message("+27718693002", 
                                  "Hi Mike, can you join us for dinner tonight?", 
                                  "0012345678", 0, "sent");
        assertEquals("00:0:HITONIGHT", msg.getMessageHash());
    }

    @Test
    void testMessageIDGenerated() {
        Message msg = new Message("+27718693002", "Hello", "sent");
        String id = msg.getMessageID();
        assertNotNull(id);
        assertEquals(10, id.length());
        assertTrue(id.matches("\\d{10}"));
        System.out.println("Message ID generated: " + id);
    }

    @Test
    void testTotalMessages() {
        assertEquals(0, Message.getTotalMessages());
        new Message("+27718693002", "Msg 1", "sent");
        new Message("+27718693002", "Msg 2", "sent");
        assertEquals(2, Message.getTotalMessages());
    }
}