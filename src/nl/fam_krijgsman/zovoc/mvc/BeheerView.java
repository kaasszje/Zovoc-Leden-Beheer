package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

class BeheerView extends JFrame {
    private JLayeredPane switchPanel;
    private HeaderPanel headerPanel;
    private TeamPanel teamPanel;
    private LedenPanel ledenPanel;
    private AddTeamPanel addTeamPanel;
    private WelcomePanel welcomePanel;
    private ImageIcon icon;
    private String userName;
    private ZovocMenuBar zovocMenuBar;

    public BeheerView(String userName) {
        // get logo and icon as resource
        icon = new ImageIcon(Objects.requireNonNull(BeheerView.class.getResource("/Images/Icon.PNG")));

        //vul user name voor tonen boven in
        this.userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

        //maak menu
        zovocMenuBar = new ZovocMenuBar();
        this.setJMenuBar(zovocMenuBar);

        //Initieer de diverse panels
        welcomePanel = new WelcomePanel();
        teamPanel = new TeamPanel();
        ledenPanel = new LedenPanel();
        addTeamPanel = new AddTeamPanel();
        headerPanel = new HeaderPanel(this.userName);

        //Maak switch panel en voeg welcomePanel toe al welkom bericht
        switchPanel = new JLayeredPane();
        switchPanel.setLayout(new GridLayout(1, 1));
        switchPanel.add(welcomePanel);

        //Maak BorderLayour en voeg de elementen toe
        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(switchPanel, BorderLayout.CENTER);

        //Zet default settings voor JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    static class ZovocMenuBar extends JMenuBar {
        private final JMenuItem ledenMenuItem, teamMenuItem, exitMenuItem;
        private final JMenu actionMenu;

        public ZovocMenuBar() {
            actionMenu = new JMenu("Acties");
            ledenMenuItem = new JMenuItem("Leden beheren");
            teamMenuItem = new JMenuItem("Teams beheren");
            exitMenuItem = new JMenuItem("Afsluiten");
            actionMenu.add(ledenMenuItem);
            actionMenu.add(teamMenuItem);
            actionMenu.add(exitMenuItem);
            this.add(actionMenu);
        }

        //Menu items
        public void ledenMenuListener(ActionListener listenForMenu) {
            this.ledenMenuItem.addActionListener(listenForMenu);
        }

        public void teamsMenuListener(ActionListener listenForMenu) {
            this.teamMenuItem.addActionListener(listenForMenu);
        }

        public void exitMenuListener(ActionListener listenForMenu) {
            this.exitMenuItem.addActionListener(listenForMenu);
        }
    }

    static class HeaderPanel extends JPanel {
        private JLabel headerLabel, headerLabelCenter;
        private ImageIcon logo;

        public HeaderPanel(String user) {
            logo = new ImageIcon(Objects.requireNonNull(UserLoginView.class.getResource("/Images/Zovoc_logo.png")));

            headerLabel = new JLabel("Welkom " + user);
            headerLabel.setHorizontalAlignment(JLabel.CENTER);

            headerLabelCenter = new JLabel("Welkom bij Zovoc Leden Administratie");
            headerLabelCenter.setIcon(logo);
            headerLabelCenter.setVerticalTextPosition(JLabel.BOTTOM);
            headerLabelCenter.setHorizontalTextPosition(JLabel.CENTER);
            headerLabelCenter.setHorizontalAlignment(JLabel.CENTER);
            headerLabelCenter.setOpaque(true);
            headerLabelCenter.setBackground(Color.WHITE);

            this.setLayout(new BorderLayout());
            this.add(headerLabel, BorderLayout.EAST);
            this.add(headerLabelCenter, BorderLayout.CENTER);
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setBackground(Color.WHITE);
        }

    }

    static class WelcomePanel extends JPanel {
        private JLabel welcomeLabel;
        private JTextArea welcomeMessage;

        public WelcomePanel() {
            welcomeLabel = new JLabel("Maak uw keuze in het menu.", SwingConstants.CENTER);
            welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
            welcomeLabel.setVerticalAlignment(JLabel.CENTER);

            welcomeMessage = new JTextArea();
            welcomeMessage.setText("Welkom bericht:" + System.getProperty("line.separator"));
            welcomeMessage.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            welcomeMessage.setLineWrap(true);
            welcomeMessage.setEditable(false);
            welcomeMessage.setWrapStyleWord(true);

            this.setLayout(new BorderLayout(10, 10));
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.add(welcomeLabel, BorderLayout.NORTH);
            this.add(welcomeMessage, BorderLayout.CENTER);
        }
    }

    static class LedenPanel extends JPanel {
        private JPanel ledenButtonPanel;
        private JLabel ledenLabel;
        private JScrollPane ledenScrollPane;
        private JTable ledenTable;
        private JComboBox<eGeslacht> lidGeslachtBox;
        private JComboBox<String> teamBox;
        private JButton voegToeLid, verwijderLid;

        public LedenPanel() {
            // Maak combobox voor geslacht, en verwijder mix, deze wordt niet bij leden gebruikt
            lidGeslachtBox = new JComboBox<>(eGeslacht.values());
            lidGeslachtBox.removeItem(eGeslacht.MIX);

            teamBox = new JComboBox<>();

            ledenLabel = new JLabel("Leden tab");
            ledenLabel.setSize(30, 25);
            ledenLabel.setHorizontalAlignment(JLabel.CENTER);

            ledenTable = new JTable();
            ledenScrollPane = new JScrollPane(ledenTable);

            voegToeLid = new JButton("Voeg toe");
            voegToeLid.setSize(30, 25);

            verwijderLid = new JButton("Verwijder");
            verwijderLid.setSize(30, 25);

            ledenButtonPanel = new JPanel();
            ledenButtonPanel.setLayout(new FlowLayout());
            ledenButtonPanel.add(verwijderLid);
            ledenButtonPanel.add(voegToeLid);

            this.setLayout(new BorderLayout());
            this.add(ledenLabel, BorderLayout.NORTH);
            this.add(ledenScrollPane, BorderLayout.CENTER);
            this.add(ledenButtonPanel, BorderLayout.SOUTH);
        }

        public void voegToeLidButtonListener(ActionListener listenForButton) {
            this.voegToeLid.addActionListener(listenForButton);
        }

        public void verwijderLidButtonListener(ActionListener listenForButton) {
            this.verwijderLid.addActionListener(listenForButton);
        }

        public void makeLedenTable() {
            this.ledenTable.getColumn("Geslacht").setCellEditor(new DefaultCellEditor(lidGeslachtBox));
            this.ledenTable.getColumn("Team").setCellEditor(new DefaultCellEditor(teamBox));
        }

        public JTable getLedenTable() {
            return this.ledenTable;
        }

        public JComboBox<String> getTeamBox() {
            return this.teamBox;
        }
    }

    static class TeamPanel extends JPanel {
        private JPanel teamButtonPanel;
        private JLabel teamLabel;
        private JTable teamTable;
        private JComboBox<eGeslacht> teamGeslachtBox;
        private JComboBox<eKlasse> teamKlasseBox;
        private JScrollPane teamScrollPane;
        private JButton voegToeTeam, verwijderTeam;

        public TeamPanel() {
            teamGeslachtBox = new JComboBox<>(eGeslacht.values());
            teamKlasseBox = new JComboBox<>(eKlasse.values());

            teamLabel = new JLabel("Team tabel:");
            teamLabel.setSize(30, 25);
            teamLabel.setHorizontalAlignment(JLabel.CENTER);

            teamTable = new JTable();
            teamScrollPane = new JScrollPane(teamTable);

            voegToeTeam = new JButton("Voeg toe");
            voegToeTeam.setSize(30, 25);
            verwijderTeam = new JButton("Verwijder");
            verwijderTeam.setSize(30, 25);

            teamButtonPanel = new JPanel();
            teamButtonPanel.setLayout(new FlowLayout());
            teamButtonPanel.add(verwijderTeam);
            teamButtonPanel.add(voegToeTeam);

            setLayout(new BorderLayout());
            add(teamLabel, BorderLayout.NORTH);
            add(teamScrollPane, BorderLayout.CENTER);
            add(teamButtonPanel, BorderLayout.SOUTH);
        }

        public void voegToeTeamButtonListener(ActionListener listenForButton) {
            this.voegToeTeam.addActionListener(listenForButton);
        }

        public void verwijderTeamButtonListener(ActionListener listenForButton) {
            this.verwijderTeam.addActionListener(listenForButton);
        }

        public JTable getTeamTable() {
            return teamTable;
        }

        public void makeTeamTable() {
            teamTable.getColumn("Klasse").setCellEditor(new DefaultCellEditor(teamKlasseBox));
            teamTable.getColumn("Geslacht").setCellEditor(new DefaultCellEditor(teamGeslachtBox));
        }
    }

    static class AddTeamPanel extends JPanel {
        private JLabel teamLabel, klasseLabel, geslachtLabel;
        private JPanel centerPanel, buttonPanel;
        private JTextField teamField;
        private JComboBox<eGeslacht> geslachtJComboBox;
        private JComboBox<eKlasse> klasseJComboBox;
        private JButton cancelButton, toevoegButton;

        public AddTeamPanel() {
            setLayout(new BorderLayout());
            geslachtJComboBox = new JComboBox<>(eGeslacht.values());
            klasseJComboBox = new JComboBox<>(eKlasse.values());
            teamLabel = new JLabel("Voer teamnaam in:");
            teamLabel.setSize(50, 25);
            klasseLabel = new JLabel("Kies klasse:");
            klasseLabel.setSize(50, 25);
            geslachtLabel = new JLabel("Wat voor geslacht:");
            geslachtLabel.setSize(50, 25);
            teamField = new JTextField();
            teamField.setSize(100, 25);
            geslachtJComboBox.setSize(50, 25);
            klasseJComboBox.setSize(50, 25);
            centerPanel = new JPanel();

            centerPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.ipadx = 100;
            gbc.gridx = 0;
            gbc.gridy = 0;
            centerPanel.add(teamLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            centerPanel.add(teamField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            centerPanel.add(klasseLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            centerPanel.add(klasseJComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            centerPanel.add(geslachtLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            centerPanel.add(geslachtJComboBox, gbc);

//            centerPanel.setLayout(new GridLayout(3, 2, 10, 25));
//            centerPanel.add(teamLabel);
//            centerPanel.add(teamField);
//            centerPanel.add(klasseLabel);
//            centerPanel.add(klasseJComboBox);
//            centerPanel.add(geslachtLabel);
//            centerPanel.add(geslachtJComboBox);

            cancelButton = new JButton("Annuleren");
            cancelButton.setSize(40, 25);
            toevoegButton = new JButton("Toevoegen");
            toevoegButton.setSize(40, 25);
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(cancelButton);
            buttonPanel.add(toevoegButton);

            add(centerPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        public void toevoegButtonListener(ActionListener listener) {
            toevoegButton.addActionListener(listener);
        }

        public void cancelButtonListener(ActionListener listener) {
            cancelButton.addActionListener(listener);
        }

        public String getTeamField() {
            return teamField.getText();
        }

        public eGeslacht getGeslacht() {
            return (eGeslacht) geslachtJComboBox.getSelectedItem();
        }

        public eKlasse getKlasse() {
            return (eKlasse) klasseJComboBox.getSelectedItem();
        }
    }

    public void switchPanel(JPanel panel) {
        switchPanel.removeAll();
        switchPanel.add(panel);
        switchPanel.repaint();
        switchPanel.revalidate();
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public AddTeamPanel getAddTeamPanel() {
        return addTeamPanel;
    }

    public LedenPanel getLedenPanel() {
        return ledenPanel;
    }

    public TeamPanel getTeamPanel() {
        return teamPanel;
    }

    public ZovocMenuBar getZovocMenuBar() {
        return zovocMenuBar;
    }
}
