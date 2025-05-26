import javax.swing.*; // Import Java Swing for GUI components like JOptionPane

public class Main {

    public static void main(String[] args) {

        // --- Simulated Login Section ---
        // Prompt user to enter their username
        String username = JOptionPane.showInputDialog(null, "Enter your username:");
        // Prompt user to enter their password
        String password = JOptionPane.showInputDialog(null, "Enter your password:");

        // Validate login input (not null and not empty)
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Login failed.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop program if login fails
        }

        // Display welcome message if login is successful
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // Ask the user how many messages they want to send
        int totalMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
        int sentCount = 0; // Initialize count of messages sent

        // --- Main Menu Loop ---
        while (true) {
            // Menu options: 0 = Send, 1 = View Sent (disabled), 2 = Quit
            String[] menuOptions = {"Send Messages", "View Sent (Coming Soon)", "Quit"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Choose an option",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    menuOptions,
                    menuOptions[0]);

            // --- Option 1: Send Messages ---
            if (choice == 0) {
                // Check if the user has reached their message limit
                if (sentCount >= totalMessages) {
                    JOptionPane.showMessageDialog(null, "Message limit reached.");
                    continue; // Skip to next iteration
                }

                // Prompt for recipient phone number
                String recipient = JOptionPane.showInputDialog("Enter recipient number (e.g., +2771234567):");

                // Prompt for message content
                String content = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

                // Check that message is not too long
                if (content.length() > 250) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a message of less than 250 characters.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // Create a new Message object
                Message msg = new Message(recipient, content);

                // Handle message options: send, store, or discard
                String result = msg.sentMessage();

                // Show the result of the message action
                JOptionPane.showMessageDialog(null, result);

                // Display the message details (ID, hash, recipient, content)
                JOptionPane.showMessageDialog(null, msg.printMessages());

                // Increment the counter for sent messages
                sentCount++;

            // --- Option 2: View Sent Messages (Feature not implemented) ---
            } else if (choice == 1) {
                JOptionPane.showMessageDialog(null, "Coming Soon.");

            // --- Option 3: Quit the Application ---
            } else {
                JOptionPane.showMessageDialog(null, "Quitting... Total messages sent: " + sentCount);
                break; // Exit the loop and end the program
            }
        }
    }
}
