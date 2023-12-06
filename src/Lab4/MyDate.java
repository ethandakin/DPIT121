package Lab4;

// Ethan Dakin
// 8209194

public class MyDate implements Cloneable, Comparable<MyDate> {
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
        } else if (getYear() <= date.getYear() && getMonth() < date.getMonth()) {
            return true;
        } else if (getYear() <= date.getYear() && getMonth() <= date.getMonth() && getDay() <= date.getDay()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(MyDate date) {
        // yes i know this isnt 100% accurate and i would love to use the default date libraries but if we were doing that this class wouldnt exist?

        int yearDifference = getYear() - date.getYear();
        int monthDifference = getMonth() - date.getMonth();
        int dayDifference = getDay() - date.getDay();


        return ((yearDifference * 365) + ((int) ((double) monthDifference * 30.4167)) + (dayDifference));
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
