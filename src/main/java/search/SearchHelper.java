package search;

import org.javatuples.Pair;

import java.util.Map;

public class SearchHelper {
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
