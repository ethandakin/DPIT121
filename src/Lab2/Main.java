package Lab1;

import java.util.ArrayList;

public class Main {
    public static final double flatRate = 100;
    public static void main(String[] args) {
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

        ArrayList<InsurancePolicy> policies = new ArrayList<>();
        policies.add(thirdPartyPolicy1);
        policies.add(comprehensivePolicy1);
        policies.add(thirdPartyPolicy2);
        policies.add(comprehensivePolicy2);
        policies.add(comprehensivePolicy3);

        System.out.print("PRINT METHOD: \n");

        for (InsurancePolicy policy: policies) {
            policy.print();
            System.out.println("\n");
        }

        System.out.print("toString METHOD: \n");

        for (InsurancePolicy policy: policies) {
            System.out.println(policy + "\n");
        }

        System.out.print("PREMIUM PAYMENTS: \n");

        for (InsurancePolicy policy: policies) {
            System.out.printf("%d: $%.2f\n", policy.id, policy.calcPayment(flatRate));
        }
    }
}
