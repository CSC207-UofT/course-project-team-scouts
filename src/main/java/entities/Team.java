package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable, Identifiable {
    private final String team_name;
    List<Player> players;

    public Team() {
        this.team_name = "";
        this.players = new ArrayList<>();
    }

    public Team(String team_name, List<Player> players) {
        this.team_name = team_name;
        this.players = players;
    }


    public String getName() {
        return team_name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

}
