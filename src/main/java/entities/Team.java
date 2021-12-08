package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable, Identifiable {
    private final String teamName;
    List<Player> players;

    public Team() {
        this.teamName = "";
        this.players = new ArrayList<>();
    }

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
