/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.poepart1.poe.part1;

/**
 *
 * @author Azaad
 */
public class MessageManager {
   private java.util.ArrayList<Message> message = new java.util.ArrayList<>();
   
   public void addMessage(Message newMessage) {
       message.add(newMessage);
   }
   
   public int getTotalMessages() {
       return message.size();
   }
   
   public int returnTotalmessages() {
       return message.size();
   }
   
   public Message getMessage(int index) {
       return message.get(index);
   }
   
   public void printMessage() {
       if (message.isEmpty()) {
           System.out.println("No messages available.");
       } else {
           System.out.println("========== ALL MESSAGES ==========");
           for (int i = 0; i < message.size(); i++) {
               Message currentMessage = message.get(i);
               System.out.println("Message Number: " + (i + 1));
               System.out.println("Message ID: " + currentMessage.getMessageID());
               System.out.println("Recipient: " + currentMessage.getRecipient());
               System.out.println("Message Text: " + currentMessage.getMessageText());
               System.out.println("Message Hash: " + currentMessage.getMessageHash());
               
               System.out.println("---------------------------------");
           }
           
           System.out.println("Total Message: " + message.size());
       }
   }
   
   public void printMessageSummary() {

    System.out.println("\n========== MESSAGE SUMMARY ==========");

    if (message.isEmpty()) {
        System.out.println("No messages have been recorded yet.");
        return;
    }

    int totalMessages = message.size();

    System.out.println("Total messages recorded: " + totalMessages);

    for (int i = 0; i < totalMessages; i++) {

        Message currentMessage = message.get(i);

        System.out.println("\nMessage " + (i + 1));
        System.out.println("Message ID: " + currentMessage.getMessageID());
        System.out.println("Recipient: " + currentMessage.getRecipient());
        System.out.println("Message: " + currentMessage.getMessageText());
        System.out.println("Hash: " + currentMessage.getMessageHash());

        System.out.println("-------------------------------------");
    }

    System.out.println("========== END OF SUMMARY ==========");
}
   
   public Message searchMessageByID(String searchID) {
       for (int i = 0; i < message.size(); i++) {
           Message currentMessage = message.get(i);
           if (currentMessage.getMessageID().equals(searchID)) {
               System.out.println("Message found: ");
               System.out.println("MessageID: " + currentMessage.getMessageID());
               System.out.println("Recipient: " + currentMessage.getRecipient());
               System.out.println("Message Text: " + currentMessage.getMessageText());
               
               return currentMessage;
           }
       }
       
       System.out.println("Message with ID " + searchID + " was not found.");
       return null;
   }
   
   public void searchMessageByRecipient(String searchRecipient) {
       boolean found = false;
       
       System.out.println("========== SEARCH RESULTS ==========");
       for (int i = 0; i < message.size(); i++) {
           Message currentMessage = message.get(i);
           if (currentMessage.getRecipient().equals(searchRecipient)) {
               System.out.println("Message Number: " + (i + 1));
               System.out.println("Message ID: " + currentMessage.getMessageID());
               System.out.println("Recipient: " + currentMessage.getRecipient());
               System.out.println("Message Text: " + currentMessage.getMessageText());
               
               System.out.println("----------------------------------");
               found = true;
           }
       }
       if (!found) {
           System.out.println("No messages found for the recipient: " + searchRecipient);
       }
   }
   
  public boolean deleteMessage(String messageID) {
      for (int i = 0; i < message.size(); i++) {
         Message currentMessage = message.get(i);
         if (currentMessage.getMessageID().equals(messageID)) {
             message.remove(i);
             System.out.println("Message with ID " + messageID + " deleted successfully.");
             return true;
         }
      }
      System.out.println("Message with ID " + messageID + " was not found.");
      return false;
  }
  
  
public void printLongestMessage() {
    if (message.isEmpty()) {
        System.out.println("No messages available.");
        return;
    }

    Message longestMessage = message.get(0);

    for (int i = 1; i < message.size(); i++) {
        Message currentMessage = message.get(i);

        if (currentMessage.getMessageText().length()
                > longestMessage.getMessageText().length()) {
            longestMessage = currentMessage;
        }
    }

    System.out.println("\n========== LONGEST MESSAGE ==========");
    System.out.println("Message ID: " + longestMessage.getMessageID());
    System.out.println("Recipient: " + longestMessage.getRecipient());
    System.out.println("Message: " + longestMessage.getMessageText());
    System.out.println("Length: "
            + longestMessage.getMessageText().length());
}

}


