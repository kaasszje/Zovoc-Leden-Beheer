package nl.fam_krijgsman.zovoc.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.Objects;

class UserLoginView extends JFrame {
    private final ImageIcon logo;
    private final ImageIcon icon;
    private final JButton loginButton;
    private final JTextField userField;
    private final JPasswordField passField;
    private final JLabel userLabel;
    private final JLabel passLabel;
    private final JLabel logoLabel;
    private final JPanel topPanel;
    private final JPanel centerPanel;
    private final JPanel bottomPanel;

    public UserLoginView() {

        this.topPanel = new JPanel();

        //Create logo fot label en setup label
        this.logo = new ImageIcon(Objects.requireNonNull(UserLoginView.class.getResource("/Images/Zovoc_logo.png")));
        this.icon = new ImageIcon(Objects.requireNonNull(UserLoginView.class.getResource("/Images/favicon.png")));

        this.logoLabel = new JLabel();
        this.logoLabel.setIcon(logo);
        this.logoLabel.setHorizontalAlignment(JLabel.CENTER);

        //Add logo to topPanel
        this.topPanel.add(logoLabel);

        //Create center panel
        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new GridLayout(2, 2));
        this.centerPanel.setSize(100, 50);

        this.userLabel = new JLabel("Gebruikersnaam:");
        this.userLabel.setSize(30, 25);
        this.userLabel.setHorizontalAlignment(JLabel.CENTER);

        this.passLabel = new JLabel("Wachtwoord:");
        this.passLabel.setSize(30, 25);
        this.passLabel.setHorizontalAlignment(JLabel.CENTER);

        this.userField = new JTextField("", 30);
        this.userField.setHorizontalAlignment(JTextField.CENTER);

        this.passField = new JPasswordField("", 30);
        this.passField.setHorizontalAlignment(JPasswordField.CENTER);

        this.centerPanel.add(userLabel);
        this.centerPanel.add(userField);

        this.centerPanel.add(passLabel);
        this.centerPanel.add(passField);

        this.centerPanel.setSize(100, 50);

        this.loginButton = new JButton("Login");
        this.loginButton.setHorizontalAlignment(JButton.CENTER);
        this.loginButton.setSize(30, 20);


        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new FlowLayout());
        this.bottomPanel.add(loginButton);

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
        this.getRootPane().setDefaultButton(loginButton);
        this.setVisible(true);
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

    public void focusUserField(){
        this.userField.requestFocus();
    }
}
