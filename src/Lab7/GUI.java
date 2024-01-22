package Lab7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI extends JFrame {
    public GUI(InsuranceCompany company) {
        /* 
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JTextField userText = new JTextField();
        JButton jButton1 = new JButton();
        JPasswordField passText = new JPasswordField();
        JMenuBar jMenuBar1 = new JMenuBar();
        JMenu jMenu1 = new JMenu();
        JMenuItem jMenuItem1 = new JMenuItem();
        JMenuItem jMenuItem2 = new JMenuItem();
        JMenu jMenu2 = new JMenu();
        setDefaultCloseOperation(3);
        jLabel1.setFont(new Font("Tahoma", 0, 18));
        jLabel1.setText("Username");
        jLabel2.setFont(new Font("Tahoma", 0, 18));
        jLabel2.setText("Password");
        userText.setFont(new Font("Tahoma", 1, 18));
        jButton1.setFont(new Font("Tahoma", 1, 18));
        jButton1.setForeground(new Color(255, 102, 102));
        jButton1.setText("Login");

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = company.validateUser(userText.getText(), passText.getText());
                passText.setText("");

                if (!user == null) {
                    
                }

                JOptionPane.showMessageDialog(jLabel1, "Username or password is wrong");

            }
        });

        //jButton1.addActionListener(new 1(this));
        passText.setFont(new Font("Tahoma", 1, 18));
        //passText.addActionListener(new 2(this));
        jMenu1.setText("File");
        jMenu1.setFont(new Font("Segoe UI", 1, 18));
        jMenuItem1.setFont(new Font("Segoe UI", 1, 18));
        jMenuItem1.setText("Load");
        //jMenuItem1.addActionListener(new 3(this));
        jMenu1.add(jMenuItem1);
        jMenuItem2.setFont(new Font("Segoe UI", 1, 18));
        jMenuItem2.setText("Save");
        //jMenuItem2.addActionListener(new 4(this));
        jMenu1.add(jMenuItem2);
        jMenuBar1.add(jMenu1);
        jMenu2.setText("List of Users");
        jMenu2.setFont(new Font("Segoe UI", 1, 18));
        //jMenu2.addMouseListener(new 5(this));
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(142, 142, 142).addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(jButton1).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1, -2, 110, -2).addComponent(jLabel2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(userText).addComponent(passText, -1, 125, 32767)))).addContainerGap(298, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(114, 114, 114).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1, -2, 29, -2).addComponent(userText, -2, -1, -2)).addGap(29, 29, 29).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel2).addComponent(passText, -2, -1, -2)).addGap(34, 34, 34).addComponent(jButton1).addContainerGap(126, 32767)));
        pack(); */
    }  

}
