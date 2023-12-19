package Lab5;

// Ethan Dakin
// 8209194

public class ThirdPartyPolicy extends InsurancePolicy{
    // ATTRIBUTES //
    private String comments;

    // CONSTRUCTOR //
    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }

    // COPY CONSTRUCTOR //
    public ThirdPartyPolicy(ThirdPartyPolicy policy) {
        super(policy);
        this.comments = policy.comments;
    }

    // ACCESSORS //
    public String getComments() {
        return comments;
    }

    // MUTATORS //
    public void setComments(String comments) {
        this.comments = comments;
    }

    // METHODS //
    // calcPayment method
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
