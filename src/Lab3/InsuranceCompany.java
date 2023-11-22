package Lab3;
import java.util.ArrayList;

public class InsuranceCompany {
    public String name;
    private ArrayList<User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    public InsuranceCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }

    public String getName() {
        return name;
    }

    public int getFlatRate() {
        return flatRate;
    }

    protected String getAdminUsername() {
        return adminUsername;
    }

    protected String getAdminPassword() {
        return adminPassword;
    }

    public boolean validateAdmin(String username, String password) {
        if (username.equals(getAdminUsername()) && password.equals(getAdminPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public User findUser(int userID) {
        for (User user: users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }

        return null;
    }

    public InsurancePolicy findPolicy(int userID, int policyID) {
        if (findUser(userID) != null && findUser(userID).findPolicy(policyID) != null) {
            return findUser(userID).findPolicy(policyID);
        } else {
            return null;
        }
    }

    public boolean addUser(User user) {
        if (findUser(user.getUserID()) == null) {
            users.add(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean addUser(String name, int userID, Address address) {
        if (findUser(userID) == null) {
            users.add(new User(name, userID, address));
            return true;
        } else {
            return false;
        }
    }

    public boolean addPolicy(int userID, InsurancePolicy policy) {
        if (findUser(userID) == null && findUser(userID).findPolicy(policy.getID()) == null) {
            findUser(userID).addPolicy(policy);
            return true;
        } else {
            return false;
        }
    }

    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        if (findUser(userID) != null && findUser(userID).createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments) == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
        if (findUser(userID) != null && findUser(userID).createComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level) == true) {
            return true;
        } else {
            return false;
        }
    }

    public double calcTotalPayments(int userID) {
        return findUser(userID).calcTotalPremiums(flatRate);
    }
    
    public double calcTotalPayments() {
        double result = 0.0;

        for (User user : users) {
            result += user.calcTotalPremiums(flatRate);
        }

        return result;
    }

    public boolean carPriceRise(int userID, double risePercent) {
        if (findUser(userID) != null) {
            findUser(userID).carPriceRiseAll(risePercent);
            return true;
        } else {
            return false;
        }
    }

    public void carPriceRise(double risePercent) {
        for (User user : users) {
            user.carPriceRiseAll(risePercent);
        }
    }

    public ArrayList<InsurancePolicy> allPolicies() {
        ArrayList<InsurancePolicy> policies = new ArrayList<InsurancePolicy>();

        for (User user : users) {
            for (InsurancePolicy policy : user.policies) {
                policies.add(policy);
            }
        }

        return policies;
    }

    public ArrayList<InsurancePolicy> filterByCarModel(int userID, String carModel) {
        return findUser(userID).filterByCarModel(carModel);
    }

    public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        return findUser(userID).filterByExpiryDate(date);
    }

    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        ArrayList<InsurancePolicy> policies = new ArrayList<InsurancePolicy>();

        for (User user : users) {
            policies.addAll(user.filterByCarModel(carModel));
        }

        return policies;
    }

    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        ArrayList<InsurancePolicy> policies = new ArrayList<InsurancePolicy>();

        for (User user : users) {
            policies.addAll(user.filterByExpiryDate(date));
        }

        return policies;
    }

    public void printPolicies(int userID) {
        
    }

    public void print() {

    }

    public String toString() {


        String value = String.format("Company name: %s\nFlat rate: %d\nAdmin Username: %s\nAdmin Password: %s\nUsers: \n\n", getName(), getFlatRate(), getAdminUsername(), getAdminPassword());

        for (User user : users) {
            value += String.format("%s\n", user);
        }

         return value;
    }
}
