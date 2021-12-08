import data.PlayerDatabase;
import data.TeamDatabase;
import entities.Player;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import search.SearchByPlayerAttributes;
import services.CSVAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SearchByPlayerAttributesTest {
    PlayerDatabase playerDatabase;
    TeamDatabase teamDatabase;
    CSVAdapter adapter;

    @Before
    public void setUp() {
        playerDatabase = new PlayerDatabase();
        teamDatabase = new TeamDatabase();
        adapter = new CSVAdapter();
        adapter.processFile("dataset(s)/testing_subset.csv", playerDatabase, teamDatabase);
    }

    @Test
    public void testPlayerAttributeSearchMaxRange() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("age", new Pair<>(0, 250));
        queries.put("weight", new Pair<>(0, 250));
        queries.put("height", new Pair<>(0, 250));
        List<Player> result = SearchByPlayerAttributes.search(playerDatabase, queries);
        assert (result.size() == 3);
    }

    @Test
    public void testPlayerAttributeSearchSpecificAge() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("age", new Pair<>(34, 34));
        queries.put("weight", new Pair<>(0, 250));
        queries.put("height", new Pair<>(0, 250));
        List<Player> result = SearchByPlayerAttributes.search(playerDatabase, queries);
        assert (result.size() == 1);
        assertEquals(result.get(0).getName(), "Cristiano Ronaldo");
    }

    @Test
    public void testPlayerAttributeSearchAgeHeightRange() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("age", new Pair<>(32, 34));
        queries.put("weight", new Pair<>(0, 250));
        queries.put("height", new Pair<>(175, 187));
        List<Player> result = SearchByPlayerAttributes.search(playerDatabase, queries);
        assert (result.size() == 1);
        assertEquals(result.get(0).getName(), "Cristiano Ronaldo");
    }
}