package Lab4;
import java.util.ArrayList;

// Ethan Dakin
// 8209194

public class InsuranceCompany implements Cloneable {
    // Attributes
    public String name;
    private ArrayList<User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    // Constructor
    public InsuranceCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }

    public InsuranceCompany(InsuranceCompany company) {
        this.name = company.name;
        this.users = company.users;
        this.adminUsername = company.adminUsername;
        this.adminPassword = company.adminPassword;
        this.flatRate = company.flatRate;
    }

    @Override
    public InsuranceCompany clone() throws CloneNotSupportedException {
        return (InsuranceCompany) super.clone();
    }

    // Accessors
    public String getName() {
        return name;
    }

    protected ArrayList<User> getUsers() {
        return users;
    }

    protected String getAdminUsername() {
        return adminUsername;
    }

    protected String getAdminPassword() {
        return adminPassword;
    }

    public int getFlatRate() {
        return flatRate;
    }

    // Mutators
    protected void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    
    // Validate admin function, checks if given username and password match the company username/password.
    public boolean validateAdmin(String username, String password) {
        // If username and password are equal then return true, else return false.
        if (username.equals(getAdminUsername()) && password.equals(getAdminPassword())) {
            return true;
        } else {
            return false;
        }
    }

    // Find user method, loops through all users and returns one with a given id, or null.
    public User findUser(int userID) {
        User foundUser = null;

        for (User user : getUsers()) {
            if (user.getUserID() == userID) {
                foundUser = user;
            }
        }

        return foundUser;
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
            getUsers().add(user);
            return true;
        } else {
            return false;
        }
    }

    // Create and add a user to the company if the user id is available.
    public boolean addUser(String name, Address address) {
        if (findUser(User.userCount + 1) == null) {
            getUsers().add(new User(name, address));
            return true;
        } else {
            return false;
        }
    }

    public boolean removeUser(int userID) {
        if (findUser(userID) != null) {
            getUsers().remove(findUser(userID));
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
    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        if (findUser(userID) != null && findUser(userID).createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments) == true) {
            return true;
        } else {
            return false;
        }
    }

    // Creates and adds a comprehensive policy for a given user, if all conditions are met.
    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
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
        double result = 0.0;

        for (User user : getUsers()) {
            result += user.calcTotalPremiums(flatRate);
        }

        return result;
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
        for (User user : getUsers()) {
            user.carPriceRiseAll(risePercent);
        }
    }

    // Returns an array list of all policies in the company.
    public ArrayList<InsurancePolicy> allPolicies() {
        ArrayList<InsurancePolicy> policies = new ArrayList<InsurancePolicy>();

        for (User user : getUsers()) {
            for (InsurancePolicy policy : user.getPolicies()) {
                policies.add(policy);
            }
        }

        return policies;
    }

    public ArrayList<String> populateDistinctCityNames() {
        ArrayList<String> cities = new ArrayList<String>();

        for (User user : getUsers()) {
            if (!cities.contains(user.getAddress().getCity())) {
                cities.add(user.getAddress().getCity());
            }
        } 

        return cities;
    }

    public double getTotalPaymentForCity(String city) {
        double price = 0.0;

        for (User user : getUsers()) {
            if (user.getAddress().getCity().equals(city)) {
                price += user.calcTotalPremiums(flatRate);
            }
        }

        return price;
    }

    public ArrayList<Double> getTotalPaymentPerCity(ArrayList<String> cities) {
        ArrayList<Double> prices = new ArrayList<Double>();

        for (String city : cities) {
            prices.add(getTotalPaymentForCity(city));
        }

        return prices;
    }

    public void reportPaymentPerCity(ArrayList<String> cities, ArrayList<Double> payments) {
        System.out.printf("%s\t%s\n", "City Name", "Total Premium Payment");
        for (int i = 0; i < cities.size(); i++) {
            System.out.printf("%-9s\t$%.2f\n", cities.get(i), payments.get(i));
        }
    }


    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> carModels = new ArrayList<String>();

        for (User user : getUsers()) {
            ArrayList<String> userCarModels = user.populateDistinctCarModels();

            for (String model : userCarModels) {
                if (!carModels.contains(model)) {
                    carModels.add(model);
                }
            }
        }

        return carModels;
    }

    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> count = new ArrayList<Integer>();

        for (String model : carModels) {
            int value = 0;
            for (User user : users) {
                value += user.getTotalCountForCarModel(model);
            }
            count.add(value);
        }

        return count;
    }

    public ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> carModels) {
        ArrayList<Double> payments = new ArrayList<Double>();

        for (String model : carModels) {
            double payment = 0;
            for (User user : users) {
                payment += user.getTotalPaymentForCarModel(model);
            }

            payments.add(payment);

        }

        return payments;
    }

    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiumPayments) {
        System.out.printf("%s\t%s\t%s\n", "Car Model", "Total Premium Payment", "Average Premium Payment");

        for (int i = 0; i < carModels.size(); i++) {
            System.out.printf("%-9s\t$%-20.2f\t$%.2f\n", carModels.get(i), premiumPayments.get(i), premiumPayments.get(i) / counts.get(i));
        }
    }

    // Filters by car model for a specific user.
    public ArrayList<InsurancePolicy> filterByCarModel(int userID, String carModel) {
        return findUser(userID).filterByCarModel(carModel);
    }

    // Filters by expiry date for a specific user.
    public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        return findUser(userID).filterByExpiryDate(date);
    }

    // Filters by car model for all users.
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        ArrayList<InsurancePolicy> policies = new ArrayList<InsurancePolicy>();

        for (User user : getUsers()) {
            policies.addAll(user.filterByCarModel(carModel));
        }

        return policies;
    }

    // Filters by expiry date for all users.
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        ArrayList<InsurancePolicy> policies = new ArrayList<InsurancePolicy>();

        for (User user : getUsers()) {
            policies.addAll(user.filterByExpiryDate(date));
        }

        return policies;
    }

    // Prints user information and all policies for a given user.
    public void printPolicies(int userID) {
        User user = findUser(userID);
        user.print();
        user.printPolicies(flatRate);
    }

    public ArrayList<User> shallowCopyUsers() {
        return User.shallowCopy(getUsers());
    } 

    public ArrayList<User> deepCopyUsers() throws CloneNotSupportedException {
        return User.deepCopy(getUsers());
    } 

    // Prints all users and policies
    public void print() {
        //System.out.print(String.format("Company name: %s\nFlat rate: %d\nAdmin Username: %s\nAdmin Password: %s\nUsers: \n\n", getName(), getFlatRate(), getAdminUsername(), getAdminPassword()));

        for (User user : getUsers()) {
            user.print();
        }
    }

    // toString method, prints the company information and all information about the users
    @Override
    public String toString() {
        String value = String.format("Company name: %s\nFlat rate: %d\nAdmin Username: %s\nAdmin Password: %s\nUsers: \n\n", getName(), getFlatRate(), getAdminUsername(), getAdminPassword());

        for (User user : getUsers()) {
            value += String.format("%s\n", user);
        }

         return value;
    }
}
