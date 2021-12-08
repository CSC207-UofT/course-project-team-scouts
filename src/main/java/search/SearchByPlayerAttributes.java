package search;

import data.PlayerDatabase;
import entities.Player;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchByPlayerAttributes {

    /**
     * Searches the database of existing Players in the program for all
     * those that match the conditions provided by the user in their specific
     * query.
     *
     * @param playerDatabase a database of all existing Players in the program.
     * @param queries a map of Player attributes to the user's desired value ranges for each.
     * @return the list of Player entities that match the user's search.
     */
    public static List<Player> search(PlayerDatabase playerDatabase,
                                      Map<String, Pair<Number, Number>> queries) {
        List<Player> playerList = playerDatabase.getEntities();
        List<Player> validPlayers = new ArrayList<>();

        for (Player p : playerList) {
            Map<String, Number> attributes = playerAttributes(p);
            boolean isValid = SearchHelper.validEntity(attributes, queries);

            if (isValid) {
                validPlayers.add(p);
            }
        }
        return validPlayers;
    }

    /**
     * Generates a Map of all the specific Player attributes considered by the user to
     * their specific values for the given Player provided as an argument.
     *
     * @param player a single Player in the database.
     * @return a Map of Player attributes to their specific values for a single player.
     */
    public static Map<String, Number> playerAttributes(Player player) {
        Map<String, Number> attributes = new HashMap<>();
        attributes.put("age", player.getAge());
        attributes.put("weight", player.getWeight());
        attributes.put("height", player.getHeight());
        attributes.putAll(player.getSkills());
        return attributes;
    }
}
