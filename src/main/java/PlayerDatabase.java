import java.util.HashMap;
import java.util.List;

/**
 * Responsible for storing Player Data as a list of player entities
 */
public class PlayerDatabase{
    private static List<Player> Players;


    public PlayerDatabase() {
    }

    /**
     * Adds new player to Player database
     * @param name name of player as string
     * @param age age of player as int
     * @param height height of player as double
     * @param weight weight of player as double
     * @param team team of player as string
     * @param scouted whether player has been scouted as boolean
     * @param position player position as string
     * @param skills player skill string and corresponding attribute value as Hashmap
     */
    public static void add_entity(String name, int age, double height, double weight,
                                  String team, boolean scouted, String position,
                                  HashMap<String, Integer> skills) {
        Player newBaller = new Player(name, age, height, weight,
        team, scouted, position, skills);
        Players.add(newBaller);
    }

    /**
     * Setter for PLayers
     * @param players list of player entities
     */
    public static void setPlayers(List<Player> players) {
        Players = players;
    }

    /**
     * Getter for Players
     * @return Players list of player entities
     */
    public static List<Player> getPlayers(){
        return Players;
    }



}