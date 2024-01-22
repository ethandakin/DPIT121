package Lab7;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// Ethan Dakin
// 8209194

public class InsuranceCompany implements Cloneable, Serializable {
    // Attributes
    private String name;
    private HashMap<Integer, User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    // Constructor
    public InsuranceCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        this.users = new HashMap<Integer, User>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }

    // Copy constructor
    public InsuranceCompany(InsuranceCompany company) {
        this.name = company.name;
        this.users = company.users;
        this.adminUsername = company.adminUsername;
        this.adminPassword = company.adminPassword;
        this.flatRate = company.flatRate;
    }


    public InsuranceCompany() {

    }
    

    // Accessors
    public int getFlatRate() {
        return flatRate;
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    // Mutators
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    
    // Validate admin function, checks if given username and password match the company username/password.
    public boolean validateAdmin(String username, String password) {
        // If username and password are equal then return true, else return false.
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            return true;
        } else {
            return false;
        }
    }


    public User validateUser(String username, String password) {
        for (User user : users.values()) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    // Find user method, returns from the get method on the hashmap.
    public User findUser(int userID) {
        return users.get(userID);
    }

    // Find policy method, finds and returns a policy if user and policy id are found, else returns null.
    public InsurancePolicy findPolicy(int userID, int policyID) {
        if (findUser(userID) != null && findUser(userID).findPolicy(policyID) != null) {
            return findUser(userID).findPolicy(policyID);
        } else {
            return null;
        }
    }

    // Adds a user to the company, if the user id is not taken.
    public boolean addUser(User user) {
        if (findUser(user.getUserID()) == null) {
            users.put(user.getUserID(), user);
            return true;
        } else {
            return false;
        }
    }

    // Create and add a user to the company if the user id is available.
    public boolean addUser(String name, String password, Address address) {
        if (findUser(User.getUserCount() + 1) == null) {
            users.put(User.getUserCount() + 1, new User(name, password, address));
            return true;
        } else {
            return false;
        }
    }

    public boolean removeUser(int userID) {
        if (findUser(userID) != null) {
            users.remove(userID);
            return true;
        } else {
            return false;
        }
    }

    // Adds a policy to a given user, if user and policy are valid.
    public boolean addPolicy(int userID, InsurancePolicy policy) {
        if (findUser(userID) != null && findUser(userID).findPolicy(policy.getID()) == null) {
            findUser(userID).addPolicy(policy);
            return true;
        } else {
            return false;
        }
    }

    public boolean removePolicy(int userID, int policyID) {
        if (findUser(userID) != null && findUser(userID).findPolicy(policyID) != null) {
            findUser(userID).removePolicy(policyID);
            return true;
        } else {
            return false;
        }
    }

    // Creates and adds a third party policy for a given user, if all conditions are met.
    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException {
        if (findUser(userID) != null && findUser(userID).createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments) == true) {
            return true;
        } else {
            return false;
        }
    }

    // Creates and adds a comprehensive policy for a given user, if all conditions are met.
    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException {
        if (findUser(userID) != null && findUser(userID).createComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level) == true) {
            return true;
        } else {
            return false;
        }
    }

    // Calculates the total payments for a given user.
    public double calcTotalPayments(int userID) {
        return findUser(userID).calcTotalPremiums(flatRate);
    }
    
    // Calculates the total payments for all users.
    public double calcTotalPayments() {
        return users.values().stream().mapToDouble(x -> x.calcTotalPremiums(flatRate)).sum();
    }

    // Rises all car prices for a given user.
    public boolean carPriceRise(int userID, double risePercent) {
        if (findUser(userID) != null) {
            findUser(userID).carPriceRiseAll(risePercent);
            return true;
        } else {
            return false;
        }
    }

    // Rises all car prices for all users.
    public void carPriceRise(double risePercent) {
        users.values().forEach(x -> x.carPriceRiseAll(risePercent));
    }

    // Returns an array list of all policies in the company.
    public ArrayList<InsurancePolicy> allPolicies() {
        ArrayList<InsurancePolicy> policies = new ArrayList<InsurancePolicy>();

        for (User user : users.values()) {
            for (InsurancePolicy policy : user.getPolicies().values()) {
                policies.add(policy);
            }
        }

        return policies;
    }

    public HashMap<Integer, InsurancePolicy> allPoliciesHashMap() {
        HashMap<Integer, InsurancePolicy> policies = new HashMap<Integer, InsurancePolicy>();

        for (int userID : users.keySet()) {
            User user = users.get(userID);
            for (int policyID : user.getPolicies().keySet()) {
                policies.put(policyID, user.getPolicies().get(policyID));
            }
        }

        return policies;
    }

    // Populate distinct city names
    public ArrayList<String> populateDistinctCityNames() {
        ArrayList<String> cities = new ArrayList<String>();

        for (User user : users.values()) {
            if (!cities.contains(user.getAddress().getCity())) {
                cities.add(user.getAddress().getCity());
            }
        } 

        return cities;
    }

    // Sort the user with collections (uses compareTo)
    public ArrayList<User> sortUsers() {
        ArrayList<User> users = new ArrayList<User>();

        for (User user : this.users.values()) {
            users.add(user);
        }

        Collections.sort(users);
        return users;
    }

    // Get total payment for city
    public double getTotalPaymentForCity(String city) {
        double price = 0.0;

        for (User user : users.values()) {
            if (user.getAddress().getCity().equals(city)) {
                price += user.calcTotalPremiums(flatRate);
            }
        }

        return price;
    }

    // Get total payment per city
    public ArrayList<Double> getTotalPaymentPerCity(ArrayList<String> cities) {
        ArrayList<Double> prices = new ArrayList<Double>();

        for (String city : cities) {
            prices.add(getTotalPaymentForCity(city));
        }

        return prices;
    }

    // Report payment per city
    public void reportPaymentPerCity(ArrayList<String> cities, ArrayList<Double> payments) {
        System.out.printf("%s\t%s\n", "City Name", "Total Premium Payment");
        for (int i = 0; i < cities.size(); i++) {
            System.out.printf("%-9s\t$%.2f\n", cities.get(i), payments.get(i));
        }
    }

    public String reportPaymentPerCity(HashMap<String, Double> cities) {
        String value = String.format("%s\t%s\n", "City Name", "Total Premium Payment");
        for (String city : cities.keySet()) {
            value += String.format("%-9s\t$%.2f\n", city, cities.get(city));
        }

        return value;
    }

    // Populate distinct car models
    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> carModels = new ArrayList<String>();

        for (User user : users.values()) {
            ArrayList<String> userCarModels = user.populateDistinctCarModels();

            for (String model : userCarModels) {
                if (!carModels.contains(model)) {
                    carModels.add(model);
                }
            }
        }

        return carModels;
    }

    // Total count per car model
    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> count = new ArrayList<Integer>();

        for (String model : carModels) {
            int value = 0;
            for (User user : users.values()) {
                value += user.getTotalCountForCarModel(model);
            }
            count.add(value);
        }

        return count;
    }

    public boolean load(String fileName) {
        ObjectInputStream inputStream = null;
        boolean passed = false;

        try {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.err.println("Error in creating/opening the file.");
            System.exit(1);
        }

        try {
            while (true) {
                InsuranceCompany company = (InsuranceCompany) inputStream.readObject();
                this.name = company.name;
                this.users = company.users;
                this.adminUsername = company.adminUsername;
                this.adminPassword = company.adminPassword;
                this.flatRate = company.flatRate;
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
                passed = true;
            }
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }

        return passed;
    }

    public boolean save(String fileName) {
        ObjectOutputStream outputStream = null;
        boolean passed = false;

        try {
            outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.err.println("Error in creating/opening the file.");
            System.exit(1);
        }

        try {
            outputStream.writeObject(this);
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

    public HashMap<String, Double> getTotalPremiumPerCity() {
        return (HashMap<String, Double>) users.values().stream()
        .collect(Collectors.groupingBy(
            x -> x.getAddress().getCity(),
            Collectors.summingDouble(x -> x.calcTotalPremiums(flatRate))
        ));
    }

    public HashMap<String, Double> getOldTotalPremiumPerCity() {
        HashMap<String, Double> cities = new HashMap<String, Double>();

        for (User user : users.values()) { 
            String city = user.getAddress().getCity();
            double premium = user.calcTotalPremiums(flatRate);

            if (cities.containsKey(city)) {
                cities.replace(city, cities.get(city) + premium);
            } else {
                cities.put(city, premium);
            }
        }

        return cities;
    }

    public HashMap<String, Integer> getTotalCountPerCarModel() {
        HashMap<String, Integer> carCount = new HashMap<String, Integer>();

        for (User user : users.values()) {
            HashMap<String, Integer> carModels = user.getTotalCountPerCarModel();

            for (String carModel : carModels.keySet()) {
                int count = carModels.get(carModel);

                if (carCount.containsKey(carModel)) {
                    carCount.replace(carModel, carCount.get(carModel) + count);
                } else {
                    carCount.put(carModel, count);
                }
            }
        }

        return carCount;
    }

    public HashMap<String, Double> getTotalPremiumPerCarModel() {
        HashMap<String, Double> premium = new HashMap<String, Double>();

        for (User user : users.values()) {
            HashMap<String, Double> premiums = user.getTotalPremiumPerCarModel(flatRate);

            for (String carModel : premiums.keySet()) {
                double payment = premiums.get(carModel);

                if (premium.containsKey(carModel)) {
                    premium.replace(carModel, premium.get(carModel) + payment);
                } else {
                    premium.put(carModel, payment);
                }
            }
        }

        return premium;
    }

    // Total payment per car model
    public ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> carModels) {
        ArrayList<Double> payments = new ArrayList<Double>();

        for (String model : carModels) {
            double payment = 0;
            for (User user : users.values()) {
                payment += user.getTotalPaymentForCarModel(model);
            }
            payments.add(payment);
        }

        return payments;
    }



    // Report method for car models
    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiumPayments) {
        System.out.printf("%s\t%s\t%s\n", "Car Model", "Total Premium Payment", "Average Premium Payment");

        for (int i = 0; i < carModels.size(); i++) {
            System.out.printf("%-9s\t$%-20.2f\t$%.2f\n", carModels.get(i), premiumPayments.get(i), premiumPayments.get(i) / counts.get(i));
        }
    }

    public String reportPaymentsPerCarModel(HashMap<String, Integer> count, HashMap<String, Double> premiumPayments) {
        String value = String.format("%s\t%s\t%s\n", "Car Model", "Total Premium Payment", "Average Premium Payment");

        for (String carModel : count.keySet()) {
            value += String.format("%-9s\t$%-20.2f\t$%.2f\n", carModel, premiumPayments.get(carModel), premiumPayments.get(carModel) / count.get(carModel));
        }

        return value;
    }

    // Filters by car model for a specific user.
    public ArrayList<InsurancePolicy> filterByCarModel(int userID, String carModel) {
        return findUser(userID).filterByCarModel(carModel);
    }

    // Filters by car model for a specific user.
    public HashMap<Integer, InsurancePolicy> filterByCarModelHashmap(int userID, String carModel) {
        return findUser(userID).filterByCarModelHashMap(carModel);
    }

    // Filters by expiry date for a specific user.
    public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        return findUser(userID).filterByExpiryDate(date);
    }

    // Filters by expiry date for a specific user.
    public HashMap<Integer, InsurancePolicy> filterByExpiryDateHashMap(int userID, MyDate date) {
        return findUser(userID).filterByExpiryDateHashMap(date);
    }

    // Filters by car model for all users.
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        return (ArrayList<InsurancePolicy>) users.values().stream()
        .flatMap(x -> x.filterByCarModel(carModel).stream())
        .collect(Collectors.toList());
    }

    public HashMap<Integer, InsurancePolicy> filterByCarModelHashMap(String carModel) {
        HashMap<Integer, InsurancePolicy> policies = new HashMap<Integer, InsurancePolicy>();

        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            policies.putAll(entry.getValue().filterByCarModelHashMap(carModel));
        }

        return policies;
    }

    // Filters by expiry date for all users.
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        ArrayList<InsurancePolicy> policies = new ArrayList<InsurancePolicy>();

        for (User user : users.values()) {
            policies.addAll(user.filterByExpiryDate(date));
        }

        return policies;
    }

    public HashMap<Integer, InsurancePolicy> filterByExpiryDateHashMap(MyDate date) {
        HashMap<Integer, InsurancePolicy> policies = new HashMap<Integer, InsurancePolicy>();

        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            policies.putAll(entry.getValue().filterByExpiryDateHashMap(date));
        }

        return policies;
    }

    // Prints user information and all policies for a given user.
    public void printPolicies(int userID) {
        User user = findUser(userID);
        user.print();
        user.printPolicies(flatRate);
    }

    // Shallow copy users
    public ArrayList<User> shallowCopyUsers() {
        return User.shallowCopy(users);
    } 

    public HashMap<Integer, User> shallowCopyHashMap() {
        return User.shallowCopyHashMap(users);
    }

    // Deep copy users
    public ArrayList<User> deepCopyUsers() throws CloneNotSupportedException {
        return User.deepCopy(users);
    } 

    public HashMap<Integer, User> deepCopyUsersHashMap() throws CloneNotSupportedException {
        return User.deepCopyHashMap(users);
    }

    // Prints all users and policies
    public void print() {
        for (User user : users.values()) {
            user.print();
        }
    }

    // toString method, prints the company information and all information about the users
    @Override
    public String toString() {
        String value = String.format("Company name: %s\nFlat rate: %d\nAdmin Username: %s\nAdmin Password: %s\nUsers: \n\n", name, flatRate, adminUsername, adminPassword);

        for (User user : users.values()) {
            value += String.format("%s\n", user);
        }

         return value;
    }

    // Clone method
    @Override
    public InsuranceCompany clone() throws CloneNotSupportedException {
        return (InsuranceCompany) super.clone();
    }

    public String toDelimitedString() {
        String delimitedString = String.format("%s#%s#%s#%d#Users#", name, adminUsername, adminPassword, flatRate);

        for (User user : users.values()) {
            delimitedString += user.toDelimitedString() + "#";
        }

        return delimitedString;
    }

    public boolean loadTextFile(String fileName) throws IOException, PolicyException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        boolean passed = false;
        String line = in.readLine();
        String[] field = line.split("#");

        name = field[0];
        adminUsername = field[1];
        adminPassword = field[2];
        flatRate = Integer.parseInt(field[3]);

        users = new HashMap<Integer, User>();

        for (int i = 5; i < field.length; i++) {
            String[] userField = field[i].split("~");

            users.put(Integer.parseInt(userField[2]), User.loadUserFromTextFile(userField));
        }

        try {
            in.close();
            passed = true;
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }

        return passed;
    }

    public boolean saveTextFile(String fileName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        boolean passed = false;

        out.write(toDelimitedString());

        try {
            out.close();
            passed = true;
        } catch(IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }
        
        return passed;
    }




}
