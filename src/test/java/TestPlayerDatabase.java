import data.PlayerDatabase;
import entities.Player;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class TestPlayerDatabase {

    Player testPlayer = new Player();

    @Test
    public void testAddEntity() {
        PlayerDatabase playerDatabase = new PlayerDatabase();
        playerDatabase.addEntity(testPlayer);
        assertEquals(1, playerDatabase.getEntities().size());
        assertSame(testPlayer, playerDatabase.getEntities().get(0));
    }

    @Test
    public void testSetEntities() {
        List<Player> footballers = new ArrayList<>();
        PlayerDatabase playerDatabase = new PlayerDatabase();
        footballers.add(testPlayer);
        playerDatabase.setEntities(footballers);
        assertSame(footballers, playerDatabase.getEntities());
    }

    @Test
    public void testGetEntities() {
        PlayerDatabase playerDatabase = new PlayerDatabase();
        assertTrue(playerDatabase.getEntities().isEmpty());
        List<Player> footballers = new ArrayList<>();
        footballers.add(testPlayer);
        assertSame(footballers, playerDatabase.getEntities());
    }
}