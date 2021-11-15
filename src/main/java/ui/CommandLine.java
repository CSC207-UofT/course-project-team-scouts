package ui;

import entities.PlayerBuilder;
import entities.User;
import entities.UserList;
import io.InputData;
import io.LoginController;
import io.LoginUseCase;
import services.CSVAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLine {

    /**
     * The main method of this program. Instantiates necessary classes
     * and starts the command line interface.
     *
     * @param args additional arguments.
     * @throws IOException if the database file ("dataset(s)/players_20.csv")
     *                     is missing
     */
    public static void main(String[] args) throws IOException {
        CSVAdapter adapter = new CSVAdapter();
        UserList users = new UserList();
        users.add(new User("John", "123")); //adds a pre-defined user to list of users database
        LoginUseCase useCase = new LoginUseCase(users); //use case
        LoginController controller = new LoginController(useCase); //controller
        adapter.dataDump("dataset(s)/players_20.csv");
        runPrompts(controller);
    }

    /**
     * Runs through all user prompts
     *
     * @param controller the LoginController used to run the login process
     *                   given a username and password
     * @throws IOException method prone to IOException when taking input
     */
    private static void runPrompts(LoginController controller) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Username: ");
        String username = reader.readLine();
        System.out.println("Password: ");
        String password = reader.readLine();
        controller.runLogin(username, password);

        System.out.print("Would you like to search for players based on their name or attributes?" +
                " (Please input 'name' or 'attributes'): ");
        String s = reader.readLine();

        try {
            InputData inputClass = PlayerBuilder.getInputType(s);
            inputClass.run();

        } catch (NullPointerException e) {
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
