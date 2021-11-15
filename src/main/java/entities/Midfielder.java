package entities;

import java.util.HashMap;

public class Midfielder extends Player {
    public Midfielder(String name, int age, double height, double weight, String team, int rating,
                      double value, String position, HashMap<String, Integer> skills) {
        super(name, age, height, weight, team, rating, value, position, skills);

    }
}
