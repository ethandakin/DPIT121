package Lab6;
import java.io.Serializable;

// Ethan Dakin
// 8209194

public class Address implements Cloneable, Serializable {
    // ATTRIBUTES //
    private int streetNum;
    private String street;
    private String suburb;
    private String city;

    // CONSTRUCTOR //
    public Address(int streetNum, String street, String suburb, String city) {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }
    
    // COPY CONSTRUCTOR //
    public Address(Address address) {
        this.streetNum = address.streetNum;
        this.street = address.street;
        this.suburb = address.suburb;
        this.city = address.city;
    }

    // ACCESSORS //
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

    // MUTATORS //
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

    // METHODS //
    // toString method
    @Override
    public String toString() {
        return String.format("%d %s, %s, %s", streetNum, street, suburb, city);
    }

    public String toDelimitedString() {
        return String.format("Address,%d,%s,%s,%s", streetNum, street, suburb, city);
    }

    // Print method
    public void print() {
        System.out.printf("%d %s, %s, %s", street, street, suburb, city);
    }

    // Clone method
    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}
