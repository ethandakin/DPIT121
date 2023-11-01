package Lab1;

// Ethan Dakin
// 8209194

public abstract class InsurancePolicy {
    // Default attributes
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;

    // Constructor
    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    // Return formatted attributes
    public String toString() {
        return String.format("ID: %d\nHolder: %s\n%s\nNumber of claims: %d", this.id, this.policyHolderName, this.car, this.numberOfClaims);
    }

    // Abstract method for subclasses
    public abstract double calcPayment(double flatRate);

    // Print out formatted values, with car print in the middle.
    public void print() {
        System.out.printf("ID: %d\nHolder: %s\n", this.id, this.policyHolderName);
        this.car.print();
        System.out.printf("\nNumber of claims: %d", this.numberOfClaims);
    }
}
