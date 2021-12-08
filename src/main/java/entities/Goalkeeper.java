package entities;

import java.util.HashMap;

/**
 * A goalkeeper with attributes and information.
 */

public class Goalkeeper extends Player {
    /**
     * A new goalkeeper with attributes and information.
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
    public Goalkeeper(String name, int age, int height, int weight, String team, int rating,
                      int value, String position, HashMap<String, Integer> skills) {
        super(name, age, height, weight, team, rating, value, position, skills);
    }

    @Override
    public HashMap<String, Integer> getPositionSkills() {
        HashMap<String, Integer> allSkills = super.getSkills();
        HashMap<String, Integer> positionAttributes = new HashMap<>();

        String[] positionSkillAttributes = {"goalkeeping diving", "goalkeeping handling", "goalkeeping kicking",
                "goalkeeping positioning", "goalkeeping reflexes"};


        for (String attribute : positionSkillAttributes) {
            positionAttributes.put(attribute, allSkills.get(attribute));


        }
        return positionAttributes;
    }
}
