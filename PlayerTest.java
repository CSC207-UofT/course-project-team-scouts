import org.junit.*;
import static org.junit.Assert.*;

public class PlayerTest {


    Player testPlayer = new Player();
    @Before
    public void setUp() throws Exception {
        CSVAdapter adapter = new CSVAdapter();
        adapter.dataDump("dataset(s)/players_20.csv");
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


}