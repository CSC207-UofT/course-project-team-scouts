package entities;

import java.util.HashMap;

/**
 * A Defender with attributes and information.
 */
public class Defender extends Player {
    /**
     * A new defender with attributes and information.
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
    public Defender(String name, int age, int height, int weight, String team, int rating,
                    int value, String position, HashMap<String, Integer> skills) {
        super(name, age, height, weight, team, rating, value, position, skills);
    }

    /**
     * Return attacking attributes that all Defender subtype of Players must have.
     *
     * @return a map of attributes to their corresponding ratings
     */
    @Override
    public HashMap<String, Integer> getPositionSkills() {
        HashMap<String, Integer> allSkills = super.getSkills();
        HashMap<String, Integer> positionAttributes = new HashMap<>();
        String[] positionSkillAttributes = {"aggression", "interceptions",
                "short passing", "long passing", "marking", "standing tackle",
                "sliding tackle"};

        for (String attribute : positionSkillAttributes) {
            positionAttributes.put(attribute, allSkills.get(attribute));
        }
        return positionAttributes;
    }
}


