package Lab2;
import java.util.Scanner;

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

        // Create the user and address.
        User user = new User("Jeremy", 1, new Address(18, "Green St", "Strathfield", "Sydney"));

        // Add all the policies into the user using addPolicy method
        user.addPolicy(thirdPartyPolicy1);
        user.addPolicy(comprehensivePolicy1);
        user.addPolicy(thirdPartyPolicy2);
        user.addPolicy(comprehensivePolicy2);
        user.addPolicy(comprehensivePolicy3);
        String carModel = "Tesla";

        InsurancePolicy.printPolicies(user.filterByCarModel(carModel));

        /* 
        // Call the print method for the user.
        user.print();
        
        // Print the user with toString
        System.out.print(user);

        // Invalid policy ID
        InsurancePolicy policyNotFound = user.findPolicy(7);
        // Valid policy ID
        InsurancePolicy policyFound = user.findPolicy(2);


        if (policyNotFound == null) { System.out.print("Policy has not been found\n\n"); }
    
        // Print the valid policy with toString method
        System.out.print(policyFound);
        System.out.println();
        // Rise the car price by 10%
        policyFound.carPriceRise(0.1);
        // Print the policy again
        System.out.print(policyFound);

        // set policyHolderName to Robert with selected method
        policyFound.setPolicyHolderName("Robert");
        // Set the car model to Toyota Camry
        policyFound.getCar().setModel("Toyota Camry");
        // Set manufacturing year to 2018
        policyFound.getCar().setManufacturingYear(2018);

        // Set the city of the user to Wollongong
        user.setCity("Wollongong");

        // Create scanner
        Scanner scan = new Scanner(System.in);
        // Print out information for the new address
        System.out.println("\nEnter new address information: ");

        // Enter Street Number
        System.out.print("Street Number: ");
        int number = Integer.parseInt(scan.nextLine());

        // Enter Street
        System.out.print("Street: ");
        String street = scan.nextLine();

        // Enter Suburb
        System.out.print("Suburb: ");
        String suburb = scan.nextLine();

        // Enter City
        System.out.print("City: ");
        String city = scan.nextLine();
        System.out.println();

        // Set the user's address with setAddress
        user.setAddress(new Address(number, street, suburb, city));

        // Print the user with toString to see the address change
        System.out.print(user);

        // Print the total premium payments for all policies that the user owns
        System.out.printf("Total premium payments: $%.1f\n", user.calcTotalPremiums(flatRate));
        // Rise the price of all cars the user owns by 10%
        user.carPriceRiseAll(0.1);
        // Print the total premium payments again to see the difference.
        System.out.printf("Total premium payments: $%.1f\n\n", user.calcTotalPremiums(flatRate));

        // Print out information for scanner
        System.out.print("Please enter a car model: ");
        // Enter the car model
        String carModel = scan.nextLine();
        System.out.println();

        // Filter the user's cars by the model entered into the scanner in the prior step, then call InsurancePolicy.printPolicies to list them all out.
        InsurancePolicy.printPolicies(user.filterByCarModel(carModel));
        // Close the scanner.
        scan.close(); */
    }
}
