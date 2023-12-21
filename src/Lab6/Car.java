package Lab6;
import java.io.Serializable;

// Ethan Dakin
// 8209194

public class Car implements Cloneable, Serializable {
    // ATTRIBUTES //
    private String model;
    private CarType type;
    private int manufacturingYear;
    private double price;

    // CONSTRUCTOR //
    public Car(String model, CarType type, int manufacturingYear, double price) {
        this.model = model;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

    // COPY CONSTRUCTOR //
    public Car(Car car) {
        this.model = car.model;
        this.type = car.type;
        this.manufacturingYear = car.manufacturingYear;
        this.price = car.price;
    }

    // ACCESSORS //
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

    // MUTATORS //
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

    // METHODS //
    // Price rise method
    public void priceRise(double rise) {
        price += rise;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%s %d %s ($%.2f)", model, manufacturingYear, type, price);
    }

    public String toDelimitedString() {
        return String.format("Car,%s,%d,%s,%f", model, manufacturingYear, type, price);
    }

    // Print method
    public void print() {
        System.out.printf("%s %d %s ($%.2f)", model, manufacturingYear, type, price);
    }

    // Clone method
    @Override
    public Car clone() throws CloneNotSupportedException {
        return (Car) super.clone();
    }
}
