package Lab2;

import java.util.ArrayList;

public abstract class InsurancePolicy {
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setNumberOfClaims(int numberOfClaims) {
        this.numberOfClaims = numberOfClaims;
    }

    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPolicyHolderName() {
        return this.policyHolderName;
    }

    public int getId() {
        return this.id;
    }

    public Car getCar() {
        return this.car;
    }

    public int getNumberOfClaims() {
        return this.numberOfClaims;
    }

    public MyDate getExpiryDate() {
        return this.expiryDate;
    }

    static void printPolicies(ArrayList<InsurancePolicy> policies) {
        System.out.print("PRINT METHOD: \n");

        for (InsurancePolicy policy: policies) {
            policy.print();
            System.out.println("\n");
        }

        System.out.print("toString METHOD: \n");

        for (InsurancePolicy policy: policies) {
            System.out.println(policy + "\n");
        }
    }

    static double calcTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate) {
        double totalPayments = 0.0;

        for (InsurancePolicy policy: policies) {
            totalPayments += policy.calcPayment(flatRate);
        }

        return totalPayments;
    }

    void carPriceRise(double risePercent) {
        this.car.priceRise(car.getPrice() * risePercent);
    } 

    static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) {
        for (InsurancePolicy policy: policies) {
            policy.carPriceRise(risePercent);;
        }
    }

    static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel) {
        return policies;
    }

    public String toString() {
        return String.format("ID: %d\nHolder: %s\n%s\nNumber of claims: %d", this.id, this.policyHolderName, this.car, this.numberOfClaims);
    }

    public abstract double calcPayment(double flatRate);

    public void print() {
        System.out.printf("ID: %d\nHolder: %s\n", this.id, this.policyHolderName);
        this.car.print();
        System.out.printf("\nNumber of claims: %d", this.numberOfClaims);
    }
}
