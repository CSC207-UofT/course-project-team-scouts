package search;

import data.TeamDatabase;
import data.TeamStatsCalculator;
import entities.Team;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchByTeamRating {

    /**
     * Searches the database of existing Teams in the program for all
     * those that match the conditions provided by the user in their specific
     * query.
     *
     * @param teamDatabase a database of all existing Teams in the program.
     * @param queries a map of Team ratings to the user's desired value ranges for each.
     * @param ratingType the type/combination of team ratings that the user is considering in their search.
     * @return the list of Team entities that match the user's search.
     */
    public static List<Team> search(TeamDatabase teamDatabase,
                                    Map<String, Pair<Number, Number>> queries, RatingType ratingType) {
        List<Team> teamList = teamDatabase.getEntities();
        List<Team> validTeams = new ArrayList<>();

        for (Team t : teamList) {
            Map<String, Number> attributes = teamRatings(t, ratingType);
            boolean isValid = SearchHelper.validEntity(attributes, queries);

            if (isValid) validTeams.add(t);
        }
        return validTeams;
    }

    /**
     * Generates a Map of all the specific team ratings considered by the user to
     * their specific values for the given team provided as an argument.
     *
     * @param team a single team in the database.
     * @param ratingType the type/combination of Team ratings the user is considering in their search.
     * @return a Map of Team ratings to their calculated values for a specific team.
     */
    public static Map<String, Number> teamRatings(Team team, RatingType ratingType) {
        TeamStatsCalculator calculator = new TeamStatsCalculator();
        Map<String, Number> ratings = new HashMap<>();

        switch (ratingType) {
            case OVERALL:
                ratings.put("Overall Rating", calculator.generateOverallRating(team));
                break;
            case OFFENSIVE:
                ratings.put("Offensive Rating", calculator.generateOffensiveRating(team));
                break;
            case DEFENSIVE:
                ratings.put("Defensive Rating", calculator.generateDefensiveRating(team));
                break;
            case NET_WORTH:
                ratings.put("Net Worth", calculator.generateNetWorth(team));
                break;
            default:
                ratings.put("Overall Rating", calculator.generateOverallRating(team));
                ratings.put("Offensive Rating", calculator.generateOffensiveRating(team));
                ratings.put("Defensive Rating", calculator.generateDefensiveRating(team));
                ratings.put("Net Worth", calculator.generateNetWorth(team));
        }

        return ratings;
    }
}
