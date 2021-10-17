import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchByPlayerAttributes {
    public static List<Player> searchPlayer(ArrayList<Integer> a) throws IOException {
        // we can add more values eventually
        List<Player> playerList = PlayerDatabase.getPlayers();
        List<Player> validPlayers = new ArrayList<>();
        for (Player p : playerList) {
            int age = p.getAge();
            double weight = p.getWeight();
            double height = p.getHeight();
            int strength = p.getStrength();
            int stamina = p.getStamina();

            if (age >= a.get(0) - 10 && age <= a.get(0) + 10
                    && height - 20 <= a.get(1) && height + 20 >= a.get(1)
                    && weight - 20 <= a.get(2) && weight + 20 >= a.get(2)
                    && strength - 25 <= a.get(3) && strength + 25 >= a.get(3)
                    && stamina - 25 <= a.get(4) && stamina - 25 >= a.get(4)) {
                validPlayers.add(p);
            }
        }
        return validPlayers;
    }
}