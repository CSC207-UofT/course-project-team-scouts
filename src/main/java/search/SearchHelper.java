package search;

import org.javatuples.Pair;

import java.util.Map;

public class SearchHelper {
    /**
     * Determines if an entity is valid by comparing the entity's attribute values
     * to the range of values desired by the user.
     *
     * @param attributes the map of attributes to their corresponding values for a single entity.
     * @param queries the map of attributes to value ranges that any entity should have to be returned by the search.
     * @return true if all the single entity's attributes are within the desired ranges, false otherwise.
     */
    public static boolean validEntity(Map<String, Number> attributes, Map<String, Pair<Number, Number>> queries) {
        for (Map.Entry<String, Pair<Number, Number>> entry : queries.entrySet()) {
            int val = (int) attributes.get(entry.getKey());
            int min = (int) entry.getValue().getValue0();
            int max = (int) entry.getValue().getValue1();
            if (!((min <= val) & (val <= max))) {
                return false;
            }
        }
        return true;
    }
}
