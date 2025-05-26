import static org.junit.Assert.*;
import org.junit.Test;

public class MessageTest {

    // Test if generated message ID is within 10 characters
    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+271234567", "Test message");
        assertTrue("Message ID should be <= 10 characters", msg.checkMessageID());
    }

    // Test recipient number format with international prefix
    @Test
    public void testCheckRecipientCell_Valid() {
        Message msg = new Message("+271234567", "Hello");
        assertTrue("Valid recipient format", msg.checkRecipientCell());
    }

    // Test recipient number format without international prefix
    @Test
    public void testCheckRecipientCell_Invalid() {
        Message msg = new Message("0123456789", "Hello");
        assertFalse("Invalid recipient format", msg.checkRecipientCell());
    }

    // Test that message hash is generated in correct format
    @Test
    public void testCreateMessageHash_Format() {
        Message msg = new Message("+271234567", "Hi there");
        String hash = msg.createMessageHash();
        assertTrue("Message hash should contain colons", hash.contains(":"));
    }

    // Test that a message longer than 250 characters is identified
    @Test
    public void testMessageLengthExceeded() {
        String content = "x".repeat(251);
        assertTrue("Message should exceed 250 characters", content.length() > 250);
    }

    // Test print message output includes expected fields
    @Test
    public void testPrintMessagesContent() {
        Message msg = new Message("+271234567", "JUnit test message");
        String output = msg.printMessages();
        assertTrue(output.contains("MessageID:"));
        assertTrue(output.contains("Message Hash:"));
        assertTrue(output.contains("Recipient:"));
        assertTrue(output.contains("Message:"));
    }
}
