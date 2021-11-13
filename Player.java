import java.util.HashMap;

/*
 * Class dealing w/ a player entity
 * */
public class Player {
    private String name;
    private int age;
    private double height;
    private double weight;
    private String team;
    private boolean scouted;
    private String position;
    private HashMap<String, Integer> skills;

    // Default Constructor
    public Player() {
        this.name = "N/A";
        this.age = 0;
        this.height = 0;
        this.weight = 0;
        this.team = null;
        this.scouted = false;
        this.position = null;
        this.skills = new HashMap<>();
    }

    public Player(String name, int age, double height, double weight, String team, boolean scouted, String position,
                  HashMap<String, Integer> skills) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.scouted = scouted;
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

    public int getStrength() { return skills.get("strength"); }

    public int getStamina() { return skills.get("stamina"); }

    public void setName(String pos) {
        name = pos;
    }
    public void setAge(int x){
        age = x;
    }
    public void displayName(){
        System.out.println("This player's name is " + getName());
    }

//    abstract void displayPos();
    public void displayAge(){
        System.out.println("This player's age is " + getAge());
    }
}