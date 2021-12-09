package entities;

import java.io.Serializable;
import java.util.List;

public class Team implements Serializable, Identifiable {
    final List<Player> players;
    private final String teamName;

    /**
     * Required constructor for Team
     *
     * @param teamName the name of the team
     * @param players a list of Player entities belonging to the team
     */
    public Team(String teamName, List<Player> players) {
        this.teamName = teamName;
        this.players = players;
    }

    /**
     * Returns the name of the team.
     *
     * @return the name of the team
     */
    public String getName() {
        return teamName;
    }

    /**
     * Adds a Player entity to the Team.
     *
     * @param player the Player entity to be added to the team
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Returns a list of the players on the Team.
     *
     * @return list of Player entities
     */
    public List<Player> getPlayers() {
        return players;
    }
}
