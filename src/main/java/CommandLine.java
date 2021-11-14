import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLine {
    
    /** 
     * The main method of this program. Instantiates necessary classes
     * and starts the command line interface.
     * 
     * @param  args        additional arguments.
     * @throws IOException if the database file ("dataset(s)/players_20.csv") 
     *                     is missing
     */
    public static void main(String[] args) throws IOException {
        CSVAdapter adapter = new CSVAdapter();
        adapter.dataDump("dataset(s)/players_20.csv");
        runPrompts();
    }


    
    /** 
     * Runs all user prompts and accepts input.
     * 
     * @throws IOException if user input is invalid
     */
    private static void runPrompts() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Would you like to search for players based on their name or attributes?" +
                " (Please input 'name' or 'attributes'): ");
        String s = reader.readLine();

        try {
            InputData inputClass = PlayerBuilder.getInputType(s);
            inputClass.run();

        }catch (NullPointerException e){
            System.out.println("Invalid Response");
        }
    }

    /**
     * Halts output to the console until the user presses the ENTER key.
     * Used primarily by presenter classes.
     *
     * @throws IOException user may enter the wrong key.
     */
    public static void resumeOutput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Press ENTER in order to get the next page of players: ");
        reader.readLine();
    }
}
