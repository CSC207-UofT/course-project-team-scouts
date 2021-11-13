import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PlayerStatsCalculator extends StatsCalculator<Player> {
    /**
     * Generates an overall offensive rating for an individual Player entity.
     * Accomplishes this by calculating the average of all the values corresponding
     * to the player's proficiencies in all the skills in the set that are offensive
     * in nature.
     *
     * @param player the individual Player entity for which an offensive rating is desired.
     * @return an integer rating of the player's overall offensive proficiency.
     */
    @Override
    public int generateOffensiveRating(Player player) {
        HashMap<String, Integer> skills = player.getSkills();
        String[] attributes = {"crossing", "finishing", "heading accuracy",
                                        "volleys", "dribbling", "curve", "fk accuracy", "shot_power",
                                        "long_shots", "vision", "penalties"};

        // Converting the array into an ArrayList for easier lookup.
        ArrayList<String> offensiveAttributes = new ArrayList<>(Arrays.asList(attributes));

        return calculateAverageBySkillType(skills, offensiveAttributes);
    }

    /**
     * Generates an overall defensive rating for an individual Player entity.
     * Accomplishes this by calculating the average of all the values corresponding
     * to the player's proficiencies in all the skills in the set that are defensive
     * in nature.
     *
     * @param player the individual Player entity for which an defensive rating is desired.
     * @return an integer rating of the player's overall defensive proficiency.
     */
    @Override
    public int generateDefensiveRating(Player player) {
        HashMap<String, Integer> skills = player.getSkills();
        String[] attributes = {"balance", "strength", "marking", "standing tackle",
                                        "sliding tackle"};
        ArrayList<String> defensiveAttributes = new ArrayList<>(Arrays.asList(attributes));

        return calculateAverageBySkillType(skills, defensiveAttributes);
    }

    /**
     * Generates an overall goalkeeping rating for an individual Player entity.
     * Accomplishes this by calculating the average of all the values corresponding
     * to the player's proficiencies in all the skills in the set that correspond to
     * their goalkeeping ability.
     *
     * @param player the individual Player entity for which a goalkeeping rating is desired.
     * @return an integer rating of the player's overall proficiency in goalkeeping.
     */
    public int generateGoalkeepingRating(Player player) {
        HashMap<String, Integer> skills = player.getSkills();
        String[] attributes = {"goalkeeping diving","goalkeeping handling",
                "goalkeeping kicking","goalkeeping positioning","goalkeeping reflexes"};

        ArrayList<String> goalkeepingAttributes = new ArrayList<>(Arrays.asList(attributes));

        return calculateAverageBySkillType(skills, goalkeepingAttributes);
    }

    /**
     * Calculates the average of all skills of a particular skill type.
     *
     * @param allSkills the set of all skills and their respective values possessed by a given Player entity.
     * @param desiredSkills the set of all skills of a specific type (e.g. offensive, defensive, etc.)
     * @return the average of all the desired skill values (truncated to the nearest integer).
     */
    public int calculateAverageBySkillType(HashMap<String, Integer> allSkills,
                                           ArrayList<String> desiredSkills) {
        // Calculate the total of all desired skill values.
        int skillsSum = 0;
        for (String skill : allSkills.keySet()) {
            if (desiredSkills.contains(skill)) {
                skillsSum += allSkills.get(skill);
            }
        }
        return skillsSum / desiredSkills.size();
    }
}
