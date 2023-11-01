package Lab2;

enum CarType {SUV, SED, LUX, HATCH, etc}
public class Car {
    protected String model;
    protected CarType type;
    protected int manufacturingYear;
    protected double price;

    public Car(String model, CarType type, int manufacturingYear, double price) {
        this.model = model;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

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

    public String getModel() {
        return this.model;
    }

    public CarType getType() {
        return this.type;
    }

    public int getManufacturingYear() {
        return this.manufacturingYear;
    }

    public double getPrice() {
        return price;
    }

    public void priceRise(double rise) {
        this.price += rise;
    }

    public String toString() {
        return String.format("%s %d %s ($%.2f)", this.model, this.manufacturingYear, this.type, this.price);
    }

    public void print() {
        System.out.printf("%s %d %s ($%.2f)", this.model, this.manufacturingYear, this.type, this.price);
    }
}
