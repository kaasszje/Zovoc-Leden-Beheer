package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.data.LidData;
import nl.fam_krijgsman.zovoc.data.TeamData;
import nl.fam_krijgsman.zovoc.generic.Helper;
import nl.fam_krijgsman.zovoc.model.Lid;
import nl.fam_krijgsman.zovoc.model.Team;
import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BeheerController {
    private final BeheerView beheerView;
    private final BeheerModel beheerModel;

    public BeheerController(BeheerView beheerView, BeheerModel beheerModel) {
        this.beheerView = beheerView;
        this.beheerModel = beheerModel;

        //koppel acties aan menu items
        this.beheerView.getZovocMenuBar().ledenMenuListener(new LedenMenuListener());
        this.beheerView.getZovocMenuBar().teamsMenuListener(new TeamMenuListener());
        this.beheerView.getZovocMenuBar().exitMenuListener(new ExitMenuListener());

        //Data vullen teams en leden
        //Teams zijn randvoorwaardelijk voor leden
        this.beheerModel.setTeams(TeamData.addTeamData());
        this.beheerModel.setLeden(LidData.addLidData(this.beheerModel.getTeams()));

        //LedenPanel acties
        this.beheerView.getLedenPanel().voegToeLidButtonListener(new VoegToeLidListener());
        this.beheerView.getLedenPanel().verwijderLidButtonListener(new VerwijderLidListener());
        this.beheerView.getLedenPanel().getLedenTable().setModel(beheerModel.getLedenModel());
        this.beheerView.getLedenPanel().makeLedenTable();
        fillLedenTeamBox();

        //Lid toevoegen acties
        this.beheerView.getAddLidPanel().toevoegButtonListener(new AddLidListener());
        this.beheerView.getAddLidPanel().cancelButtonListener(new CancelAddLidListener());


        //TeamPanel acties
        this.beheerView.getTeamPanel().voegToeTeamButtonListener(new VoegToeTeamListener());
        this.beheerView.getTeamPanel().verwijderTeamButtonListener(new VerwijderTeamListener());
        this.beheerView.getTeamPanel().getTeamTable().setModel(beheerModel.getTeamModel());
        this.beheerView.getTeamPanel().makeTeamTable();

        //Team toevoegen acties
        this.beheerView.getAddTeamPanel().toevoegButtonListener(new AddTeamListener());
        this.beheerView.getAddTeamPanel().cancelButtonListener(new CancelAddTeamListener());
    }

    class LedenMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getLedenPanel());
            fillLedenTeamBox();
        }
    }

    class TeamMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getTeamPanel());
        }
    }

    static class ExitMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class VoegToeLidListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getAddLidPanel());
        }
    }

    class VoegToeTeamListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getAddTeamPanel());
        }
    }

    class AddTeamListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String naam = beheerView.getAddTeamPanel().getTeamField();
            if (naam.isEmpty()) {
                beheerView.displayErrorMessage("Teamnaam moet gevuld zijn");
            } else {
                eKlasse klasse = beheerView.getAddTeamPanel().getKlasse();
                eGeslacht geslacht = beheerView.getAddTeamPanel().getGeslacht();
                beheerModel.addTeam(new Team(naam, klasse, geslacht));
                beheerView.switchPanel(beheerView.getTeamPanel());
            }
        }
    }

    class CancelAddTeamListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getTeamPanel());
        }
    }

    class AddLidListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String achterNaam = beheerView.getAddLidPanel().getAchterNaamField();
            String voorNaam = beheerView.getAddLidPanel().getVoorNaamField();
            String tussenVoegsel = beheerView.getAddLidPanel().getTussenVoegselField();
            String telefoonNummer = beheerView.getAddLidPanel().getTelefoonField();
            String email = beheerView.getAddLidPanel().getEmailField();
            String geboorteJaarString = beheerView.getAddLidPanel().getGeboorteJaarField();
            eGeslacht geslacht = beheerView.getAddLidPanel().getGeslacht();

            if (achterNaam.isEmpty() && voorNaam.isEmpty() && geboorteJaarString.isEmpty()) {
                beheerView.displayErrorMessage("Achter-, voornaam of geboorte jaar is nog leeg.");
            } else if (!Helper.checkPhoneNumber(telefoonNummer)) {
                beheerView.displayErrorMessage("Dat is geen geldig telefoonnummer.");
            } else if (!Helper.checkEmail(email)) {
                beheerView.displayErrorMessage("Dat is geen geldig email adres.");
            } else {
                try {
                    Integer geboorteJaar = Integer.parseInt(geboorteJaarString);
                    beheerModel.addLid(new Lid(achterNaam, voorNaam, tussenVoegsel, telefoonNummer, email, geboorteJaar, geslacht));
                    beheerView.switchPanel(beheerView.getLedenPanel());
                } catch (NumberFormatException nfe) {
                    beheerView.displayErrorMessage("Dat is geen valide geboorte jaar.");
                }
            }
        }
    }

    class CancelAddLidListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getLedenPanel());
        }
    }


    class VerwijderLidListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = beheerView.getLedenPanel().getLedenTable().getSelectedRow();
            if ((rowIndex != -1) && (rowIndex < beheerModel.getLeden().size())) {
                beheerModel.getLedenModel().removeLid(rowIndex);
                beheerView.switchPanel(beheerView.getLedenPanel());
            } else {
                beheerView.displayErrorMessage("Selecteer eerst een rij.");
            }
        }
    }

    class VerwijderTeamListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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

    public void fillLedenTeamBox() {
        this.beheerView.getLedenPanel().getTeamBox().removeAllItems();
        for (Team team : this.beheerModel.getTeams()) {
            this.beheerView.getLedenPanel().getTeamBox().addItem(team.getNaam());
        }
    }
}
