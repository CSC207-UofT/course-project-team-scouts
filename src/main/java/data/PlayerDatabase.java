package data;

import entities.Player;
import entities.PlayerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Responsible for storing Player Data as a list of player entities
 */
public class PlayerDatabase extends Database {
    private List<Player> players = new ArrayList<>();

    /**
     * Adds new player to Player database
     *
     * @param player player object to be added to database
     */
    public Player createPlayer(String name, int age, double height, double weight,
                               String team, int rating, double value, String position,
                               HashMap<String, Integer> skills) {
        return PlayerFactory.makePlayer(name, age, height, weight, team, rating, value, position, skills);
    }
    @Override
    public void addEntity(Player player) {
        players.add(player);
    }

    /**
     * Setter for Players
     *
     * @param playerList list of player entities
     */
    public void setPlayers(List<Player> playerList) {
        players = playerList;
    }

    /**
     * Getter for Players
     *
     * @return Players list of player entities
     */
    public List<Player> getPlayers() {
        return players;
    }
}
