import java.util.HashMap;

public class Midfielder extends Player{
    public Midfielder(String name, int age, double height, double weight, String team, int rating, int value, String position,
                      HashMap<String, Integer> skills)
    {
        super(name, age, height, weight, team, rating, value, position, skills);

    }

    @Override
    public void displayPos() {
        System.out.println("This player's pos is " + super.getPosition());}
}
