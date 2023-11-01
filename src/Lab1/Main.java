package Lab1;
import java.util.ArrayList;

// Ethan Dakin
// 8209194

public class Main {
    // Attribute for flatRate
    public static final double flatRate = 100;

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

        // Create ArrayList for all the InsurancePolicy objects.
        ArrayList<InsurancePolicy> policies = new ArrayList<>();
        // Add all the policies into the ArrayList policies
        policies.add(thirdPartyPolicy1);
        policies.add(comprehensivePolicy1);
        policies.add(thirdPartyPolicy2);
        policies.add(comprehensivePolicy2);
        policies.add(comprehensivePolicy3);

        // Print out title
        System.out.print("PRINT METHOD: \n");

        // Loop through policies and call print method on them all.
        for (InsurancePolicy policy: policies) {
            policy.print();
            System.out.println("\n");
        }

        // Print out title
        System.out.print("toString METHOD: \n");

        // Loop through policies and print them, calling the toString method.
        for (InsurancePolicy policy: policies) {
            System.out.println(policy + "\n");
        }

        // Print out title
        System.out.print("PREMIUM PAYMENTS: \n");
        // Declare total variable
        double total = 0;

        // Loop through all policies, printing premium payment for each one, and adding that to total
        for (InsurancePolicy policy: policies) {
            double premiumPayment = policy.calcPayment(flatRate);
            System.out.printf("%d: $%.2f\n", policy.id, premiumPayment);
            total += premiumPayment;
        }

        // Print out title
        System.out.print("\nTOTAL PREMIUM PAYMENTS: \n");
        // Print out the total, formatted to 2 decimal places.
        System.out.printf("$%.2f", total);
    }
}
