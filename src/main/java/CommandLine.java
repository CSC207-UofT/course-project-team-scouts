import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLine {
    public static void main(String[] args) throws IOException {
        CSVAdapter adapter = new CSVAdapter();
        adapter.dataDump("dataset(s)/players_20.csv");
        runPrompts();
    }

    private static void runPrompts() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Would you like to search for players based on their name or attributes?" +
                " (Please input 'name' or 'attributes'): ");
        String s = reader.readLine();
        if (s.equals("name")) {
            InputPlayerName inputClass = new InputPlayerName();
            inputClass.run();
        }
        else if (s.equals("attributes")) {
            InputPlayerAttributes inputClass = new InputPlayerAttributes();
            inputClass.run();
        }
        else {
            System.out.println("Invalid Response!");
        }
    }
}