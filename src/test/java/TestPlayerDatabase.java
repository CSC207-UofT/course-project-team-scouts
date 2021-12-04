import data.PlayerDatabase;
import entities.Player;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class TestPlayerDatabase {

    Player testPlayer = new Player();

    @Test
    public void testAddEntity() {
        PlayerDatabase playerDatabase = new PlayerDatabase();
        playerDatabase.addEntity(testPlayer);
        assertTrue(playerDatabase.getPlayers().contains(testPlayer));
    }

    @Test
    public void testSetPlayers() {
        List<Player> footballers = new ArrayList<>();
        PlayerDatabase playerDatabase = new PlayerDatabase();
        footballers.add(testPlayer);
        playerDatabase.setPlayers(footballers);
        assertEquals(footballers, playerDatabase.getPlayers());
    }

    @Test
    public void testGetPlayers() {
        PlayerDatabase playerDatabase = new PlayerDatabase();
        playerDatabase.setPlayers(new ArrayList<>());
        List<Player> empty = new ArrayList<>();
        assertEquals(playerDatabase.getPlayers(), empty);

        List<Player> footballers = new ArrayList<>();
        footballers.add(testPlayer);
        assertNotEquals(playerDatabase.getPlayers(), footballers);

        footballers.add(testPlayer);
        assertNotEquals(playerDatabase.getPlayers(), footballers);
    }
}