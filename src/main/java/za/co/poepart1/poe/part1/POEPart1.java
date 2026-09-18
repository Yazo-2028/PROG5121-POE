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
}







    

