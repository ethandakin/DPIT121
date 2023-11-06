package Lab3;
import java.util.ArrayList;

// Ethan Dakin
// 8209194

public class User {
    // Private attributes for user details, and ArrayList for policies.
    private String name;
    private int userID;
    private Address address;
    ArrayList<InsurancePolicy> policies;

    // Constructor
    public User(String name, int userID, Address address) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        // Create new ArrayList for all the policies this user holds
        this.policies = new ArrayList<InsurancePolicy>();
    }

    // Accessors
    public Address getAddress() {
        return address;
    }

    protected int getUserID() {
        return userID;
    }

    // Mutators
    public void setCity(String city) {
        this.address.setCity(city);
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    // Add policy to the policies ArrayList if the ID is valid.
    public boolean addPolicy(InsurancePolicy policy) {
        if (findPolicy(policy.id) == null) {
            this.policies.add(policy);
            return true;
        } else {
            return false;
        }
    }

    // Returns a policy by searching the policies list with int policyID
    public InsurancePolicy findPolicy(int policyID) {
        for (InsurancePolicy policy: this.policies) {
            if (policy.getID() == policyID) {
                return policy;
            }
        }

        return null;
    }

    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        if (findPolicy(id) != null) {
            return false;
        }

        addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, id, numberOfClaims));

        return true;
    }

    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
        if (findPolicy(id) != null) {
            return false;
        }

        addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level));

        return true;
    }

    // Print the user, and the user's policies
    public void print() {
        System.out.printf("UserID: %d\nName: %s\nAddress: %s\nPolicies: \n\n", userID, name, getAddress());
        InsurancePolicy.printPolicies(policies);
    }

    // Return the user information and the user's policies as a string.
    public String toString() {
        String policiesString = "";
        for (InsurancePolicy policy : policies) {
            policiesString += String.format("%s\n", policy);
        }
        return String.format("UserID: %d\nName: %s\nAddress: %s\nPolicies: \n\n", userID, name, getAddress()) + policiesString;
    }

    // Print all the policies the user owns
    public void printPolicies(int flatRate) {
        // Call the static method in InsurancePolicy class
        print();
        System.out.print("Total premium payments: \n");
        InsurancePolicy.printTotalPayments(policies, flatRate);
    }

    // Calculate the total premiums
    public double calcTotalPremiums(int flatRate) {
        return InsurancePolicy.calcTotalPayments(this.policies, flatRate);
    }

    // Rise all the policies car prices
    public void carPriceRiseAll(double risePercent) {
        InsurancePolicy.carPriceRiseAll(this.policies, risePercent);
    }

    // Filter the policies by a certain car model.
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        return InsurancePolicy.filterByCarModel(this.policies, carModel);
    }

    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        return InsurancePolicy.filterByExpiryDate(policies, date);
    }
    
}
