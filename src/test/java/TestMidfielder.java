import entities.Player;
import entities.Midfielder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestMidfielder {
    final Player testPlayer2 = new Player();
    Player testPlayer = new Player();
    //set up for tests
    @Before
    public void setUp() {
        HashMap<String, Integer> testSkills = new HashMap<>();
        testSkills.put("dribbling", 10);
        testSkills.put("sprint speed", 20);
        testSkills.put("acceleration", 30);
        testSkills.put("volleys", 40);
        testSkills.put("positioning", 80);
        testSkills.put("short passing", 40);
        testSkills.put("long passing", 40);
        testSkills.put("ball control", 40);
        testSkills.put("fk accuracy", 40);
        this.testPlayer = new Midfielder("John", 20, 100, 100, "Scouts", 90,
                1000, "Defender", testSkills);
    }

    @Test
    public void testGetName() {
        String expectedName = "John";
        String actualName = testPlayer.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetSkills() {
        HashMap<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("dribbling", 10);
        expectedSkills.put("sprint speed", 20);
        expectedSkills.put("acceleration", 30);
        expectedSkills.put("positioning", 80);
        expectedSkills.put("volleys", 40);
        expectedSkills.put("short passing", 40);
        expectedSkills.put("long passing", 40);
        expectedSkills.put("ball control", 40);
        expectedSkills.put("fk accuracy", 40);
        HashMap<String, Integer> actualSkills = testPlayer.getSkills();
        assertEquals(expectedSkills, actualSkills);
    }
    //testing for getPosisitionSkills for Midfielder
    @Test
    public void testGetPositionSkills() {
        HashMap<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("dribbling", 10);
        expectedSkills.put("sprint speed", 20);
        expectedSkills.put("acceleration", 30);
        expectedSkills.put("volleys", 40);
        expectedSkills.put("positioning", 80);
        expectedSkills.put("short passing", 40);
        expectedSkills.put("long passing", 40);
        expectedSkills.put("ball control", 40);
        expectedSkills.put("fk accuracy", 40);
        HashMap<String, Integer> actualSkills = testPlayer.getPositionSkills();
        assertEquals(expectedSkills, actualSkills);
    }
}