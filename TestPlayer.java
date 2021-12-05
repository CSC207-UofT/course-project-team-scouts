import data.PlayerDatabase;
import entities.Player;
import org.junit.*;
import services.CSVAdapter;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestPlayer {


    Player testPlayer = new Player();
    @Before
    public void setUp() throws IOException {
        CSVAdapter adapter = new CSVAdapter();
        PlayerDatabase playerdatabase = new PlayerDatabase();
        data.TeamDatabase teamdatabase = new data.TeamDatabase();
        adapter.processFile("dataset(s)/players_20.csv", playerdatabase, teamdatabase);
    }

    @Test
    public void TestSetAge(){
        testPlayer.setAge(20);
        assertEquals(testPlayer.getAge(), 20);
    }
    @Test
    public void TestSetName(){
        testPlayer.setName("Kaartik");
        assertEquals(testPlayer.getName(), "Kaartik");
    }

    @Test
    public void TestDefaultHeight(){
        double height = testPlayer.getHeight();
        assertEquals(height, 0.0, 0);
    }

    @Test
    public void TestDefaultWeight(){
        double height = testPlayer.getWeight();
        assertEquals(height, 0.0, 0);
    }

    @Test
    public void TestDefaultRating(){
        double rating = testPlayer.getRating();
        assertEquals(rating, 0.0, 0);
    }

    @Test
    public void TestDefaultPosition(){
        assertNull(testPlayer.getPosition());
    }
    @Test
    public void TestUpdateTeam(){
        testPlayer.updateTeam("Scouts");
        assertEquals(testPlayer.getTeam(), "Scouts");
    }


}