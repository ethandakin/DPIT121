package Lab2;
import java.util.ArrayList;
import java.util.Collections;
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
                "Got flattened by an 18 wheeler"
        );

        ComprehensivePolicy comprehensivePolicy1 = new ComprehensivePolicy(
                "Mike",
                2,
                new Car("Toyota", CarType.HATCH, 2016, 12000),
                3,
                37,
                6
        );

        ThirdPartyPolicy thirdPartyPolicy2 = new ThirdPartyPolicy(
                "Michelle",
                3,
                new Car("Honda", CarType.SUV, 2014, 10000),
                8,
                "Boom"
        );

        ComprehensivePolicy comprehensivePolicy2 = new ComprehensivePolicy(
                "Amy",
                4,
                new Car("Tesla", CarType.LUX, 2022, 57000),
                6,
                56,
                14
        );

        ComprehensivePolicy comprehensivePolicy3 = new ComprehensivePolicy(
                "Tommy",
                5,
                new Car("Tractor", CarType.etc, 1998, 20000),
                1,
                8,
                2
        );

        User user = new User("Ethan", 1, new Address(18, "Green St", "Strathfield", "Sydney"));

        // Add all the policies into the ArrayList policies
        user.addPolicy(thirdPartyPolicy1);
        user.addPolicy(comprehensivePolicy1);
        user.addPolicy(thirdPartyPolicy2);
        user.addPolicy(comprehensivePolicy2);
        user.addPolicy(comprehensivePolicy3);

        user.print();
        
        System.out.print(user);

        InsurancePolicy policyFound = user.findPolicy(2);
        InsurancePolicy policyNotFound = user.findPolicy(7);

        if (policyNotFound == null) {
                System.out.print("Policy has not been found");
        }

        System.out.print(policyFound);
        policyFound.carPriceRise(0.1);
        System.out.print(policyFound);

        policyFound.setPolicyHolderName("Robert");
        policyFound.getCar().setModel("Toyota Camry");
        policyFound.getCar().setManufacturingYear(2018);

        user.setCity("Wollongong");

        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter new address information: ");
        System.out.print("Number: ");
        int number = Integer.parseInt(scan.nextLine());

        System.out.print("Street: ");
        String street = scan.nextLine();

        System.out.print("Suburb: ");
        String suburb = scan.nextLine();

        System.out.print("City: ");
        String city = scan.nextLine();

        user.setAddress(new Address(number, street, suburb, city));

        System.out.println(user.calcTotalPremiums(flatRate));
        user.carPriceRiseAll(0.1);
        System.out.println(user.calcTotalPremiums(flatRate));

        System.out.print("\nPlease enter a car model: ");
        String carModel = scan.nextLine();

        InsurancePolicy.printPolicies(user.filterByCarModel(carModel));
    }
}
