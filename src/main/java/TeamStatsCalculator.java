import java.util.List;

public class TeamStatsCalculator extends StatsCalculator<Team> {
    PlayerStatsCalculator calculator = new PlayerStatsCalculator();

    /**
     * Generates an overall offensive rating for a Team. Accomplishes this by making
     * use of PlayerStatsCalculator to calculate the offensive rating of each
     * player on the team, and then taking the average of all individual offensive ratings.
     *
     * @param team the team for which an offensive rating is desired.
     * @return an integer rating of the team's offensive proficiency.
     */
    @Override
    public int generateOffensiveRating(Team team) {
        List<Player> players = team.getPlayers();
        int offensiveSum = 0;
        for (Player p : players) {
            offensiveSum += calculator.generateOffensiveRating(p);
        }

        return offensiveSum / players.size();
    }

    /**
     * Generates an overall defensive rating for a Team. Accomplishes this by
     * making use of PlayerStatsCalculator to calculate the defensive rating of each
     * player on the team, and then taking the average of all individual defensive ratings.
     *
     * @param team the team for which a defensive rating is desired.
     * @return an integer rating of the team's defensive proficiency.
     */
    @Override
    public int generateDefensiveRating(Team team) {
        List<Player> players = team.getPlayers();
        int defensiveSum = 0;
        for (Player p : players) {
            defensiveSum += calculator.generateDefensiveRating(p);
        }

        return defensiveSum / players.size();
    }

    /**
     * Generates an overall rating for a Team. Accomplishes this by
     * taking the average of all individual overall player ratings.
     *
     * @param team the team for which an overall rating is desired.
     * @return an integer rating representing how skilled the team is.
     */
    public int generateOverallRating(Team team) {
        List<Player> players = team.getPlayers();
        int ratingSum = 0;
        for (Player p : players) {
            ratingSum += p.getRating();
        }

        return ratingSum / players.size();
    }
}
