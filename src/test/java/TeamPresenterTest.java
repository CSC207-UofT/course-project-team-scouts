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
    private HashMap<String, Integer> defaultSkills;
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

        defaultSkills = new HashMap<>();
        String[] skillTypes = {"crossing" ,"finishing",
                "heading accuracy","short passing",
                "volleys","dribbling" ,"curve",
                "fk accuracy", "long passing",
                "ball control", "acceleration" , "sprint speed",
                "agility", "reactions", "balance",
                "shot_power", "jumping", "stamina", "strength",
                "long_shots", "aggression", "interceptions",
                "positioning", "vision", "penalties",
                "composure","marking","standing tackle",
                "sliding tackle","goalkeeping diving","goalkeeping handling",
                "goalkeeping kicking","goalkeeping positioning","goalkeeping reflexes"};
        for (int i = 0; i <= skillTypes.length - 1; i = i + 1){
            defaultSkills.put(skillTypes[i], 10);
        }
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
//    @Test
//    public void testOutputResultsWithTeam() {
//        List<Team> teams = new ArrayList<>();
//        List<Player> players = new ArrayList<>();
//
//        players.add(new Player("Cristiano Ronaldo", 35, 181, 75, "Real Madrid",
//                92, 90000000, "LW", defaultSkills));
//        players.add(new Player("Lionel Messi", 33, 175, 70, "Real Madrid",
//                94, 90000000, "CF", defaultSkills));
//        teams.add(new Team("Real Madrid", players));
//
//        presenter.outputResults(teams);
//        assertEquals(
//                "================================================\n"+
//                        "Team id: 0\n"+
//                        "Team name: Real Madrid\n"+
//                        "Overall Rating: 93\n"+
//                        "Net worth: 180000000\n"+
//                        "Number of players: 2\n"+
//                        "================================================\n", outputStreamCaptor.toString());
//    }
}

