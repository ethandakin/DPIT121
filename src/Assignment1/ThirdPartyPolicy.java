package Assignment1;

// Ethan Dakin
// 8209194

public class ThirdPartyPolicy extends InsurancePolicy{
    // Attributes
    protected String comments;

    // Constructor
    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }

    // Accessors
    public String getComments() {
        return comments;
    }

    // Mutators
    public void setComments(String comments) {
        this.comments = comments;
    }

    // calcPayment method, returns the premium payment for this class
    @Override
    public double calcPayment(double flatRate) {
        return super.getCar().getPrice() / 100 + numberOfClaims * 200 + flatRate;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%s\nComments: %s\n", super.toString(), getComments());
    }

    // Print method
    @Override
    public void print() {
        super.print();
        System.out.printf("\nComments: %s\n", getComments());
    }
}
