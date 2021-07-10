package nl.fam_krijgsman.zovoc.data;

import nl.fam_krijgsman.zovoc.model.classes.Adres;
import nl.fam_krijgsman.zovoc.model.classes.Lid;
import nl.fam_krijgsman.zovoc.model.classes.Vereniging;
import nl.fam_krijgsman.zovoc.model.enums.eGeslacht;

public class LidData {
    public static void addLidData(Vereniging vereniging) {
        vereniging.addLid(new Lid("Krijgsman","Martijn", "",
                "0626454220", "m.s.krijgsman@gmail.com",
                new Adres("Malachietgroen", 27, "2718HB", "Zoetermeer")
        , 1981, eGeslacht.MAN));
        vereniging.findLid("Krijgsman", "Martijn").setTeam(vereniging.findTeam("Heren 1"));

    }
}
