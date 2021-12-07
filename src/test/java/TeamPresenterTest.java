import entities.Player;
import entities.Team;
import io.TeamsPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import search.SearchByName;
import data.Database;
import search.SearchByName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TeamPresenterTest {
    /*
    Code for testing results printed to standard output originally designed and shared
    by Jonathan Cook of baeldung.com (August 13, 2020)

    https://www.baeldung.com/java-testing-system-out-println
     */
    private TeamsPresenter presenter;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private List<Team> searchResults;

    @Before
    public void setUp() {
        // Create a presenter object to be used.
        presenter = new TeamsPresenter();
        Database<Team> database;
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testOutputResultsWithNoTeam() {
        Team t = new Team();
        String teamName = "";
        data.TeamDatabase database = new data.TeamDatabase();
        SearchByName<Team> searchByName = new SearchByName<>();
        this.searchResults = searchByName.search(database, teamName);
        TeamsPresenter tPresenter = new TeamsPresenter();
        tPresenter.outputResults(searchResults);
        // Call outputResults to have the code run, then we capture it's output for assertion.
        // If there are no players,
        assertEquals(
                "================================================\n" + "No matching teams found.\n", outputStreamCaptor.toString());
    }
    /*
    @Test
    public void testOutputResultsWithTeam() {
        Team t = new Team();
        String teamName = "Real Madrid";
        data.TeamDatabase database = new data.TeamDatabase();
        SearchByName<Team> searchByName = new SearchByName<>();
        this.searchResults = searchByName.search(database, teamName);
        TeamsPresenter tPresenter = new TeamsPresenter();
        tPresenter.outputResults(searchResults);
        assertEquals(
                "=============================================\n" + "No matching teams found.\n", outputStreamCaptor.toString());
    }

     */
}

