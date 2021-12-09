package search;

import data.Database;
import entities.Identifiable;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchByName<T> {
    /**
     * Searches a database of entities to find all that have a name that is either
     * contains the target name entered by the user, is relatively similar to it,
     * or is exactly the same as it. Levenshtein Distance algorithm is used to provide
     * a fuzzy search functionality, where names that differ by less than 5 changes in
     * characters will still be returned (to account for typos).
     *
     * @param database a database of entities containing the kinds of items user is looking for.
     * @param target the name of the target entity the user is looking for.
     * @return a list of entities (from the database) matching the user's query.
     */
    public List<T> search(Database<T> database, String target) {
        String fTarget = target.toLowerCase(Locale.ROOT).strip();

        // If input is empty, we return an empty list
        if (fTarget.equals("")) {
            return new ArrayList<>();
        }

        List<T> entityList = database.getEntities();
        List<T> validEntities = new ArrayList<>();

        // Check if each entity is "valid", i.e. if they belong in the search results
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