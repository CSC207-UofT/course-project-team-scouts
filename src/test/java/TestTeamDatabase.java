import data.TeamDatabase;
import entities.Player;
import entities.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTeamDatabase {

    Team club;
    List<Player> roster;
    Player test_player;

    @Before
    public void setup(){
        test_player = new Player();
        List<Player> roster = new ArrayList<>();
        roster.add(test_player);
        club = new Team("Fc Barcelona", roster);
        TeamDatabase.addEntity("Fc Barcelona", roster);
    }

    @Test
    public void testAddEntity() {
        TeamDatabase.addEntity("Fc Barcelona", roster);
        assertEquals(2, TeamDatabase.getTeams().size());
    }

    @Test
    public void testUpdateRoster() {
        TeamDatabase.updateRoster("Fc Barcelona", test_player);
        int test_team_size = TeamDatabase.getTeams().get(0).getPlayers().size();
        assertEquals(2, test_team_size);
    }

    @Test
    public void testSetTeams() {
        List<Team> test_teams = new ArrayList<>();
        test_teams.add(club);
        test_teams.add(club);
        TeamDatabase.setTeams(test_teams);
        assertEquals(2, TeamDatabase.getTeams().size());
    }

    @Test
    public void testGetTeams() {
        assertEquals(1, TeamDatabase.getTeams().size());
    }

    @After
    public void teardown(){
        List<Team> empty = new ArrayList<>();
        TeamDatabase.setTeams(empty);
    }
}