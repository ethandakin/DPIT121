package Lab4;
import java.lang.Cloneable;

// Ethan Dakin
// 8209194

public class MyDate implements Cloneable{
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

    @Override 
    protected MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
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

    // toString method
    @Override
    public String toString() {
        return String.format("%d/%d/%d", getDay(), getMonth(), getYear());
    }
    
    // Print method
    public void print() {
        System.out.printf("%d/%d/%d", getDay(), getMonth(), getYear());
    }
}
