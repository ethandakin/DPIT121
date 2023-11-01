package Lab2;

// Ethan Dakin
// 8209194

public class ComprehensivePolicy extends InsurancePolicy{
    // Default attribute
    protected int driverAge;
    protected int level;

    // Constructor
    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level) {
        super(policyHolderName, id, car, numberOfClaims);
        this.driverAge = driverAge;
        this.level = level;
    }

    // Override calcPayment method
    @Override
    public double calcPayment(double flatRate) {
        // Add extra cost if driver is younger than 30
        if (this.driverAge < 30) {
            flatRate += ((30 - driverAge) * 50);
        }

        return super.car.price / 50 + numberOfClaims * 200 + flatRate;
    }

    // String format
    @Override
    public String toString() {
        return String.format("%s\nDriver Age: %d\nLevel: %d", super.toString(), this.driverAge, this.level);
    }

    // Print method
    @Override
    public void print() {
        super.print();
        System.out.printf("\nDriver Age: %d\nLevel: %d", this.driverAge, this.level);
    }
}
