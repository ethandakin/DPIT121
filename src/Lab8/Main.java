package Lab8;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

// Ethan Dakin
// 8209194

public class Main {
    // Attributes for main, basically global values
    protected static Scanner scan;
    protected static InsuranceCompany company;
    protected static User user;

    public static void main(String[] args) throws CloneNotSupportedException, PolicyException, IOException {
        // Initialize the scanner
        scan = new Scanner(System.in);
        // Create the company
        company = new InsuranceCompany("Test Company", "admin", "password", 100);

        // Call main menu function.
        mainMenu();

        // Close the scanner after all input is finished.
        scan.close();
    }

    
    // Method to print a bunch of new lines..
    public static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    // MAIN MENU
    // Initial menu prompt, a choice between admin login, user login and an exit.
    public static void displayMainMenu() {
        clearScreen();
        System.out.println("1: Admin Login");
        System.out.println("2: User Login");
        System.out.println("3: Exit");
    }

    // Base logic for menu prompt, while loop with a switch statement.
    public static void mainMenu() throws CloneNotSupportedException, PolicyException, IOException {
        int option = 0;

        while (option != 3) {
            displayMainMenu();
            System.out.print("\nPlease choose an option from 1 to 3: ");
            option = Integer.parseInt(scan.nextLine());

            switch(option) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("\nPlease enter a valid option\n");
            }
        }    
    }

    // ADMIN MENU
    // Admin menu prompt, for all admin choices when successfully logged in.
    public static void displayAdminMenu() {
        clearScreen();
        System.out.println("1: Test Code");
        System.out.println("2: Create User");
        System.out.println("3: Remove User");
        System.out.println("4: Create ThirdParty Policy");
        System.out.println("5: Create Comprehensive Policy");
        System.out.println("6: Remove Policy");
        System.out.println("7: Print User Information");
        System.out.println("8: Filter by Car Model");
        System.out.println("9: Filter by Expiry Date");
        System.out.println("10: Update Address");
        System.out.println("11: Change Admin Password");
        System.out.println("12: Report Payments Per Car Model");
        System.out.println("13: Report Payments Per City");
        System.out.println("14: Report All Payments Per Car Model");
        System.out.println("15: Save Binary File");
        System.out.println("16: Load Binary File");
        System.out.println("17: Save Text File");
        System.out.println("18: Load Text File");
        System.out.println("19: Log Out");
    }
    
    // Switch statement for all admin commands
    public static void adminMenu() throws CloneNotSupportedException, PolicyException, IOException {
        int option = 0;

        while (option != 19) {
            displayAdminMenu();
            System.out.print("\nPlease choose an option from 1 to 19: ");
            option = Integer.parseInt(scan.nextLine());

            switch(option) {
                case 1:
                    testCode();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    removeUser();
                    break;
                case 4:
                    createThirdPartyPolicy();
                    break;
                case 5:
                    createComprehensivePolicy();                
                    break;
                case 6:
                    removePolicy();
                    break;    
                case 7:
                    printUserInformation();
                    break;
                case 8:
                    filterByCarModel();
                    break;
                case 9:
                    filterByExpiryDate();
                    break;
                case 10:
                    updateAddress();
                    break;
                case 11:
                    changeAdminPassword();
                    break;
                case 12:
                    reportPaymentsPerCarModel();
                    break;
                case 13:
                    reportCities();
                    break;
                case 14:
                    reportAllPaymentsPerCarModel();
                    break;
                case 15:
                    saveBinaryFile();
                    break;
                case 16:
                    loadBinaryFile();
                    break;
                case 17:
                    saveTextFile();
                    break;
                case 18:
                    loadTextFile();
                    break;
                case 19:
                    break;
                default:
                    System.out.println("\nPlease enter a valid option\n");
            }

            if (option != 19) {
                System.out.println("\nPress the ENTER key to continue.");
                scan.nextLine();
            }
        }
    }

    // Admin login
    public static void adminLogin() throws CloneNotSupportedException, PolicyException, IOException {
        System.out.print("Enter admin username: ");
        String username = scan.nextLine();
        System.out.print("Enter admin password: ");
        String password = scan.nextLine();
        
        if (company.validateAdmin(username, password)) {
            adminMenu();
        } else {
            System.out.print("Incorrect login credentials\n\n");
        }
    }

    // USER MENU
    public static void displayUserMenu() {
        clearScreen();
        System.out.println("1: Update Name");
        System.out.println("2: Update Address");
        System.out.println("3: Create ThirdParty Policy");
        System.out.println("4: Create Comprehensive Policy");
        System.out.println("5: Remove Policy");
        System.out.println("6: Calculate Total Premium Payments");
        System.out.println("7: Car Price Rise All");
        System.out.println("8: Filter by Car Model");
        System.out.println("9: Filter by Expiry Date");
        System.out.println("10: Print policies");
        System.out.println("11: Print user information");
        System.out.println("12: Print a policy");
        System.out.println("13: Log Out");
        System.out.print("\nPlease choose an option from 1 to 13: ");
    }

    public static void userMenu() throws PolicyException {
        int option = 0;

        while (option != 13) {
            displayUserMenu();
            option = Integer.parseInt(scan.nextLine());

            switch(option) {
                case 1:
                    updateUserName();
                    break;
                case 2:
                    updateUserAddress();
                    break;
                case 3:
                    createUserThirdPartyPolicy();
                    break;
                case 4:
                    createUserComprehensivePolicy();          
                    break;
                case 5:
                    removeUserPolicy();
                    break;
                case 6:
                    calculateUserTotalPremiumPayments();
                    break;
                case 7:
                    carPriceRiseAllUser();
                    break;
                case 8:
                    filterByUserCarModel();
                    break;
                case 9:
                    filterByUserExpiryDate();
                    break;
                case 10:
                    printUserPolicies();
                    break;
                case 11:
                    printUser();
                    break;
                case 12:
                    printPolicy();
                    break;
                case 13:
                    break;
                default:
                    System.out.println("\nPlease enter a valid option\n");
            }

            if (option != 13) {
                System.out.println("\nPress the ENTER key to continue.");
                scan.nextLine();
            }
        }
    }

    public static void userLogin() throws PolicyException {
        System.out.print("Enter userID: ");
        int userID = Integer.parseInt(scan.nextLine());

        user = company.findUser(userID);

        if (user != null) {
            userMenu();
        } else {
            System.out.print("Incorrect login credentials\n\n");
        }
    }

    public static void baseCode() throws CloneNotSupportedException, PolicyException, IOException {
        // Create all the policies, with attributes described in the constructor.
        ThirdPartyPolicy thirdPartyPolicy1 = new ThirdPartyPolicy(
                "John",
                3000001,
                new Car("Subaru", CarType.SED, 2011, 2000),
                1,
                new MyDate(2025, 11, 3),
                "Got flattened by an 18 wheeler"
        );

        ComprehensivePolicy comprehensivePolicy1 = new ComprehensivePolicy(
                "Mike",
                3000002,
                new Car("Toyota", CarType.HATCH, 2016, 12000),
                3,
                new MyDate(2023, 1, 3),
                37,
                6
        );

        ThirdPartyPolicy thirdPartyPolicy2 = new ThirdPartyPolicy(
                "Michelle",
                3000003,
                new Car("Honda", CarType.SUV, 2014, 10000),
                8,
                new MyDate(2019, 7, 25),
                "Boom"
        );

        ComprehensivePolicy comprehensivePolicy2 = new ComprehensivePolicy(
                "Amy",
                3000004,
                new Car("Tesla", CarType.LUX, 2022, 57000),
                6,
                new MyDate(2031, 8, 16),
                56,
                14
        );

        ComprehensivePolicy comprehensivePolicy3 = new ComprehensivePolicy(
                "Tommy",
                3000005,
                new Car("Tractor", CarType.etc, 1998, 20000),
                1,
                new MyDate(2024, 4, 19),
                8,
                2
        );

        ThirdPartyPolicy thirdPartyPolicy3 = new ThirdPartyPolicy(
                "Liam",
                3000006,
                new Car("Toyota", CarType.HATCH, 2007, 8500),
                32,
                new MyDate(2018, 3, 3),
                "Tax fraud"
        );

        User user1 = new User("Jeremy", new Address(18, "Green St", "Strathfield", "Sydney"));
        User user2 = new User("Lisa", new Address(4, "Louisa St", "Brunswick", "Melbourne"));

        company.addUser(user1);
        company.addUser("Thomas", new Address(144, "Brokers Rd", "Mount Pleasant", "Wollongong"));
        company.addUser(user2);
        
        company.addPolicy(1, thirdPartyPolicy1);
        company.addPolicy(1, comprehensivePolicy1);

        company.addPolicy(2, thirdPartyPolicy2);
        company.addPolicy(2, comprehensivePolicy2);

        company.addPolicy(3, thirdPartyPolicy3);
        company.addPolicy(3, comprehensivePolicy3);
    }

    public static ArrayList<InsurancePolicy> filterPolicies(ArrayList<InsurancePolicy> policies, Predicate<InsurancePolicy> criteria) {
        return (ArrayList<InsurancePolicy>) policies.stream()
                .filter(criteria)
                .collect(Collectors.toList()); 
    }

    // ADMIN MENU METHODS
    public static void testCode() throws CloneNotSupportedException, PolicyException, IOException {
        baseCode();

        ArrayList<InsurancePolicy> policies = company.allPolicies();

        System.out.printf("A. Find all policies with the name 'John' and display the policy information\n");
        policies.stream()
        .filter(x -> x.getPolicyHolderName().contains("John"))
        .forEach(System.out::println);

        System.out.printf("B. Find all policies with the name 'John' and disply total premiums\n");
        policies.stream()
        .filter(x -> x.getPolicyHolderName().contains("John"))
        .forEach(x -> System.out.printf("$%.2f\n", x.calcPayment(company.getFlatRate())));

        Predicate<InsurancePolicy> p1 = x -> x.calcPayment(company.getFlatRate()) >= 200 && x.calcPayment(company.getFlatRate()) <= 500;

        System.out.printf("C. Find the first policy between $200 and $500:\n"); 
        policies.stream()
        .filter(p1)
        .findFirst()
        .ifPresent(x -> {
            System.out.printf("%s %d $%.2f\n", x.getPolicyHolderName(), x.getID(), x.calcPayment(company.getFlatRate()));
        });

        System.out.printf("D. Find all policies between $200 and $500\n");
        policies.stream()
        .filter(p1)
        .sorted(Comparator.comparing(InsurancePolicy::getID))
        .forEach(x -> System.out.printf("%s %d $%.2f\n", x.getPolicyHolderName(), x.getID(), x.calcPayment(company.getFlatRate())));

        System.out.printf("E. Calculate the total premium for all policies between $200 and $500\n$%.2f\n", 
        policies.stream()
        .filter(p1)
        .mapToDouble(x->x.calcPayment(company.getFlatRate()))
        .sum());


        Predicate<InsurancePolicy> c1 = x -> x.getPolicyHolderName().equals("John Smith");
        ArrayList<InsurancePolicy> policies1 = filterPolicies(policies, c1);
        InsurancePolicy.printPolicies(policies1);

        InsurancePolicy.printPolicies(filterPolicies(policies, x -> x.getExpiryDate().getYear()==2020));
        InsurancePolicy.printPolicies(filterPolicies(policies, x -> x.getCar().getModel().contains("Toyota")));

        Predicate<InsurancePolicy> c2 = x -> x.getCar().getType() == CarType.LUX;
        ArrayList<InsurancePolicy> policies3 = filterPolicies(policies, c2);
        policies3.stream()
        .sorted(Comparator.comparing(x -> x.getCar().getPrice()))
        .forEach(System.out::println);
        
    }

    public static void createUser() {
        System.out.print("Please enter the user's name: ");
        String name = scan.nextLine();

        System.out.print("Please enter the user's street number: ");
        int streetNum = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the user's street: ");
        String street = scan.nextLine();

        System.out.print("Please enter the user's suburb: ");
        String suburb = scan.nextLine();

        System.out.print("Please enter the user's city: ");
        String city = scan.nextLine();

        if (company.addUser(name, new Address(streetNum, street, suburb, city))) {
            System.out.print("User created successfully.");
        } else {
            System.out.print("User creation failed");
        }
    }

    public static void removeUser() {
        System.out.print("Please enter the user's userID: ");
        int userID = Integer.parseInt(scan.nextLine());

        if (company.removeUser(userID) == true) {
            System.out.printf("User %d successfully removed!", userID);
        } else {
            System.out.printf("Could not remove user %d", userID);
        }

        System.out.println();
    }

    public static void createThirdPartyPolicy() throws PolicyException {
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

        try {
            company.createThirdPartyPolicy(userID, name, policyID, car, numberOfClaims, expiryDate, comments);
        } catch (PolicyException e) {
            System.out.println(e);
        }
    }

    public static void createComprehensivePolicy() throws PolicyException {
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

        try {
            company.createComprehensivePolicy(userID, name, policyID, car, numberOfClaims, expiryDate, driverAge, level);
        } catch (PolicyException e) {
            System.out.println(e);
        }
    }

    public static void removePolicy() {
        System.out.print("Please enter the user's userID: ");
        int userID = Integer.parseInt(scan.nextLine());
        
        System.out.print("Please enter the policy ID: ");
        int policyID = Integer.parseInt(scan.nextLine());

        if (company.findUser(userID).removePolicy(policyID) == true) {
            System.out.printf("Policy %d removed from user %d successfully!", policyID, userID);
        } else {
            System.out.printf("Could not remove policy %d from user %d", policyID, userID);
        }

        System.out.println();
    }

    public static void printUserInformation() {
        System.out.print("Please enter the user's ID: ");
        int userID = Integer.parseInt(scan.nextLine());    

        System.out.print("\n");
        
        User user = company.findUser(userID);

        if (user != null) {
            user.print();
        } else {
            System.out.printf("User %d does not exist.", userID);
        }
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

    public static void changeAdminPassword() {
        System.out.print("Please enter the new admin password: ");
        String adminPassword = scan.nextLine();

        company.setAdminPassword(adminPassword);

        System.out.println("Admin password successfully changed");
    }

    public static void reportPaymentsPerCarModel() {
        System.out.print("Please enter the user's userID: ");
        int userID = Integer.parseInt(scan.nextLine());
        User user = company.findUser(userID);

        if (user != null) {
            System.out.println(user.reportPaymentsPerCarModel(user.getTotalCountPerCarModel(), user.getTotalPremiumPerCarModel(company.getFlatRate())));
        }
    }

    public static void reportCities() {
        System.out.println(company.reportPaymentPerCity(company.getTotalPremiumPerCity()));
    }

    public static void reportAllPaymentsPerCarModel() {
        System.out.println(company.reportPaymentsPerCarModel(company.getTotalCountPerCarModel(), company.getTotalPremiumPerCarModel()));
    }

    public static void saveBinaryFile() {
        System.out.print("Please enter the file name: ");
        String fileName = scan.nextLine();
    
        if (company.save(fileName)) {
            System.out.println("Successfully saved company to binary file: " + fileName);
        } else {
            System.out.println("Unable to save company to binary file: " + fileName);
        }
    }

    public static void loadBinaryFile() {
        System.out.print("Please enter the file name: ");
        String fileName = scan.nextLine();
    
        if (company.load(fileName)) {
            System.out.println("Successfully loaded company from binary file: " + fileName);
        } else {
            System.out.println("Unable to load company from binary file: " + fileName);
        }
    }

    public static void saveTextFile() throws IOException {
        System.out.print("Please enter the file name: ");
        String fileName = scan.nextLine();
    
        if (company.saveTextFile(fileName)) {
            System.out.println("Successfully saved company to text file: " + fileName);
        } else {
            System.out.println("Unable to save company to text file: " + fileName);
        }
    }

    public static void loadTextFile() throws IOException, PolicyException {
        System.out.print("Please enter the file name: ");
        String fileName = scan.nextLine();
    
        if (company.loadTextFile(fileName)) {
            System.out.println("Successfully loaded company from text file: " + fileName);
        } else {
            System.out.println("Unable to load company from text file: " + fileName);
        }
    }



    // USER MENU METHODS
    public static void updateUserName() {
        System.out.print("Please enter updated name: ");
        String name = scan.nextLine();

        user.setName(name);
    }

    public static void updateUserAddress() {
        System.out.print("Please enter the street number: ");
        int streetNum = Integer.parseInt(scan.nextLine());
        System.out.print("Please enter the street: ");
        String street = scan.nextLine();
        System.out.print("Please enter the suburb: ");
        String suburb = scan.nextLine();
        System.out.print("Please enter the city: ");
        String city = scan.nextLine();

        user.setAddress(new Address(streetNum, street, suburb, city));
    }

    public static void createUserThirdPartyPolicy() throws PolicyException {
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

        try {
            user.createThirdPartyPolicy(name, policyID, car, numberOfClaims, expiryDate, comments);
        } catch (PolicyException e) {
            System.out.println(e);
        }
    }

    public static void createUserComprehensivePolicy() throws PolicyException {
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

        try {
            user.createComprehensivePolicy(name, policyID, car, numberOfClaims, expiryDate, driverAge, level);
        } catch (PolicyException e) {
            System.out.println(e);
        }
    }

    public static void removeUserPolicy() {
        System.out.print("Please enter the policy ID: ");
        int policyID = Integer.parseInt(scan.nextLine());
        
        if (user.removePolicy(policyID) == true) {
            System.out.printf("Policy %d removed from user %d successfully!", policyID, user.getUserID());
        } else {
            System.out.printf("Could not remove policy %d from user %d", policyID, user.getUserID());
        }
    }

    public static void calculateUserTotalPremiumPayments() {
        System.out.printf("Total premium payments: $%.2f", user.calcTotalPremiums(company.getFlatRate()));
    }

    public static void carPriceRiseAllUser() {
        System.out.print("Please enter car price rise percent: ");
        double priceRise = Double.parseDouble(scan.nextLine());

        user.carPriceRiseAll(priceRise / 100);
    }

    public static void filterByUserCarModel() {
        System.out.print("Please enter the car model: ");
        String model = scan.nextLine();

        ArrayList<InsurancePolicy> policies = user.filterByCarModel(model);
        double totalPayments = InsurancePolicy.calcTotalPayments(policies, company.getFlatRate());
        System.out.print("\n");

        InsurancePolicy.printPolicies(policies);
        System.out.printf("Total payments: $%.2f", totalPayments);
    }

    public static void filterByUserExpiryDate() {
        System.out.print("Please enter the current year: ");
        int year = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the current month: ");
        int month = Integer.parseInt(scan.nextLine());

        System.out.print("Please enter the current day: ");
        int day = Integer.parseInt(scan.nextLine());

        System.out.print("\n");
        InsurancePolicy.printPolicies(company.filterByExpiryDate(user.getUserID(), new MyDate(year, month, day)));
    }

    public static void printUserPolicies() {
        user.printPolicies(company.getFlatRate());
    }

    public static void printUser() {
        user.print();
    }

    public static void printPolicy() {
        System.out.print("Please enter the policyID: ");
        int policyID = Integer.parseInt(scan.nextLine());
        
        user.findPolicy(policyID).print();
    }
}