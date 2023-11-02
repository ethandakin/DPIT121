package Lab2;
import java.util.ArrayList;

// Ethan Dakin
// 8209194

public abstract class InsurancePolicy {
    // Default attributes
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    // Constructor
    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    // Mutators
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

    // Return formatted attributes
    public String toString() {
        return String.format("ID: %d\nHolder: %s\n%s\nNumber of claims: %d", this.id, this.policyHolderName, this.car, this.numberOfClaims);
    }
    
    public static void printPolicies(ArrayList<InsurancePolicy> policies) {
        // Print out title
        System.out.print("Policies: \n");

        // Loop through policies and call print method on them all.
        for (InsurancePolicy policy: policies) {
            policy.print();
            System.out.println("\n");
        }
    }

    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate) {
        double total = 0;

        // Loop through all policies and add the premium payment to total
        for (InsurancePolicy policy: policies) {
            total += policy.calcPayment(flatRate);;
        }

        return total;
    }

    public void carPriceRise(double risePercent) {
        this.getCar().priceRise(risePercent);
    }

    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) {
        for (InsurancePolicy policy: policies) {
            policy.carPriceRise(risePercent);
        }
    }

    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<InsurancePolicy>();
        
        for (InsurancePolicy policy: policies) {
            if (policy.getCar().getModel().equals(carModel)) {
                filteredPolicies.add(policy);
            }
        }

        return filteredPolicies;
    }

    // Abstract method for subclasses
    public abstract double calcPayment(double flatRate);

    // Print out formatted values, with car print in the middle.
    public void print() {
        System.out.printf("ID: %d\nHolder: %s\n", this.id, this.policyHolderName);
        this.car.print();
        System.out.printf("\nNumber of claims: %d", this.numberOfClaims);
    }
}
