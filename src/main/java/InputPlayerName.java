import java.io.BufferedReader;
import java.io.IOException;

public class InputPlayerName implements InputData {
    BufferedReader reader;

    @Override
    public String getInput() throws IOException {
        return reader.readLine();
    }

    @Override
    public void run() {
        String name = "";
        System.out.print("Enter a player's name: ");
        try {
            name = this.getInput();
        } catch (IOException e) {
            System.out.println("Invalid player name.");
        }
        
        try {
            if (name != null) {
                PlayersPresenter pPresenter = new PlayersPresenter();
                pPresenter.outputResults(SearchForPlayer.searchPlayer(name));
            }
        } catch (Exception e) {
            System.out.println("Empty name.");
        }
    }
}