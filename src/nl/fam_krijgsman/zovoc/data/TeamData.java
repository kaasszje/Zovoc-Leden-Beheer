package nl.fam_krijgsman.zovoc.data;

import nl.fam_krijgsman.zovoc.model.Team;
import nl.fam_krijgsman.zovoc.model.Vereniging;
import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TeamData {
    public static ArrayList<Team> addTeamData() {
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team("Jongens A1", eKlasse.JUNIOR, eGeslacht.MAN));
        teams.add(new Team("Jongens A2", eKlasse.JUNIOR, eGeslacht.MAN));
        teams.add(new Team("Meisjes A1", eKlasse.JUNIOR, eGeslacht.VROUW));
        teams.add(new Team("Meisjes A2", eKlasse.JUNIOR, eGeslacht.VROUW));
        teams.add(new Team("Dames 1", eKlasse.EREDIVISIE, eGeslacht.VROUW));
        teams.add(new Team("Dames 2", eKlasse.PROMOTIE, eGeslacht.VROUW));
        teams.add(new Team("Heren 1", eKlasse.EREDIVISIE, eGeslacht.MAN));
        teams.add(new Team("Heren 2", eKlasse.PROMOTIE, eGeslacht.MAN));
        teams.add(new Team("Recreanten", eKlasse.SENIOR, eGeslacht.MIX));
        return teams;
    }

}
