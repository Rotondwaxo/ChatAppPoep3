package com.mycompany.chatapppoejl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class Part3Test {
    private ArrayList<Message> testMessages;

    @BeforeEach
    void setUp() {
        Message.resetCounter();
        testMessages = new ArrayList<>();
        // Simulate messages 1-4 from assignment
        testMessages.add(new Message("+27718693002", "Did you get the cake?", "0012345678", 1, "sent"));
        testMessages.add(new Message("+27718693002", "Where are you? You are late! I have asked you to be on time.", "0012345679", 2, "sent"));
        testMessages.add(new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "0012345680", 3, "sent"));
        testMessages.add(new Message("0838884567", "It is dinner time!", "0012345681", 4, "sent"));
        // Message 5 stored
        testMessages.add(new Message("+27838884567", "Ok, I am leaving without you.", "0012345682", 5, "stored"));
    }

    @Test
    void testSentMessagesArrayCorrectlyPopulated() {
        // Simulate sending messages 1-4 (sent status)
        ArrayList<String> sentTexts = new ArrayList<>();
        for (Message m : testMessages) {
            if (m.getStatus().equals("sent")) {
                sentTexts.add(m.getText());
            }
        }
        assertTrue(sentTexts.contains("Did you get the cake?"));
        assertTrue(sentTexts.contains("It is dinner time!"));
        assertEquals(4, sentTexts.size());
    }

    @Test
    void testLongestMessage() {
        String longest = "";
        for (Message m : testMessages) {
            if (m.getText().length() > longest.length())
                longest = m.getText();
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    void testSearchByMessageID() {
        String targetID = "0012345681"; // message 4
        String foundText = "";
        for (Message m : testMessages) {
            if (m.getMessageID().equals(targetID)) {
                foundText = m.getText();
                break;
            }
        }
        assertEquals("It is dinner time!", foundText);
    }

    @Test
    void testSearchByRecipient() {
        String targetRecipient = "+27838884567";
        ArrayList<String> resultTexts = new ArrayList<>();
        for (Message m : testMessages) {
            if (m.getRecipient().equals(targetRecipient)) {
                resultTexts.add(m.getText());
            }
        }
        assertTrue(resultTexts.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(resultTexts.contains("Ok, I am leaving without you."));
        assertEquals(2, resultTexts.size());
    }

    @Test
    void testDeleteByHash() {
        String targetHash = testMessages.get(1).getMessageHash(); // message 2
        int sizeBefore = testMessages.size();
        // Remove
        for (int i = 0; i < testMessages.size(); i++) {
            if (testMessages.get(i).getMessageHash().equals(targetHash)) {
                testMessages.remove(i);
                break;
            }
        }
        assertEquals(sizeBefore - 1, testMessages.size());
        // Verify the deleted message no longer exists
        boolean found = false;
        for (Message m : testMessages) {
            if (m.getMessageHash().equals(targetHash)) found = true;
        }
        assertFalse(found);
    }

    @Test
    void testDisplayReportFormat() {
        // Just check that we can retrieve report data without errors
        for (Message m : testMessages) {
            if (m.getStatus().equals("sent")) {
                assertNotNull(m.getMessageHash());
                assertNotNull(m.getRecipient());
                assertNotNull(m.getText());
            }
        }
    }
}
//corrected part 3 test.