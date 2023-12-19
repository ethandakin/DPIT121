package Lab4;
import java.util.ArrayList;

// Ethan Dakin
// 8209194

public abstract class InsurancePolicy implements Cloneable, Comparable<InsurancePolicy> {
    // Attributes
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

    // Copy constructor
    public InsurancePolicy(InsurancePolicy policy) {
        this.policyHolderName = policy.policyHolderName;
        this.id = policy.id;
        this.car = policy.car;
        this.numberOfClaims = policy.numberOfClaims;
        this.expiryDate = policy.expiryDate;
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
        double rise = car.getPrice() * risePercent;
        car.priceRise(rise);
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
        car.print();
        System.out.printf("\nNumber of claims: %d\nExpiry date: %s", numberOfClaims, expiryDate);
    }

    // Clone method
    @Override
    public InsurancePolicy clone() throws CloneNotSupportedException {
        return (InsurancePolicy) super.clone();
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

    // Filter by expiry date
    public static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy> policies, MyDate date) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<InsurancePolicy>();

        for (InsurancePolicy policy: policies) {
            // If the policy is expired then add it to the ArrayList.
            if (policy.getExpiryDate().isExpired(date) == true) {
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

    // Print policies and the premium payment for each policy.
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

    // Shallow copy
    public static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) {
        ArrayList<InsurancePolicy> copy = new ArrayList<InsurancePolicy>();

        for (InsurancePolicy policy : policies) {
            copy.add(policy);
        }

        return copy;
    }

    // Deep copy (implements clone)
    public static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> copy = new ArrayList<InsurancePolicy>();

        for (InsurancePolicy policy : policies) {
            copy.add(policy.clone());
        }

        return copy;
    }

    // CompareTo method, compares two dates.
    @Override
    public int compareTo(InsurancePolicy other) {
        return expiryDate.compareTo(other.getExpiryDate());
    }
}
