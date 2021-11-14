import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class SearchForPlayer{
    public static List<Player> searchPlayer(String target) {
        String lower_t  = target.toLowerCase(Locale.ROOT);

        List<Player> playerList = PlayerDatabase.getPlayers();
        List<Player> validPlayers = new ArrayList<>();

        for (Player p : playerList) {
            String n = p.getName();
            String lower_n = n.toLowerCase(Locale.ROOT);

            int score = LevenshteinDistance.getDefaultInstance().apply(lower_t,lower_n);
            if ((score < 5) | (lower_n.contains(lower_t))) {
                validPlayers.add(p);
            }
        }
        return validPlayers;
    }
}