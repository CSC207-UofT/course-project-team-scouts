import entities.Player;
import entities.Defender;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestDefender {
    final Player testPlayer2 = new Player();
    Player testPlayer = new Player();
    @Before
    public void setUp() {
        HashMap<String, Integer> testSkills = new HashMap<>();
        testSkills.put("aggression", 10);
        testSkills.put("interceptions", 20);
        testSkills.put("marking", 30);
        testSkills.put("short passing", 40);
        testSkills.put("standing tackle", 40);
        testSkills.put("sliding tackle", 40);
        testSkills.put("long passing", 50);
        this.testPlayer = new Defender("John", 20, 100, 100, "Scouts", 90,
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
        expectedSkills.put("aggression", 10);
        expectedSkills.put("interceptions", 20);
        expectedSkills.put("marking", 30);
        expectedSkills.put("standing tackle", 40);
        expectedSkills.put("sliding tackle", 40);
        expectedSkills.put("short passing", 40);
        expectedSkills.put("long passing", 50);
        HashMap<String, Integer> actualSkills = testPlayer.getSkills();
        assertEquals(expectedSkills, actualSkills);
    }

    //testing for getPosisitionSkills for Defender

    @Test
    public void testGetPositionSkills() {
        HashMap<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("aggression", 10);
        expectedSkills.put("interceptions", 20);
        expectedSkills.put("marking", 30);
        expectedSkills.put("sliding tackle",40);
        expectedSkills.put("standing tackle",40);
        expectedSkills.put("short passing", 40);
        expectedSkills.put("long passing", 50);
        HashMap<String, Integer> actualSkills = testPlayer.getPositionSkills();
        assertEquals(expectedSkills, actualSkills);
    }

}
