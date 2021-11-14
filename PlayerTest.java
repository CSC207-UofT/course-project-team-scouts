import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class PlayerTest {

    Player testPlayer = new Player();
    @Before
    public void setUp() throws Exception {
        CSVAdapter adapter = new CSVAdapter();
        adapter.dataDump("dataset(s)/players_20.csv");
    }

    @Test
    public void TestsetAge(){
        testPlayer.setAge(20);
        assertEquals(testPlayer.getAge(), 20);
    }
    @Test
    public void TestsetName(){
        testPlayer.setName("Kaartik");
        assertEquals(testPlayer.getName(), "Kaartik");
    }

}