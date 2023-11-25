package Assignment1;
import java.util.Scanner;

public class UserInterface {
    public static void displayLoginMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        System.out.println("1: Log in");
        System.out.println("2: Exit");

        System.out.println("\nPlease choose an option from 1 to 2: ");
    }

    public static void displayMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        System.out.println("1: Test code");
        System.out.println("2: Create User");
        System.out.println("3: Create ThirdParty Policy");
        System.out.println("4: Create Comprehensive Policy");
        System.out.println("5: Print User Information");
        System.out.println("6: Filter by Car Model");
        System.out.println("7: Filter by Expiry Date");
        System.out.println("8: Update Address");
        System.out.println("9: Log Out");
        System.out.println("\nPlease choose an option from 1 to 9: ");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        displayLoginMenu();

        int choice = Integer.parseInt(scan.nextLine());

        if (choice != 1) {
            return;
        }




        //System.out.println("1: Test code");

        //displayMainMenu();

        //scan.close();
    }
}
