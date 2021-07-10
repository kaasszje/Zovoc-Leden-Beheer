package nl.fam_krijgsman.zovoc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BeheerView extends JFrame {
    JLayeredPane switchPanel;
    JPanel ledenPanel, teamPanel, welcomePanel;
    JLabel text, header, ledenLabel, teamLabel, welcomeLabel;
    ImageIcon icon, logo;
    String user;
    JMenuBar menuBar;
    JMenu actionMenu;
    JMenuItem ledenMenuItem, teamMenuItem, exitMenuItem;


    public BeheerView(String user) {
        try {
            logo = new ImageIcon(UserLoginView.class.getResource("/Images/Zovoc_logo.png"));
        } catch (NullPointerException e) {
            logo = null;
        }

        try {
            icon = new ImageIcon(BeheerView.class.getResource("/Images/Icon.PNG"));
        } catch (NullPointerException e) {
            icon = null;
        }

        this.user = user.substring(0,1).toUpperCase() + user.substring(1).toLowerCase();
        text = new JLabel("Succesvol ingelogt");
        text.setBackground(Color.GREEN);

        header = new JLabel("Welkom " + this.user);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setBackground(Color.GREEN);

        this.setLayout(new BorderLayout());
        //add(text, BorderLayout.CENTER);
        //add(header, BorderLayout.NORTH);

        actionMenu = new JMenu("Acties");
        ledenMenuItem = new JMenuItem("Leden beheren");
        teamMenuItem = new JMenuItem("Teams beheren");
        exitMenuItem = new JMenuItem("Afsluiten");
        actionMenu.add(ledenMenuItem);
        actionMenu.add(teamMenuItem);
        actionMenu.add(exitMenuItem);

        menuBar = new JMenuBar();
        menuBar.add(actionMenu);
        this.setJMenuBar(menuBar);




        ledenPanel = new JPanel();
        ledenLabel = new JLabel("Leden tab");
        ledenLabel.setSize(30,25);
        ledenLabel.setHorizontalAlignment(JLabel.CENTER);
        ledenLabel.setSize(200,200);
        ledenPanel.add(ledenLabel);

        teamPanel = new JPanel();
        teamPanel.setLayout(new FlowLayout());
        teamLabel = new JLabel("Team tab");
        teamLabel.setSize(30,25);
        teamLabel.setHorizontalAlignment(JLabel.CENTER);
        teamPanel.setSize(175,175);
        teamPanel.add(teamLabel);

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        welcomeLabel = new JLabel();
        welcomeLabel.setIcon(logo);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel);

        switchPanel = new JLayeredPane();
        switchPanel.setLayout(new FlowLayout());
        switchPanel.add(welcomePanel);

        //this.add(switchPanel, BorderLayout.CENTER);
        this.add(switchPanel);

        this.setBackground(Color.GREEN);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout());
        this.setIconImage(icon.getImage());
        //Add elements to frame

        this.setSize(500,500);
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void ledenMenuListener (ActionListener listenForMenu) {
        this.ledenMenuItem.addActionListener(listenForMenu);
    }

    public void teamsMenuListener (ActionListener listenForMenu){
        this.teamMenuItem.addActionListener(listenForMenu);
    }

    public void exitMenuListener (ActionListener listenForMenu) {
        this.exitMenuItem.addActionListener(listenForMenu);
    }

    public JMenuItem getLedenMenuItem() {
        return ledenMenuItem;
    }

    public JMenuItem getTeamMenuItem() {
        return teamMenuItem;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JPanel getLedenPanel() {
        return ledenPanel;
    }

    public JPanel getTeamPanel() {
        return teamPanel;
    }

    public void switchPanel(JPanel panel) {
        switchPanel.removeAll();
        switchPanel.add(panel);
        switchPanel.repaint();
        switchPanel.revalidate();
    }
}
