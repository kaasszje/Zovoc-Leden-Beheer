package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class BeheerView extends JFrame {
    JLayeredPane switchPanel;
    JPanel ledenPanel, teamPanel, welcomePanel, headerPanel, teamButtonPanel, ledenButtonPanel;
    JLabel headerLabel, headerLabelCenter, ledenLabel, teamLabel, welcomeLabel;
    ImageIcon icon, logo;
    String userName;
    JMenuBar menuBar;
    JMenu actionMenu;
    JMenuItem ledenMenuItem, teamMenuItem, exitMenuItem;
    JScrollPane teamScrollPane, ledenScrollPane;
    JTable teamTable, ledenTable;
    JComboBox lidGeslachtBox ,teamGeslachtBox, klasseBox, teamBox;
    JTextArea welcomeMessage;
    JButton voegToeTeam, voegToeLid, verwijderTeam, verwijderLid;

    public BeheerView(String userName) {
        // get logo and icon as resource
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

        //vul user name voor tonen boven in
        this.userName = userName.substring(0,1).toUpperCase() + userName.substring(1).toLowerCase();

        //Maak boven paneel
        headerLabel = new JLabel("Welkom " + this.userName);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setBackground(Color.GREEN);
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(headerLabel, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        headerLabelCenter = new JLabel("Welkom bij Zovoc Leden Administratie");
        headerLabelCenter.setIcon(logo);
        headerLabelCenter.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabelCenter.setHorizontalTextPosition(JLabel.CENTER);
        headerLabelCenter.setHorizontalAlignment(JLabel.CENTER);
        headerLabelCenter.setOpaque(true);
        headerLabelCenter.setBackground(Color.WHITE);
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(headerLabelCenter, BorderLayout.CENTER);

        //maak menu
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

        //maak JComboboxen
        teamGeslachtBox = new JComboBox(eGeslacht.values());
        lidGeslachtBox = new JComboBox(eGeslacht.values());
        lidGeslachtBox.removeItem(eGeslacht.MIX);
        klasseBox = new JComboBox(eKlasse.values());
        teamBox = new JComboBox();

        //maak paneel om te tonen voor leden optie
        ledenPanel = new JPanel();
        ledenPanel.setLayout(new BorderLayout());
        ledenLabel = new JLabel("Leden tab");
        ledenLabel.setSize(30,25);
        ledenLabel.setHorizontalAlignment(JLabel.CENTER);
        ledenTable = new JTable();
        ledenScrollPane = new JScrollPane(ledenTable);
        voegToeLid = new JButton("Voeg toe");
        voegToeLid.setSize(30,25);
        verwijderLid = new JButton("Verwijder");
        verwijderLid.setSize(30,25);
        ledenButtonPanel = new JPanel();
        ledenButtonPanel.setLayout(new FlowLayout());
        ledenButtonPanel.add(verwijderLid);
        ledenButtonPanel.add(voegToeLid);
        ledenPanel.add(ledenLabel, BorderLayout.NORTH);
        ledenPanel.add(ledenScrollPane, BorderLayout.CENTER);
        ledenPanel.add(ledenButtonPanel, BorderLayout.SOUTH);

        //maak paneel om te tonen voor team optie
        teamPanel = new JPanel();
        teamPanel.setLayout(new BorderLayout());
        teamLabel = new JLabel("Team tab");
        teamLabel.setSize(30,25);
        teamLabel.setHorizontalAlignment(JLabel.CENTER);
        teamTable = new JTable();
        teamScrollPane = new JScrollPane(teamTable);
        voegToeTeam = new JButton("Voeg toe");
        voegToeTeam.setSize(30,25);
        verwijderTeam = new JButton("Verwijder");
        verwijderTeam.setSize(30,25);
        teamButtonPanel = new JPanel();
        teamButtonPanel.setLayout(new FlowLayout());
        teamButtonPanel.add(verwijderTeam);
        teamButtonPanel.add(voegToeTeam);
        teamPanel.add(teamLabel, BorderLayout.NORTH);
        teamPanel.add(teamScrollPane, BorderLayout.CENTER);
        teamPanel.add(teamButtonPanel, BorderLayout.SOUTH);

        //maak default paneel bij opstarten applicatie
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout(10, 10));
        welcomeLabel = new JLabel("Maak uw keuze in het menu.", SwingConstants.CENTER);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        welcomePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        welcomeMessage = new JTextArea();
        welcomeMessage.setText("Welkom bericht:" + System.getProperty("line.separator"));
        welcomeMessage.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        welcomeMessage.setLineWrap(true);
        welcomeMessage.setEditable(false);
        welcomeMessage.setWrapStyleWord(true);

        welcomePanel.add(welcomeMessage, BorderLayout.CENTER);
        switchPanel = new JLayeredPane();
        switchPanel.setLayout(new GridLayout(1,1));
        switchPanel.add(welcomePanel);

        //define border layout
        this.setLayout(new BorderLayout());

        //add elements to JFrame

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(switchPanel, BorderLayout.CENTER);

        this.setBackground(Color.GREEN);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout());
        this.setIconImage(icon.getImage());
        //Add elements to frame

        //prefered size
        this.setSize(1024,768);
        //start fullscreen
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

    public void voegToeLidButtonListener (ActionListener listenForButton) {
        this.voegToeLid.addActionListener(listenForButton);
    }

    public void voegToeTeamButtonListener (ActionListener listenForButton) {
        this.voegToeTeam.addActionListener(listenForButton);
    }

    public void verwijderLidButtonListener (ActionListener listenForButton) {
        this.verwijderLid.addActionListener(listenForButton);
    }

    public void verwijderTeamButtonListener (ActionListener listenForButton) {
        this.verwijderTeam.addActionListener(listenForButton);
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

    public JTable getTeamTable() {
        return teamTable;
    }

    public void makeTeamTable() {
        teamTable.getColumn("Klasse").setCellEditor(new DefaultCellEditor(klasseBox));
        teamTable.getColumn("Geslacht").setCellEditor(new DefaultCellEditor(teamGeslachtBox));
    }

    public void makeLedenTable() {
        ledenTable.getColumn("Geslacht").setCellEditor(new DefaultCellEditor(lidGeslachtBox));
        ledenTable.getColumn("Team").setCellEditor(new DefaultCellEditor(teamBox));
    }

    public JTable getLedenTable() {
        return ledenTable;
    }

    public JComboBox getTeamBox() {
        return teamBox;
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
