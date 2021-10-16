import java.util.ArrayList;
import java.util.List;

public class SearchForPlayer{
    public static List<Player> searchPlayer(String target) {

        List<Player> playerList = PlayerDatabase.getPlayers();
        List<Player> validPlayers = new ArrayList<>();
        for (Player p : playerList) {
            String n = p.getName();
            if (n.contains(target)) {
                validPlayers.add(p);
            }
        }
        return validPlayers;
    }
}