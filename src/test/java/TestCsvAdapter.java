import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class TestCsvAdapter {

    CSVAdapter adapter;

    @Before
    public void setUp(){
        adapter = new CSVAdapter();
    }

    @Test
    public void testStringToInt() {
        String t1 = "69";
        String t2 = "kanye West";
        assertEquals(69, adapter.stringToInt(t1));
        assertEquals(0, adapter.stringToInt(t2));
    }

    @Test
    public void testStringToDouble() {
        String t1 = "69.0";
        String t2 = "kanye West";
        assertEquals(69.0, adapter.stringToDouble(t1), 0.0);
        assertEquals(0.0, adapter.stringToDouble(t2), 0.0);
    }

    @Test
    public void testMakeHashMap() {
        String[] testArray = new String[35];
        HashMap<String, Integer> hashMapTest = new HashMap<>();
        String[] skillTypes = {"crossing" ,"finishing",
                "heading accuracy","short passing",
                "volleys","dribbling" ,"curve",
                "fk accuracy", "long passing",
                "ball control", "acceleration" , "sprint speed",
                "agility", "reactions", "balance",
                "shot_power", "jumping", "stamina", "strength",
                "long_shots", "aggression", "interceptions",
                "positioning", "vision", "penalties",
                "composure","marking","standing tackle",
                "sliding tackle","goalkeeping diving","goalkeeping handling",
                "goalkeeping kicking","goalkeeping positioning","goalkeeping reflexes"};

        for (int i = 0; i <= skillTypes.length - 1; i = i + 1){
            hashMapTest.put(skillTypes[i], i);
            testArray[i] = String.valueOf(i);}

        assertEquals(hashMapTest, adapter.makeHashMap(testArray));
    }

    @Test
    public void testUpdateTeamsDatabase() {
        Player test_player = new Player();
        String team_name = "Fc Barcelona";
        ArrayList<String> teams = new ArrayList<>();
        adapter.updateTeamsDatabase(team_name, teams, test_player);

        assertEquals(TeamDatabase.getTeams().size(), 1);

        // Teardown
        List<Team> empty = new ArrayList<>();
        TeamDatabase.setTeams(empty);
    }


    @Test
    public void testDataDump() {
        adapter.dataDump("dataset(s)/testing_subset.csv");
        assertEquals(PlayerDatabase.getPlayers().size(), 3);
        assertEquals(TeamDatabase.getTeams().size(), 3);

        // TearDown
        List<Team> empty = new ArrayList<>();
        List<Player> empty2 = new ArrayList<>();
        TeamDatabase.setTeams(empty);
        PlayerDatabase.setPlayers(empty2);
    }
}