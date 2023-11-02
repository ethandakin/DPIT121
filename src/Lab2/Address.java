package Lab2;

public class Address {
    protected int streetNum;
    protected String street;
    protected String suburb;
    protected String city;

    public Address(int streetNum, String street, String suburb, String city) {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }

    // Mutators
    public int getStreetNum() {
        return this.streetNum;
    }

    public String getStreet() {
        return this.street;
    }

    public String getSuburb() {
        return this.suburb;
    }

    public String getCity() {
        return this.city;
    }

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
}
