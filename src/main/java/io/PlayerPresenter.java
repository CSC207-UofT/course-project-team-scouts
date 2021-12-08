package io;

import data.PlayerStatsCalculator;
import entities.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPresenter implements PresentData<Player> {
    /* Create an instance of PlayerStatsCalculator for the purpose of
     aggregated statistics calculation. */
    PlayerStatsCalculator calculator = new PlayerStatsCalculator();
    // TODO - maybe actually do something with stats, or not if not necessary.

    /**
     * Outputs all the relevant data pertaining to a specific player
     * in the database, typically after the player has been deliberately
     * selected from the results of a search.
     *
     * @param resultsList the list of players for which detailed output is desired.
     */
    @Override
    public void outputResults(List<Player> resultsList) {
        Player p = resultsList.get(0);

        printBasicPlayerAttributes(p);
        printSkillAttributes(p);
    }

    /**
     * Prints the most basic attributes of the player, such as their
     * physical attributes, name, team name, etc.
     *
     * @param p the player for which detailed output is desired.
     */
    private void printBasicPlayerAttributes(Player p) {
        System.out.println("Name: " + p.getName());
        System.out.println("Overall Rating: " + p.getRating());
        System.out.println("Position: " + p.getPosition());
        System.out.println("Team: " + p.getTeam());
        System.out.println("Age: " + p.getAge());
        System.out.println("Weight: " + p.getWeight());
    }

    /**
     * Prints all the individual skill attributes of the given player,
     * but only those that are specifically relevant to the player's position
     * (e.g., for a forward, it prints such attributes as "finishing", and omits
     * those like "sliding tackle").
     *
     * @param p the player for which detailed output is desired.
     */
    private void printSkillAttributes(Player p) {
        HashMap<String, Integer> playerSkills = p.getPositionSkills();

        for (Map.Entry<String, Integer> e : playerSkills.entrySet()) {
            String skill = e.getKey();
            Integer value = e.getValue();

            System.out.println("\t" + skill + ": " + value);
        }
    }

}