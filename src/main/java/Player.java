import java.util.HashMap;
import java.util.Map;

/*
 * Class dealing w/ a player entity
 * Attributes to include:
 *  - attacking_work_rate
 *  - defensive_work_rate
 *  - crossing
 *  - finishing
 *  - heading_accuracy
 *  - short_passing
 *  - volleys
 *  - dribbling
 *  - curve
 *  - free_kick_accuracy
 *  - long_passing
 *  - ball_control
 *  - acceleration
 *  - sprint_speed
 *  - agility
 *  - reactions
 *  - balance
 *  - shot_power
 *  - jumping
 *  - stamina
 *  - strength
 *  - long_shots
 *  - aggression
 *  - interceptions
 *  - positioning
 *  - vision
 *  - penalties
 *  - marking
 *  - standing_tackle
 *  - sliding_tackle
 *  - gk_diving
 *  - gk_handling
 *  - gk_kicking
 *  - gk_positioning
 *  - gk_reflexes
 * */
public class Player {
    private String name;
    private int age;
    private double height;
    private double weight;
    private Team team;
    private boolean scouted;
    private String position;
    private HashMap<String, Integer> skills;

    // Default Constructor
    public Player() {
        this.name = "John Doe";
        this.age = 0;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.scouted = scouted;
        this.position = position;
        this.skills = new HashMap<String, Integer>();
    }

    public Player(HashMap<String, Integer> skills) {
        this.name = "John Doe";
        this.age = 0;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.scouted = scouted;
        this.position = position;
        this.skills = skills;
    }

    public Player(String name, int age, double height, double weight, Team team, boolean scouted, String position,
                  HashMap<String, Integer> skills) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.scouted = scouted;
        this.position = position;
        this.skills = skills;
    }

    public HashMap<String, Integer> getSkills() {
        return this.skills;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}