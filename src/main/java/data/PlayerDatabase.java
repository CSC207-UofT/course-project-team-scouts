package data;

import entities.Player;

/**
 * Responsible for storing Player Data as a list of player entities
 */
public class PlayerDatabase extends Database<Player> {
    // This method is unnecessary - CSVAdapter calls PlayerFactory, which
    // calls the constructor of Player
    // public Player createPlayer(String name, int age, double height, double weight,
    //                            String team, int rating, double value, String position,
    //                            HashMap<String, Integer> skills) {
    //     return PlayerFactory.makePlayer(name, age, height, weight, team, rating, value, position, skills);
    // }

    // TODO: Add functionality to this class that makes it unique from Database
}
