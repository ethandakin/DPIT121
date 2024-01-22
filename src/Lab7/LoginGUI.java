package Lab7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginGUI extends JFrame {
    public JLabel usernameLabel;
    public JLabel passwordLabel;
    public JTextField usernameField;
    public JButton loginButton;
    public JPasswordField passwordField;
    public JMenuBar menuBar;
    public JMenu fileMenu;
    public JMenuItem loadMenuItem;
    public JMenuItem saveMenuItem;
    public JMenu listOfUsersMenu;

    public LoginGUI() {
        createUI();
        setDefaultCloseOperation(3);
        loadUI();
    }


    public void createUI() {
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        usernameField = new JTextField();
        loginButton = new JButton();
        passwordField = new JPasswordField();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        loadMenuItem = new JMenuItem();
        saveMenuItem = new JMenuItem();
        listOfUsersMenu = new JMenu();
    }

    public void loadUI() {
        usernameLabel.setFont(new Font("Tahoma", 0, 18));
        usernameLabel.setText("Username");

        passwordLabel.setFont(new Font("Tahoma", 0, 18));
        passwordLabel.setText("Password");

        loginButton.setFont(new Font("Tahoma", 1, 18));
        loginButton.setForeground(new Color(255, 102, 102));
        loginButton.setText("Login");

        usernameField.setFont(new Font("Tahoma", 1, 18));
        passwordField.setFont(new Font("Tahoma", 1, 18));

        fileMenu.setText("File");
        fileMenu.setFont(new Font("Segoe UI", 1, 18));
        menuBar.add(fileMenu);

        loadMenuItem.setFont(new Font("Segoe UI", 1, 18));
        loadMenuItem.setText("Load");
        
        
        saveMenuItem.setFont(new Font("Segoe UI", 1, 18));
        saveMenuItem.setText("Save");
        //jMenuItem2.addActionListener(new 4(this));
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        
        listOfUsersMenu.setText("List of Users");
        listOfUsersMenu.setFont(new Font("Segoe UI", 1, 18));
        //jMenu2.addMouseListener(new 5(this));
        menuBar.add(listOfUsersMenu);
        setJMenuBar(menuBar);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(142, 142, 142).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(loginButton).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(usernameLabel, -2, 110, -2).addComponent(passwordLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(usernameField).addComponent(passwordField, -1, 125, 32767)))).addContainerGap(298, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(114, 114, 114).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(usernameLabel, -2, 29, -2).addComponent(usernameField, -2, -1, -2)).addGap(29, 29, 29).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(passwordLabel).addComponent(passwordField, -2, -1, -2)).addGap(34, 34, 34).addComponent(loginButton).addContainerGap(126, 32767)));
        pack();
    }
    
}
