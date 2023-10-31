package Lab1;

public class ComprehensivePolicy extends InsurancePolicy{
    protected int driverAge;
    protected int level;
    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level) {
        super(policyHolderName, id, car, numberOfClaims);
        this.driverAge = driverAge;
        this.level = level;
    }

    @Override
    public double calcPayment(double flatRate) {
        if (this.driverAge < 30) {
            flatRate += ((30 - driverAge) * 50);
        }

        return super.car.price / 50 + numberOfClaims * 200 + flatRate;
    }

    @Override
    public String toString() {
        return String.format("%s\nDriver Age: %d\nLevel: %d", super.toString(), this.driverAge, this.level);
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("\nDriver Age: %d\nLevel: %d", this.driverAge, this.level);
    }
}
