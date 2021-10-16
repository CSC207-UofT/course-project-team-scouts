import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class InputPlayerAttributes implements InputData {
    BufferedReader reader;

    @Override
    public String getInput() throws IOException{
        return reader.readLine();
    }

    /**
     * Will give prompts for each attribute that we will search players on.
     * Will also accept user input for each of these prompts and output each player that
     * falls under the range of the given categories.
     */
    @Override
    public void run() {
        PlayerPropertiesIterator prompts = new PlayerPropertiesIterator();
        List<String> temp = new ArrayList<>();

        System.out.print("Type 'exit' to quit or 'ok' to continue (only integers accepted): ");
        try {
            String input = getInput();
            while (!input.equals("exit") && prompts.hasNext()) {
                System.out.print(prompts.next());
                input = this.getInput();
                if (!input.equals("exit")) {
                    temp.add(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }

        try {
            if (temp.get(0) != null) {
                ArrayList<Integer> attributes = new ArrayList<>(); // NOTE: Will change to HashMap in future version
                for (String s : temp) {
                    attributes.add(Integer.parseInt(s));
                }
                PlayersPresenter pPresenter = new PlayersPresenter();
                pPresenter.outputResults(SearchByPlayerAttributes.searchPlayer(attributes));
            }
        } catch (IndexOutOfBoundsException | IOException e) {
            System.out.println("Empty input.");
        }
    }
}