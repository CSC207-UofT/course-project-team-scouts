import data.PlayerDatabase;
import data.TeamDatabase;
import entities.Team;
import search.RatingType;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;
import search.SearchByTeamRating;
import services.CSVAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SearchByTeamRatingsTest {
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
    public void testTeamRatingSearchAllRatingsSpecificNetWorth() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("Overall Rating", new Pair<>(0, 100));
        queries.put("Offensive Rating", new Pair<>(0, 100));
        queries.put("Defensive Rating", new Pair<>(0, 100));
        queries.put("Net Worth", new Pair<>(95500000, 95500000));
        List<Team> result = SearchByTeamRating.search(teamDatabase, queries, RatingType.ALL);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), "FC Barcelona");
    }

    @Test
    public void testTeamRatingSearchAllRatingsWorthOverallRange() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("Overall Rating", new Pair<>(0, 93));
        queries.put("Offensive Rating", new Pair<>(0, 100));
        queries.put("Defensive Rating", new Pair<>(0, 100));
        queries.put("Net Worth", new Pair<>(0, 100000000));
        List<Team> result = SearchByTeamRating.search(teamDatabase, queries, RatingType.ALL);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), "Juventus");
    }

    @Test
    public void testTeamRatingSearchOverallRating() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("Overall Rating", new Pair<>(94, 100));
        List<Team> result = SearchByTeamRating.search(teamDatabase, queries, RatingType.OVERALL);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), "FC Barcelona");
    }

    @Test
    public void testTeamRatingSearchOffensiveRating() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("Offensive Rating", new Pair<>(72, 100));
        List<Team> result = SearchByTeamRating.search(teamDatabase, queries, RatingType.OFFENSIVE);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), "FC Barcelona");
    }

    @Test
    public void testTeamRatingSearchDefensiveRating() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("Defensive Rating", new Pair<>(45, 50));
        List<Team> result = SearchByTeamRating.search(teamDatabase, queries, RatingType.DEFENSIVE);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), "Juventus");
    }

    @Test
    public void testTeamRatingSearchSpecificNetWorth() {
        Map<String, Pair<Number, Number>> queries = new HashMap<>();
        queries.put("Net Worth", new Pair<>(105500000, 105500000));
        List<Team> result = SearchByTeamRating.search(teamDatabase, queries, RatingType.NET_WORTH);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getName(), "Paris Saint-Germain");
    }
}