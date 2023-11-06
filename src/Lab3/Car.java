package Lab3;

// Ethan Dakin
// 8209194

// CarTypes enum
enum CarType {SUV, SED, LUX, HATCH, etc}

public class Car {
    // Attributes
    protected String model;
    protected CarType type;
    protected int manufacturingYear;
    protected double price;

    // Constructor
    public Car(String model, CarType type, int manufacturingYear, double price) {
        this.model = model;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

    // Accessors
    public String getModel() {
        return model;
    }

    public CarType getType() {
        return type;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public double getPrice() {
        return price;
    }

    // Mutators
    public void setModel(String model) {
        this.model = model;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Price rise method, increases the price double by (price * (rise + 1))
    public void priceRise(double rise) {
        this.setPrice(this.price * (rise + 1));
    }

    // toString method
    public String toString() {
        return String.format("%s %d %s ($%.2f)", this.model, this.manufacturingYear, this.type, this.price);
    }

    // Print method
    public void print() {
        System.out.printf("%s %d %s ($%.2f)", this.model, this.manufacturingYear, this.type, this.price);
    }
}
