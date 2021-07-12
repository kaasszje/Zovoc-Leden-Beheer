package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;

class BeheerView extends JFrame {
    JLayeredPane switchPanel;
    JPanel ledenPanel, teamPanel, welcomePanel, headerPanel;
    JLabel headerLabel, headerLabelCenter, ledenLabel, teamLabel, welcomeLabel;
    ImageIcon icon, logo;
    String userName;
    JMenuBar menuBar;
    JMenu actionMenu;
    JMenuItem ledenMenuItem, teamMenuItem, exitMenuItem;
    JScrollPane teamScrollPane, ledenScrollPane;
    JTable teamTable, ledenTable;
    JComboBox geslachtBox, klasseBox, teamBox;


    public JComboBox getGeslachtBox() {
        return geslachtBox;
    }

    public JComboBox getKlasseBox() {
        return klasseBox;
    }

    public BeheerView(String userName) {
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

        this.userName = userName.substring(0,1).toUpperCase() + userName.substring(1).toLowerCase();

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

        geslachtBox = new JComboBox(eGeslacht.values());
        klasseBox = new JComboBox(eKlasse.values());
        teamBox = new JComboBox();

        ledenPanel = new JPanel();
        ledenPanel.setLayout(new BorderLayout());
        ledenLabel = new JLabel("Leden tab");
        ledenLabel.setSize(30,25);
        ledenLabel.setHorizontalAlignment(JLabel.CENTER);
        ledenPanel.add(ledenLabel, BorderLayout.NORTH);
        ledenTable = new JTable();
        ledenScrollPane = new JScrollPane(ledenTable);
        ledenPanel.add(ledenScrollPane, BorderLayout.CENTER);

        teamPanel = new JPanel();
        teamPanel.setLayout(new BorderLayout());
        teamLabel = new JLabel("Team tab");
        teamLabel.setSize(30,25);
        teamLabel.setHorizontalAlignment(JLabel.CENTER);
        teamTable = new JTable();
        teamScrollPane = new JScrollPane(teamTable);
        teamPanel.add(teamLabel, BorderLayout.NORTH);
        teamPanel.add(teamScrollPane, BorderLayout.CENTER);

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        welcomeLabel = new JLabel("Maak uw keuze in het menu.", SwingConstants.CENTER);

        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel);
        welcomePanel.setBorder(BorderFactory.createRaisedBevelBorder());

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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

    public JTable getTeamTable() {
        return teamTable;
    }

    public void makeTeamTable() {
        teamTable.getColumn("Klasse").setCellEditor(new DefaultCellEditor(klasseBox));
        teamTable.getColumn("Geslacht").setCellEditor(new DefaultCellEditor(geslachtBox));
    }

    public void makeLedenTable() {
        ledenTable.getColumn("Geslacht").setCellEditor(new DefaultCellEditor(geslachtBox));
        ledenTable.getColumn("Team").setCellEditor(new DefaultCellEditor(teamBox));
    }

    public JTable getLedenTable() {
        return ledenTable;
    }

    public JComboBox getTeamBox() {
        return teamBox;
    }
}
