/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package za.co.poepart1.poe.part1;
import java.util.Scanner;

/**
 *
 * @author Imtiyaaz @ST10532626
 */
public class POEPart1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter your first name: ");
        String firstname = input.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastname = input.nextLine();
        
        System.out.print("Enter your username: ");
        String username = input.nextLine();
        
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        
        System.out.print("Enter your cell phone number: ");
        String cellPhone = input.nextLine(); 
        
        System.out.println(registerUser(username, password, cellPhone));
        
        System.out.println("Enter your username to login: ");
        String loginUsername = input.nextLine();
        
        System.out.println("Enter your password to login: ");
        String loginPassword = input.nextLine();
        
        boolean loginSuccess = loginUser(loginUsername, loginPassword, username, password);
        
        System.out.println(returnLoginStatus(loginSuccess, firstname, lastname));
      
       
if (loginSuccess) {
    System.out.println("Welcome to QuickChat.");

    MessageManager messageManager = new MessageManager();
    int messageNumber = 0;
    int messagesProcessed = 0;
    int choice;

    System.out.print("How many messages would you like to send? ");
    int numberOfMessages = Integer.parseInt(input.nextLine());

    do {
        System.out.println("\n1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Quit");
        System.out.print("Choose an option: ");

        choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1:
                if (messagesProcessed >= numberOfMessages) {
                    System.out.println("You have reached your message limit.");
                    break;
                }

                System.out.println("\n--- New Message "
                        + (messagesProcessed + 1) + " ---");

                String messageID = Message.generateMessageId();

                System.out.println("Message ID: " + messageID);
                System.out.print("Enter recipient cell phone number: ");
                String recipient = input.nextLine();

                System.out.print("Enter your message: ");
                String messageText = input.nextLine();

                Message message = new Message(
                        messageID,
                        ++messageNumber,
                        recipient,
                        messageText
                );

                System.out.println(message.checkRecipientCell());

                if (!recipient.matches("^\\+27[0-9]{9}$")) {
                    System.out.println("Message was not sent.");
                    messagesProcessed++;
                    break;
                }

                String lengthCheck = message.checkMessageLength();
                System.out.println(lengthCheck);

                if (!lengthCheck.equals("Message ready to send.")) {
                    System.out.println("Message was not sent.");
                    messagesProcessed++;
                    break;
                }

                System.out.println("Message length: " + messageText.length());

                message.createMessageHash();

                System.out.println("1) Send Message");
                System.out.println("2) Disregard Message");
                System.out.println("3) Store Message");
                System.out.print("Choose an option: ");

                int messageChoice = Integer.parseInt(input.nextLine());

                switch (messageChoice) {
                    case 1:
                        System.out.println(message.SentMessage(1));
                        messageManager.addMessage(message);
                        break;

                    case 2:
                        System.out.println(message.SentMessage(2));
                        System.out.print("Enter 0 to confirm deletion: ");
                        int deleteChoice =
                                Integer.parseInt(input.nextLine());

                        if (deleteChoice == 0) {
                            System.out.println("Message deleted successfully.");
                        } else {
                            System.out.println("Message was not deleted.");
                        }
                        break;

                    case 3:
                        System.out.println(message.storeMessage());
                        messageManager.addMessage(message);
                        break;

                    default:
                        System.out.println(
                                "Invalid option. Please choose 1, 2, or 3."
                        );
                        break;
                }

                messagesProcessed++;
                System.out.println("Messages processed: " + messagesProcessed);
                break;

            case 2:
                System.out.println("Coming Soon.");
                break;

            case 3:
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
                break;
        }

    } while (choice != 3);

} else {
    System.out.println("You must log in successfully to use QuickChat.");
}
    }

        
    // Regex reference: Oracle Java Documentation - Regular Expressions
// https://docs.oracle.com/javase/tutorial/essential/regex/
    public static boolean checkCellPhoneNumber(String cellPhone) {
        return cellPhone.matches("^\\+27[0-9]{9}$");
    }
    
    public static String registerUser(String username, String password, String cellPhone) {
        
        if (!checkUserName(username)) {
             return "Username is not correctly formatted; please ensurethat your username contains an underscore and is no more than 5 characters in length. ";
    }
        
        if (!checkPasswordComplexity(password)) {
            return "Password was not correctly formatted; please ensure that the password contains a capital letter, a number a special character, and is at least eight charaters in length. ";
        }
        
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain international code. ";
        }
        
        return "Registration successful. ";
    }
    
    public static boolean loginUser(String username, String password, String registeredUsername, String registeredPassword) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }
    
    public static String returnLoginStatus(boolean loginSuccess, String firstname, String lastname) {
        if (loginSuccess) {
            return "Welcome " + firstname + ""+ lastname + " it is great to see you again. ";
        } else {
           return "Username or password incorrect, please try again. ";
        }
    }
    
    
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
 
    public static boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                hasCapital = true;
                break;
            }
        }
        boolean hasNumber = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                hasNumber = true;
                break;
            }
        }
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetterOrDigit(password.charAt(i))) {
                hasSpecial = true;
                break;
    }
}
        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }
    public static void showMessageReports(MessageManager messageManager,
                                      Scanner input) {
    int reportChoice;

    do {
        System.out.println("\n========== MESSAGE REPORTS ==========");
        System.out.println("1. Display all messages");
        System.out.println("2. Display longest message");
        System.out.println("3. Search by message ID");
        System.out.println("4. Search by recipient");
        System.out.println("5. Delete a message");
        System.out.println("6. Display total messages");
        System.out.println("7. Return to main menu");
        System.out.print("Choose an option: ");

        reportChoice = Integer.parseInt(input.nextLine());

        switch (reportChoice) {
            case 1:
                messageManager.printMessage();
                break;

            case 2:
                messageManager.printLongestMessage();
                break;

            case 3:
                System.out.print("Enter message ID: ");
                String searchID = input.nextLine();
                messageManager.searchMessageByID(searchID);
                break;

            case 4:
                System.out.print("Enter recipient number: ");
                String recipient = input.nextLine();
                messageManager.searchMessageByRecipient(recipient);
                break;

            case 5:
                System.out.print("Enter message ID to delete: ");
                String deleteID = input.nextLine();
                messageManager.deleteMessage(deleteID);
                break;

            case 6:
                System.out.println("Total messages: "
                        + messageManager.getTotalMessages());
                break;

            case 7:
                System.out.println("Returning to QuickChat...");
                break;

            default:
                System.out.println("Invalid option.");
        }

    } while (reportChoice != 7);
}
}







    
