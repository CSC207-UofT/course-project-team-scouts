import entities.Player;
import entities.Team;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTeam {
    Team testTeam;

    @Before
    public void setup(){
        Player testPlayer = new Player();
        List<Player> players = new ArrayList<>();
        players.add(testPlayer);
        this.testTeam = new Team("Scouts", players);
    }

    @Test
    public void testGetTeamName() {
        String expectedTeamName = "Scouts";
        String actualTeamName = testTeam.getName();
        assertEquals(expectedTeamName, actualTeamName);
    }

    @Test
    public void testAddPlayer() {
        Player testPlayer = new Player();
        List<Player> players = new ArrayList<>();
        players.add(testPlayer);
        players.add(testPlayer);

        Team expectedTeam = new Team("Scouts", players);

        this.testTeam.addPlayer(testPlayer);
        Team actualTeam = this.testTeam;

        assertEquals(expectedTeam.getPlayers().size(), actualTeam.getPlayers().size());
    }

    @Test
    public void testGetPlayers() {
        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player());

        List<Player> actualPlayers = this.testTeam.getPlayers();

        assertSame(expectedPlayers.size(), actualPlayers.size());
    }
}