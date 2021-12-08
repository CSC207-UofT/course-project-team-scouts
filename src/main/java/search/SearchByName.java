package search;

import data.Database;
import entities.Identifiable;
import entities.Player;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchByName<T> {
    public List<T> search(Database<T> database, String target) {
        String fTarget = target.toLowerCase(Locale.ROOT).strip();

        if (fTarget.equals("")) {
            return new ArrayList<>();
        }

        List<T> entityList = database.getEntities();
        List<T> validEntities = new ArrayList<>();

        for (T entity : entityList) {
            String n = ((Identifiable) entity).getName();
            String lower_n = n.toLowerCase(Locale.ROOT);

            int score = LevenshteinDistance.getDefaultInstance().apply(fTarget, lower_n);
            if ((score < 5) | (lower_n.contains(fTarget))) {
                validEntities.add(entity);
            }
        }
        return validEntities;
    }
}