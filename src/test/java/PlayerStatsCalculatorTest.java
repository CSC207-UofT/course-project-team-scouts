import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class PlayerStatsCalculatorTest {
    private PlayerStatsCalculator calc;
    private HashMap<String, Integer> defaultSkills;

    @Before
    public void setUp() {
        calc = new PlayerStatsCalculator();
        defaultSkills = new HashMap<>();
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
            defaultSkills.put(skillTypes[i], 10);
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void generateOffensiveRating() {
        // Create a player with default skills.
        Player p = new Player("Cristiano Ronaldo", 35, 181, 75, "Manchester United",
                92, 90000000, "LW", defaultSkills);

        int actual = calc.generateOffensiveRating(p);
        assertEquals(10, actual);
    }

    @Test
    public void generateDefensiveRating() {
        // Create a player with default skills.
        Player p = new Player("Cristiano Ronaldo", 35, 181, 75, "Manchester United",
                92, 90000000, "LW", defaultSkills);

        int actual = calc.generateDefensiveRating(p);
        assertEquals(10, actual);
    }

    @Test
    public void generateGoalkeepingRating() {
        // Create a player with default skills.
        Player p = new Player("Cristiano Ronaldo", 35, 181, 75, "Manchester United",
                92, 90000000, "LW", defaultSkills);

        int actual = calc.generateGoalkeepingRating(p);
        assertEquals(10, actual);
    }

    @Test
    public void calculateAverageBySkillType() {
        // Create a specific subset of all skills to calculate an average for.
        ArrayList<String> desiredSkills = new ArrayList<>();
        desiredSkills.add("crossing");
        desiredSkills.add("finishing");
        desiredSkills.add("volleys");
        desiredSkills.add("curve");
        desiredSkills.add("stamina");

        int actual = calc.calculateAverageBySkillType(defaultSkills, desiredSkills);
        assertEquals(10, actual);
    }
}