package Assignment1.Advanced;
import java.util.ArrayList;
import Assignment1.*;

// Ethan Dakin
// 8209194

public class User {
    // Private attributes for user details, and ArrayList for policies.
    private String name;
    private int userID;
    private Address address;
    protected ArrayList<InsurancePolicy> policies;

    // Constructor
    public User(String name, int userID, Address address) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        // Create new ArrayList for all the policies this user holds
        this.policies = new ArrayList<InsurancePolicy>();
    }

    // Assessors
    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public Address getAddress() {
        return address;
    }

    public ArrayList<InsurancePolicy> getPolicies() {
        return policies;
    }

    // Mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPolicies(ArrayList<InsurancePolicy> policies) {
        this.policies = policies;
    }

    public void setCity(String city) {
        this.address.setCity(city);
    }

    // Add policy to the policies ArrayList if the ID is valid.
    public boolean addPolicy(InsurancePolicy policy) {
        if (findPolicy(policy.getID()) == null) {
            this.policies.add(policy);
            return true;
        } else {
            return false;
        }
    }

    // Returns a policy by searching the policies list with int policyID
    public InsurancePolicy findPolicy(int policyID) {
        for (InsurancePolicy policy: getPolicies()) {
            if (policy.getID() == policyID) {
                return policy;
            }
        }

        return null;
    }

    // Calculate the total premiums
    public double calcTotalPremiums(int flatRate) {
        return InsurancePolicy.calcTotalPayments(getPolicies(), flatRate);
    }

    // Rise all the policies car prices
    public void carPriceRiseAll(double risePercent) {
        InsurancePolicy.carPriceRiseAll(getPolicies(), risePercent);
    }

    // Filter the policies by a certain car model.
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        return InsurancePolicy.filterByCarModel(getPolicies(), carModel);
    }

        // Filter the policies by a certain car model.
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        return InsurancePolicy.filterByExpiryDate(getPolicies(), date);
    }

    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        if (findPolicy(id) == null) {
            addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments));
            return true;
        } else {
            return false;
        }
    }

    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
        if (findPolicy(id) == null) {
            addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level));
            return true;
        } else {
            return false;
        }
    }

    // Print the user, and the user's policies
    public void print() {
        System.out.printf("UserID: %d\nName: %s\nAddress: %s\nPolicies: \n\n", getUserID(), getName(), getAddress());
        InsurancePolicy.printPolicies(getPolicies());
    }

    // Return the user information and the user's policies as a string.
    @Override
    public String toString() {
        String policiesString = "";
        for (InsurancePolicy policy : getPolicies()) {
            policiesString += String.format("%s\n", policy);
        }
        return String.format("UserID: %d\nName: %s\nAddress: %s\nPolicies: \n\n", getUserID(), getName(), getAddress()) + policiesString;
    }

    // Print all the policies the user owns
    public void printPolicies(int flatRate) {
        InsurancePolicy.printPoliciesAndPremium(getPolicies(), flatRate);
    }
}
