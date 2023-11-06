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
    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    // Accessors
    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public int getID() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public int getNumberOfClaims() {
        return numberOfClaims;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    // Mutators
    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }
    
    public void setID(int id) {
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

    // Price rise for car class
    public void carPriceRise(double risePercent) {
        double rise = getCar().getPrice() * risePercent;
        getCar().priceRise(rise);
    }

    // Abstract method for subclasses
    public abstract double calcPayment(double flatRate);

    // Return formatted attributes
    @Override
    public String toString() {
        return String.format("PolicyID: %d\nHolder: %s\n%s\nNumber of claims: %d\nExpiry date: %s", id, policyHolderName, car, numberOfClaims, expiryDate);
    }

    // Print out formatted values, with car print in the middle.
    public void print() {
        System.out.printf("PolicyID: %d\nHolder: %s\n", id, policyHolderName);
        getCar().print();
        System.out.printf("\nNumber of claims: %d\nExpiry date: %s", numberOfClaims, expiryDate);
    }

    // Static methods
    // Calc all payments static method
    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate) {
        double total = 0;

        // Loop through all policies and add the premium payment to total
        for (InsurancePolicy policy: policies) {
            total += policy.calcPayment(flatRate);
        }

        return total;
    }

    // Price rise static method
    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) {
        for (InsurancePolicy policy: policies) {
            policy.carPriceRise(risePercent);
        }
    }

    // Filter by car model
    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<InsurancePolicy>();
        
        for (InsurancePolicy policy: policies) {
            //If the car model is equal to the parameter, then add it to the ArrayList.
            if (policy.getCar().getModel().equals(carModel)) {
                filteredPolicies.add(policy);
            }
        }

        return filteredPolicies;
    }

    // Print policies method
    public static void printPolicies(ArrayList<InsurancePolicy> policies) {
        // Loop through policies and call print method on them all.
        for (InsurancePolicy policy: policies) {
            policy.print();
            System.out.print("\n");
        }
    }

    public static void printPoliciesAndPremium(ArrayList<InsurancePolicy> policies, int flatRate) {
        // Loop through policies and call print method on them all.
        for (InsurancePolicy policy: policies) {
            policy.print();
            System.out.printf("Premium payment: $%.2f\n", policy.calcPayment(flatRate));
            System.out.print("\n");
        }
    }

    // Print total premium payment
    public static void printTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate) {
        // Print out the total, formatted to 2 decimal places.
        System.out.printf("$%.2f", calcTotalPayments(policies, flatRate));
    }
}
