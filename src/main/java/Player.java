import java.util.HashMap;

/*
 * Class dealing w/ a player entity
 * */
public class Player {
    private String name;
    private int age;
    private final double height;
    private final double weight;
    private final String team;
    private final int rating;
    private final double value;
    private final String position;
    private final HashMap<String, Integer> skills;

    // Default Constructor
    public Player() {
        this.name = "N/A";
        this.age = 0;
        this.height = 0;
        this.weight = 0;
        this.team = null;
        this.rating = 0;
        this.value = 0.0;
        this.position = null;
        this.skills = new HashMap<>();
    }

    public Player(String name, int age, double height, double weight, String team,
                  int rating, double value, String position,
                  HashMap<String, Integer> skills) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.rating = rating;
        this.value = value;
        this.position = position;
        this.skills = skills;
    }

    public HashMap<String, Integer> getSkills() {
        return this.skills;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() { return height; }

    public double getWeight() { return weight; }

    public int getRating() { return rating; }

    public double getValue() { return value; }

    public int getStrength() { return skills.get("strength"); }

    public int getStamina() { return skills.get("stamina"); }

    public void setName(String pos) {
        name = pos;
    }
    public void setAge(int x){
        age = x;
    }
    public void displayName(){
        System.out.println("This player's position is " + getName());
    }
    public void displayAge(){
        System.out.println("This player's age is " + getAge());
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    public void displayPos(){
        System.out.println("Arbitrary Position");}
}
