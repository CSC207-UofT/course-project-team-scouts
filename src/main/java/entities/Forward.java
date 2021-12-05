package entities;

import java.util.HashMap;
/**
 * A forward with attributes and information.
 */
public class Forward extends Player {
    /**
     * A new forward with attributes and information.
     *
     * @param name the name
     * @param age the age
     * @param height the height
     * @param weight the weight
     * @param team the team
     * @param rating the rating
     * @param value the value
     * @param position the position
     * @param skills the skills
     */
    public Forward(String name, int age, double height, double weight, String team, int rating,
                   double value, String position, HashMap<String, Integer> skills) {
        super(name, age, height, weight, team, rating, value, position, skills);


    }

    /**
     * Return attacking attributes that all Forward subtype of Players must have.
     */
    @Override
    public HashMap<String, Integer> getPositionSkills(){
        HashMap<String, Integer> allSkills = super.getSkills();
        HashMap<String, Integer> positionAttributes = new HashMap<String, Integer>();
        String[] positionSkillAttributes = {"dribbling","finishing","sprint speed",
                "short passing","crossing","ball control","fk accuracy"};


        for (String attribute : positionSkillAttributes) {
            positionAttributes.put(attribute, allSkills.get(attribute));





        }
        return positionAttributes;

    }
}
