/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author imtiyaaz
 */

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import za.co.poepart1.poe.part1.Message;

public class MessageTest {

    @Test
    public void testValidMessageID() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", "Hello John"
        );

        assertTrue(message.checkMessageID());
    }

    @Test
    public void testInvalidMessageID() {
        Message message = new Message(
                "12345678901", 1, "+27838968976", "Hello John"
        );

        assertFalse(message.checkMessageID());
    }

    @Test
    public void testValidRecipientCell() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", "Hello John"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testInvalidRecipientCell() {
        Message message = new Message(
                "1234567890", 1, "0838968976", "Hello John"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testCreateMessageHash() {
        Message message = new Message(
                "1545704248", 1, "+27098765432", "hi john"
        );

        assertEquals("15:1:HIJOHN", message.createMessageHash());
    }

    @Test
    public void testValidMessageLength() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", "Hello John"
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testMessageExceeds250Characters() {
        String longText = "a".repeat(251);

        Message message = new Message(
                "1234567890", 1, "+27838968976", longText
        );

        assertEquals(
                "Message exceeds 250 characters by 1, please reduce size.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testGenerateMessageID() {
        String id = Message.generateMessageId();

        assertNotNull(id);
        assertTrue(id.matches("[0-9]{10}"));
    }

    @Test
    public void testSendMessageOption() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", "Hello John"
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage(1)
        );
    }

    @Test
    public void testDisregardMessageOption() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", "Hello John"
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage(2)
        );
    }

    @Test
    public void testStoreMessageOption() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", "Hello John"
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage(3)
        );
    }

    @Test
    public void testInvalidMessageOption() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", "Hello John"
        );

        assertEquals(
                "Invalid option. Please choose 1, 2, 3.",
                message.SentMessage(4)
        );
    }
    
        @Test
    public void testMessageExactly250Characters() {
        String text = "a".repeat(250);

        Message message = new Message(
                "1234567890", 1, "+27838968976", text
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testEmptyMessageLength() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", ""
        );

        assertEquals(
                "Message cannot be empty.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testEmptyMessageHash() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", ""
        );

        assertEquals(
                "Invalid message text.",
                message.createMessageHash()
        );
    }

    @Test
    public void testInvalidShortMessageIDHash() {
        Message message = new Message(
                "1", 1, "+27838968976", "Hello John"
        );

        assertEquals(
                "Invalid message ID.",
                message.createMessageHash()
        );
    }

    @Test
    public void testMessageGetters() {
        Message message = new Message(
                "1234567890", 1, "+27838968976", "Hello John"
        );

        assertEquals("1234567890", message.getMessageID());
        assertEquals(1, message.getMessageNumber());
        assertEquals("+27838968976", message.getRecipient());
        assertEquals("Hello John", message.getMessageText());
    }

    @Test
    public void testMessageHashGetter() {
        Message message = new Message(
                "1545704248", 1, "+27098765432", "hi john"
        );

        message.createMessageHash();

        assertEquals(
                "15:1:HIJOHN",
                message.getMessageHash()
        );
    }
    
    @Test
public void testStoreMessageCreatesValidJSON() throws Exception {

    Path file = Path.of("messages.json");

    boolean fileExisted = Files.exists(file);
    byte[] originalContents = fileExisted
            ? Files.readAllBytes(file)
            : null;

    try {
        Message message = new Message(
                "1234567890",
                99,
                "+27838968976",
                "Storage test message"
        );

        String result = message.storeMessage();

        assertEquals(
                "Message successfully stored.",
                result
        );

        assertTrue(Files.exists(file));

        String contents = Files.readString(
                file,
                StandardCharsets.UTF_8
        );

        assertTrue(contents.contains("Storage test message"));
        assertTrue(contents.contains("1234567890"));
        assertTrue(contents.contains("+27838968976"));

        assertDoesNotThrow(() ->
                new com.google.gson.Gson().fromJson(
                        contents,
                        Message[].class
                )
        );

    } finally {
        // Restore the original file so the test
        // does not permanently change your messages.
        if (fileExisted) {
            Files.write(file, originalContents);
        } else {
            Files.deleteIfExists(file);
        }
    }
}
}
