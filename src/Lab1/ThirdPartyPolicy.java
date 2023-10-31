package Lab1;

public class ThirdPartyPolicy extends InsurancePolicy{
    protected String comments;

    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, String comments) {
        super(policyHolderName, id, car, numberOfClaims);
        this.comments = comments;
    }

    @Override
    public double calcPayment(double flatRate) {
        return super.car.price / 100 + numberOfClaims * 200 + flatRate;
    }

    @Override
    public String toString() {
        return String.format("%s\nComments: %s", super.toString(), this.comments);
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("\nComments: %s", this.comments);
    }
}
