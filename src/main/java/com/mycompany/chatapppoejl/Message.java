package com.mycompany.chatapppoejl;

import java.util.Random;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private String messageID;          // 10-digit random
    private int numMessagesSent;       // auto-incremented per message sent
    private String recipient;          // cell number with +27
    private String messageText;        // max 250 chars
    private String messageHash;        // generated
    private boolean sent = false;
    private boolean stored = false;
    private static int totalMessagesSent = 0; // cumulative across all messages

    // Constructor – auto‑generates ID and hash, but recipient + text provided
    public Message(String recipient, String messageText, int currentMessageNumber) {
        this.messageID = generateMessageID();
        this.numMessagesSent = currentMessageNumber; // 1,2,3...
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash(currentMessageNumber);
    }

    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id).substring(0, 10);
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Reuse the cell phone validation from Registration (static method maybe)
    public static String checkRecipientCell(String cell) {
        String regex = "^\\+27[0-9]{9}$";
        if (cell != null && Pattern.matches(regex, cell))
            return "Cell phone number successfully captured.";
        else
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String createMessageHash(int messageNumber) {
        // Format: first two digits of messageID + ":" + messageNumber + ":" + first word + last word (all caps)
        String idPrefix = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length-1] : firstWord;
        String hash = idPrefix + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    public String sendMessageOption(int choice) { // 1=Send, 2=Store, 3=Disregard
        switch(choice) {
            case 1:
                sent = true;
                totalMessagesSent++;
                storeMessageToJSON("SENT");
                return "Message successfully sent.";
            case 2:
                stored = true;
                storeMessageToJSON("STORED");
                return "Message successfully stored.";
            case 3:
                return "Press 0 to delete the message.";
            default:
                return "Invalid option.";
        }
    }

    private void storeMessageToJSON(String status) {
        JSONObject msgObj = new JSONObject();
        msgObj.put("messageID", messageID);
        msgObj.put("numMessagesSent", numMessagesSent);
        msgObj.put("recipient", recipient);
        msgObj.put("messageText", messageText);
        msgObj.put("messageHash", messageHash);
        msgObj.put("status", status);

        // Append to messages.json
        JSONArray messageList = new JSONArray();
        try {
            // Read existing if any (simplified: overwrite with new list)
            messageList.add(msgObj);
            try (FileWriter file = new FileWriter("messages.json", true)) {
                file.write(msgObj.toJSONString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String printMessage() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText + "\n";
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Getters for testing
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public boolean isSent() { return sent; }
    public boolean isStored() { return stored; }
}