import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class InputPlayerAttributesTest {

    @Before
    public void setUp() throws Exception {
        CSVAdapter adapter = new CSVAdapter();
        adapter.dataDump("dataset(s)/players_20.csv");
    }

    @Test
    public void runTest()  throws IOException{
        List<Player> expectedPlayers;
        expectedPlayers = new ArrayList<Player>();
//        expectedPlayers.add(new Player("L. Messi", 32, 170, , ));
        //     public Player(String name, int age, double height, double weight, String team, boolean scouted, String position,
        //                  HashMap<String, Integer> skills)
        ArrayList<Integer> attributes = new ArrayList<Integer>();
        attributes.add(30); // Searching for players within the range of 20 to 40 years of age.
        attributes.add(100); // Searching for players within the range of 90 to 110 cm of height.
        attributes.add(110); // Searching for players within the range of 100 to 120 kg of weight.
        attributes.add(50); // Searching for players within the range of 42 to 58 score of strength.
        attributes.add(45); // Searching for players within the range of 37 to 53 score of stamina.
        List<Player> players = new ArrayList<Player>(SearchByPlayerAttributes.searchPlayer(attributes));
        boolean truth_value;
        truth_value = false;
        for(Player p : players){
            if (p.getName().equals("L. Messi")){
                truth_value = true;
            }

        assertTrue(truth_value);
        }







    }
}