package entities;

import java.io.Serializable;
import java.util.List;

public class Team implements Serializable, Identifiable {
    final List<Player> players;
    private final String teamName;

    public Team(String teamName, List<Player> players) {
        this.teamName = teamName;
        this.players = players;
    }

    public String getName() {
        return teamName;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
