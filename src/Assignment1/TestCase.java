package Assignment1;

import Lab4.test;

public class TestCase {
    public static void main(String[] args) {
        // Test case for Assignment 1
        // Creation of Insurance Company class.
        InsuranceCompany company = new InsuranceCompany("TestCase Company", "testUsername", "testPassword", 0);

        // Verification of accessor methods in company.
        System.out.printf("\nPrinting company information (toString):\n%s", company);
    
        // ValidateAdmin method with incorrect username and password.
        System.out.printf("Logging in with incorrect credentials:\nADMIN LOGIN VERIFIED: %s\n\n", company.validateAdmin("INCORRECTUSERNAME", "INCORRECTPASSWORD"));
        // ValidateAdmin method with correct username and password.
        System.out.printf("Logging in with correct credentials\nADMIN LOGIN VERIFIED: %s\n\n", company.validateAdmin("testUsername", "testPassword"));
        
        // Creation of Test Address
        Address testAddress = new Address(1, "Test St", "Mt Test", "Test City");
        // Print address (toString)
        System.out.printf("Printing address information (toString):\n%s\n\n", testAddress);
        // Print address (print method)
        System.out.print("Printing address information (print method):\n");
        testAddress.print();
        System.out.print("\n\n");

        // Creation of test users
        User testUser1 = new User("TestUser1", 1, testAddress);
        User testUser2 = new User("TestUser2", 2, testAddress);
        User testUser3 = new User("TestUser2", 3, testAddress);

        System.out.printf("Printing testUser1 (toString method):\n%s", testUser1);

    }
}