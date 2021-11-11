import java.util.Scanner;
public class PlayerTesting {
    public static void main(String[] args) {
        PlayerFactory factory = new PlayerFactory();
        Player p = null;
        Scanner inp = new Scanner(System.in);
        System.out.println("What kind of player do you want to create?");
        if (inp.hasNextLine()){
            String type = inp.nextLine();
            p = PlayerFactory.makePlayer(type);
            if(p != null){
                details(p);
            } else System.out.println("Please entet a valid position");
        }
    }
    public static void details(Player aPlayer){
        aPlayer.displayAge();
        aPlayer.displayName();
    }

}
