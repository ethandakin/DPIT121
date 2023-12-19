package Lab4;
import java.util.ArrayList;
import java.util.Collections;

// Ethan Dakin
// 8209194

public class User implements Cloneable, Comparable<User> {
    // Attributes
    private String name;
    private int userID;
    private Address address;
    private ArrayList<InsurancePolicy> policies;
    private static int userCount = 0;

    // Constructor
    public User(String name, Address address) {
        userCount++;
        this.name = name;
        this.userID = userCount;
        this.address = address;
        // Create new ArrayList for all the policies this user holds
        this.policies = new ArrayList<InsurancePolicy>();
    }

    // Copy constructor
    public User(User user) {
        this.name = user.name;
        this.userID = user.userID;
        this.address = user.address;
        this.policies = user.policies;
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

    public static int getUserCount() {
        return userCount;
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

    // Returns a policy by searching the policies list with int policyID
    public InsurancePolicy findPolicy(int policyID) {
        for (InsurancePolicy policy: policies) {
            if (policy.getID() == policyID) {
                return policy;
            }
        }

        return null;
    }

    // Add policy to the policies ArrayList if the ID is valid.
    public boolean addPolicy(InsurancePolicy policy) {
        if (findPolicy(policy.getID()) == null) {
            policies.add(policy);
            return true;
        } else {
            return false;
        }
    }

    // Remove a policy with a given policyID
    public boolean removePolicy(int policyID) {
        if (findPolicy(policyID) != null) {
            policies.remove(findPolicy(policyID));
            return true;
        } else {
            return false;
        }
    }

    // Create third party policy
    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        if (findPolicy(id) == null) {
            addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments));
            return true;
        } else {
            return false;
        }
    }

    // Create comprehensive policy
    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
        if (findPolicy(id) == null) {
            addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level));
            return true;
        } else {
            return false;
        }
    }

    // Calculate the total premiums
    public double calcTotalPremiums(int flatRate) {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }

    // Rise all the policies car prices
    public void carPriceRiseAll(double risePercent) {
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }

    // Filter the policies by a certain car model.
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        return InsurancePolicy.filterByCarModel(policies, carModel);
    }

    // Filter the policies by a certain car model.
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        return InsurancePolicy.filterByExpiryDate(policies, date);
    }

    // Sort policies with compareTo
    public ArrayList<InsurancePolicy> sortPoliciesByDate() throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> sorted = deepCopyPolicies();
        Collections.sort(sorted);
        return sorted;
    }

    // Shallow copy policies
    public ArrayList<InsurancePolicy> shallowCopyPolicies() {
        ArrayList<InsurancePolicy> copy = new ArrayList<InsurancePolicy>();

        for (InsurancePolicy policy : policies) {
            copy.add(policy);
        }

        return copy;
    } 

    // Deep copy user's policies.
    public ArrayList<InsurancePolicy> deepCopyPolicies() throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> copy = new ArrayList<InsurancePolicy>();

        for (InsurancePolicy policy : policies) {
            copy.add(policy.clone());
        }

        return copy;
    } 

    // Static shallowCopy for a list of users
    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
        ArrayList<User> copy = new ArrayList<User>();

        for (User user : users) {
            copy.add(user);
        }

        return copy;
    }

    // Static deepCopy for a list of users.
    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
        ArrayList<User> copy = new ArrayList<User>();

        for (User user : users) {
            copy.add(user.clone());
        }

        return copy;
    }

    // Populate distinct car models
    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> carModels = new ArrayList<String>();

        for (InsurancePolicy policy : policies) {
            if (!carModels.contains(policy.getCar().getModel())) {
                carModels.add(policy.getCar().getModel());
            }
        } 

        return carModels;
    }

    // Get total count for car model
    public int getTotalCountForCarModel(String carModel) {
        int carModels = 0;

        for (InsurancePolicy policy : policies) {
            if (policy.getCar().getModel().equals(carModel)) {
                carModels++;
            }
        } 

        return carModels;
    }

    // Get total payment for car model
    public double getTotalPaymentForCarModel(String carModel) {
        double totalPrice = 0;

        for (InsurancePolicy policy : policies) {
            if (policy.getCar().getModel().equals(carModel)) {
                totalPrice += policy.getCar().getPrice();
            }
        } 

        return totalPrice;
    }

    // Get total count per car model
    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> carModelCount = new ArrayList<Integer>();

        for (String model : carModels) {
            carModelCount.add(getTotalCountForCarModel(model));
        } 

        return carModelCount;
    }

    // Get total payment per car model
    public ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> carModels) {
        ArrayList<Double> totalPayment = new ArrayList<Double>();

        for (String model : carModels) {
            totalPayment.add(getTotalPaymentForCarModel(model));
        } 

        return totalPayment;
    }

    // Report car payments
    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiumPayments) {
        System.out.printf("%s\t%s\t%s\n", "Car Model", "Total Premium Payment", "Average Premium Payment");

        for (int i = 0; i < carModels.size(); i++) {
            System.out.printf("%-9s\t$%-20.2f\t$%.2f\n", carModels.get(i), premiumPayments.get(i), premiumPayments.get(i) / counts.get(i));
        }
    }

    // Print all the policies the user owns
    public void printPolicies(int flatRate) {
        InsurancePolicy.printPoliciesAndPremium(policies, flatRate);
    }

    // Print the user, and the user's policies
    public void print() {
        System.out.printf("UserID: %d\nName: %s\nAddress: %s\nPolicies: \n\n", userID, name, address);
        InsurancePolicy.printPolicies(policies);
    }


    // Return the user information and the user's policies as a string.
    @Override
    public String toString() {
        String policiesString = "";
        for (InsurancePolicy policy : policies) {
            policiesString += String.format("%s\n", policy);
        }
        return String.format("UserID: %d\nName: %s\nAddress: %s\nPolicies: \n\n", userID, name, address) + policiesString;
    }

    // Primary compareTo, this one compares the cities.
    @Override
    public int compareTo(User user) {
        return address.getCity().compareTo(user.address.getCity());
    }

    // Secondary compareTo, this one compares the premium rate.
    public int compareTo1(User user) {
        return (int) (calcTotalPremiums(0)) - (int) (user.calcTotalPremiums(userCount));
    }

    // Clone method
    @Override 
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
