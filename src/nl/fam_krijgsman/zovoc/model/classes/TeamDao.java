package nl.fam_krijgsman.zovoc.model.classes;

import java.util.List;

interface TeamDao {
    List<Team> getTeams();
    Team findTeam(String naam);
    boolean addTeam(Team team);
    boolean removeTeam(Team team);
    boolean updateTeam(Team team);
}
