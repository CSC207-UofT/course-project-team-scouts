package search;

import data.PlayerDatabase;
import entities.Player;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchByPlayerAttributes {

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

    public static Map<String, Number> playerAttributes(Player player) {
        Map<String, Number> attributes = new HashMap<>();
        attributes.put("age", player.getAge());
        attributes.put("weight", player.getWeight());
        attributes.put("height", player.getHeight());
        attributes.putAll(player.getSkills());
        return attributes;
    }
}
