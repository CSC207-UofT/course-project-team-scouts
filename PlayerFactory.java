public class PlayerFactory {
    public static Player makePlayer(String type){
        Player newPLayer = null;
        if(type.equals("Goalkeeper")){
            return new Goalkeeper();
        } else
        if (type.equals("Defender")){
            return new Defender();
        } else
        if (type.equals("Midfielder")){
            return new Midfielder();
        } else
        if (type.equals("Forward")){
            return new Forward();
        } else return null;
    }

}
