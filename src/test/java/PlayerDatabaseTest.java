import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class PlayerDatabaseTest {

    Player testPlayer = new Player();

    @Test
    public void addEntityTest() {
        PlayerDatabase.add_entity(testPlayer);
        assertTrue(PlayerDatabase.getPlayers().contains(testPlayer));
    }

    @Test
    public void setPlayersTest() {
        List<Player> footballers = new ArrayList<>();
        footballers.add(testPlayer);
        PlayerDatabase.setPlayers(footballers);
        assertEquals(PlayerDatabase.getPlayers(), footballers);
    }

    @Test
    public void getPlayersTest() {
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