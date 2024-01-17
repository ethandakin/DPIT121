package Lab8;

// Ethan Dakin
// 8209194

public class ComprehensivePolicy extends InsurancePolicy {
    // ATTRIBUTES //
    private int driverAge;
    private int level;

    // CONSTRUCTOR //
    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.driverAge = driverAge;
        this.level = level;
    }

    // COPY CONSTRUCTOR //
    public ComprehensivePolicy(ComprehensivePolicy policy) {
        super(policy);
        this.driverAge = policy.driverAge;
        this.level = policy.level;
    }

    // ACCESSORS //
    public int getDriverAge() {
        return driverAge;
    }

    public int getLevel() {
        return level;
    }

    // MUTATORS //
    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // METHODS //
    // calcPayment method
    @Override
    public double calcPayment(double flatRate) {
        if (driverAge < 30) {
            flatRate += ((30 - driverAge) * 50);
        }

        return car.getPrice() / 50 + numberOfClaims * 200 + flatRate;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%s\nDriver Age: %d\nLevel: %d\n", super.toString(), driverAge, level);
    }

    @Override
    public String toDelimitedString() {
        return String.format("ComprehensivePolicy,%s,%d,%d", super.toDelimitedString(), driverAge, level);
    }

    // Print method
    @Override
    public void print() {
        super.print();
        System.out.printf("\nDriver Age: %d\nLevel: %d\n", driverAge, level);
    }
}
