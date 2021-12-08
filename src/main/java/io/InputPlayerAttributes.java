package io;

import data.Database;
import data.PlayerDatabase;
import entities.Player;
import org.javatuples.Pair;
import search.SearchByPlayerAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputPlayerAttributes implements InputData<Player> {
    // Instance variable storing the search results after a search by attributes is completed.
    List<Player> searchResults;

    /**
     * Will give prompts for each attribute that we will search players on.
     * Will also accept user input for each of these prompts and output each player that
     * falls under the range of the given categories.
     */
    @Override
    public void run(Database<Player> database) {
        // Print prompt
        System.out.println("Which attributes would you like to include in your search? " +
                "(Enter the number corresponding to your choice)");
        System.out.println("1: Forward (Offensive) Attributes\n" +
                "2: Midfielder Attributes\n" +
                "3: Defensive Attributes\n" +
                "4: Goalkeeping Attributes\n");

        // List of valid inputs for this prompt
        List<String> searchTypes = Arrays.asList("1", "2", "3", "4");

        String searchType;
        while (true) {
            try {
                System.out.print("Selection (1-4): ");
                searchType = getInput();
                // If the input is valid, break out of while loop
                if (searchTypes.contains(searchType.strip())) {
                    break;
                } else {
                    // Input is not valid, the loop will run again
                    System.out.println("'" + searchType + "' is not a valid input. Please try again.\n");
                }
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.\n");
            }
        }

        // Call the correct input prompt for the next step
        Map<String, Pair<Number, Number>> attributes;
        switch (searchType) {
            case "2":
                attributes = inputMidfield();
                break;
            case "3":
                attributes = inputDefense();
                break;
            case "4":
                attributes = inputGoalkeeping();
                break;
            default:
                attributes = inputForward();
        }

        searchResults = SearchByPlayerAttributes.search((PlayerDatabase) database, attributes);
        PlayersPresenter playersPresenter = new PlayersPresenter();
        playersPresenter.outputResults(searchResults);
    }

    /**
     * Establishes a collection of all the attributes that are relevant to any "Forward"
     * player, and maps each attribute to a range in skill level that the user desires.
     *
     * @return a map of "forward" attributes to range of corresponding skill level for each.
     */
    private Map<String, Pair<Number, Number>> inputForward() {
        String[] forwardAttributes = new String[]{"age", "height", "weight", "sprint speed", "acceleration",
                "strength", "stamina", "finishing", "composure", "volleys", "curve", "shot power", "long shots",
                "heading accuracy", "jumping", "fk accuracy", "penalties", "reactions"};
        Map<String, Pair<Number, Number>> attributes = new HashMap<>();
        for (String attribute : forwardAttributes) {
            loopOverAttributes(attributes, attribute);
        }
        return attributes;
    }

    /**
     * Establishes a collection of all the attributes that are relevant to any "Midfield"
     * player, and maps each attribute to a range in skill level that the user desires.
     *
     * @return a map of "midfield" attributes to range of corresponding skill level for each.
     */
    private Map<String, Pair<Number, Number>> inputMidfield() {
        String[] midfieldAttributes = new String[]{"age", "height", "weight", "sprint speed", "acceleration",
                "strength", "stamina", "short passing", "long passing", "crossing", "vision", "dribbling",
                "ball control", "curve", "fk accuracy", "agility", "reactions", "balance"};
        Map<String, Pair<Number, Number>> attributes = new HashMap<>();
        for (String attribute : midfieldAttributes) {
            loopOverAttributes(attributes, attribute);
        }
        return attributes;
    }

    /**
     * Establishes a collection of all the attributes that are relevant to any "Defense"
     * player, and maps each attribute to a range in skill level that the user desires.
     *
     * @return a map of "defense" attributes to range of corresponding skill level for each.
     */
    private Map<String, Pair<Number, Number>> inputDefense() {
        String[] defenseAttributes = new String[]{"age", "height", "weight", "sprint speed", "acceleration",
                "strength", "stamina", "jumping", "heading accuracy", "aggression", "interceptions", "positioning",
                "marking", "standing tackle", "sliding tackle"};
        Map<String, Pair<Number, Number>> attributes = new HashMap<>();
        for (String attribute : defenseAttributes) {
            loopOverAttributes(attributes, attribute);
        }
        return attributes;
    }

    /**
     * Establishes a collection of all the attributes that are relevant to any "Goalkeeper"
     * and maps each attribute to a range in skill level that the user desires.
     *
     * @return a map of "goalkeeping" attributes to range of corresponding skill level for each.
     */
    private Map<String, Pair<Number, Number>> inputGoalkeeping() {
        String[] goalkeepingAttributes = new String[]{"age", "height", "weight", "long passing", "goalkeeping diving",
                "goalkeeping handling", "goalkeeping kicking", "goalkeeping positioning", "goalkeeping reflexes"};
        Map<String, Pair<Number, Number>> attributes = new HashMap<>();
        for (String attribute : goalkeepingAttributes) {
            loopOverAttributes(attributes, attribute);
        }
        return attributes;
    }

    /**
     * Loops over all the attributes for a player of a specific position, prompting
     * user to enter desired minimum and maximum values for each, and mutating the Map of
     * attributes to skill ranges for each.
     *
     * @param attributes a Map of attributes to skill ranges for players of a specific position.
     * @param attribute a specific attribute in the list of attributes relevant to a certain position.
     */
    private void loopOverAttributes(Map<String, Pair<Number, Number>> attributes, String attribute) {
        while (true) {
            try {
                // Print instructions
                System.out.println("\nEnter a value or range of values for the attribute '"
                        + attribute.toUpperCase() + "'. You can also press Enter to skip.");
                System.out.println("(Values must be integers >= 0. If specifying a range, " +
                        "separate the two values with a space.)");
                // Get user input
                String input = getInput();
                // Get formatted user input
                Pair<Number, Number> formattedInput = getTuple(input);
                // If the input is valid (not null)...
                if (formattedInput != null) {
                    attributes.put(attribute, formattedInput); // Add this value to the mapping of attributes
                    break; // Break out of the while loop
                } else {
                    // Input is not valid, the loop will run again
                    System.out.println("'" + input.strip() + "' is not a valid input. Please try again.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.");
            }
        }
    }

    /**
     * Packages the minimum and maximum values for a particular attribute of a player
     * chosen by the user into a tuple of two values (a Pair).
     *
     * @param input the line of input corresponding to a range entered by user.
     * @return a Pair of two numbers corresponding to the min. and max. values for an attribute.
     */
    private Pair<Number, Number> getTuple(String input) {
        // Remove leading and trailing space
        input = input.strip();
        // If the input was empty, return the default min. and max.
        if (input.equals("")) {
            return new Pair<>(0, 250);
        } else {
            // Split the input string into separate strings
            return InputHelper.splitInput(input);
        }
    }
}