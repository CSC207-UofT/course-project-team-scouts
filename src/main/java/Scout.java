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
        this.name = "John Doe";
        this.team = null;
        this.history = new HashMap<Player, Date>();
        this.type = "Player";           // Player scout by default, not tactical
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