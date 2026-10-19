/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.poepart1.poe.part1;
import java.util. Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Azaad
 */
public class Message {
   private String messageID;
   private int messageNumber;
   private String recipient;
   private String messageText;
   private String messageHash;

public Message(String messageID, int messageNumber, String recipient, String messageText) {
    this.messageID = messageID;
    this.messageNumber = messageNumber;
    this.recipient = recipient;
    this.messageText = messageText;
            
}

public boolean checkMessageID() {
    return messageID.length() <= 10;
}

public String checkRecipientCell() {
    if (recipient.matches("^\\+27[0-9]{9}$")) {
        return "Cell phone number successfully captured.";
    } else {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
}

public String createMessageHash() {
    if (messageID == null || messageID.length() < 2) {
        return "Invalid message ID.";
    }
    String cleanedMessage = messageText.trim();

    if (cleanedMessage.isEmpty()) {
        return "Invalid message text.";
    }

    String[] words = cleanedMessage.split("\\s+");
    String firstTwoDigits = messageID.substring(0, 2);
    String firstWord = words[0];
    String lastWord = words[words.length - 1];

    firstWord = firstWord.replaceAll(
            "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", ""
    );

    lastWord = lastWord.replaceAll(
            "^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", ""
    );

    // Build the message hash
    messageHash = (
            firstTwoDigits + ":"
            + messageNumber + ":"
            + firstWord + lastWord
    ).toUpperCase();

    return messageHash;
}

public String checkMessageLength() {
    if (messageText == null || messageText.isEmpty()) {
        return "Message cannot be empty.";
    }
    if (messageText.length() <= 250) {
        return "Message ready to send.";
}

int excessCharacters = messageText.length() - 250;

return "Message exceeds 250 characters by "
        + excessCharacters
        + ", please reduce size.";
}

public static String generateMessageId() {
    Random random = new Random();
    
    long minimum = 1_000_000_000L;
    long maximum = 9_999_999_999L;
    
    long generatedID = minimum 
            + (long) (random.nextDouble() * (maximum - minimum + 1));
    
    return String.valueOf(generatedID);
}

    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3: 
                return "Message successfully stored.";
            default:
                return "Invalid option. Please choose 1, 2, 3.";
        }
    }
    
    public String getMessageID() {
        return messageID;
    }
    
    public int getMessageNumber() {
        return messageNumber;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public String getMessageText() {
        return messageText;
    }
    
    public String getMessageHash() {
        return messageHash;
    }
  
   
public String storeMessage() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    java.io.File file = new java.io.File("messages.json");
    java.util.List<Message> messages = new java.util.ArrayList<>();

    try {
        // Load existing messages if the file already exists.
        if (file.exists() && file.length() > 0) {
            try (java.io.FileReader reader = new java.io.FileReader(file)) {
                Message[] existingMessages =
                        gson.fromJson(reader, Message[].class);

                if (existingMessages != null) {
                    messages.addAll(java.util.Arrays.asList(existingMessages));
                }
            }
        }

        // Add the current message.
        messages.add(this);

        // Write all messages as one valid JSON array.
        try (FileWriter writer = new FileWriter(file, false)) {
            gson.toJson(messages, writer);
        }

        return "Message successfully stored.";

    } catch (IOException | com.google.gson.JsonSyntaxException e) {
        return "Error storing message: " + e.getMessage();
    }
}

}

