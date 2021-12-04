package search;

import data.PlayerDatabase;
import entities.Player;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchForPlayer {
    public static List<Player> searchPlayer(PlayerDatabase playerDatabase, String target) {
        String lower_t = target.toLowerCase(Locale.ROOT);

        List<Player> playerList = playerDatabase.getEntities();
        List<Player> validPlayers = new ArrayList<>();

        for (Player p : playerList) {
            String n = p.getName();
            String lower_n = n.toLowerCase(Locale.ROOT);

            int score = LevenshteinDistance.getDefaultInstance().apply(lower_t, lower_n);
            if ((score < 5) | (lower_n.contains(lower_t))) {
                validPlayers.add(p);
            }
        }
        return validPlayers;
    }
}