package Lab3;
import java.util.Scanner;
import java.util.ArrayList;

// Ethan Dakin
// 8209194

public class Main {
    // Attribute for flatRate
    public static final int flatRate = 100;

    public static void main(String[] args) {
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

        Scanner scan = new Scanner(System.in);

        User user1 = new User("Jeremy", 1, new Address(18, "Green St", "Strathfield", "Sydney"));
        User user3 = new User("Lisa", 3, new Address(4, "Louisa St", "Brunswick", "Melbourne"));

        InsuranceCompany company = new InsuranceCompany("Awesome Insurance", "SuperSecretLogin", "ThisIsASafePassword", flatRate);

        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();

        System.out.println("Login successful: " + company.validateAdmin(username, password));
        System.out.println();

        System.out.print("Enter wrong username: ");
        String wrongUsername = scan.nextLine();
        System.out.print("Enter wrong password: ");
        String wrongPassword = scan.nextLine();
        
        System.out.println("Login successful: " + company.validateAdmin(wrongUsername, wrongPassword));
        System.out.println();


        // First method, pass the user object through
        company.addUser(user1);
        // Second method, attributes as parameters
        company.addUser("Thomas", 2, new Address(144, "Brokers Rd", "Mount Pleasant", "Wollongong"));
        // Third user, correct
        company.addUser(user3);
        // Fourth user, duplicate.
        company.addUser("Thomas", 2, new Address(144, "Brokers Rd", "Mount Pleasant", "Wollongong"));
        
        // Add policies
        company.addPolicy(1, thirdPartyPolicy1);
        company.addPolicy(1, comprehensivePolicy1);

        company.addPolicy(2, thirdPartyPolicy2);
        company.addPolicy(2, comprehensivePolicy2);

        company.addPolicy(3, thirdPartyPolicy3);
        company.addPolicy(3, comprehensivePolicy3);

        // Incorrect policies
        company.addPolicy(4, thirdPartyPolicy1);
        company.addPolicy(1, thirdPartyPolicy1);

        

        System.out.print("Enter UserID: ");
        int userID = Integer.parseInt(scan.nextLine());
        System.out.println();

        company.printPolicies(userID);

        System.out.print("Enter UserID: ");
        int userID2 = Integer.parseInt(scan.nextLine());
        System.out.print("Enter PolicyID: ");
        int policyID = Integer.parseInt(scan.nextLine());
        System.out.println();

        System.out.print(company.findPolicy(userID2, policyID));
        System.out.println();

        company.print();

        company.carPriceRise(0.1);
        company.print();


        System.out.print("Enter UserID: ");
        int userID3 = Integer.parseInt(scan.nextLine());
        System.out.printf("Total payments for UserID %d: %.2f", userID3, company.calcTotalPayments(userID3));
        System.out.println("\n");
        System.out.printf("Total payments for all users: %.2f", company.calcTotalPayments());
        System.out.println("\n");

        ArrayList<InsurancePolicy> policies = company.allPolicies();
        InsurancePolicy.printPolicies(policies);

        System.out.print("Enter UserID: ");
        int userID4 = Integer.parseInt(scan.nextLine());
        System.out.print("Enter current date: ");
        System.out.println();

        MyDate expiryDate = new MyDate(scan.nextInt(), scan.nextInt(), scan.nextInt());
        scan.nextLine();
        ArrayList<InsurancePolicy> expiredPolicies = company.filterByExpiryDate(userID4, expiryDate);
        System.out.println();
        InsurancePolicy.printPolicies(expiredPolicies);

        System.out.print("Enter car model: ");
        String model = scan.nextLine();

        System.out.println();

        InsurancePolicy.printPolicies(company.filterByCarModel(model));

        System.out.print("Enter expiry date: ");
        MyDate expiryDate2 = new MyDate(scan.nextInt(), scan.nextInt(), scan.nextInt());
        scan.nextLine();
        InsurancePolicy.printPolicies(company.filterByExpiryDate(expiryDate2));


        System.out.print("Enter UserID: ");
        int userID5 = Integer.parseInt(scan.nextLine());

        User user = company.findUser(userID5);

        System.out.print("Enter street number: ");
        int streetNumber = Integer.parseInt(scan.nextLine());
        System.out.print("Enter street: ");
        String street = scan.nextLine();
        System.out.print("Enter suburb: ");
        String suburb = scan.nextLine();
        System.out.print("Enter city: ");
        String city = scan.nextLine();

        user.setAddress(new Address(streetNumber, street, suburb, city));

        scan.close();
    }
}
