package nl.fam_krijgsman.zovoc.data;

import nl.fam_krijgsman.zovoc.model.classes.Adres;
import nl.fam_krijgsman.zovoc.model.classes.Lid;
import nl.fam_krijgsman.zovoc.model.classes.Team;
import nl.fam_krijgsman.zovoc.model.classes.Vereniging;
import nl.fam_krijgsman.zovoc.model.enums.eGeslacht;

public class LidData {
    public static void addLidData(Vereniging vereniging) {
        //teams moeten eerst bestaan
        Adres adres1 = new Adres("Malachietgroen", 27, "2718HB", "Zoetermeer");
        Lid lid1 = new Lid("Krijgsman","Martijn", "",
                "0626454220", "m.s.krijgsman@gmail.com",
                adres1, 1981, eGeslacht.MAN);
        lid1.setTeam(vereniging.findTeam("Heren 1"));
        vereniging.addLid(lid1);

        vereniging.findLid("Krijgsman", "Martijn").setTeam(vereniging.findTeam("Heren 1"));

        Adres adres2 = new Adres("Marineblauw", 28, "2718KA", "Zoetermeer");
        Lid lid2 = new Lid("Ruijg", "Karin", "",
                "0626798570", "kaat.ruijg@gmail.com",
                adres2, 1982, eGeslacht.VROUW);
        lid2.setTeam(vereniging.findTeam("Dames 1"));
        vereniging.addLid(lid2);

        Adres adres3 = new Adres("Rokkeveenseweg-Zuid", 176, "2718ER", "Zoetermeer");
        Lid lid3 = new Lid("Krijgsman", "Marga", "",
        "0616064870", "marga.krijgsman@gmail.com",
                adres3, 1953, eGeslacht.VROUW);
        lid3.setTeam(vereniging.findTeam("Dames 1"));
        vereniging.addLid(lid3);

        Adres adres4 = new Adres("Zalkerbos", 138, "2716KG", "Zoetermeer");
        Lid lid4 = new Lid("Krijgsman", "Owen", "",
                "0612345611", "owen.krijgsman@fam-krijgsman.nl",
                adres4, 2016, eGeslacht.MAN);
        lid4.setTeam(vereniging.findTeam("Heren 1"));
        vereniging.addLid(lid4);

        Lid lid5 = new Lid("Krijgsmna", "Lilly", "",
                "0612345612", "lilly@fam-krijgsman.nl",
                adres4, 2012, eGeslacht.VROUW);
        lid5.setTeam(vereniging.findTeam("Dames 1"));
        vereniging.addLid(lid5);

        // generate full team voor heren 2, dames 2 en recreanten
        addLeden(vereniging, vereniging.findTeam("Dames 2"), 1980);
        addLeden(vereniging, vereniging.findTeam("Heren 2"), 1980);
        addLeden(vereniging, vereniging.findTeam("Meisjes A1"), 2010);
        addLeden(vereniging, vereniging.findTeam("Meisjes A2"), 2010);
        addLeden(vereniging, vereniging.findTeam("Jongens A1"), 2010);
        addLeden(vereniging, vereniging.findTeam("Jongens A2"), 2010);
        addLeden(vereniging, vereniging.findTeam("Recreanten"), 1960);
    }

    private static void addLeden(Vereniging vereniging, Team team, Integer startJaar) {
        Adres adres;
        Lid lid;
        for (int i=0;i<8;i++){
            adres = new Adres("straat " + i, i*10, "27" + i + i + "AA", "Zoetermeer");
            lid = new Lid(team.getGeslacht().toString() + i, team.getKlasse().toString() + i,  "",
                    "06123123" + i + i, "voornaam" + i + ".achternaam" + i + "@example.com",
                    adres, (startJaar + i), team.getGeslacht());
            lid.setTeam(team);
            vereniging.addLid(lid);
        }
    }
}
