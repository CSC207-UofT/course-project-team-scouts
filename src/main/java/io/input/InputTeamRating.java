package io.input;

import data.Database;
import data.TeamDatabase;
import entities.Team;
import io.output.TeamsPresenter;
import org.javatuples.Pair;
import search.RatingType;
import search.SearchByTeamRating;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputTeamRating implements InputData<Team> {
    // Storing the results of a search in a list.
    List<Team> searchResults;

    /**
     * Provides prompts to user for each rating that for which teams will be searched on.
     * Also accepts user input for each prompt and outputs each Team that
     * falls under the range of the given categories.
     */
    @Override
    public void run(Database<Team> database) {
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
                if (searchTypes.contains(searchType.strip())) {
                    break;
                } else {
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
                ratings = inputOffensive();
                type = RatingType.OFFENSIVE;
                break;
            case "3":
                ratings = inputDefensive();
                type = RatingType.DEFENSIVE;
                break;
            case "4":
                ratings = inputNetWorth();
                type = RatingType.NET_WORTH;
                break;
            case "5":
                ratings = inputAll();
                type = RatingType.ALL;
                break;
            default:
                ratings = inputOverall();
                type = RatingType.OVERALL;
        }

        searchResults = SearchByTeamRating.search((TeamDatabase) database, ratings, type);
        TeamsPresenter teamPresenter = new TeamsPresenter();
        teamPresenter.outputResults(searchResults);
    }

    /**
     * Establishes a collection of the ratings needed to calculate the Overall rating
     * of a team, and maps each rating to the range that the user desires any Team to have.
     *
     * @return a map of ratings to the corresponding range for each.
     */
    private Map<String, Pair<Number, Number>> inputOverall() {
        String[] ratingNames = new String[]{"Overall Rating"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    /**
     * Establishes a collection of the ratings needed to calculate the Offensive rating
     * of a team, and maps each rating to the range that the user desires any Team to have.
     *
     * @return a map of ratings to the corresponding range for each.
     */
    private Map<String, Pair<Number, Number>> inputOffensive() {
        String[] ratingNames = new String[]{"Offensive Rating"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    /**
     * Establishes a collection of the ratings needed to calculate the Defensive rating
     * of a team, and maps each rating to the range that the user desires any Team to have.
     *
     * @return a map of ratings to the corresponding range for each.
     */
    private Map<String, Pair<Number, Number>> inputDefensive() {
        String[] ratingNames = new String[]{"Defensive Rating"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    /**
     * Establishes a collection of the ratings needed to calculate the Net Worth
     * of a team, and maps each rating to the range that the user desires any Team to have.
     *
     * @return a map of ratings to the corresponding range for each.
     */
    private Map<String, Pair<Number, Number>> inputNetWorth() {
        String[] ratingNames = new String[]{"Net Worth"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    /**
     * Establishes a collection of the ratings needed to calculate All possible ratings
     * of a team, and maps each rating to the range that the user desires any Team to have.
     *
     * @return a map of ratings to the corresponding range for each.
     */
    private Map<String, Pair<Number, Number>> inputAll() {
        String[] ratingNames = new String[]{"Overall Rating", "Offensive Rating",
                "Defensive Rating", "Net Worth"};
        Map<String, Pair<Number, Number>> ratings = new HashMap<>();
        for (String rating : ratingNames) {
            loopOverRatings(ratings, rating);
        }
        return ratings;
    }

    /**
     * Loops over all the ratings which the user has chosen to consider in their search, prompting
     * them to enter desired minimum and maximum values for each, and mutating the Map of
     * ratings to value ranges for each.
     *
     * @param ratings a Map of ratings to corresponding value range for each.
     * @param rating a specific type of rating in the list of ratings chosen by user.
     */
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
                } else {
                    // Input is not valid, the loop will run again
                    System.out.println("'" + input + "' is not a valid input. Please try again.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.");
            }
        }
    }

    /**
     * Packages the minimum and maximum values for a particular Team rating
     * chosen by the user into a tuple of two values (a Pair).
     *
     * @param input the line of input corresponding to a range entered by user.
     * @return a Pair of two numbers corresponding to the min. and max. values for a given rating.
     */
    private Pair<Number, Number> getTuple(String input) {
        // Remove leading and trailing space
        input = input.strip();
        // If the input was empty, return the default min. and max.
        if (input.equals("")) {
            return new Pair<>(0, 100);
        } else {
            // Split the input string into separate strings
            return InputHelper.splitInput(input);
        }
    }


}
