import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for storing Team Data as a list of Team entities
 */
public class TeamDatabase {
    private static List<Team> teams = new ArrayList<>();

    public TeamDatabase() {
    }

    /**
     * Adds new team to Team database
     *
     * @param name    team name as string
     * @param players arraylist of players on team
     */
    public static void addEntity(String name, List<Player> players) {
        Team new_club = new Team(name, players);
        teams.add(new_club);
    }

    public static void updateRoster(String t_name, Player player) {
        for (Team t : teams) {
            if (t.getTeamName().equals(t_name)) {
                t.addPlayer(player);
            }
        }
    }

    /**
     * Setter for Teams
     *
     * @param teamList list of  entities
     */
    public static void setTeams(List<Team> teamList) {
        teams = teamList;
    }

    /**
     * Getter for Teams
     *
     * @return list of Team entities
     */
    public static List<Team> getTeams() {
        return teams;
    }
}
