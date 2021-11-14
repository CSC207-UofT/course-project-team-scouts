import entities.Player;
import entities.Team;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTeam {

    Team club;
    Player test_player;

    @Before
    public void setup(){
        test_player = new Player();
        List<Player> roster = new ArrayList<>();
        roster.add(test_player);
        club = new Team("Fc Barcelona", roster);
    }


    @Test
    public void testGetTeamName() {
        assertEquals("Fc Barcelona", club.getTeamName());
    }

    @Test
    public void testAddPlayer() {
        club.addPlayer(test_player);
        assertEquals(2, club.getPlayers().size());
    }

    @Test
    public void testGetPlayers() {
        assertEquals(1, club.getPlayers().size());
    }

}