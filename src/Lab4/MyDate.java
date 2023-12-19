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

    // Expired method, checks if current date is greater than given date.
    public boolean isExpired(MyDate date) {
        if (year < date.getYear()) {
            return true;
        } else if (year <= date.getYear() && month < date.getMonth()) {
            return true;
        } else if (year <= date.getYear() && month <= date.getMonth() && day <= date.getDay()) {
            return true;
        } else {
            return false;
        }
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

    // CompareTo method, compares the two dates.
    @Override
    public int compareTo(MyDate date) {
        if (year > date.getYear()) {
            return 1;
        } else if (year < date.getYear()) {
            return -1;
        }

        if (month > date.getMonth()) {
            return 1;
        } else if (month < date.getMonth()) {
            return -1;
        }

        if (day > date.getDay()) {
            return 1;
        } else if (day < date.getDay()) {
            return -1;
        }

        return 0;
    }

    // Clone method
    @Override
    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }
}
