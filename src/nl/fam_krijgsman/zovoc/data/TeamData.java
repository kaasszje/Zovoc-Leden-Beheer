package nl.fam_krijgsman.zovoc.data;

import nl.fam_krijgsman.zovoc.model.Team;
import nl.fam_krijgsman.zovoc.model.Vereniging;
import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;
import org.jetbrains.annotations.NotNull;

public class TeamData {
    public static void addTeamData(@NotNull Vereniging vereniging) {
        vereniging.addTeam(new Team("Jongens A1", eKlasse.JUNIOR, eGeslacht.MAN));
        vereniging.addTeam(new Team("Jongens A2", eKlasse.JUNIOR, eGeslacht.MAN));
        vereniging.addTeam(new Team("Meisjes A1", eKlasse.JUNIOR, eGeslacht.VROUW));
        vereniging.addTeam(new Team("Meisjes A2", eKlasse.JUNIOR, eGeslacht.VROUW));
        vereniging.addTeam(new Team("Dames 1", eKlasse.EREDIVISIE, eGeslacht.VROUW));
        vereniging.addTeam(new Team("Dames 2", eKlasse.PROMOTIE, eGeslacht.VROUW));
        vereniging.addTeam(new Team("Heren 1", eKlasse.EREDIVISIE, eGeslacht.MAN));
        vereniging.addTeam(new Team("Heren 2", eKlasse.PROMOTIE, eGeslacht.MAN));
        vereniging.addTeam(new Team("Recreanten", eKlasse.SENIOR, eGeslacht.MIX));
    }

}
