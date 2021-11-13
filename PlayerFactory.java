import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PlayerFactory {
    public static Player makePlayer(String name, int age, double height, double weight, String team, boolean scouted, String position,
                                    HashMap<String, Integer> skills){
        Player newPLayer = null;
        Set<String> defender = new HashSet<String>();
        defender.add("CB");
        defender.add("LB");
        defender.add("RB");
        defender.add("RWB");
        defender.add("LWB");


        Set<String> midfielder = new HashSet<String>();
        midfielder.add("CM");
        midfielder.add("LM");
        midfielder.add("RM");
        midfielder.add("CAM");
        midfielder.add("CDM");

        Set<String> forward = new HashSet<String>();
        forward.add("RW");
        forward.add("LW");
        forward.add("ST");
        forward.add("CM");




        if(position.equals("GK")){
            return new Goalkeeper(name, age, height, weight, team, scouted, position, skills);
        } else
        if (defender.contains(position)){
            return new Defender(name, age, height, weight, team, scouted, position, skills);
        } else
        if (midfielder.contains(position)){
            return new Midfielder(name, age, height, weight, team, scouted, position, skills);
        } else
        if (forward.contains(position)){
            return new Forward(name, age, height, weight, team, scouted, position, skills);
        } else return null;
    }

}
