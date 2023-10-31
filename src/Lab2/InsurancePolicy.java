package Lab1;

public abstract class InsurancePolicy {
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    public String toString() {
        return String.format("ID: %d\nHolder: %s\n%s\nNumber of claims: %d", this.id, this.policyHolderName, this.car, this.numberOfClaims);
    }

    public abstract double calcPayment(double flatRate);

    public void print() {
        System.out.printf("ID: %d\nHolder: %s\n", this.id, this.policyHolderName);
        this.car.print();
        System.out.printf("\nNumber of claims: %d", this.numberOfClaims);
    }
}
