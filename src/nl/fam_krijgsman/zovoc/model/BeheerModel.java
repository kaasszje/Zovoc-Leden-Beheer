package nl.fam_krijgsman.zovoc.model;

import nl.fam_krijgsman.zovoc.generic.Helper;
import nl.fam_krijgsman.zovoc.model.classes.Lid;
import nl.fam_krijgsman.zovoc.model.classes.Team;
import nl.fam_krijgsman.zovoc.model.classes.Vereniging;
import nl.fam_krijgsman.zovoc.model.enums.eGeslacht;
import nl.fam_krijgsman.zovoc.model.enums.eKlasse;

import java.util.ArrayList;

public class BeheerModel extends Vereniging {
    public BeheerModel() {
        super(Helper.getVerenigingNaam());
    }

    @Override
    public ArrayList<Lid> getLeden() {
        return super.getLeden();
    }

    // Team filter voor leden
    public ArrayList<Lid> getLeden(Team team) {
        ArrayList<Lid> leden = new ArrayList<>();
        for (Lid lid : super.getLeden()) {
            if (lid.getTeam().equals(team)) {
                leden.add(lid);
            }
        }
        return leden;
    }

    // Geslacht filter voor leden
    public ArrayList<Lid> getLeden(eGeslacht geslacht) {
        ArrayList<Lid> leden = new ArrayList<>();
        for (Lid lid : super.getLeden()) {
            if (lid.getGeslacht().equals(geslacht)) {
                leden.add(lid);
            }
        }
        return leden;
    }

    @Override
    public ArrayList<Team> getTeams() {
        return super.getTeams();
    }

    // Geslacht filter voor teams
    public ArrayList<Team> getTeams(eGeslacht geslacht) {
        ArrayList<Team> teams = new ArrayList<>();
        for (Team team : super.getTeams()) {
           if (team.getGeslacht().equals(geslacht)) {
               teams.add(team);
           }
        }
        return teams;
    }

    // Klasse filter voor teams
    public ArrayList<Team> getTeams(eKlasse klasse) {
        ArrayList<Team> teams = new ArrayList<>();
        for (Team team : super.getTeams()) {
            if (team.getKlasse().equals(klasse)) {
                teams.add(team);
            }
        }
        return teams;
    }
}
