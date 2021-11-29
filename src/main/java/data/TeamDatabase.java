package data;

import entities.Player;
import entities.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for storing Team Data as a list of Team entities
 */
public class TeamDatabase extends Database {
    private List<Team> teams = new ArrayList<>();

    /**
     * Adds new team to Team database
     *
     * @param name    team name as string
     * @param players arraylist of players on team
     */
    public Team createTeam(String name, List<Player> players) {
        return new Team(name, players);
    }

    @Override
    public void addEntity(Team team) {
        teams.add(team);
    }

    public void updateRoster(String t_name, Player player) {
        for (Team t : teams) {
            if (t.getTeamName().equals(t_name)) {
                t.addPlayer(player);
            }
        }
    }

    /**
     * Setter for Teams
     *
     * @param teamList list of  entities
     */
    public void setTeams(List<Team> teamList) {
        teams = teamList;
    }

    /**
     * Getter for Teams
     *
     * @return list of Team entities
     */
    public List<Team> getTeams() {
        return teams;
    }
}
