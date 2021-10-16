import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InputPlayerAttributes implements InputData {
    BufferedReader reader;
    public String getInput() throws IOException{
        return reader.readLine();
    }

    public void run() {
        PlayerPropertiesIterator prompts = new PlayerPropertiesIterator();
        List<String> temp = new ArrayList<>();

        System.out.print("Type 'exit' to quit or 'ok' to continue: ");
        try {
            String input = getInput();
            while (!input.equals("exit") && prompts.hasNext()) {
                System.out.print(prompts.next());
                input = InputPlayerAttributes.getInput();
                if (!input.equals("exit")) {
                    temp.add(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }

        try {
            if (temp.get(0) != null) {
                //TODO: utilize temp for any further calculation/queries
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Empty input.");
        }
    }
}