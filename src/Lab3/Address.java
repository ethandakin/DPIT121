package Lab3;

// Ethan Dakin
// 8209194

public class Address {
    // Protected attributes for address
    protected int streetNum;
    protected String street;
    protected String suburb;
    protected String city;

    // Constructor
    public Address(int streetNum, String street, String suburb, String city) {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }

    // Accessors
    public int getStreetNum() {
        return streetNum;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    // Mutators
    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%d %s, %s, %s", getStreetNum(), getStreet(), getSuburb(), getCity());
    }

    // Print method
    public void print() {
        System.out.printf("%d %s, %s, %s", getStreetNum(), getStreet(), getSuburb(), getCity());
    }
}
