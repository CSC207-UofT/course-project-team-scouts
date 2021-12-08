import data.PlayerDatabase;
import data.TeamDatabase;
import entities.Player;
import entities.Team;
import org.junit.Before;
import org.junit.Test;
import services.CSVAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class CSVAdapterTest {
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
    public void testIsolatePosition() {
        String position1 = "LW";
        String position2 = "RW, CF";
        assertEquals("LW", adapter.isolatePosition(position1));
        assertEquals("RW", adapter.isolatePosition(position2));
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
                "shot power", "jumping", "stamina", "strength",
                "long shots", "aggression", "interceptions",
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
    public void testProcessFile() {
        PlayerDatabase playerDatabase = new PlayerDatabase();
        TeamDatabase teamDatabase = new TeamDatabase();
        adapter.processFile("dataset(s)/testing_subset.csv", playerDatabase, teamDatabase);
        assertEquals(playerDatabase.getEntities().size(), 3);
        assertEquals(teamDatabase.getEntities().size(), 3);
    }

    @Test
    public void testFileMissing() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        PlayerDatabase playerDatabase = new PlayerDatabase();
        TeamDatabase teamDatabase = new TeamDatabase();
        adapter.processFile("not_a_file.csv", playerDatabase, teamDatabase);
        assertEquals("An error has occurred while loading in player/team data!\r\n" +
                "Please ensure that 'players_20.csv' is located in the 'dataset(s)' folder.\r\n",
                outContent.toString());
    }
}