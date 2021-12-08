import data.PlayerDatabase;
import data.TeamDatabase;
import entities.Player;
import entities.Team;
import org.junit.Before;
import org.junit.Test;
import search.SearchByName;
import services.CSVAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchByNameTest {
    PlayerDatabase playerDatabase;
    TeamDatabase teamDatabase;
    CSVAdapter adapter;
    SearchByName<Player> searchByPlayerName;
    SearchByName<Team> searchByTeamName;

    @Before
    public void setUp() {
        playerDatabase = new PlayerDatabase();
        teamDatabase = new TeamDatabase();
        adapter = new CSVAdapter();
        adapter.processFile("dataset(s)/testing_subset.csv", playerDatabase, teamDatabase);
        searchByPlayerName = new SearchByName<>();
        searchByTeamName = new SearchByName<>();
    }

    @Test
    public void testPlayerSearchEmptyString() {
        List<Player> actual = searchByPlayerName.search(playerDatabase, "");
        List<Player> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    @Test
    public void testTeamSearchEmptyString() {
        List<Team> actual = searchByTeamName.search(teamDatabase, "");
        List<Team> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    @Test
    public void testPlayerSearchSpecific() {
        List<Player> result = searchByPlayerName.search(playerDatabase, "Messi");
        assert (result.size() == 1);
        assertEquals(result.get(0).getName(), "L. Messi");
    }

    @Test
    public void testTeamSearchSpecific() {
        List<Team> result = searchByTeamName.search(teamDatabase, "Barcelona");
        assert (result.size() == 1);
        assertEquals(result.get(0).getName(), "FC Barcelona");
    }
}