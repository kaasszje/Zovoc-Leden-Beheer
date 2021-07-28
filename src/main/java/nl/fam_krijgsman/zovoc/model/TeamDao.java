package nl.fam_krijgsman.zovoc.model;

import java.util.List;

public interface TeamDao {
    List<Team> getTeams();
    Team findTeam(String naam);
    boolean addTeam(Team team);
    boolean removeTeam(Team team);
}
