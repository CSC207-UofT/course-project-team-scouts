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
}
