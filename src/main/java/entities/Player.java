package entities;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A player with his attributes and information.
 */
public class Player implements Serializable, Identifiable {
    // Player's height
    private final int height;
    // Player's weight
    private final int weight;
    // Player's overall rating
    private final int rating;
    // Player's value (Euros)
    private final int value;
    // Player's position
    private final String position;
    // Mapping of player's skills to their rating in that skill
    private final HashMap<String, Integer> skills;
    // Player's name
    private String name;
    // Player's age (years)
    private int age;
    // Player's team
    private String team;

    /**
     * Default constructor
     */
    public Player() {
        this.name = "N/A";
        this.age = 0;
        this.height = 0;
        this.weight = 0;
        this.team = null;
        this.rating = 0;
        this.value = 0;
        this.position = null;
        this.skills = new HashMap<>();
    }

    /**
     * A new player with attributes and information.
     *
     * @param name     the name
     * @param age      the age
     * @param height   the height
     * @param weight   the weight
     * @param team     the team
     * @param rating   the rating
     * @param value    the value
     * @param position the position
     * @param skills   the skills
     */
    public Player(String name, int age, int height, int weight, String team,
                  int rating, int value, String position,
                  HashMap<String, Integer> skills) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.rating = rating;
        this.value = value;
        this.position = position;
        this.skills = skills;
    }

    /**
     * Return the hashmap of this player's skills.
     */
    public HashMap<String, Integer> getSkills() {
        return this.skills;
    }

    /**
     * Return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the player.
     *
     * @param namePlayer the name.
     */
    public void setName(String namePlayer) {
        name = namePlayer;
    }

    /**
     * Return the age of this player.
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age of the player.
     *
     * @param agePlayer the age.
     */
    public void setAge(int agePlayer) {
        age = agePlayer;
    }

    /**
     * Return the height of this player.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return the weight of this player.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Return the rating of this player.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Return the value of this player.
     */
    public int getValue() {
        return value;
    }

    /**
     * Return player's team.
     */
    public String getTeam() {
        return team;
    }

    /**
     * Return player's position.
     */
    public String getPosition() {
        return position;
    }

    public void updateTeam(String teamName) {
        team = teamName;
    }

    /**
     * Return generic attributes that all players have irrespective of their subtype.
     */
    public HashMap<String, Integer> getPositionSkills() {
        HashMap<String, Integer> allSkills = getSkills();
        HashMap<String, Integer> positionAttributes = new HashMap<>();

        String[] positionSkillAttributes = {"crossing", "finishing",
                "heading accuracy", "short passing",
                "volleys"};

        for (String attribute : positionSkillAttributes) {
            positionAttributes.put(attribute, allSkills.get(attribute));
        }
        return positionAttributes;
    }
}
