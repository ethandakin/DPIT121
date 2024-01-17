package Lab7;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI extends JFrame {
    public GUI() {

        JLabel usernameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JTextField usernameField = new JTextField();
        JButton loginButton = new JButton();
        JPasswordField passwordField = new JPasswordField();
        JMenuBar jMenuBar1 = new JMenuBar();
        JMenu jMenu1 = new JMenu();
        JMenuItem jMenuItem1 = new JMenuItem();
        JMenuItem jMenuItem2 = new JMenuItem();
        JMenu jMenu2 = new JMenu();

        setDefaultCloseOperation(3);
        
        usernameLabel.setFont(new Font("Tahoma", 0, 18));
        usernameLabel.setText("Username");
        passwordLabel.setFont(new Font("Tahoma", 0, 18));
        passwordLabel.setText("Password");
        usernameField.setFont(new Font("Tahoma", 1, 18));
        loginButton.setFont(new Font("Tahoma", 1, 18));
        loginButton.setForeground(new Color(255, 102, 102));
        loginButton.setText("Login");
        passwordField.setFont(new Font("Tahoma", 1, 18));
        jMenu1.setText("File");
        jMenu1.setFont(new Font("Segoe UI", 1, 18));
        jMenuItem1.setFont(new Font("Segoe UI", 1, 18));
        jMenuItem1.setText("Load");
        jMenu1.add(jMenuItem1);
        jMenuItem2.setFont(new Font("Segoe UI", 1, 18));
        jMenuItem2.setText("Save");
        jMenu1.add(jMenuItem2);
        jMenuBar1.add(jMenu1);
        jMenu2.setText("List of Users");
        jMenu2.setFont(new Font("Segoe UI", 1, 18));
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        //layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(142, 142, 142).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(loginButton).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(usernameLabel, -2, 110, -2).addComponent(passwordLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(usernameField).addComponent(passwordField, -1, 125, 32767)))).addContainerGap(298, 32767)));
        //layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(114, 114, 114).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(usernameLabel, -2, 29, -2).addComponent(usernameField, -2, -1, -2)).addGap(29, 29, 29).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(passwordLabel).addComponent(passwordField, -2, -1, -2)).addGap(34, 34, 34).addComponent(loginButton).addContainerGap(126, 32767)));
        pack();
    }  
}
