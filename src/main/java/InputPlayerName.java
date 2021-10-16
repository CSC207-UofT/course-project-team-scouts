import java.io.BufferedReader;
import java.io.IOException;

public class InputPlayerName implements InputData {
    BufferedReader reader;
    public String getInput() throws IOException {
        return reader.readLine();
    }

    public void run() {
        String name = "";
        System.out.print("Enter a player's name: ");
        try {
            name = getInput();
        } catch (IOException e) {
            System.out.println("Invalid player name.");
        }
        
        try {
            if (name != null) {
                //TODO: utilize name for any further calculation/queries
            }
        } catch (Exception e) {
            System.out.println("Empty name.");
        }
    }
}