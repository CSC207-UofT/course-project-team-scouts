package data;

import entities.Player;
import entities.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for storing Team Data as a list of Team entities
 */
public class TeamDatabase extends Database<Team> {
    /** 
     * Updates the given team's roster to include the given
     * Player object. If team is not found, add the team with
     * the given player.
     * 
     * @param teamName String representing team name
     * @param player   Player object to be added to the team
     */
    public void updateRoster(String teamName, Player player) {
        for (Team t : entityList) {
            if (t.getTeamName().equals(teamName)) {
                t.addPlayer(player);
                player.updateTeam(teamName);
                return;
            }
        }
        // Team has not been found, we add a new team
        Team team = new Team(teamName, new ArrayList<>(List.of(player)));
        this.addEntity(team);
    }
}
