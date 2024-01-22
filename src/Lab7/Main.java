package Lab7;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Main {
    protected static InsuranceCompany company;
    protected static User user;
    public static LoginGUI loginGUI;
    public static UserUI userUI;

    public static void main(String[] args) throws CloneNotSupportedException, PolicyException, IOException {
        company = new InsuranceCompany("Test Company", "admin", "password", 100);
        baseCode();
        loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        hookLoginEvents();
    }  

    public static void login() {
        User user = company.validateUser(loginGUI.usernameField.getText(), loginGUI.passwordField.getText());
        loginGUI.passwordField.setText("");

        if (user == null) {
            JOptionPane.showMessageDialog(loginGUI, "Username or password is wrong"); 
        } else {
            userUI = new UserUI();
            userUI.setVisible(true);
            loginGUI.setVisible(false);
        }
    }

    public static void hookLoginEvents() {
        loginGUI.loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        login();
                }
        });

        loginGUI.passwordField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        login();
                }      
        });

        loginGUI.loadMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        company.load("company.ser");
                }
        });

        loginGUI.saveMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        company.save("company.ser");
                }
        });

        loginGUI.listOfUsersMenu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                        String output = "";

                        for(User x : company.getUsers().values()) {
                           output += String.format("Username: %s | Password: %s\n", x.getName(), x.getPassword());
                        }
                  
                        JOptionPane.showMessageDialog(loginGUI, output);
                }
        });
    }

    public static void hookUserUiEvents() {

    }
    public static void baseCode() throws CloneNotSupportedException, PolicyException, IOException {
        // Create all the policies, with attributes described in the constructor.
        ThirdPartyPolicy thirdPartyPolicy1 = new ThirdPartyPolicy(
                "John",
                3000001,
                new Car("Subaru", CarType.SED, 2011, 2000),
                1,
                new MyDate(2025, 11, 3),
                "Got flattened by an 18 wheeler"
        );

        ComprehensivePolicy comprehensivePolicy1 = new ComprehensivePolicy(
                "Mike",
                3000002,
                new Car("Toyota", CarType.HATCH, 2016, 12000),
                3,
                new MyDate(2023, 1, 3),
                37,
                6
        );

        ThirdPartyPolicy thirdPartyPolicy2 = new ThirdPartyPolicy(
                "Michelle",
                3000003,
                new Car("Honda", CarType.SUV, 2014, 10000),
                8,
                new MyDate(2019, 7, 25),
                "Boom"
        );

        ComprehensivePolicy comprehensivePolicy2 = new ComprehensivePolicy(
                "Amy",
                3000004,
                new Car("Tesla", CarType.LUX, 2022, 57000),
                6,
                new MyDate(2031, 8, 16),
                56,
                14
        );

        ComprehensivePolicy comprehensivePolicy3 = new ComprehensivePolicy(
                "Tommy",
                3000005,
                new Car("Tractor", CarType.etc, 1998, 20000),
                1,
                new MyDate(2024, 4, 19),
                8,
                2
        );

        ThirdPartyPolicy thirdPartyPolicy3 = new ThirdPartyPolicy(
                "Liam",
                3000006,
                new Car("Toyota", CarType.HATCH, 2007, 8500),
                32,
                new MyDate(2018, 3, 3),
                "Tax fraud"
        );

        User user1 = new User("Jeremy", "password", new Address(18, "Green St", "Strathfield", "Sydney"));
        User user2 = new User("Lisa", "password", new Address(4, "Louisa St", "Brunswick", "Melbourne"));

        company.addUser(user1);
        company.addUser("Thomas", "123456", new Address(144, "Brokers Rd", "Mount Pleasant", "Wollongong"));
        company.addUser(user2);
        
        company.addPolicy(1, thirdPartyPolicy1);
        company.addPolicy(1, comprehensivePolicy1);

        company.addPolicy(2, thirdPartyPolicy2);
        company.addPolicy(2, comprehensivePolicy2);

        company.addPolicy(3, thirdPartyPolicy3);
        company.addPolicy(3, comprehensivePolicy3);
    }
    
}
