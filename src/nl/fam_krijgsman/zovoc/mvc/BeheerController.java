package nl.fam_krijgsman.zovoc.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeheerController {
    private BeheerView beheerView;
    private BeheerModel beheerModel;

    public BeheerController(BeheerView beheerView, BeheerModel beheerModel) {
        this.beheerView = beheerView;
        this.beheerModel = beheerModel;

        this.beheerView.ledenMenuListener(new LedenMenuListener());
        this.beheerView.teamsMenuListener(new TeamMenuListener());
        this.beheerView.exitMenuListener(new ExitMenuListener());
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
}
