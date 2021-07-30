package nl.fam_krijgsman.zovoc.model;

import java.util.ArrayList;

public class Vereniging implements LidDao, TeamDao {
    private final String naam;
    private final ArrayList<Lid> leden;
    private final ArrayList<Team> teams;

    public Vereniging(String naam) {
        this.naam = naam;
        this.leden = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public String getNaam() {
        return this.naam;
    }

    public ArrayList<Lid> getLeden() {
        return this.leden;
    }

    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    private Lid findLid(Lid lid) {
        return findLid(lid.getAchterNaam(), lid.getVoorNaam());
    }

    public Lid findLid(String achterNaam, String voorNaam) {
        for (Lid lid : this.leden) {
            if ((lid.getAchterNaam().equals(achterNaam)) && (lid.getVoorNaam().equals(voorNaam))) {
                return lid;
            }
        }
        return null;
    }

    public boolean addLid(Lid lid) {
        //controlleer of lid al bestaat
        if (findLid(lid) == null) {
            this.leden.add(lid);
            return true;
        }
        //lid bestond al
        return false;
    }

    public boolean removeLid(Lid lid) {
        //controlleer of lid voorkomt
        if (findLid(lid) != null) {
            this.leden.remove(lid);
            return true;
        }
        //lid bestond nog niet
        return false;
    }

    private Team findTeam(Team team) {
        return findTeam(team.getNaam());
    }

    public Team findTeam(String naam) {
        for (Team team : this.teams) {
            if (team.getNaam().equals(naam)) {
                return team;
            }
        }
        return null;
    }

    public boolean addTeam(Team team) {
        //controlleer of team al bestaat
        if (findTeam(team) == null) {
            this.teams.add(team);
            return true;
        }
        // team bestond all
        return false;
    }

    public boolean removeTeam(Team team) {
        //controlleer of te verwijderen team voorkomt
        if (findTeam(team) != null) {
            teams.remove(team);
            return true;
        }
        // team kwam niet voor
        return false;
    }

    public int aantalLeden() {
        return this.leden.size();
    }
}
