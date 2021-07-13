package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.data.LidData;
import nl.fam_krijgsman.zovoc.data.TeamData;
import nl.fam_krijgsman.zovoc.model.Team;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BeheerController {
    private BeheerView beheerView;
    private BeheerModel beheerModel;

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
        }
    }

    class TeamMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beheerView.switchPanel(beheerView.getTeamPanel());
        }
    }

    class ExitMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public void fillTeamBox() {
        this.beheerView.getTeamBox().removeAllItems();
        for (Team team: this.beheerModel.getTeams()) {
            this.beheerView.getTeamBox().addItem(team.getNaam());
        }
    }
}
