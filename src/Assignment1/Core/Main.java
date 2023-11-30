package Assignment1.Core;
import java.util.Scanner;
import java.util.ArrayList;

import Assignment1.*;

// Ethan Dakin
// 8209194

public class Main {
    public static Scanner scan;
    public static InsuranceCompany company;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        company = new InsuranceCompany("Test Company", "admin", "password", 100);

        login();

        scan.close();
    }

    public static void login() {
        boolean valid = false;

        while (valid == false) {
            System.out.print("Enter username: ");
            String username = scan.nextLine();
            System.out.print("Enter password: ");
            String password = scan.nextLine();
            valid = company.validateAdmin(username, password);
            System.out.print("\n");

            if (valid == false) {
                System.out.print("Incorrect login credentials\n\n");
            } else {
                System.out.print("Logged in successfully\n\n");
            }
        }

        mainMenu();
    }

    public static void displayMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("1: Test Code");
        System.out.println("2: Create User");
        System.out.println("3: Create ThirdParty Policy");
        System.out.println("4: Create Comprehensive Policy");
        System.out.println("5: Print User Information");
        System.out.println("6: Filter by Car Model");
        System.out.println("7: Filter by Expiry Date");
        System.out.println("8: Update Address");
        System.out.println("9: Log Out");
        System.out.print("\nPlease choose an option from 1 to 9: ");
    }

    public static void mainMenu() {
        int option = 0;

        while (option != 9) {
            displayMainMenu();
            option = Integer.parseInt(scan.nextLine());

            switch(option) {
                case 1:
                    testCode();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    createThirdPartyPolicy();
                    break;
                case 4:
                    createComprehensivePolicy();                
                    break;
                case 5:
                    printUserInformation();
                    break;
                case 6:
                    filterByCarModel();
                    break;
                case 7:
                    filterByExpiryDate();
                    break;
                case 8:
                    updateAddress();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("\nPlease enter a valid option\n");
            }
        }
    }

    public static void testCode() {
        // Create all the policies, with attributes described in the constructor.
        ThirdPartyPolicy thirdPartyPolicy1 = new ThirdPartyPolicy(
                "John",
                1,
                new Car("Subaru", CarType.SED, 2011, 9500),
                2,
                new MyDate(2025, 11, 3),
                "Got flattened by an 18 wheeler"
        );

        ComprehensivePolicy comprehensivePolicy1 = new ComprehensivePolicy(
                "Mike",
                2,
                new Car("Toyota", CarType.HATCH, 2016, 12000),
                3,
                new MyDate(2023, 1, 3),
                37,
                6
        );

        ThirdPartyPolicy thirdPartyPolicy2 = new ThirdPartyPolicy(
                "Michelle",
                3,
                new Car("Honda", CarType.SUV, 2014, 10000),
                8,
                new MyDate(2019, 7, 25),
                "Boom"
        );

        ComprehensivePolicy comprehensivePolicy2 = new ComprehensivePolicy(
                "Amy",
                4,
                new Car("Tesla", CarType.LUX, 2022, 57000),
                6,
                new MyDate(2031, 8, 16),
                56,
                14
        );

        ComprehensivePolicy comprehensivePolicy3 = new ComprehensivePolicy(
                "Tommy",
                5,
                new Car("Tractor", CarType.etc, 1998, 20000),
                1,
                new MyDate(2024, 4, 19),
                8,
                2
        );

        ThirdPartyPolicy thirdPartyPolicy3 = new ThirdPartyPolicy(
                "Liam",
                6,
                new Car("Suzuki", CarType.HATCH, 2007, 8500),
                32,
                new MyDate(2018, 3, 3),
                "Tax fraud"
        );

        
        User user1 = new User("Jeremy", 1, new Address(18, "Green St", "Strathfield", "Sydney"));
        User user3 = new User("Lisa", 3, new Address(4, "Louisa St", "Brunswick", "Melbourne"));

        InsuranceCompany company = new InsuranceCompany("Awesome Insurance", "SuperSecretLogin", "ThisIsASafePassword", 100);

        company.addUser(user1);
        company.addUser("Thomas", 2, new Address(144, "Brokers Rd", "Mount Pleasant", "Wollongong"));
        company.addUser(user3);
        company.addUser("Thomas", 2, new Address(144, "Brokers Rd", "Mount Pleasant", "Wollongong"));
        
        company.addPolicy(1, thirdPartyPolicy1);
        company.addPolicy(1, comprehensivePolicy1);

        company.addPolicy(2, thirdPartyPolicy2);
        company.addPolicy(2, comprehensivePolicy2);

        company.addPolicy(3, thirdPartyPolicy3);
        company.addPolicy(3, comprehensivePolicy3);

        // Incorrect policies
        company.addPolicy(4, thirdPartyPolicy1);
        company.addPolicy(1, thirdPartyPolicy1);

        company.print();

        company.carPriceRise(0.1);
        company.print();

        System.out.printf("Total payments for all users: %.2f", company.calcTotalPayments());
        System.out.println("\n");

        ArrayList<InsurancePolicy> policies = company.allPolicies();
        InsurancePolicy.printPolicies(policies);
    }

    public static void createUser() {
        System.out.print("Please enter the user's name: ");
        String name = scan.nextLine();

        System.out.print("Please enter the user's userID: ");
        int userID = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the user's street number: ");
        int streetNum = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the user's street: ");
        String street = scan.nextLine();

        System.out.print("Please enter the user's suburb: ");
        String suburb = scan.nextLine();

        System.out.print("Please enter the user's city: ");
        String city = scan.nextLine();

        if (company.addUser(name, userID, new Address(streetNum, street, suburb, city))) {
            System.out.print("User created successfully.");
        } else {
            System.out.print("User creation failed");
        }
    }

    public static void createThirdPartyPolicy() {
        System.out.print("Please enter the user's ID: ");
        int userID = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the policy holder's name: ");
        String name = scan.nextLine();

        System.out.print("Please enter the policy ID: ");
        int policyID = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the car model: ");
        String model = scan.nextLine();

        System.out.print("Please enter the car type: ");
        CarType carType = CarType.valueOf(scan.nextLine());

        System.out.print("Please enter the car manufacturing year: ");
        int manufacturingYear = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the car price: ");
        double price = Double.parseDouble(scan.nextLine());

        System.out.print("Please enter the number of claims: ");
        int numberOfClaims = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the expiry date year: ");
        int year = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the expiry date month: ");
        int month = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the expiry date day: ");
        int day = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter any comments: ");
        String comments = scan.nextLine();
        
        Car car = new Car(model, carType, manufacturingYear, price);
        MyDate expiryDate = new MyDate(year, month, day);

        if (company.createThirdPartyPolicy(userID, name, policyID, car, numberOfClaims, expiryDate, comments)) {
            System.out.print("ThirdParty Policy created successfully.");
        } else {
            System.out.print("ThirdParty Policy creation failed");
        }    
    }

    public static void createComprehensivePolicy() {
        System.out.print("Please enter the user's ID: ");
        int userID = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the policy holder's name: ");
        String name = scan.nextLine();

        System.out.print("Please enter the policy ID: ");
        int policyID = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the car model: ");
        String model = scan.nextLine();

        System.out.print("Please enter the car type: ");
        CarType carType = CarType.valueOf(scan.nextLine());

        System.out.print("Please enter the car manufacturing year: ");
        int manufacturingYear = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the car price: ");
        double price = Double.parseDouble(scan.nextLine());

        System.out.print("Please enter the number of claims: ");
        int numberOfClaims = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the expiry date year: ");
        int year = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the expiry date month: ");
        int month = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the expiry date day: ");
        int day = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter driver age: ");
        int driverAge = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter level: ");
        int level = Integer.parseInt(scan.nextLine());
        
        Car car = new Car(model, carType, manufacturingYear, price);
        MyDate expiryDate = new MyDate(year, month, day);

        if (company.createComprehensivePolicy(userID, name, policyID, car, numberOfClaims, expiryDate, driverAge, level)) {
            System.out.print("Comprehensive Policy created successfully.");
        } else {
            System.out.print("Comprehensive Policy creation failed");
        }
    }

    public static void printUserInformation() {
        System.out.print("Please enter the user's ID: ");
        int userID = Integer.parseInt(scan.nextLine());    

        System.out.print("\n");
        company.findUser(userID).print();

        // TODO
        // Add error handling?
    }

    public static void filterByCarModel() {
        System.out.print("Please enter the car model: ");
        String model = scan.nextLine();

        ArrayList<InsurancePolicy> policies = company.filterByCarModel(model);
        double totalPayments = InsurancePolicy.calcTotalPayments(policies, company.getFlatRate());
        System.out.print("\n");

        InsurancePolicy.printPolicies(policies);
        System.out.printf("Total payments: $%.2f", totalPayments);
    }

    public static void filterByExpiryDate() {
        System.out.print("Please enter the user's ID: ");
        int userID = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the current year: ");
        int year = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the current month: ");
        int month = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the current day: ");
        int day = Integer.parseInt(scan.nextLine());

        System.out.print("\n");
        InsurancePolicy.printPolicies(company.filterByExpiryDate(userID, new MyDate(year, month, day)));
    }

    public static void updateAddress() {
        System.out.print("Please enter the user's ID: ");
        int userID = Integer.parseInt(scan.nextLine());     
        
        System.out.print("Please enter the user's street number: ");
        int streetNum = Integer.parseInt(scan.nextLine());
        System.out.print("Please enter the user's street: ");
        String street = scan.nextLine();
        System.out.print("Please enter the user's suburb: ");
        String suburb = scan.nextLine();
        System.out.print("Please enter the user's city: ");
        String city = scan.nextLine();

        company.findUser(userID).setAddress(new Address(streetNum, street, suburb, city));
    }
}