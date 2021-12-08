import data.TeamDatabase;
import entities.Player;
import entities.Team;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTeamDatabase {

    Team club;
    Player test_player;

    @Before
    public void setup() {
        test_player = new Player();
        List<Player> roster = new ArrayList<>();
        roster.add(test_player);
        club = new Team("Fc Barcelona", roster);
    }

    @Test
    public void testAddEntity() {
        TeamDatabase teamDatabase = new TeamDatabase();
        teamDatabase.addEntity(club);
        assertEquals(1, teamDatabase.getEntities().size());
        assertEquals(teamDatabase.getEntities().get(0), club);
    }

    @Test
    public void testUpdateRosterKnownTeam() {
        Player testPlayer2 = new Player();
        TeamDatabase teamDatabase = new TeamDatabase();
        teamDatabase.addEntity(club);
        teamDatabase.updateRoster("Fc Barcelona", testPlayer2);
        assertTrue(club.getPlayers().contains(testPlayer2));
        assertEquals(club.getName(), testPlayer2.getTeam());
    }

    @Test
    public void testUpdateRosterUnknownTeam() {
        Player testPlayer2 = new Player();
        TeamDatabase teamDatabase = new TeamDatabase();
        teamDatabase.updateRoster("Calver Club", testPlayer2);
        assertEquals(1, teamDatabase.getEntities().size());
        assertSame(teamDatabase.getEntities().get(0).getPlayers().get(0), testPlayer2);
    }

    @Test
    public void testSetEntities() {
        List<Team> test_teams = new ArrayList<>();
        test_teams.add(club);
        TeamDatabase teamDatabase = new TeamDatabase();
        teamDatabase.setEntities(test_teams);
        assertSame(teamDatabase.getEntities(), test_teams);
    }

    @Test
    public void testGetEntities() {
        TeamDatabase teamDatabase = new TeamDatabase();
        List<Team> test_teams = new ArrayList<>();
        test_teams.add(club);
        assertTrue(teamDatabase.getEntities().isEmpty());
        teamDatabase.setEntities(test_teams);
        assertSame(teamDatabase.getEntities(), test_teams);
    }

}