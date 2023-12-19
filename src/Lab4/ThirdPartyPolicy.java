package Lab4;

// Ethan Dakin
// 8209194

public class ThirdPartyPolicy extends InsurancePolicy{
    // Attributes
    private String comments;

    // Constructor
    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }

    // Copy constructor
    public ThirdPartyPolicy(ThirdPartyPolicy policy) {
        super(policy);
        this.comments = policy.comments;
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
        return car.getPrice() / 100 + numberOfClaims * 200 + flatRate;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%s\nComments: %s\n", super.toString(), comments);
    }

    // Print method
    @Override
    public void print() {
        super.print();
        System.out.printf("\nComments: %s\n", comments);
    }
}
