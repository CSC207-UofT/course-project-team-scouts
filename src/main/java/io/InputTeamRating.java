package io;

import data.Database;
import data.TeamDatabase;
import entities.Team;
import org.javatuples.Pair;
import search.SearchByTeamRating;

import java.io.IOException;
import java.util.*;

public class InputTeamRating implements InputData<Team> {
    // Storing the results of a search in a list.
    List<Team> searchResults;

    @Override
    public void run(Database<Team> database) throws IOException {
        // Display prompt to user.
        System.out.println("Which team rating would you like to include in your search? " +
                "(Enter the number corresponding to your choice)");
        System.out.println("1: Overall Rating\n" +
                "2: Offensive Rating\n" +
                "3: Defensive Rating\n" +
                "4: Total Net Worth\n" +
                "5: All Ratings");

        // List of all choices user could make from list above.
        List<String> searchTypes = Arrays.asList("1", "2", "3", "4", "5");

        String searchType;
        while (true) {
            try {
                searchType = getInput();
                // If the input is valid, break out of while loop
                if (searchTypes.contains(searchType.strip())) { break; }
                else {
                    // Input is not valid, the loop will run again
                    System.out.println("'" + searchType + "' is not a valid input. Please try again.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.");
            }
        }

        // Call the correct input prompt for the next step
        Map<String, Pair<Number, Number>> ratings;
        RatingType type;
        switch (searchType) {
            case "2":
                ratings = inputOffensive(database);
                type = RatingType.OFFENSIVE;
                break;
            case "3":
                ratings = inputDefensive(database);
                type = RatingType.DEFENSIVE;
                break;
            case "4":
                ratings = inputNetWorth(database);
                type = RatingType.NET_WORTH;
                break;
            case "5":
                ratings = inputAll(database);
                type = RatingType.ALL;
                break;
            default:
                ratings = inputOverall(database);
                type = RatingType.OVERALL;
        }

        searchResults = SearchByTeamRating.search((TeamDatabase) database, ratings, type);
        TeamsPresenter teamPresenter = new TeamsPresenter();
        teamPresenter.outputResults(searchResults);
    }

    private Map<String, Pair<Number, Number>> inputOverall(Database<Team> database) {
        String[] ratingNames = new String[]{"Overall Rating"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    private Map<String, Pair<Number, Number>> inputOffensive(Database<Team> database) {
        String[] ratingNames = new String[]{"Offensive Rating"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    private Map<String, Pair<Number, Number>> inputDefensive(Database<Team> database) {
        String[] ratingNames = new String[]{"Defensive Rating"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    private Map<String, Pair<Number, Number>> inputNetWorth(Database<Team> database) {
        String[] ratingNames = new String[]{"Net Worth"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    private Map<String, Pair<Number, Number>> inputAll(Database<Team> database) {
        String[] ratingNames = new String[]{"Overall Rating", "Offensive Rating",
                "Defensive Rating", "Net Worth"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    private void loopOverRatings(Map<String, Pair<Number, Number>> ratings, String rating) {
        while (true) {
            try {
                // Print instructions
                System.out.println("Please enter a value or range of values for the rating '"
                        + rating + "'. You can also press enter to skip.");
                System.out.println("(Values must be integers between 0 and 100. If specifying a range, " +
                        "separate the two values with a space.)");
                // Get user input
                String input = getInput();
                // Get formatted user input
                Pair<Number, Number> formattedInput = getTuple(input);
                // If the input is valid (not null)...
                if (formattedInput != null) {
                    ratings.put(rating, formattedInput); // Add this value to the mapping of attributes
                    break; // Break out of the while loop
                }
                else {
                    // Input is not valid, the loop will run again
                    System.out.println("'" + input + "' is not a valid input. Please try again.");
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
        if (input.equals("")) { return new Pair<>(0, 100); }
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
            if (splitInputInts.length == 1) { return new Pair<>(splitInputInts[0], 100); }
            // User has entered 2 values, we use those as the min. and max.
            else if (splitInputInts.length == 2) { return new Pair<>(splitInputInts[0], 100); }
            // User has entered more than 2 values
            return null;
        }
    }


}
