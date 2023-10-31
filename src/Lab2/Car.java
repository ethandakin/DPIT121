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

    public String toString() {
        return String.format("%s %d %s ($%.2f)", this.model, this.manufacturingYear, this.type, this.price);
    }

    public void print() {
        System.out.printf("%s %d %s ($%.2f)", this.model, this.manufacturingYear, this.type, this.price);
    }
}
