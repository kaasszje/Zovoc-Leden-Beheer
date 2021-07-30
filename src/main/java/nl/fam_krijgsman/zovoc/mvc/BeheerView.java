package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class BeheerView extends JFrame {
    private final JLayeredPane switchPanel;
    private final HeaderPanel headerPanel;
    private final TeamPanel teamPanel;
    private final LedenPanel ledenPanel;
    private final AddTeamPanel addTeamPanel;
    private final AddLidPanel addLidPanel;
    private final WelcomePanel welcomePanel;
    private final ImageIcon icon;
    private final String userName;
    private final ZovocMenuBar zovocMenuBar;

    public BeheerView(String userName) {
        // get logo and icon as resource
        this.icon = new ImageIcon(Objects.requireNonNull(BeheerView.class.getResource("/Images/favicon.png")));

        //vul user name voor tonen boven in
        this.userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

        //maak menu
        this.zovocMenuBar = new ZovocMenuBar();
        this.setJMenuBar(zovocMenuBar);

        //Initieer de diverse panels
        this.welcomePanel = new WelcomePanel();
        this.teamPanel = new TeamPanel();
        this.ledenPanel = new LedenPanel();
        this.addTeamPanel = new AddTeamPanel();
        this.addLidPanel = new AddLidPanel();
        this.headerPanel = new HeaderPanel(this.userName);

        //Maak switch panel en voeg welcomePanel toe al welkom bericht
        this.switchPanel = new JLayeredPane();
        this.switchPanel.setLayout(new GridLayout(1, 1));
        this.switchPanel.add(welcomePanel);

        //Maak BorderLayour en voeg de elementen toe
        this.setLayout(new BorderLayout());
        this.add(this.headerPanel, BorderLayout.NORTH);
        this.add(this.switchPanel, BorderLayout.CENTER);

        //Zet default settings voor JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void switchPanel(JPanel panel) {
        this.switchPanel.removeAll();
        this.switchPanel.add(panel);
        this.switchPanel.repaint();
        this.switchPanel.revalidate();
    }

    public AddTeamPanel getAddTeamPanel() {
        return this.addTeamPanel;
    }

    public AddLidPanel getAddLidPanel() {
        return this.addLidPanel;
    }

    public LedenPanel getLedenPanel() {
        return this.ledenPanel;
    }

    public TeamPanel getTeamPanel() {
        return this.teamPanel;
    }

    public ZovocMenuBar getZovocMenuBar() {
        return this.zovocMenuBar;
    }

    static class ZovocMenuBar extends JMenuBar {
        private final JMenuItem ledenMenuItem, teamMenuItem, exitMenuItem;
        private final JMenu actionMenu;

        public ZovocMenuBar() {
            this.actionMenu = new JMenu("Acties");
            this.ledenMenuItem = new JMenuItem("Leden beheren");
            this.teamMenuItem = new JMenuItem("Teams beheren");
            this.exitMenuItem = new JMenuItem("Afsluiten");
            this.actionMenu.add(ledenMenuItem);
            this.actionMenu.add(teamMenuItem);
            this.actionMenu.add(exitMenuItem);
            this.add(actionMenu);
        }

        public JMenuItem getLedenMenuItem() {
            return this.ledenMenuItem;
        }

        public JMenuItem getTeamMenuItem() {
            return this.teamMenuItem;
        }

        public JMenuItem getExitMenuItem() {
            return this.exitMenuItem;
        }
    }

    static class HeaderPanel extends JPanel {
        private final JLabel headerLabel;
        private final JLabel headerLabelCenter;
        private final ImageIcon logo;

        public HeaderPanel(String user) {
            this.logo = new ImageIcon(Objects.requireNonNull(UserLoginView.class.getResource("/Images/Zovoc_logo.png")));

            this.headerLabel = new JLabel("Welkom " + user);
            this.headerLabel.setHorizontalAlignment(JLabel.CENTER);

            this.headerLabelCenter = new JLabel("Welkom bij Zovoc Leden Administratie");
            this.headerLabelCenter.setIcon(logo);
            this.headerLabelCenter.setVerticalTextPosition(JLabel.BOTTOM);
            this.headerLabelCenter.setHorizontalTextPosition(JLabel.CENTER);
            this.headerLabelCenter.setHorizontalAlignment(JLabel.CENTER);
            this.headerLabelCenter.setOpaque(true);
            this.headerLabelCenter.setBackground(Color.WHITE);

            this.setLayout(new BorderLayout());
            this.add(this.headerLabel, BorderLayout.EAST);
            this.add(this.headerLabelCenter, BorderLayout.CENTER);
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.setBackground(Color.WHITE);
        }

    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    static class WelcomePanel extends JPanel {
        private final JLabel welcomeLabel;
        private final JTextArea welcomeMessage;

        public WelcomePanel() {
            this.welcomeLabel = new JLabel("Maak uw keuze in het menu.", SwingConstants.CENTER);
            this.welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
            this.welcomeLabel.setVerticalAlignment(JLabel.CENTER);

            this.welcomeMessage = new JTextArea();
            this.welcomeMessage.setText("Welkom bericht:" + System.getProperty("line.separator"));
            this.welcomeMessage.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            this.welcomeMessage.setLineWrap(true);
            this.welcomeMessage.setEditable(false);
            this.welcomeMessage.setWrapStyleWord(true);

            this.setLayout(new BorderLayout(10, 10));
            this.setBorder(BorderFactory.createRaisedBevelBorder());
            this.add(welcomeLabel, BorderLayout.NORTH);
            this.add(welcomeMessage, BorderLayout.CENTER);
        }
    }

    static class LedenPanel extends JPanel {
        private final JPanel ledenButtonPanel;
        private final JLabel ledenLabel;
        private final JScrollPane ledenScrollPane;
        private final JTable ledenTable;
        private final JComboBox<eGeslacht> lidGeslachtBox;
        private final JComboBox<String> teamBox;
        private final JButton voegToeLid;
        private final JButton verwijderLid;

        public LedenPanel() {
            // Maak combobox voor geslacht, en verwijder mix, deze wordt niet bij leden gebruikt
            this.lidGeslachtBox = new JComboBox<>(eGeslacht.values());
            this.lidGeslachtBox.removeItem(eGeslacht.MIX);

            this.teamBox = new JComboBox<>();

            this.ledenLabel = new JLabel("Leden tab");
            this.ledenLabel.setSize(30, 25);
            this.ledenLabel.setHorizontalAlignment(JLabel.CENTER);

            this.ledenTable = new JTable();
            this.ledenTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.ledenScrollPane = new JScrollPane(ledenTable);

            this.voegToeLid = new JButton("Voeg toe");
            this.voegToeLid.setSize(30, 25);

            this.verwijderLid = new JButton("Verwijder");
            this.verwijderLid.setSize(30, 25);

            this.ledenButtonPanel = new JPanel();
            this.ledenButtonPanel.setLayout(new FlowLayout());
            this.ledenButtonPanel.add(verwijderLid);
            this.ledenButtonPanel.add(voegToeLid);

            this.setLayout(new BorderLayout());
            this.add(this.ledenLabel, BorderLayout.NORTH);
            this.add(this.ledenScrollPane, BorderLayout.CENTER);
            this.add(this.ledenButtonPanel, BorderLayout.SOUTH);
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

        public JButton getVoegToeLid() {
            return voegToeLid;
        }

        public JButton getVerwijderLid() {
            return verwijderLid;
        }
    }

    static class TeamPanel extends JPanel {
        private final JPanel teamButtonPanel;
        private final JLabel teamLabel;
        private final JTable teamTable;
        private final JComboBox<eGeslacht> teamGeslachtBox;
        private final JComboBox<eKlasse> teamKlasseBox;
        private final JScrollPane teamScrollPane;
        private final JButton voegToeTeam;
        private final JButton verwijderTeam;

        public TeamPanel() {
            this.teamGeslachtBox = new JComboBox<>(eGeslacht.values());
            this.teamKlasseBox = new JComboBox<>(eKlasse.values());

            this.teamLabel = new JLabel("Team tabel:");
            this.teamLabel.setSize(30, 25);
            this.teamLabel.setHorizontalAlignment(JLabel.CENTER);

            this.teamTable = new JTable();
            this.teamTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.teamScrollPane = new JScrollPane(teamTable);

            this.voegToeTeam = new JButton("Voeg toe");
            this.voegToeTeam.setSize(30, 25);
            this.verwijderTeam = new JButton("Verwijder");
            this.verwijderTeam.setSize(30, 25);

            this.teamButtonPanel = new JPanel();
            this.teamButtonPanel.setLayout(new FlowLayout());
            this.teamButtonPanel.add(verwijderTeam);
            this.teamButtonPanel.add(voegToeTeam);

            setLayout(new BorderLayout());
            add(this.teamLabel, BorderLayout.NORTH);
            add(this.teamScrollPane, BorderLayout.CENTER);
            add(this.teamButtonPanel, BorderLayout.SOUTH);
        }

        public JTable getTeamTable() {
            return teamTable;
        }

        public void makeTeamTable() {
            this.teamTable.getColumn("Klasse").setCellEditor(new DefaultCellEditor(teamKlasseBox));
            this.teamTable.getColumn("Geslacht").setCellEditor(new DefaultCellEditor(teamGeslachtBox));
        }

        public JButton getVoegToeTeam() {
            return this.voegToeTeam;
        }

        public JButton getVerwijderTeam() {
            return this.verwijderTeam;
        }
    }

    static class AddTeamPanel extends JPanel {
        private final JLabel teamLabel;
        private final JLabel klasseLabel;
        private final JLabel geslachtLabel;
        private final JPanel centerPanel;
        private final JPanel buttonPanel;
        private final JTextField teamField;
        private final JComboBox<eGeslacht> geslachtJComboBox;
        private final JComboBox<eKlasse> klasseJComboBox;
        private final JButton cancelButton;
        private final JButton toevoegButton;

        public AddTeamPanel() {
            this.geslachtJComboBox = new JComboBox<>(eGeslacht.values());
            this.klasseJComboBox = new JComboBox<>(eKlasse.values());
            this.teamLabel = new JLabel("Voer teamnaam in:");
            this.teamLabel.setSize(50, 25);
            this.klasseLabel = new JLabel("Kies klasse:");
            this.klasseLabel.setSize(50, 25);
            this.geslachtLabel = new JLabel("Wat voor geslacht:");
            this.geslachtLabel.setSize(50, 25);
            this.teamField = new JTextField();
            this.teamField.setSize(100, 25);
            this.geslachtJComboBox.setSize(50, 25);
            this.klasseJComboBox.setSize(50, 25);

            this.centerPanel = new JPanel();
            this.centerPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.ipadx = 100;
            gbc.gridx = 0;
            gbc.gridy = 0;
            this.centerPanel.add(this.teamLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            this.centerPanel.add(this.teamField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            this.centerPanel.add(this.klasseLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            this.centerPanel.add(this.klasseJComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            this.centerPanel.add(this.geslachtLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            this.centerPanel.add(this.geslachtJComboBox, gbc);

            this.cancelButton = new JButton("Annuleren");
            this.cancelButton.setSize(40, 25);
            this.toevoegButton = new JButton("Toevoegen");
            this.toevoegButton.setSize(40, 25);
            this.buttonPanel = new JPanel();
            this.buttonPanel.setLayout(new FlowLayout());
            this.buttonPanel.add(this.cancelButton);
            this.buttonPanel.add(this.toevoegButton);

            setLayout(new BorderLayout());
            add(this.centerPanel, BorderLayout.CENTER);
            add(this.buttonPanel, BorderLayout.SOUTH);
        }

        public String getTeamField() {
            return this.teamField.getText();
        }

        public eGeslacht getGeslacht() {
            return (eGeslacht) this.geslachtJComboBox.getSelectedItem();
        }

        public eKlasse getKlasse() {
            return (eKlasse) this.klasseJComboBox.getSelectedItem();
        }

        public void clearTextFields() {
            this.teamField.setText("");
        }

        public JButton getCancelButton() {
            return this.cancelButton;
        }

        public JButton getToevoegButton() {
            return this.toevoegButton;
        }
    }

    static class AddLidPanel extends JPanel {
        private final JLabel achterNaamLabel;
        private final JLabel voorNaamLabel;
        private final JLabel tussenVoegselLabel;
        private final JLabel telefoonLabel;
        private final JLabel emailLabel;
        private final JLabel geboorteJaarLabel;
        private final JLabel geslachtLabel;
        private final JLabel headerLabel;
        private final JPanel centerPanel;
        private final JPanel buttonPanel;
        private final JTextField achterNaamField;
        private final JTextField voorNaamField;
        private final JTextField tussenVoegselField;
        private final JTextField telefoonField;
        private final JTextField emailField;
        private final JTextField geboorteJaarField;
        private final JComboBox<eGeslacht> geslachtJComboBox;
        private final JButton cancelButton;
        private final JButton toevoegButton;
        private JPanel headerPanel;

        public AddLidPanel() {
            this.headerLabel = new JLabel("Vul de volgende gegevens in:", SwingConstants.CENTER);

            this.achterNaamLabel = new JLabel("Achternaam: ");
            this.achterNaamLabel.setSize(50, 25);
            this.voorNaamLabel = new JLabel("Voornaam: ");
            this.voorNaamLabel.setSize(50, 25);
            this.tussenVoegselLabel = new JLabel("Tussenvoegsel: ");
            this.tussenVoegselLabel.setSize(50, 25);
            this.telefoonLabel = new JLabel("Telefoonnummer: ");
            this.telefoonLabel.setSize(50, 25);
            this.emailLabel = new JLabel("Email-adres: ");
            this.emailLabel.setSize(50, 25);
            this.geboorteJaarLabel = new JLabel("Geboortejaar: ");
            this.geboorteJaarLabel.setSize(50, 25);
            this.geslachtLabel = new JLabel("Geslacht: ");
            this.geslachtLabel.setSize(50, 25);

            this.achterNaamField = new JTextField();
            this.achterNaamField.setSize(50, 25);
            this.voorNaamField = new JTextField();
            this.voorNaamField.setSize(50, 25);
            this.tussenVoegselField = new JTextField();
            this.tussenVoegselField.setSize(50, 25);
            this.telefoonField = new JTextField();
            this.telefoonField.setSize(50, 25);
            this.emailField = new JTextField();
            this.emailField.setSize(50, 25);
            this.geboorteJaarField = new JTextField();
            this.geboorteJaarField.setSize(50, 25);
            this.geslachtJComboBox = new JComboBox<>(eGeslacht.values());
            this.geslachtJComboBox.removeItem(eGeslacht.MIX);

            this.centerPanel = new JPanel();
            this.centerPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.ipadx = 100;
            gbc.gridx = 0;
            gbc.gridy = 0;
            this.centerPanel.add(this.achterNaamLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            this.centerPanel.add(this.achterNaamField, gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            this.centerPanel.add(this.voorNaamLabel, gbc);

            gbc.gridx = 3;
            gbc.gridy = 0;
            this.centerPanel.add(this.voorNaamField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            this.centerPanel.add(this.tussenVoegselLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            this.centerPanel.add(this.tussenVoegselField, gbc);

            gbc.gridx = 2;
            gbc.gridy = 1;
            this.centerPanel.add(this.telefoonLabel, gbc);

            gbc.gridx = 3;
            gbc.gridy = 1;
            this.centerPanel.add(this.telefoonField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            this.centerPanel.add(this.emailLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            this.centerPanel.add(this.emailField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.centerPanel.add(this.geboorteJaarLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            this.centerPanel.add(this.geboorteJaarField, gbc);

            gbc.gridx = 2;
            gbc.gridy = 3;
            this.centerPanel.add(this.geslachtLabel, gbc);

            gbc.gridx = 3;
            gbc.gridy = 3;
            this.centerPanel.add(this.geslachtJComboBox, gbc);

            this.cancelButton = new JButton("Annuleren");
            this.cancelButton.setSize(40, 25);
            this.toevoegButton = new JButton("Toevoegen");
            this.toevoegButton.setSize(40, 25);
            this.buttonPanel = new JPanel();
            this.buttonPanel.setLayout(new FlowLayout());
            this.buttonPanel.add(this.cancelButton);
            this.buttonPanel.add(this.toevoegButton);

            setLayout(new BorderLayout());
            add(this.headerLabel, BorderLayout.PAGE_START);
            add(this.centerPanel, BorderLayout.CENTER);
            add(this.buttonPanel, BorderLayout.PAGE_END);
        }

        public String getAchterNaamField() {
            return this.achterNaamField.getText();
        }

        public String getVoorNaamField() {
            return this.voorNaamField.getText();
        }

        public String getTussenVoegselField() {
            return this.tussenVoegselField.getText();
        }

        public String getTelefoonField() {
            return this.telefoonField.getText();
        }

        public String getEmailField() {
            return this.emailField.getText();
        }

        public String getGeboorteJaarField() {
            return this.geboorteJaarField.getText();
        }

        public eGeslacht getGeslacht() {
            return (eGeslacht) this.geslachtJComboBox.getSelectedItem();
        }

        public void clearTextFields() {
            this.achterNaamField.setText("");
            this.voorNaamField.setText("");
            this.tussenVoegselField.setText("");
            this.telefoonField.setText("");
            this.emailField.setText("");
            this.geboorteJaarField.setText("");
        }

        public JButton getCancelButton() {
            return this.cancelButton;
        }

        public JButton getToevoegButton() {
            return this.toevoegButton;
        }
    }
}
