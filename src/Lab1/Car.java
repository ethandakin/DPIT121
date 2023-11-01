package Lab1;

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

    // toString method
    public String toString() {
        return String.format("%s %d %s ($%.2f)", this.model, this.manufacturingYear, this.type, this.price);
    }

    // Print method
    public void print() {
        System.out.printf("%s %d %s ($%.2f)", this.model, this.manufacturingYear, this.type, this.price);
    }
}
