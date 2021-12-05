package search;

import data.PlayerDatabase;
import entities.Player;

import org.javatuples.Pair;
import java.util.*;

public class SearchByPlayerAttributes {

    public static List<Player> search(PlayerDatabase playerDatabase,
                                            Map<String, Pair<Number, Number>> queries){
        List<Player> playerList = playerDatabase.getEntities();
        List<Player> validPlayers = new ArrayList<>();

        for (Player p : playerList) {
            Map<String, Number> attributes = playerAttributes(p);
            boolean addPlayer = addPlayer(attributes, queries);

            if (addPlayer) {
                validPlayers.add(p);
            }
        }
        return validPlayers;
    }

    public static Map<String,Number> playerAttributes(Player player){
        Map<String, Number> attributes = new HashMap<>();
        attributes.put("age", player.getAge());
        attributes.put("weight", player.getWeight());
        attributes.put("height", player.getHeight());
        attributes.putAll(player.getSkills());
        return attributes;
    }

    public static boolean addPlayer(Map<String, Number> attributes, Map<String, Pair<Number, Number>> queries){
        for (Map.Entry<String, Pair<Number, Number>> entry: queries.entrySet()){
            double val = (double) attributes.get(entry.getKey());
            double min = (double) entry.getValue().getValue0();
            double max = (double) entry.getValue().getValue1();
            if (!((min <= val) & (val <= max))){ return false;}
        }
      return true;
    }
}
