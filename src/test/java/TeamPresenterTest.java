import entities.Team;
import io.output.TeamsPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import search.SearchByName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TeamPresenterTest {
    final PrintStream standardOut = System.out;
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    /*
    Code for testing results printed to standard output originally designed and shared
    by Jonathan Cook of baeldung.com (August 13, 2020)

    https://www.baeldung.com/java-testing-system-out-println
     */
    TeamsPresenter presenter;
    List<Team> searchResults;

    @Before
    public void setUp() {
        // Create a presenter object to be used.
        presenter = new TeamsPresenter();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testOutputResultsWithNoTeam() {
        String teamName = "";
        data.TeamDatabase database = new data.TeamDatabase();
        SearchByName<Team> searchByName = new SearchByName<>();
        this.searchResults = searchByName.search(database, teamName);
        presenter.outputResults(searchResults);
        // Call outputResults to have the code run, then we capture it's output for assertion.
        // If there are no players,
        assertEquals(
                "================================================\r\n" + "No matching teams found.\r\n", outputStreamCaptor.toString());
    }
}

