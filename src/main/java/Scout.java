import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class Scout {
    private String name;
    private Team team;
    private Map<Player, Date> history;
    private String type;

    // default constructor
    public Scout() {
        this.name = "N/A";
        this.team = null;
        this.history = new HashMap<>();
        this.type = "Player";           // Made a player scout by default, not a tactical one.
    }

    // typical constructor (w/ all args)
    public Scout(String name, Team team, Map<Player, Date> history, String type) {
        this.name = name;
        this.team = team;
        this.history = history;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public Map<Player, Date> getHistory() {
        return this.history;
    }
}