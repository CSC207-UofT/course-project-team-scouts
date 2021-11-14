package data;

import entities.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for storing Player Data as a list of player entities
 */
public class PlayerDatabase {
    private static List<Player> players = new ArrayList<>();

    public PlayerDatabase() {
    }

    /**
     * Adds new player to Player database
     *
     * @param player player object to be added to database
     */
    public static void add_entity(Player player) {
        players.add(player);
    }

    /**
     * Setter for PLayers
     *
     * @param playerList list of player entities
     */
    public static void setPlayers(List<Player> playerList) {
        players = playerList;
    }

    /**
     * Getter for Players
     *
     * @return Players list of player entities
     */
    public static List<Player> getPlayers() {
        return players;
    }
}
