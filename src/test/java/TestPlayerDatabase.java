import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class TestPlayerDatabase {

    Player testPlayer = new Player();

    @Test
    public void testAddEntity() {
        PlayerDatabase.add_entity(testPlayer);
        assertTrue(PlayerDatabase.getPlayers().contains(testPlayer));
    }

    @Test
    public void testSetPlayers() {
        List<Player> footballers = new ArrayList<>();
        footballers.add(testPlayer);
        PlayerDatabase.setPlayers(footballers);
        assertEquals(footballers, PlayerDatabase.getPlayers());
    }

    @Test
    public void testGetPlayers() {
        List<Player> empty = new ArrayList<>();
        assertEquals(PlayerDatabase.getPlayers(), empty);

        List<Player> footballers = new ArrayList<>();
        footballers.add(testPlayer);
        assertNotEquals(PlayerDatabase.getPlayers(), footballers);

        footballers.add(testPlayer);
        assertNotEquals(PlayerDatabase.getPlayers(), footballers);
    }

    @After
    public void tearDown(){
        List<Player> empty = new ArrayList<>();
        PlayerDatabase.setPlayers(empty);
    }

}