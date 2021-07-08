package nl.fam_krijgsman.zovoc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class UserLoginView extends JFrame {
    private ImageIcon logo, icon;
    private JButton loginButton;
    private JTextField userField;
    private JPasswordField passField;
    private JLabel userLabel, passLabel, logoLabel;
    private JPanel topPanel, centerPanel, bottomPanel;

    public UserLoginView() {

        topPanel = new JPanel();

        //Create logo fot label en setup label
        try {
            logo = new ImageIcon(UserLoginView.class.getResource("/Images/Zovoc_logo.png"));
        } catch (NullPointerException e) {
            logo = null;
        }

        try {
            icon = new ImageIcon(UserLoginView.class.getResource("/Images/Icon.PNG"));
        } catch (NullPointerException e) {
            icon = null;
        }

        logoLabel = new JLabel();
        logoLabel.setIcon(logo);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        //Add logo to topPanel
        topPanel.add(logoLabel);

        //Create center panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2));
        centerPanel.setSize(100, 50);

        userLabel = new JLabel("Gebruikersnaam:");
        userLabel.setSize(30, 25);
        userLabel.setHorizontalAlignment(JLabel.CENTER);

        passLabel = new JLabel("Wachtwoord:");
        passLabel.setSize(30, 25);
        passLabel.setHorizontalAlignment(JLabel.CENTER);


        userField = new JTextField("", 30);
        userField.setHorizontalAlignment(JTextField.CENTER);


        passField = new JPasswordField("", 30);
        passField.setHorizontalAlignment(JPasswordField.CENTER);

        centerPanel.add(userLabel);
        centerPanel.add(userField);

        centerPanel.add(passLabel);
        centerPanel.add(passField);

        centerPanel.setSize(100, 50);

        loginButton = new JButton("Login");
        loginButton.setHorizontalAlignment(JButton.CENTER);
        loginButton.setSize(30, 20);


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(loginButton);


        //Setup frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setIconImage(icon.getImage());

        //Add elements to frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public String getUserField() {
        return this.userField.getText();
    }

    public String getPassField() {
        return String.valueOf(this.passField.getPassword());
    }

    public void setUserField(String userFieldText) {
        this.userField.setText(userFieldText);
    }

    public void setPassField(String passFieldText) {
        this.passField.setText(passFieldText);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void loginActionListener (ActionListener listenForLoginButton) {
        this.loginButton.addActionListener(listenForLoginButton);
    }

    public void userTextFocusListener (FocusListener userTextFieldFocusListener) {
        this.userField.addFocusListener(userTextFieldFocusListener);
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
