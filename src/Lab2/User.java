package Lab2;
import java.util.ArrayList;

public class User {
    private String name;
    private int userID;
    private Address address;
    ArrayList<InsurancePolicy> policies;

    public User(String name, int userID, Address address) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.policies = new ArrayList<InsurancePolicy>();
    }

    public boolean addPolicy(InsurancePolicy policy) {
        if (findPolicy(policy.id) == null) {
            this.policies.add(policy);
            return true;
        } else {
            return false;
        }
    }

    public InsurancePolicy findPolicy(int policyID) {
        for (InsurancePolicy policy: this.policies) {
            if (policy.id == policyID) {
                return policy;
            }
        }

        return null;
    }

    public void print() {
        System.out.printf("%s\n%d", this.name, this.userID);
    }

    public String toString() {
        return String.format("%s\n%d", this.name, this.userID);
    }

    public void printPolicies(int flatRate) {

    }

    public double calcTotalPremiums(int flatRate) {
        return InsurancePolicy.calcTotalPayments(this.policies, flatRate);
    }

    public void carPriceRiseAll(double risePercent) {
        InsurancePolicy.carPriceRiseAll(this.policies, risePercent);
    }

    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        return InsurancePolicy.filterByCarModel(this.policies, carModel);
    }
}
