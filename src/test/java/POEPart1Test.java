/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.poepart1.poe.part1.POEPart1;

/**
 *
 * @author Azaad
 */
public class POEPart1Test {
    
    public POEPart1Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

   @Test 
   public void testValidUsername() {
       assertTrue(POEPart1.checkUserName("Kyl_1"));
   }
   
   @Test
   public void testInvalidUserName() {
       assertFalse(POEPart1.checkUserName("Kyle!!!!1"));
   }
   
   @Test
   public void testValidPassword() {
       assertTrue(POEPart1.checkPasswordComplexity("Ch&&sec@ke99!"));
   }
   
   @Test
   public void testInvalidPassword() {
       assertFalse(POEPart1.checkPasswordComplexity("password"));
   }
   
   @Test 
   public void testValidCellPhone() {
       assertTrue(POEPart1.checkCellPhoneNumber("+27838968976"));
   }
   
   @Test
   public void testInvalidCellPhone() {
       assertFalse(POEPart1.checkCellPhoneNumber("08966553"));
   }
   
   @Test 
   public void testSuccessfulLogin(){
       assertTrue(POEPart1.loginUser("Kyl_1", "Ch&&sec@ke99!", "Kyl_1", "Ch&&sec@ke99!"));
   }
   
   @Test
   public void testFailedLogin() {
       assertFalse(POEPart1.loginUser("wrong_user", "wrong_password", "Kyl_1", "Ch&&sec@ke99!"));
   }
   
   @Test
   public void testSuccessfulRegistration() {
       assertEquals("Registration successful. ", POEPart1.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976"));
   }
   
   @Test
   public void testInvalidUsernameMessage() {
       assertEquals("Username is not correctly formatted; please ensurethat your username contains an underscore and is no more than 5 characters in length. ", POEPart1.registerUser("Kyle!!!!!", "Ch&&sec@ke99!", "+27838968976"));
   }
   
   @Test
   public void testInvalidPasswordMessage() {
       assertEquals("Password was not correctly formatted; please ensure that the password contains a capital letter, a number a special character, and is at least eight charaters in length. ", POEPart1.registerUser("Kyl_1", "password", "+27838968976"));
   }
   
   @Test
   public void testInvalidCellPhoneMessage() {
       assertEquals("Cell phone number incorrectly formatted or does not contain international code. ", POEPart1.registerUser("Kyl_1", "Ch&&sec@ke99!", "0838968976"));
   }
}