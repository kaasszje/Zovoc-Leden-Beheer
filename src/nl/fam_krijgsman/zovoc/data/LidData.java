package nl.fam_krijgsman.zovoc.data;

import nl.fam_krijgsman.zovoc.model.Adres;
import nl.fam_krijgsman.zovoc.model.Lid;
import nl.fam_krijgsman.zovoc.model.Team;
import nl.fam_krijgsman.zovoc.model.eGeslacht;

import java.util.ArrayList;

public class LidData {
    public static ArrayList<Lid> addLidData(ArrayList<Team> teams) {
        ArrayList<Lid> leden = new ArrayList<>();
        //teams moeten eerst bestaan
        Adres adres1 = new Adres("Malachietgroen", 27, "2718HB", "Zoetermeer");
        Lid lid1 = new Lid("Krijgsman","Martijn", "",
                "0626454220", "m.s.krijgsman@gmail.com",
                adres1, 1981, eGeslacht.MAN);
        lid1.setTeam(findTeam(teams, "Heren 1"));
        leden.add(lid1);

        Adres adres2 = new Adres("Marineblauw", 28, "2718KA", "Zoetermeer");
        Lid lid2 = new Lid("Ruijg", "Karin", "",
                "0626798570", "kaat.ruijg@gmail.com",
                adres2, 1982, eGeslacht.VROUW);
        lid2.setTeam(findTeam(teams, "Dames 1"));
        leden.add(lid2);

        Adres adres3 = new Adres("Rokkeveenseweg-Zuid", 176, "2718ER", "Zoetermeer");
        Lid lid3 = new Lid("Krijgsman", "Marga", "",
        "0616064870", "marga.krijgsman@gmail.com",
                adres3, 1953, eGeslacht.VROUW);
        lid3.setTeam(findTeam(teams, "Dames 1"));
        leden.add(lid3);

        Adres adres4 = new Adres("Zalkerbos", 138, "2716KG", "Zoetermeer");
        Lid lid4 = new Lid("Krijgsman", "Owen", "",
                "0612345611", "owen.krijgsman@fam-krijgsman.nl",
                adres4, 2016, eGeslacht.MAN);
        lid4.setTeam(findTeam(teams, "Heren 1"));
        leden.add(lid4);

        Lid lid5 = new Lid("Krijgsmna", "Lilly", "",
                "0612345612", "lilly@fam-krijgsman.nl",
                adres4, 2012, eGeslacht.VROUW);
        lid5.setTeam(findTeam(teams, "Dames 1"));
        leden.add(lid5);

        // generate full team voor heren 2, dames 2 en recreanten
        addLeden(leden, findTeam(teams, "Dames 2"), 1980);
        addLeden(leden, findTeam(teams, "Heren 2"), 1980);
        addLeden(leden, findTeam(teams, "Meisjes A1"), 2010);
        addLeden(leden, findTeam(teams, "Meisjes A2"), 2010);
        addLeden(leden, findTeam(teams, "Jongens A1"), 2010);
        addLeden(leden, findTeam(teams, "Jongens A2"), 2010);
        addLeden(leden, findTeam(teams, "Recreanten"), 1960);
        return leden;
    }

    private static void addLeden(ArrayList<Lid> leden, Team team, Integer startJaar) {
        Adres adres;
        Lid lid;
        for (int i=0;i<8;i++){
            adres = new Adres("straat " + i, i*10, "27" + i + i + "AA", "Zoetermeer");
            lid = new Lid(team.getGeslacht().toString() + i, team.getKlasse().toString() + i,  "",
                    "06123123" + i + i, "voornaam" + i + ".achternaam" + i + "@example.com",
                    adres, (startJaar + i), team.getGeslacht());
            lid.setTeam(team);
            leden.add(lid);
        }
    }
    
    private static Team findTeam(ArrayList<Team> teams, String teamNaam) {
        for (Team team: teams) {
            if (team.getNaam().equals(teamNaam)) {
                return team;
            }
        }
        return null;
    }
}
