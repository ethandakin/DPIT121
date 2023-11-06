package Lab3;
import java.util.ArrayList;

public class InsuranceCompany {
    String name;
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

    private String getAdminUsername() {
        return adminUsername;
    }

    private String getAdminPassword() {
        return adminPassword;
    }

    public boolean validateAdmin(String username, String password) {
        if (getAdminUsername().equals(username) && getAdminPassword().equals(password)) {
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
        if (findUser(userID) != null || findUser(userID).findPolicy(policy.getID()) == null) {
            findUser(userID).addPolicy(policy);
            return true;
        } else {
            return false;
        }
    }

    public InsurancePolicy findPolicy(int userID, int policyID) {
        if (findUser(userID) != null || findUser(userID).findPolicy(policyID) != null) {
            return findUser(userID).findPolicy(policyID);
        }

        return null;
    }

    public void printPolicies(int userID) {
        findUser(userID).printPolicies(flatRate);
    } 

    public void print() {
        for (User user : users) {
            user.printPolicies(flatRate);
            System.out.println();
        }
    }

    public String toString() {
        String usersString = "";

        for (User user : users) {
            usersString += String.format("%s\n", user);
        }

        return String.format("Name: %s\nFlat-Rate: %d\nUsers: \n\n", name, flatRate) + usersString;
    }


    
}
