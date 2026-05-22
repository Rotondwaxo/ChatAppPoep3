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
        Message msg = new Message("+27718693002", shortMsg);
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
        Message msg = new Message("+27718693002", "Hello");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    void testRecipientInvalid() {
        Message msg = new Message("08575975889", "Hello");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    void testMessageHashExact() {
        Message msg = new Message("+27718693002", 
                                  "Hi Mike, can you join us for dinner tonight?", 
                                  "0012345678", 0);
        assertEquals("00:0:HITONIGHT", msg.getMessageHash());
    }

    @Test
    void testMessageIDGenerated() {
        Message msg = new Message("+27718693002", "Hello");
        String id = msg.getMessageID();
        assertNotNull(id);
        assertEquals(10, id.length());
        assertTrue(id.matches("\\d{10}"));
        System.out.println("Message ID generated: " + id);
    }

    @Test
    void testTotalMessages() {
        assertEquals(0, Message.getTotalMessages());
        new Message("+27718693002", "Msg 1");
        new Message("+27718693002", "Msg 2");
        assertEquals(2, Message.getTotalMessages());
    }
}