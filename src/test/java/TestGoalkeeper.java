import entities.Player;
import entities.Goalkeeper;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestGoalkeeper {
    final Player testPlayer2 = new Player();
    Player testPlayer = new Player();

    @Before
    public void setUp() {
        HashMap<String, Integer> testSkills = new HashMap<>();
        testSkills.put("goalkeeping diving", 10);
        testSkills.put("goalkeeping handling", 20);
        testSkills.put("goalkeeping kicking", 30);
        testSkills.put("goalkeeping positioning", 40);
        testSkills.put("goalkeeping reflexes", 40);
        this.testPlayer = new Goalkeeper("John", 20, 100, 100, "Scouts", 90,
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
        expectedSkills.put("goalkeeping diving", 10);
        expectedSkills.put("goalkeeping handling", 20);
        expectedSkills.put("goalkeeping kicking", 30);
        expectedSkills.put("goalkeeping positioning", 40);
        expectedSkills.put("goalkeeping reflexes", 40);
        HashMap<String, Integer> actualSkills = testPlayer.getSkills();
        assertEquals(expectedSkills, actualSkills);
    }

    //testing for getPosisitionSkills for Goalkeeper

    @Test
    public void testGetPositionSkills() {
        HashMap<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("goalkeeping diving", 10);
        expectedSkills.put("goalkeeping handling", 20);
        expectedSkills.put("goalkeeping kicking", 30);
        expectedSkills.put("goalkeeping positioning", 40);
        expectedSkills.put("goalkeeping reflexes", 40);
        HashMap<String, Integer> actualSkills = testPlayer.getPositionSkills();
        assertEquals(expectedSkills, actualSkills);
    }
}