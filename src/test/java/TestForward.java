import entities.Player;
import entities.Forward;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestForward {
    final Player testPlayer2 = new Player();
    Player testPlayer = new Player();

    @Before
    public void setUp() {
        HashMap<String, Integer> testSkills = new HashMap<>();
        testSkills.put("dribbling", 10);
        testSkills.put("finishing", 20);
        testSkills.put("sprint speed", 80);
        testSkills.put("crossing", 40);
        testSkills.put("short passing", 40);
        testSkills.put("ball control", 40);
        testSkills.put("fk accuracy", 40);
        this.testPlayer = new Forward("John", 20, 100, 100, "Scouts", 90,
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
        expectedSkills.put("finishing", 20);
        expectedSkills.put("sprint speed", 80);
        expectedSkills.put("crossing", 40);
        expectedSkills.put("short passing", 40);
        expectedSkills.put("ball control", 40);
        expectedSkills.put("fk accuracy", 40);
        HashMap<String, Integer> actualSkills = testPlayer.getSkills();
        assertEquals(expectedSkills, actualSkills);
    }

    //testing for getPosisitionSkills for Forward

    @Test
    public void testGetPositionSkills() {
        HashMap<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("dribbling", 10);
        expectedSkills.put("finishing", 20);
        expectedSkills.put("sprint speed", 80);
        expectedSkills.put("crossing", 40);
        expectedSkills.put("short passing", 40);
        expectedSkills.put("ball control", 40);
        expectedSkills.put("fk accuracy", 40);
        HashMap<String, Integer> actualSkills = testPlayer.getPositionSkills();
        assertEquals(expectedSkills, actualSkills);
    }
}