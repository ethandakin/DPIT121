package Lab4;

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

    public MyDate(MyDate date) {
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
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

    public boolean isExpired(MyDate date) {
        if (getYear() < date.getYear()) {
            return true;
        }

        if (getMonth() < date.getMonth()) {
            return true;
        }

        if (getDay() < date.getDay()) {
            return true;
        }

        return false;
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
