import data.PlayerDatabase;
import entities.Player;
import entities.Team;
import org.junit.Before;
import org.junit.Test;
import services.CSVAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTeam {

    Team club;
    Player testPlayer;

    @Before
    public void setup(){
        testPlayer = new Player();
        CSVAdapter adapter = new CSVAdapter();
        PlayerDatabase playerdatabase = new PlayerDatabase();
        data.TeamDatabase teamdatabase = new data.TeamDatabase();
        adapter.processFile("dataset(s)/players_20.csv", playerdatabase, teamdatabase);
        List<Player> roster = new ArrayList<>();
        roster.add(testPlayer);
        club = new Team("Fc Barcelona", roster);
    }


    @Test
    public void testGetTeamName() {
        assertEquals("Fc Barcelona", club.getTeamName());
    }

    @Test
    public void testAddPlayer() {
        club.addPlayer(testPlayer);
        assertEquals(2, club.getPlayers().size());
    }

    @Test
    public void testGetPlayers() {
        Player testPlayerOne;
        testPlayerOne = new Player();
        Player testPlayerTwo;
        testPlayerTwo = new Player();
        club.addPlayer(testPlayerOne);
        club.addPlayer(testPlayerTwo);
        assertEquals(3, club.getPlayers().size());
    }


}