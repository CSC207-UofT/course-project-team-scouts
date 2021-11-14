import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PlayerFactory {
    public static Player makePlayer(String name, int age, double height, double weight,
                                    String team, int rating, double value, String position,
                                    HashMap<String, Integer> skills){
//        Player newPLayer = null;
        Set<String> defender = new HashSet<>();
        defender.add("CB");
        defender.add("LB");
        defender.add("RB");
        defender.add("RWB");
        defender.add("LWB");


        Set<String> midfielder = new HashSet<>();
        midfielder.add("CM");
        midfielder.add("LM");
        midfielder.add("RM");
        midfielder.add("CAM");
        midfielder.add("CDM");

        Set<String> forward = new HashSet<>();
        forward.add("RW");
        forward.add("LW");
        forward.add("ST");
        forward.add("CF");
        forward.add("RF");
        forward.add("LF");




        if(position.equals("GK")){
            return new Goalkeeper(name, age, height, weight, team, rating, value, position, skills);
        } else
        if (defender.contains(position)){
            return new Defender(name, age, height, weight, team, rating, value, position, skills);
        } else
        if (midfielder.contains(position)){
            return new Midfielder(name, age, height, weight, team, rating, value, position, skills);
        } else
        if (forward.contains(position)){
            return new Forward(name, age, height, weight, team, rating, value, position, skills);
        } else
            return null;
    }

}
