package search;

import data.Database;
import data.PlayerDatabase;
import entities.Player;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchForPlayer {
    public static List<Identifiable> searchPlayer(Database<Identifiable> database, String target) {
        String lower_t = target.toLowerCase(Locale.ROOT);

        List<Identifiable> entityList = database.getEntities();
        List<Identifiable> validEntities = new ArrayList<>();

        for (Identifiable entity : entityList) {
            String n = entity.getName();
            String lower_n = n.toLowerCase(Locale.ROOT);

            int score = LevenshteinDistance.getDefaultInstance().apply(lower_t, lower_n);
            if ((score < 5) | (lower_n.contains(lower_t))) {
                validEntities.add(entity);
            }
        }
        return validEntities;
    }
}