package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * A team with its players and teamname.
 */
public class Team {
    /**
     * The name of team.
     */
    private final String teamName;
    /**
     * The list of players.
     */
    List<Player> players;
    /**
     * Default constructor
     */
    public Team() {
        this.teamName = "";
        this.players = new ArrayList<>();
    }
    /**
     * A new team with nqme and list of players.
     *
     * @param team_name the name of team.
     * @param players the list of players.
     */
    public Team(String team_name, List<Player> players) {
        this.teamName = team_name;
        this.players = players;
    }

    /**
     * Return the name of the team.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Add the player to the team.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Return the list of players.
     */
    public List<Player> getPlayers() {
        return players;
    }

}
