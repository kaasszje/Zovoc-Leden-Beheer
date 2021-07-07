package nl.fam_krijgsman.zovoc.model.classes;

import java.util.ArrayList;

public class Vereniging {
    private String naam;
    private ArrayList<Lid> leden;
    private ArrayList<Team> teams;

    public Vereniging(String naam) {
        this.naam = naam;
        leden = new ArrayList<>();
        teams = new ArrayList<>();
    }

    public String getNaam() {
        return naam;
    }

    public ArrayList<Lid> getLeden() {
        return leden;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    private Lid findLid(Lid lid) {
        for (Lid currentLid : leden) {
            if (currentLid.equals(lid)) {
                return lid;
            }
        }
        return null;
    }

    public boolean addLid(Lid lid) {
        //controlleer of lid al bestaat
        if (findLid(lid) == null) {
            leden.add(lid);
            return true;
        }
        //lid bestond al
        return false;
    }

    public boolean removeLid(Lid lid) {
        //controlleer of lid voorkomt
        if (findLid(lid) != null) {
            leden.remove(lid);
            return true;
        }
        //lid bestond nog niet
        return false;
    }

    private Team findTeam(Team team) {
        for (Team currentTeam: teams) {
            if (currentTeam.equals(team)) {
                return team;
            }
        }
        return null;
    }

    public boolean addTeam(Team team) {
        //controlleer of team al bestaat
        if (findTeam(team) == null) {
            teams.add(team);
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
        return leden.size();
    }
}
