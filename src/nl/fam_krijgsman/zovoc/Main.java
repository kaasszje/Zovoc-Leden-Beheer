package nl.fam_krijgsman.zovoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Main {
    static JFrame gui;
    static ImageIcon logo, icon;
    static JButton loginButton;
    static JTextField userField;
    static JPasswordField passField;
    static JLabel userLabel, passLabel, logoLabel;
    static JPanel topPanel, centerPanel, bottomPanel;

    public static void main(String[] args) {
        //Setup top Panel
        topPanel = new JPanel();

        //Create logo fot label en setup label
        logo = new ImageIcon("Images/Zovoc_logo.png", "Zovoc Logo");
        logoLabel = new JLabel();
        logoLabel.setIcon(logo);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        //Add logo to topPanel
        topPanel.add(logoLabel);

        //Create center panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2,2));
        centerPanel.setSize(100,50);

        userLabel = new JLabel("Gebruikersnaam:");
        userLabel.setSize(30,25);
        userLabel.setHorizontalAlignment(JLabel.CENTER);

        passLabel = new JLabel("Wachtwoord:");
        passLabel.setSize(30,25);
        passLabel.setHorizontalAlignment(JLabel.CENTER);


        userField = new JTextField("Vul hier uw gebruikersnaam in.",30);
        userField.setHorizontalAlignment(JTextField.CENTER);

        //userField.setMaximumSize(userField.getMinimumSize());
        userField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                userField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userField.getText().equals("")) {
                    userField.setText("Vul hier uw gebruikersnaam in.");
                }
            }
        });

        passField = new JPasswordField("", 30);
        passField.setHorizontalAlignment(JPasswordField.CENTER);

        centerPanel.add(userLabel);
        centerPanel.add(userField);

        centerPanel.add(passLabel);
        centerPanel.add(passField);

        centerPanel.setSize(100,50);

        loginButton = new JButton("Login");
        loginButton.setHorizontalAlignment(JButton.CENTER);
        loginButton.setSize(30,20);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate login
                JOptionPane.showMessageDialog(null,"You are logged in.");
            }
        });

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(loginButton);



        //Setup frame
        icon = new ImageIcon("Images/Icon.PNG");
        gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //gui.setLayout(new BorderLayout());
        gui.setIconImage(icon.getImage());
        //Add elements to frame
        gui.add(topPanel, BorderLayout.NORTH);
        gui.add(centerPanel, BorderLayout.CENTER);
        gui.add(bottomPanel, BorderLayout.SOUTH);
        gui.setResizable(false);

        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);


    }

}
