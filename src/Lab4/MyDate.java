package Lab4;

// Ethan Dakin
// 8209194

public class MyDate implements Cloneable, Comparable<MyDate> {
    // Protected attributes for date
    private int year;
    private int month;
    private int day;

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
        } else if (getYear() <= date.getYear() && getMonth() < date.getMonth()) {
            return true;
        } else if (getYear() <= date.getYear() && getMonth() <= date.getMonth() && getDay() <= date.getDay()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(MyDate other) {
        if (year > other.getYear()) {
            return 1;
        } else if (year < other.getYear()) {
            return -1;
        }

        if (month > other.getMonth()) {
            return 1;
        } else if (month < other.getMonth()) {
            return -1;
        }

        if (day > other.getDay()) {
            return 1;
        } else if (day < other.getDay()) {
            return -1;
        }

        return 0;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%d/%d/%d", getDay(), getMonth(), getYear());
    }
    
    // Print method
    public void print() {
        System.out.printf("%d/%d/%d", getDay(), getMonth(), getYear());
    }

    @Override
    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }
}
