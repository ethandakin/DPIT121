package Lab4;

// Ethan Dakin
// 8209194

public class ComprehensivePolicy extends InsurancePolicy{
    // Default attributes
    protected int driverAge;
    protected int level;

    // Constructor
    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.driverAge = driverAge;
        this.level = level;
    }
    
    // Copy constructor
    public ComprehensivePolicy(ComprehensivePolicy policy) {
        super(policy.policyHolderName, policy.id, policy.car, policy.numberOfClaims, policy.expiryDate);
        this.driverAge = policy.driverAge;
        this.level = policy.level;
    }

    // Override calcPayment method
    @Override
    public double calcPayment(double flatRate) {
        // Add extra cost if driver is younger than 30
        if (this.driverAge < 30) {
            flatRate += ((30 - driverAge) * 50);
        }

        return super.getCar().getPrice() / 50 + numberOfClaims * 200 + flatRate;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%s\nDriver Age: %d\nLevel: %d\n", super.toString(), this.driverAge, this.level);
    }

    // Print method
    @Override
    public void print() {
        super.print();
        System.out.printf("\nDriver Age: %d\nLevel: %d\n", this.driverAge, this.level);
    }
}
