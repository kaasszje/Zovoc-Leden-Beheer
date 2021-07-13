package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.data.LidData;
import nl.fam_krijgsman.zovoc.data.TeamData;
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

        //fill data
        this.beheerModel.setTeams(TeamData.addTeamData());
        this.beheerModel.setLeden(LidData.addLidData(this.beheerModel.getTeams()));


        //koppel acties aan menu items
        this.beheerView.ledenMenuListener(new LedenMenuListener());
        this.beheerView.teamsMenuListener(new TeamMenuListener());
        this.beheerView.exitMenuListener(new ExitMenuListener());
        this.beheerView.voegToeLidButtonListener(new VoegToeLidListener());
        this.beheerView.verwijderLidButtonListener(new VerwijderLidListener());
        this.beheerView.voegToeTeamButtonListener(new VoegToeTeamListener());
        this.beheerView.verwijderTeamButtonListener(new VerwijderTeamListener());
        this.beheerView.addTeamPanel.toevoegButtonListener(new AddTeamListener());
        this.beheerView.addTeamPanel.cancelButtonListener(new CancelAddTeamListener());

        this.beheerView.getTeamTable().setModel(beheerModel.getTeamModel());
        this.beheerView.makeTeamTable();

        this.beheerView.getLedenTable().setModel(beheerModel.getLedenModel());


        fillTeamBox();
        this.beheerView.makeLedenTable();



    }

    class LedenMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getLedenPanel());
            fillTeamBox();
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
            eKlasse klasse = beheerView.getAddTeamPanel().getKlasse();
            eGeslacht geslacht = beheerView.getAddTeamPanel().getGeslacht();
            beheerModel.addTeam(new Team(naam, klasse, geslacht));
            beheerView.switchPanel(beheerView.getTeamPanel());
        }
    }

    class CancelAddTeamListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getTeamPanel());
        }
    }

    class VerwijderLidListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = beheerView.ledenTable.getSelectedRow();
            if (rowIndex != -1) {
                beheerModel.ledenModel.removeLid(rowIndex);
                beheerView.switchPanel(beheerView.getLedenPanel());
            } else {
                beheerView.displayErrorMessage("Selecteer eerst een rij.");
            }
        }
    }

    class VerwijderTeamListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = beheerView.teamTable.getSelectedRow();
            if (rowIndex != -1) {
                beheerModel.teamModel.removeTeam(rowIndex);
                beheerView.switchPanel(beheerView.getTeamPanel());
            } else {
                beheerView.displayErrorMessage("Selecteer eerst een rij.");
            }
        }
    }

    public void fillTeamBox() {
        this.beheerView.getTeamBox().removeAllItems();
        for (Team team: this.beheerModel.getTeams()) {
            this.beheerView.getTeamBox().addItem(team.getNaam());
        }
    }
}
