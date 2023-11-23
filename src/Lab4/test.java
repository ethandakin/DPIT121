package Lab4;

public class test {
    public static void main(String[] args) {
        MyDate currentDate = new MyDate(2023, 11, 23);
        MyDate expiryDate = new MyDate(2012, 10, 17);

        System.out.print(expiryDate.isExpired(currentDate));
    }
}
