package data;

import entities.Player;
import entities.Team;

/**
 * Responsible for storing Team Data as a list of Team entities
 */
public class TeamDatabase extends Database<Team> {
    // Unnecessary! (I think)
    // /**
    //  * Adds new team to Team database
    //  *
    //  * @param name    team name as string
    //  * @param players arraylist of players on team
    //  */
    // public Team createTeam(String name, List<Player> players) {
    //     return new Team(name, players);
    // }
    
    /** 
     * Updates the given team's roster to include the given
     * Player object
     * 
     * @param teamName String representing team name
     * @param player   Player object to be added to the team
     */
    public void updateRoster(String teamName, Player player) {
        for (Team t : entityList) {
            if (t.getTeamName().equals(teamName)) {
                t.addPlayer(player);
                player.updateTeam(teamName);
            }
        }
    }
}
