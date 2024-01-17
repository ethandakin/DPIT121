package Lab8;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Collections;

// Ethan Dakin
// 8209194

public class User implements Cloneable, Comparable<User>, Serializable {
    // ATTRIBUTES //
    private String name;
    private int userID;
    private Address address;
    private HashMap<Integer, InsurancePolicy> policies;
    private static int userCount = 0;

    // CONSTRUCTOR //
    public User(String name, Address address) {
        userCount++;
        this.name = name;
        this.userID = userCount;
        this.address = address;
        this.policies = new HashMap<Integer, InsurancePolicy>();
    }

    // COPY CONSTRUCTOR //
    public User(User user) {
        this.name = user.name;
        this.userID = user.userID;
        this.address = user.address;
        this.policies = user.policies;
    }

    // ACCESSORS //
    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public Address getAddress() {
        return address;
    }

    public HashMap<Integer, InsurancePolicy> getPolicies() {
        return policies;
    }

    public static int getUserCount() {
        return userCount;
    }

    // MUTATORS //
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // METHODS //
    // findPolicy method
    public InsurancePolicy findPolicy(int policyID) {
        return policies.get(policyID);
    }

    // addPolicy method
    public boolean addPolicy(InsurancePolicy policy) {
        if (findPolicy(policy.getID()) == null) {
            policies.put(policy.getID(), policy);
            return true;
        } else {
            return false;
        }
    }

    // removePolicy method
    public boolean removePolicy(int policyID) {
        if (findPolicy(policyID) != null) {
            policies.remove(policyID);
            return true;
        } else {
            return false;
        }
    }

    public static HashMap<Integer, User> load(String fileName) {
        HashMap<Integer, User> users = new HashMap<Integer, User>();
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.err.println("Error in creating/opening the file.");
            System.exit(1);
        }

        try {
            while (true) {
                User user = (User) inputStream.readObject();
                users.put(user.getUserID(), user);
            }
        } catch (EOFException e) {
            System.out.println("No new records.");
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong class in file");
        } catch (IOException e) {
            System.err.println("Error in adding object to file."); 
            System.exit(1);
        }

        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }

        return users;
    }

    public static boolean save(HashMap<Integer, User> users, String fileName) {
        ObjectOutputStream outputStream = null;
        boolean passed = false;

        try {
            outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.err.println("Error in creating/opening the file.");
            System.exit(1);
        }

        try {
            for (int userID : users.keySet()) {
                outputStream.writeObject(users.get(userID));
            }
        } catch(IOException e) {
            System.err.println("Error in adding the objects to the file.");
            System.exit(1);
        }

        try {
            if (outputStream != null) {
                outputStream.close();
                passed = true;
            }
        } catch(IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }

        return passed;
    }

    // createThirdPartyPolicy method
    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException {
        if (findPolicy(id) == null) {
            addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments));
            return true;
        } else {
            return false;
        }
    }

    // createComprehensivePolicy method
    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException {
        if (findPolicy(id) == null) {
            addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level));
            return true;
        } else {
            return false;
        }
    }

    // calcTotalPremiums method
    public double calcTotalPremiums(int flatRate) {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }

    // carPriceRiseAll method
    public void carPriceRiseAll(double risePercent) {
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }

    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        return InsurancePolicy.filterByCarModel(policies, carModel);
    }

    // Filter the policies by a certain expiry date.
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        return InsurancePolicy.filterByExpiryDate(policies, date);
    }

    public HashMap<Integer, InsurancePolicy> filterByCarModelHashMap(String carModel) {
        return InsurancePolicy.filterByCarModelHashMap(policies, carModel);
    }

    // Filter the policies by a certain expiry date.
    public HashMap<Integer, InsurancePolicy> filterByExpiryDateHashMap(MyDate date) {
        return InsurancePolicy.filterByExpiryDateHashMap(policies, date);
    }

    // Sort policies with compareTo
    public ArrayList<InsurancePolicy> sortPoliciesByDate() throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> sorted = deepCopyPolicies();
        Collections.sort(sorted);
        return sorted;
    }
    
    // Populate distinct car models
    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> carModels = new ArrayList<String>();

        for (InsurancePolicy policy : policies.values()) {
            if (!carModels.contains(policy.getCar().getModel())) {
                carModels.add(policy.getCar().getModel());
            }
        } 

        return carModels;
    }

    // Get total count for car model
    public int getTotalCountForCarModel(String carModel) {
        int carModels = 0;

        for (InsurancePolicy policy : policies.values()) {
            if (policy.getCar().getModel().equals(carModel)) {
                carModels++;
            }
        } 

        return carModels;
    }

    // Get total payment for car model
    public double getTotalPaymentForCarModel(String carModel) {
        double totalPrice = 0;

        for (InsurancePolicy policy : policies.values()) {
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
    public String reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiumPayments) {
        String value = String.format("%s\t%s\t%s\n", "Car Model", "Total Premium Payment", "Average Premium Payment");

        for (int i = 0; i < carModels.size(); i++) {
            value +=String.format("%-9s\t$%-20.2f\t$%.2f\n", carModels.get(i), premiumPayments.get(i), premiumPayments.get(i) / counts.get(i));
        }

        return value;
    }

    // Report car payments
    public String reportPaymentsPerCarModel(HashMap<String, Integer> count, HashMap<String, Double> premiumPayments) {
        String value = String.format("%s\t%s\t%s\n", "Car Model", "Total Premium Payment", "Average Premium Payment");

        for (String carModel : count.keySet()) {
            value += String.format("%-9s\t$%-20.2f\t$%.2f\n", carModel, premiumPayments.get(carModel), premiumPayments.get(carModel) / count.get(carModel));
        }

        return value;
    }

    // Shallow copy policies
    public ArrayList<InsurancePolicy> shallowCopyPolicies() {
        ArrayList<InsurancePolicy> copy = new ArrayList<InsurancePolicy>();

        for (InsurancePolicy policy : policies.values()) {
            copy.add(policy);
        }

        return copy;
    } 

    public HashMap<Integer, InsurancePolicy> shallowCopyPoliciesHashMap() {
        HashMap<Integer, InsurancePolicy> copy = new HashMap<Integer, InsurancePolicy>();

        for (int policyID : policies.keySet()) {
            copy.put(policyID, policies.get(policyID));
        }

        return copy;
    } 

    // Deep copy user's policies.
    public ArrayList<InsurancePolicy> deepCopyPolicies() throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> copy = new ArrayList<InsurancePolicy>();

        for (InsurancePolicy policy : policies.values()) {
            copy.add(policy.clone());
        }

        return copy;
    } 

    public HashMap<Integer, InsurancePolicy> deepCopyPoliciesHashMap() throws CloneNotSupportedException {
        HashMap<Integer, InsurancePolicy> copy = new HashMap<Integer, InsurancePolicy>();

        for (int policyID : policies.keySet()) {
            copy.put(policyID, policies.get(policyID).clone());
        }

        return copy;
    } 

    // Static shallowCopy for a list of users
    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
        return (ArrayList<User>) users.stream().collect(Collectors.toList());
    }

    // Static shallowCopy for a list of users
    public static ArrayList<User> shallowCopy(HashMap<Integer, User> users) {
        return (ArrayList<User>) users.values().stream().collect(Collectors.toList());
    }

    // Static shallowCopy for a list of users
    public static HashMap<Integer, User> shallowCopyHashMap(HashMap<Integer, User> users) {
        HashMap<Integer, User> copy = new HashMap<Integer, User>();

        for (int userID : users.keySet()) {
            copy.put(userID, users.get(userID));
        }

        return copy;
    }

    // Static deepCopy for a list of users.
    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
        return (ArrayList<User>) users.stream().map(x -> {
            try {
                return x.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }).collect(Collectors.toList());
    }

    // Static deepCopy for a list of users.
    public static ArrayList<User> deepCopy(HashMap<Integer, User> users) throws CloneNotSupportedException {
        ArrayList<User> copy = new ArrayList<User>();

        for (User user : users.values()) {
            copy.add(user.clone());
        }

        return copy;
    }

    // Static deepCopy for a list of users.
    public static HashMap<Integer, User> deepCopyHashMap(HashMap<Integer, User> users) throws CloneNotSupportedException {
        HashMap<Integer, User> copy = new HashMap<Integer, User>();

        for (Integer id : users.keySet()) {
            copy.put(id, users.get(id).clone());
        }

        return copy;
    }

    public HashMap<String, Integer> getTotalCountPerCarModel() {
        HashMap<String, Integer> carModelCount = new HashMap<String, Integer>();

        for (int policyID : policies.keySet()) { 
            InsurancePolicy policy = policies.get(policyID);
            String carModel = policy.getCar().getModel(); 

            if (carModelCount.containsKey(carModel)) {
                carModelCount.replace(carModel, carModelCount.get(carModel) + 1);
            } else {
                carModelCount.put(carModel, 1);
            }
        }

        return carModelCount;
    }

     public static boolean saveTextFile(HashMap<Integer, User> users, String fileName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        boolean passed = false;

        for (User user : users.values()) {
            out.write(user.toDelimitedString() + "\n");
        }

        try {
            out.close();
            passed = true;
        } catch(IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }
        
        return passed;
    }

    public static User loadUserFromTextFile(String[] field) throws PolicyException {
        String userName = field[1];
        int userID = Integer.parseInt(field[2]);

        String[] addressField = field[3].split(",");
        int streetNum = Integer.parseInt(addressField[0]);
        String street = addressField[1];
        String suburb = addressField[2];
        String city = addressField[3];

        Address address = new Address(streetNum, street, suburb, city);

        HashMap<Integer, InsurancePolicy> policies = new HashMap<Integer, InsurancePolicy>();

        for (int i = 5; i < field.length; i++) {
            String[] policyField = field[i].split(",");

            policies.put(Integer.parseInt(policyField[2]), InsurancePolicy.loadInsurancePolicyFromTextFile(policyField));
        }

        User user = new User(userName, address);
        user.userID = userID;
        user.policies = policies;
        
        return user;
    }

    public static HashMap<Integer, User> loadTextFile(String fileName) throws IOException, PolicyException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        HashMap<Integer, User> users = new HashMap<Integer, User>();
        String line = in.readLine();

        
        while (line != null) {
            line = line.trim();
            String[] field = line.split("~");

            users.put(Integer.parseInt(field[2]), loadUserFromTextFile(field));
            
            line = in.readLine();
        }

        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }

        return users;
    }

    public HashMap<String, Double> getTotalPremiumPerCarModel(int flatRate) {
        HashMap<String, Double> premiums = new HashMap<String, Double>();

        for (int policyID : policies.keySet()) {
            InsurancePolicy policy = policies.get(policyID);
            String carModel = policy.getCar().getModel();

            if (premiums.containsKey(carModel)) {
                premiums.replace(carModel, premiums.get(carModel) + policy.calcPayment(flatRate));
            } else {
                premiums.put(carModel, policy.calcPayment(flatRate));
            }
        }

        return premiums;
    }

    // printPolicies method
    public void printPolicies(int flatRate) {
        InsurancePolicy.printPoliciesAndPremium(policies, flatRate);
    }

    // Print method
    public void print() {
        System.out.printf("UserID: %d\nName: %s\nAddress: %s\nPolicies: \n\n", userID, name, address);
        InsurancePolicy.printPolicies(policies);
    }

    // toString method
    @Override
    public String toString() {
        String policiesString = "";
        for (InsurancePolicy policy : policies.values()) {
            policiesString += String.format("%s\n", policy);
        }
        return String.format("UserID: %d\nName: %s\nAddress: %s\nPolicies: \n\n", userID, name, address) + policiesString;
    }

    public String toDelimitedString() {
        String delimitedString = String.format("User~%s~%d~%s~Policies~", name, userID, address.toDelimitedString());

        for (InsurancePolicy policy : policies.values()) {
            delimitedString += policy.toDelimitedString() + "~";
        }

        return delimitedString;
    }

    // compareTo method
    @Override
    public int compareTo(User user) {
        return address.getCity().compareTo(user.address.getCity());
    }

    /* 
    // Secondary compareTo method
    public int compareTo1(User user) {
        return (int) (calcTotalPremiums(0)) - (int) (user.calcTotalPremiums(userCount));
    }
    */

    // Clone method
    @Override 
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
