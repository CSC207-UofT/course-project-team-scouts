package io;

import data.Database;
import data.PlayerDatabase;
import entities.Player;
import search.SearchByPlayerAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import org.javatuples.Pair;

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
                if (searchTypes.contains(searchType.strip())) { break; }
                else {
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
                attributes = inputMidfield(database);
                break;
            case "3":
                attributes = inputDefense(database);
                break;
            case "4":
                attributes = inputGoalkeeping(database);
                break;
            default:
                attributes = inputForward(database);
        }

        searchResults = SearchByPlayerAttributes.search((PlayerDatabase) database, attributes);
        PlayersPresenter playersPresenter = new PlayersPresenter();
        playersPresenter.outputResults(searchResults);
    }

    private Map<String, Pair<Number, Number>> inputForward(Database<Player> database) {
        String[] forwardAttributes = new String[]{"age", "height", "weight", "sprint speed",  "acceleration",
                "strength", "stamina",  "finishing", "composure", "volleys", "curve", "shot power", "long shots",
                "heading accuracy", "jumping", "fk accuracy", "penalties", "reactions"};
        Map<String, Pair<Number, Number>> attributes = new HashMap<>();
        for (String attribute : forwardAttributes) {
            loopOverAttributes(attributes, attribute);
        }
        return attributes;
    }

    private Map<String, Pair<Number, Number>> inputMidfield(Database<Player> database) {
        String[] midfieldAttributes = new String[]{"age", "height", "weight", "sprint speed",  "acceleration",
                "strength", "stamina",  "short passing", "long passing", "crossing", "vision", "dribbling",
                "ball control",  "curve", "fk accuracy", "agility", "reactions", "balance"};
        Map<String, Pair<Number, Number>> attributes = new HashMap<>();
        for (String attribute : midfieldAttributes) {
            loopOverAttributes(attributes, attribute);
        }
        return attributes;
    }

    private Map<String, Pair<Number, Number>> inputDefense(Database<Player> database) {
        String[] defenseAttributes = new String[]{"age", "height", "weight", "sprint speed", "acceleration",
                "strength", "stamina", "jumping", "heading accuracy",  "aggression", "interceptions", "positioning",
                "marking", "standing tackle", "sliding tackle"};
        Map<String, Pair<Number, Number>> attributes = new HashMap<>();
        for (String attribute : defenseAttributes) {
            loopOverAttributes(attributes, attribute);
        }
        return attributes;
    }

    private Map<String, Pair<Number, Number>> inputGoalkeeping(Database<Player> database) {
        String[] goalkeepingAttributes = new String[]{"age", "height", "weight", "long passing", "goalkeeping diving",
                "goalkeeping handling", "goalkeeping kicking", "goalkeeping positioning", "goalkeeping reflexes"};
        Map<String, Pair<Number, Number>> attributes = new HashMap<>();
        for (String attribute : goalkeepingAttributes) {
            loopOverAttributes(attributes, attribute);
        }
        return attributes;
    }

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
                }
                else {
                    // Input is not valid, the loop will run again
                    System.out.println("'" + input.strip() + "' is not a valid input. Please try again.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.");
            }
        }
    }

    private Pair<Number, Number> getTuple(String input) {
        // Remove leading and trailing space
        input = input.strip();
        // If the input was empty, return the default min. and max.
        if (input.equals("")) { return new Pair<>(0, 250); }
        else {
            // Split the input string into separate strings
            String[] splitInput = input.split(" ");
            int[] splitInputInts = new int[splitInput.length];
            try {
                for (int i = 0 ; i < splitInput.length ; i++) {
                    // Convert the input to an integer
                    splitInputInts[i] = Integer.parseInt(splitInput[i]);
                }
            } catch (NumberFormatException e) {
                // User has input something other than an integer
                return null;
            }
            // User has entered 1 value, we use that as the min. and use the default max.
            if (splitInputInts.length == 1) { return new Pair<>(splitInputInts[0], 250); }
            // User has entered 2 values, we use those as the min. and max.
            else if (splitInputInts.length == 2) { return new Pair<>(splitInputInts[0], splitInputInts[1]); }
            // User has entered more than 2 values
            return null;
        }
    }
}