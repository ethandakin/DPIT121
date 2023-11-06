package Lab3;

// Ethan Dakin
// 8209194

public class MyDate {
    // Protected attributes for date
    protected int year;
    protected int month;
    protected int day;

    // Constructor
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Accessors
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // Mutators
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.month = day;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%d/%d/%d", day, month, year);
    }
    
    // Print method
    public void print() {
        System.out.printf("%d/%d/%d", day, month, year);
    }
}
