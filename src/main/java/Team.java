import java.util.ArrayList;
import java.util.List;

class Team {
    private String team_name;
    private int number_of_players;
    List<Player> players;
    private int net_worth;
    private int rating;

    public Team() {
        this.team_name = "";
        this.number_of_players = 0;
        this.net_worth = 0;
        this.rating = 0;
        this.players = new ArrayList<>();
    }

    public Team(int net_worth, int rating, int number_of_players, String team_name, List<Player> players) {
        this.team_name = team_name;
        this.number_of_players = number_of_players;
        this.net_worth = net_worth;
        this.rating = rating;
        this.players = players;
    }

}
