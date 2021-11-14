package entities;

import java.util.HashMap;

public class Defender extends Player {
    public Defender(String name, int age, double height, double weight, String team, int rating,
                    double value, String position, HashMap<String, Integer> skills) {
        super(name, age, height, weight, team, rating, value, position, skills);
    }

    @Override
    public void displayPos() {
        System.out.println("This player's position is " + super.getPosition());
    }
}


