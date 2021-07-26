package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.data.LidData;
import nl.fam_krijgsman.zovoc.data.TeamData;
import nl.fam_krijgsman.zovoc.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class BeheerController {
    private final BeheerView beheerView;
    private final BeheerModel beheerModel;

    public BeheerController(BeheerView beheerView, BeheerModel beheerModel) {
        this.beheerView = beheerView;
        this.beheerModel = beheerModel;

        //koppel acties aan menu items
        this.beheerView.getZovocMenuBar().getLedenMenuItem().addActionListener(new MenuListener());
        this.beheerView.getZovocMenuBar().getTeamMenuItem().addActionListener(new MenuListener());
        this.beheerView.getZovocMenuBar().getExitMenuItem().addActionListener(new MenuListener());

        //Data vullen teams en leden
        //Teams zijn randvoorwaardelijk voor leden
        List<Team> teams = TeamData.maakTeamLijst();
        for (Team team: teams) {
            this.beheerModel.addTeam(team);
        }
        List<Lid> leden = LidData.maakLidLijstMetTeam(this.beheerModel.getTeams());
        for (Lid lid: leden) {
            this.beheerModel.addLid(lid);
        }

        //LedenPanel acties
        this.beheerView.getLedenPanel().getVoegToeLid().addActionListener(new LedenViewListener());
        this.beheerView.getLedenPanel().getVerwijderLid().addActionListener(new LedenViewListener());
        this.beheerView.getLedenPanel().getLedenTable().setModel(beheerModel.getLedenModel());
        this.beheerView.getLedenPanel().makeLedenTable();
        fillLedenTeamBox();

        //Lid toevoegen acties
        this.beheerView.getAddLidPanel().getToevoegButton().addActionListener(new AddLedenViewListener());
        this.beheerView.getAddLidPanel().getCancelButton().addActionListener(new AddLedenViewListener());


        //TeamPanel acties
        this.beheerView.getTeamPanel().getVoegToeTeam().addActionListener(new TeamViewListener());
        this.beheerView.getTeamPanel().getVerwijderTeam().addActionListener(new TeamViewListener());
        this.beheerView.getTeamPanel().getTeamTable().setModel(beheerModel.getTeamModel());
        this.beheerView.getTeamPanel().makeTeamTable();

        //Team toevoegen acties
        this.beheerView.getAddTeamPanel().getToevoegButton().addActionListener(new AddTeamViewListener());
        this.beheerView.getAddTeamPanel().getCancelButton().addActionListener(new AddTeamViewListener());
    }

    class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(beheerView.getZovocMenuBar().getLedenMenuItem())) {
                beheerView.switchPanel(beheerView.getLedenPanel());
                fillLedenTeamBox();
            } else if (e.getSource().equals(beheerView.getZovocMenuBar().getTeamMenuItem())) {
                beheerView.switchPanel(beheerView.getTeamPanel());
            } else if (e.getSource().equals(beheerView.getZovocMenuBar().getExitMenuItem())) {
                System.exit(0);
            }
        }
    }

    class LedenViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(beheerView.getLedenPanel().getVoegToeLid())) {
                beheerView.switchPanel(beheerView.getAddLidPanel());
            } else if (e.getSource().equals(beheerView.getLedenPanel().getVerwijderLid())) {
                int rowIndex = beheerView.getLedenPanel().getLedenTable().getSelectedRow();
                if ((rowIndex != -1) && (rowIndex < beheerModel.getLeden().size())) {
                    beheerModel.getLedenModel().removeLid(rowIndex);
                    beheerView.switchPanel(beheerView.getLedenPanel());
                } else {
                    beheerView.displayErrorMessage("Selecteer eerst een rij.");
                }
            }
        }
    }

    class AddLedenViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(beheerView.getAddLidPanel().getToevoegButton())) {
                String achterNaam = beheerView.getAddLidPanel().getAchterNaamField();
                String voorNaam = beheerView.getAddLidPanel().getVoorNaamField();
                String tussenVoegsel = beheerView.getAddLidPanel().getTussenVoegselField();
                String telefoonNummer = beheerView.getAddLidPanel().getTelefoonField();
                String email = beheerView.getAddLidPanel().getEmailField();
                String geboorteJaarString = beheerView.getAddLidPanel().getGeboorteJaarField();
                eGeslacht geslacht = beheerView.getAddLidPanel().getGeslacht();

                if (achterNaam.isEmpty() && voorNaam.isEmpty() && geboorteJaarString.isEmpty()) {
                    beheerView.displayErrorMessage("Achter-, voornaam of geboorte jaar is nog leeg.");
                } else if (!TelefoonNummer.isValideTelefoonNummer(telefoonNummer)) {
                    beheerView.displayErrorMessage("Dat is geen geldig telefoonnummer.");
                } else if (!Email.isValideEmail(email)) {
                    beheerView.displayErrorMessage("Dat is geen geldig email adres.");
                } else {
                    try {
                        Integer geboorteJaar = Integer.parseInt(geboorteJaarString);
                        beheerModel.addLid(new Lid(achterNaam, voorNaam, tussenVoegsel, telefoonNummer, email, geboorteJaar, geslacht));
                        beheerView.getAddLidPanel().clearTextFields();
                        beheerView.switchPanel(beheerView.getLedenPanel());
                    } catch (NumberFormatException nfe) {
                        beheerView.displayErrorMessage("Dat is geen valide geboorte jaar.");
                    }
                }
            } else if (e.getSource().equals(beheerView.getAddLidPanel().getCancelButton())) {
                beheerView.switchPanel(beheerView.getLedenPanel());
            }
        }
    }

    class TeamViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(beheerView.getTeamPanel().getVoegToeTeam())) {
                beheerView.switchPanel(beheerView.getAddTeamPanel());
            } else if (e.getSource().equals(beheerView.getTeamPanel().getVerwijderTeam())) {
                int rowIndex = beheerView.getTeamPanel().getTeamTable().getSelectedRow();
                if ((rowIndex != -1) && (rowIndex < beheerModel.getTeams().size())) {
                    beheerModel.removeTeamFromLid(beheerModel.getTeams().get(rowIndex));
                    beheerModel.getTeamModel().removeTeam(rowIndex);
                    beheerView.switchPanel(beheerView.getTeamPanel());
                } else {
                    beheerView.displayErrorMessage("Selecteer eerst een rij.");
                }
            }
        }
    }

    class AddTeamViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(beheerView.getAddTeamPanel().getToevoegButton())) {
                String naam = beheerView.getAddTeamPanel().getTeamField();
                if (naam.isEmpty()) {
                    beheerView.displayErrorMessage("Teamnaam moet gevuld zijn");
                } else {
                    eKlasse klasse = beheerView.getAddTeamPanel().getKlasse();
                    eGeslacht geslacht = beheerView.getAddTeamPanel().getGeslacht();
                    beheerModel.addTeam(new Team(naam, klasse, geslacht));
                    beheerView.getAddTeamPanel().clearTextFields();
                    beheerView.switchPanel(beheerView.getTeamPanel());
                }
            } else if (e.getSource().equals(beheerView.getAddTeamPanel().getCancelButton())) {
                beheerView.switchPanel(beheerView.getTeamPanel());
            }
        }
    }

    public void fillLedenTeamBox() {
        this.beheerView.getLedenPanel().getTeamBox().removeAllItems();
        for (Team team : this.beheerModel.getTeams()) {
            this.beheerView.getLedenPanel().getTeamBox().addItem(team.getNaam());
        }
    }
}
