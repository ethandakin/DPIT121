package Lab8;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.*;

// Ethan Dakin
// 8209194

public abstract class InsurancePolicy implements Cloneable, Comparable<InsurancePolicy>, Serializable {
    // ATTRIBUTES //
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    // CONSTRUCTOR //
    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate) throws PolicyException {
        this.policyHolderName = policyHolderName;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;

        
        
        if (id < 3000000 || id > 3999999) {
            this.id = generateID();
            throw new PolicyException(this.id);
        } else {
            this.id = id;
        }
    }

    // COPY CONSTRUCTOR //
    public InsurancePolicy(InsurancePolicy policy) {
        this.policyHolderName = policy.policyHolderName;
        this.id = policy.id;
        this.car = policy.car;
        this.numberOfClaims = policy.numberOfClaims;
        this.expiryDate = policy.expiryDate;
    }

    // ACCESSORS //
    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public int getID() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public int getNumberOfClaims() {
        return numberOfClaims;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    // MUTATORS //
    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }
    
    public void setID(int id) {
        this.id = id;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setNumberOfClaims(int numberOfClaims) {
        this.numberOfClaims = numberOfClaims;
    }

    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // METHODS //
    // Car price rise method
    public void carPriceRise(double risePercent) {
        double rise = car.getPrice() * risePercent;
        car.priceRise(rise);
    }

    public static HashMap<Integer, InsurancePolicy> load(String fileName) throws IOException {
        HashMap<Integer, InsurancePolicy> policies = new HashMap<Integer, InsurancePolicy>();
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.err.println("Error in creating/opening the file.");
            System.exit(1);
        }

        try {
            while (true) {
                InsurancePolicy policy = (InsurancePolicy) inputStream.readObject();
                policies.put(policy.getID(), policy);
            }
        } catch (EOFException e) {
            System.out.println("No new records.");
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong class in file");
        } catch (IOException e) {
            System.err.println("Error in adding object to file."); 
            System.exit(1);
        }

        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }

        return policies;
    }

    public static boolean save(HashMap<Integer, InsurancePolicy> policies, String fileName) throws IOException {
        ObjectOutputStream outputStream = null;
        boolean passed = false;

        try {
            outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException e) {
            System.err.println("Error in creating/opening the file.");
            System.exit(1);
        }

        try {
            for (int policyID : policies.keySet()) {
                outputStream.writeObject(policies.get(policyID));
            }
        } catch(IOException e) {
            System.err.println("Error in adding the objects to the file.");
            System.exit(1);
        }

        try {
            if (outputStream != null) {
                outputStream.close();
                passed = true;
            }
        } catch(IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }

        return passed;
    }

    public static boolean saveTextFile(HashMap<Integer, InsurancePolicy> policies, String fileName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        boolean passed = false;

        for (InsurancePolicy policy : policies.values()) {
            out.write(policy.toDelimitedString() + "\n");
        }

        try {
            out.close();
            passed = true;
        } catch(IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }
        
        return passed;
    }

    public static InsurancePolicy loadInsurancePolicyFromTextFile(String[] field) throws PolicyException {
        String type = field[0];

        String policyHolderName = field[1];
        int id = Integer.parseInt(field[2]);

        String model = field[3];
        CarType carType = CarType.valueOf(field[4]);
        int manufacturingYear = Integer.parseInt(field[5]);
        double price = Double.parseDouble(field[6]);

        Car car = new Car(model, carType, manufacturingYear, price);

        int numberOfClaims = Integer.parseInt(field[7]);

        int day = Integer.parseInt(field[8]);
        int month = Integer.parseInt(field[9]);
        int year = Integer.parseInt(field[10]);

        MyDate date = new MyDate(year, month, day);

        if (type.equals("ThirdPartyPolicy")) {
            String comments = field[11];
            return new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, date, comments);
        } else if (type.equals("ComprehensivePolicy")) {
            int driverAge = Integer.parseInt(field[11]);
            int level = Integer.parseInt(field[12]);
            return new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, date, driverAge, level);
        }

        return null;
    }

    public static HashMap<Integer, InsurancePolicy> loadTextFile(String fileName) throws IOException, PolicyException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        HashMap<Integer, InsurancePolicy> policies = new HashMap<Integer, InsurancePolicy>();
        String line = in.readLine();

        while (line != null) {
            line = line.trim();
            String[] field = line.split(",");

            policies.put(Integer.parseInt(field[2]), loadInsurancePolicyFromTextFile(field));

            line = in.readLine();
        }

        try {
            in.close();
        } catch(IOException e) {
            System.err.println("Error in closing the file.");
            System.exit(1);
        }

        return policies;
    }
    public int generateID() {
        return 3000000 + (int) (Math.random() * 1000000);
    }

    // Abstract method calcPayment
    public abstract double calcPayment(double flatRate);

    // toString method
    @Override
    public String toString() {
        return String.format("PolicyID: %d\nHolder: %s\n%s\nNumber of claims: %d\nExpiry date: %s", id, policyHolderName, car, numberOfClaims, expiryDate);
    }

    // Print method
    public void print() {
        System.out.printf("PolicyID: %d\nHolder: %s\n", id, policyHolderName);
        car.print();
        System.out.printf("\nNumber of claims: %d\nExpiry date: %s", numberOfClaims, expiryDate);
    }

    // Clone method
    @Override
    public InsurancePolicy clone() throws CloneNotSupportedException {
        return (InsurancePolicy) super.clone();
    }

    // Calc all payments static method
    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate) {
        return policies.stream().mapToDouble(x -> x.calcPayment(flatRate)).sum();
    }

    public static double calcTotalPayments(HashMap<Integer, InsurancePolicy> policies, int flatRate) {
        return policies.values().stream().mapToDouble(x -> x.calcPayment(flatRate)).sum(); 
    }

    // Price rise static method
    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) {
        policies.forEach(x -> x.carPriceRise(risePercent));
    }

    public static void carPriceRiseAll(HashMap<Integer, InsurancePolicy> policies, double risePercent) {
        policies.values().stream().forEach(x -> x.carPriceRise(risePercent));
    }

    // Filter by car model
    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel) {
        return (ArrayList<InsurancePolicy>)(policies.stream())
        .filter(x-> x.getCar().getModel().contains(carModel))
        .collect(Collectors.toList());
    }

    // Filter by car model
    public static ArrayList<InsurancePolicy> filterByCarModel(HashMap<Integer, InsurancePolicy> policies, String carModel) {
        return (ArrayList<InsurancePolicy>)(policies.values().stream())
        .filter(x-> x.getCar().getModel().contains(carModel))
        .collect(Collectors.toList());
    }

    public static HashMap<Integer, InsurancePolicy> filterByCarModelHashMap(HashMap<Integer, InsurancePolicy> policies, String carModel) {
        HashMap<Integer, InsurancePolicy> filteredPolicies = new HashMap<Integer, InsurancePolicy>();
        
        for (Map.Entry<Integer, InsurancePolicy> entry : policies.entrySet()) {
            // Checks if the entry value (policy) has the same car as specified. If so, add it to the hashmap
            if (entry.getValue().getCar().getModel().equals(carModel)) {
                filteredPolicies.put(entry.getKey(), entry.getValue());
            }
        }

        return filteredPolicies;
    }

    // Filter by expiry date
    public static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy> policies, MyDate date) {
        return (ArrayList<InsurancePolicy>) policies.stream()
                        .filter(x -> x.getExpiryDate().isExpired(date) == true)
                        .collect(Collectors.toList());
    }

    // Filter by expiry date
    public static ArrayList<InsurancePolicy> filterByExpiryDate(HashMap<Integer, InsurancePolicy> policies, MyDate date) {
        return (ArrayList<InsurancePolicy>) policies.values().stream()
                        .filter(x -> x.getExpiryDate().isExpired(date) == true)
                        .collect(Collectors.toList());
    }

    public static HashMap<Integer, InsurancePolicy> filterByExpiryDateHashMap(HashMap<Integer, InsurancePolicy> policies, MyDate date) {
        HashMap<Integer, InsurancePolicy> filteredPolicies = new HashMap<Integer, InsurancePolicy>();
        
        for (Map.Entry<Integer, InsurancePolicy> entry : policies.entrySet()) {
            // Checks if the entry value (policy) is expired. If so, add it to the hashmap
            if (entry.getValue().getExpiryDate().isExpired(date)) {
                filteredPolicies.put(entry.getKey(), entry.getValue());
            }
        }

        return filteredPolicies;
    }

    // Print policies method
    public static void printPolicies(ArrayList<InsurancePolicy> policies) {
        // Loop through policies and call print method on them all.
        policies.forEach(System.out::println);
    }

    // Print policies method (hashmap version)
    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies) {
        // Loop through policies and call print method on them all.
        policies.values().stream().forEach(System.out::println);
    }
    

    // Print policies and the premium payment for each policy.
    public static void printPoliciesAndPremium(ArrayList<InsurancePolicy> policies, int flatRate) {
        // Loop through policies and call print method on them all.
        for (InsurancePolicy policy: policies) {
            policy.print();
            System.out.printf("Premium payment: $%.2f\n", policy.calcPayment(flatRate));
            System.out.print("\n");
        }
    }

    // Print policies and the premium payment for each policy. (hashmap)
    public static void printPoliciesAndPremium(HashMap<Integer, InsurancePolicy> policies, int flatRate) {
        // Loop through policies and call print method on them all.
        policies.values().stream().forEach(x -> System.out.printf("%s\nPremium payment: $%.2f\n\n", x.toString(), x.calcPayment(flatRate)));
    }

    public String toDelimitedString() {
        return String.format("%s,%d,%s,%d,%s", policyHolderName, id, car.toDelimitedString(), numberOfClaims, expiryDate.toDelimitedString());
    }

    // Print total premium payment
    public static void printTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate) {
        // Print out the total, formatted to 2 decimal places.
        System.out.printf("$%.2f", calcTotalPayments(policies, flatRate));
    }

    // Shallow copy
    public static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) {
        return (ArrayList<InsurancePolicy>) policies.stream().collect(Collectors.toList());
    }

    // Deep copy (implements clone)
    public static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException {
        return (ArrayList<InsurancePolicy>) policies.stream().map(x -> {
            try {
                return x.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }).collect(Collectors.toList());
    }

    // CompareTo method, compares two dates.
    @Override
    public int compareTo(InsurancePolicy other) {
        return expiryDate.compareTo(other.getExpiryDate());
    }
}
