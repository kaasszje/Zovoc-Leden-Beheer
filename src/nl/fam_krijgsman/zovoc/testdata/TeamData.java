package nl.fam_krijgsman.zovoc.testdata;

import nl.fam_krijgsman.zovoc.model.classes.Team;
import nl.fam_krijgsman.zovoc.model.classes.Vereniging;
import nl.fam_krijgsman.zovoc.model.enums.eGeslacht;
import nl.fam_krijgsman.zovoc.model.enums.eKlasse;
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
