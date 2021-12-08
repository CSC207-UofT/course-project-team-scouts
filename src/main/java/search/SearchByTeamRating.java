package search;

import data.TeamDatabase;
import data.TeamStatsCalculator;
import entities.Team;
import io.RatingType;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchByTeamRating {

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
