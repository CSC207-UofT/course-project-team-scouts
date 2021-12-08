import data.TeamStatsCalculator;
import entities.Player;
import entities.Team;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.HashMap;

public class TeamStatsCalculatorTest extends TestCase {
    HashMap<String, Integer> sampleSkills;
    Player[] samplePlayers;
    Team sampleTeam;
    TeamStatsCalculator calculator;
    String[] skillTypes;
    String[] offensiveSkills;
    String[] defensiveSkills;

    public void setUp() {
        sampleSkills = new HashMap<>();
        samplePlayers = new Player[3];
        skillTypes = new String[]{"crossing", "finishing",
                "heading accuracy", "short passing",
                "volleys", "dribbling", "curve",
                "fk accuracy", "long passing",
                "ball control", "acceleration", "sprint speed",
                "agility", "reactions", "balance",
                "shot_power", "jumping", "stamina", "strength",
                "long_shots", "aggression", "interceptions",
                "positioning", "vision", "penalties",
                "composure", "marking", "standing tackle",
                "sliding tackle", "goalkeeping diving", "goalkeeping handling",
                "goalkeeping kicking", "goalkeeping positioning", "goalkeeping reflexes"};
        offensiveSkills = new String[]{"crossing", "finishing", "heading accuracy",
                "volleys", "dribbling", "curve", "fk accuracy", "shot_power",
                "long_shots", "vision", "penalties"};
        defensiveSkills = new String[]{"balance", "strength", "marking", "standing tackle",
                "sliding tackle"};

        for (int i = 0; i <= skillTypes.length - 1; i = i + 1) {
            if (Arrays.asList(offensiveSkills).contains(skillTypes[i])) {
                sampleSkills.put(skillTypes[i], 25);
            } else if (Arrays.asList(defensiveSkills).contains(skillTypes[i])) {
                sampleSkills.put(skillTypes[i], 75);
            } else {
                sampleSkills.put(skillTypes[i], 0);
            }
        }

        samplePlayers[0] = new Player("Player 0", 25, 180, 60,
                "Team", 10, 100, "FW", sampleSkills);
        samplePlayers[1] = new Player("Player 1", 25, 180, 60,
                "Team", 20, 100, "FW", sampleSkills);
        samplePlayers[2] = new Player("Player 2", 25, 180, 60,
                "Team", 30, 100, "FW", sampleSkills);

        sampleTeam = new Team("Team", Arrays.asList(samplePlayers));

        calculator = new TeamStatsCalculator();
    }

    public void testGenerateOffensiveRating() {
        double expected = 25;
        double actual = calculator.generateOffensiveRating(sampleTeam);
        assertEquals(expected, actual);
    }

    public void testGenerateDefensiveRating() {
        double expected = 75;
        double actual = calculator.generateDefensiveRating(sampleTeam);
        assertEquals(expected, actual);
    }

    public void testGenerateOverallRating() {
        double expected = 20;
        double actual = calculator.generateOverallRating(sampleTeam);
        assertEquals(expected, actual);
    }

    public void testGenerateNetWorth() {
        double expected = 300;
        double actual = calculator.generateNetWorth(sampleTeam);
        assertEquals(expected, actual);
    }
}