import entities.Player;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.HashMap;

public class TestPlayer {

    Player testPlayer = new Player();
    Player testPlayer2 = new Player();

    @Before
    public void setUp() {
        HashMap<String, Integer> testSkills = new HashMap<>();
        testSkills.put("crossing", 10);
        testSkills.put("finishing", 20);
        testSkills.put("heading accuracy", 30);
        testSkills.put("short passing", 40);
        testSkills.put("volleys", 50);
        this.testPlayer = new Player("John", 20, 100, 100, "Scouts", 90,
                1000, "forward", testSkills);
    }

    @Test
    public void testGetName(){
        String expectedName = "John";
        String actualName = testPlayer.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetAge(){
        int expectedAge = 20;
        int actualAge = testPlayer.getAge();
        assertEquals(expectedAge, actualAge);
    }

    @Test
    public void testGetHeight(){
        int expectedHeight = 100;
        int actualHeight = testPlayer.getHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    @Test
    public void testGetWeight(){
        int expectedWeight = 100;
        int actualWeight = testPlayer.getWeight();
        assertEquals(expectedWeight, actualWeight);
    }

    @Test
    public void testGetRating(){
        int expectedRating = 90;
        int actualRating = testPlayer.getRating();
        assertEquals(expectedRating, actualRating);
    }

    @Test
    public void testGetValue(){
        int expectedValue = 1000;
        int actualValue = testPlayer.getValue();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSetName(){
        String expectedName = "Johnny";
        testPlayer.setName("Johnny");
        String actualName = testPlayer.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetAge(){
        int expectedAge = 40;
        testPlayer.setAge(40);
        int actualAge = testPlayer.getAge();
        assertEquals(expectedAge, actualAge);
    }

    @Test
    public void testGetTeam(){
        String expectedTeam = "Scouts";
        String actualTeam = testPlayer.getTeam();
        assertEquals(expectedTeam, actualTeam);
    }

    @Test
    public void testGetPosition(){
        String expectedPosition = "forward";
        String actualPosition = testPlayer.getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void testUpdateTeam(){
        String expectedTeamName = "Scouts Cool";
        testPlayer.updateTeam("Scouts Cool");
        String actualTeamName = testPlayer.getTeam();
        assertEquals(expectedTeamName, actualTeamName);
    }

    @Test
    public void testGetSkills(){
        HashMap<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("crossing", 10);
        expectedSkills.put("finishing", 20);
        expectedSkills.put("heading accuracy", 30);
        expectedSkills.put("short passing", 40);
        expectedSkills.put("volleys", 50);
        HashMap<String, Integer> actualSkills = testPlayer.getSkills();
        assertEquals(expectedSkills, actualSkills);
    }

    @Test
    public void testGetPositionSkills(){
        HashMap<String, Integer> expectedSkills = new HashMap<>();
        expectedSkills.put("crossing", 10);
        expectedSkills.put("finishing", 20);
        expectedSkills.put("heading accuracy", 30);
        expectedSkills.put("short passing", 40);
        expectedSkills.put("volleys", 50);
        HashMap<String, Integer> actualSkills = testPlayer.getPositionSkills();
        assertEquals(expectedSkills, actualSkills);
    }

    @Test
    public void testDefaultHeight(){
        double height = testPlayer2.getHeight();
        assertEquals(height, 0.0, 0);
    }

    @Test
    public void testDefaultWeight(){
        double weight = testPlayer2.getWeight();
        assertEquals(weight, 0.0, 0);
    }

    @Test
    public void testDefaultRating(){
        double rating = testPlayer2.getRating();
        assertEquals(rating, 0.0, 0);
    }

    @Test
    public void testDefaultPosition(){
        assertNull(testPlayer2.getPosition());
    }
}