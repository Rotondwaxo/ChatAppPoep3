package com.mycompany.chatapppoejl;

import java.util.Random;
import java.util.regex.Pattern;

public class Message {
    private static int messageCounter = 0;
    private String messageID;
    private int numSent;
    private String recipient;
    private String text;
    private String messageHash;
    private String status;  // "sent", "disregarded", "stored"

    // Normal constructor
    public Message(String recipient, String text, String status) {
        this.recipient = recipient;
        this.text = text;
        this.status = status;
        this.messageID = generateMessageID();
        this.numSent = ++messageCounter;
        this.messageHash = createMessageHash();
    }

    // Test-only constructor (allows fixed ID and counter)
    public Message(String recipient, String text, String fixedID, int fixedNum, String status) {
        this.recipient = recipient;
        this.text = text;
        this.status = status;
        this.messageID = fixedID;
        this.numSent = fixedNum;
        this.messageHash = createMessageHash();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10 && messageID.matches("\\d+");
    }

    public boolean checkRecipientCell() {
        String regex = "^\\+[1-9][0-9]{0,2}[0-9]{7,10}$";
        return Pattern.matches(regex, recipient);
    }

    public String createMessageHash() {
        String idPrefix = messageID.length() >= 2 ? messageID.substring(0, 2) : "00";
        String[] words = text.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        String hash = idPrefix + ":" + numSent + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    private String generateMessageID() {
        Random rand = new Random();
        long id = 1_000_000_000L + (long)(rand.nextDouble() * 9_000_000_000L);
        return String.valueOf(id);
    }

    // Getters
    public String getMessageID() { return messageID; }
    public int getNumSent() { return numSent; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }
    public String getMessageHash() { return messageHash; }
    public String getStatus() { return status; }

    public static int getTotalMessages() { return messageCounter; }
    public static void resetCounter() { messageCounter = 0; }
}