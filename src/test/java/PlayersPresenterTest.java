import entities.Player;
import io.PlayersPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayersPresenterTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    /*
    Code for testing results printed to standard output originally designed and shared
    by Jonathan Cook of baeldung.com (August 13, 2020)

    https://www.baeldung.com/java-testing-system-out-println
     */
    private PlayersPresenter presenter;
    private HashMap<String, Integer> defaultSkills;

    @Before
    public void setUp() {
        // Create a presenter object to be used.
        presenter = new PlayersPresenter();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create a HashMap of default skills
        /* TODO - if it turns out to be the case that HashMap entries can't be retrieved in order,
            this will break testing and cause unpredictable output, so we should consider
             using a sorted version, like TreeMap */
        defaultSkills = new HashMap<>();
        String[] skillTypes = {"crossing", "finishing",
                "heading accuracy", "short passing",
                "volleys", "dribbling", "curve",
                "fk accuracy", "long passing",
                "ball control", "acceleration", "sprint speed",
                "agility", "reactions", "balance",
                "shot_power", "jumping", "stamina", "strength",
                "long_shots", "aggression", "interceptions",
                "positioning", "vision", "penalties",
                "composure", "marking", "standing tackle",
                "sliding tackle", "goalkeeping diving", "goalkeeping handling",
                "goalkeeping kicking", "goalkeeping positioning", "goalkeeping reflexes"};
        for (int i = 0; i <= skillTypes.length - 1; i = i + 1) {
            defaultSkills.put(skillTypes[i], 10);
        }
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testOutputResultsWithNoPlayers() {
        List<Player> players = new ArrayList<>();

        // Call outputResults to have the code run, then we capture it's output for assertion.
        presenter.outputResults(players);

        // If there are no players, we expect the presenter to output nothing.
        assertEquals("No matching players found.\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void testOutputResultsWithWrongPlayers() {
        List<Player> players = new ArrayList<>();

        // Call outputResults to have the code run, then we capture it's output for assertion.
        presenter.outputResults(players);
        players.add(new Player("xyz", 35, 181, 75, "Manchester United",
                92, 90000000, "LW", defaultSkills));

        // If there are no players, we expect the presenter to output nothing.
        assertEquals("No matching players found.\r\n", outputStreamCaptor.toString());
    }

    @Test
    public void testOutputResultsWithTwoPlayers() {
        List<Player> players = new ArrayList<>();

        // Manually Create some Player objects
        players.add(new Player("Cristiano Ronaldo", 35, 181, 75, "Manchester United",
                92, 90000000, "LW", defaultSkills));
        players.add(new Player("Lionel Messi", 33, 175, 70, "Paris St. Germain",
                94, 90000000, "CF", defaultSkills));

        // Call outputResults to have the code run, then we capture it's output for assertion.
        presenter.outputResults(players);

        // We expect neat formatting of the table as follows.
        String expectedOutput =
                "                                      ID|                Name|                 Age|    Offensive Rating|    Defensive Rating|                  jumping|                 strength|                penalties|                composure|       goalkeeping diving|                    curve|                  stamina|                  volleys|  goalkeeping positioning|                finishing|             acceleration|                dribbling|                  balance|              positioning|             sprint speed|            short passing|               long_shots|             ball control|          standing tackle|     goalkeeping handling|         heading accuracy|     goalkeeping reflexes|              fk accuracy|      goalkeeping kicking|               aggression|                 crossing|            interceptions|             long passing|                   vision|                  marking|                reactions|           sliding tackle|               shot_power|                  agility|\r\n" +
                        "                                       0|   Cristiano Ronaldo|                  35|                  10|                  10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|\r\n" +
                        "                                       1|        Lionel Messi|                  33|                  10|                  10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|\r\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testOutputResultsWithChineseCharacters() {
        List<Player> players = new ArrayList<>();

        // Manually Create some Player objects
        players.add(new Player("Cristiano Ronaldo", 35, 181, 75, "Manchester United",
                92, 90000000, "LW", defaultSkills));
        players.add(new Player("王晓", 33, 175, 70, "Paris St. Germain",
                94, 90000000, "CF", defaultSkills));

        // Call outputResults to have the code run, then we capture it's output for assertion.
        presenter.outputResults(players);

        // We expect that for now, the player with Chinese characters in their name does not get output.
        String expectedOutput =
                "                                      ID|                Name|                 Age|    Offensive Rating|    Defensive Rating|                  jumping|                 strength|                penalties|                composure|       goalkeeping diving|                    curve|                  stamina|                  volleys|  goalkeeping positioning|                finishing|             acceleration|                dribbling|                  balance|              positioning|             sprint speed|            short passing|               long_shots|             ball control|          standing tackle|     goalkeeping handling|         heading accuracy|     goalkeeping reflexes|              fk accuracy|      goalkeeping kicking|               aggression|                 crossing|            interceptions|             long passing|                   vision|                  marking|                reactions|           sliding tackle|               shot_power|                  agility|\r\n" +
                        "                                       0|   Cristiano Ronaldo|                  35|                  10|                  10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|                       10|\r\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}