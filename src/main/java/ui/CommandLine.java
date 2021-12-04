package ui;

import data.Database;
import data.PlayerDatabase;
import data.TeamDatabase;
import data.UserDatabase;
import io.*;
import services.CSVAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CommandLine {
    // This boolean controls the running of the program (prompts will stop when true)
    private static boolean stop = false;

    /**
     * The main method of this program. Instantiates necessary classes
     * and starts the command line interface.
     *
     * @param args additional arguments
     */
    public static void main(String[] args) {
        // Initialize adapter
        CSVAdapter adapter = new CSVAdapter();

        // Initialize databases
        Database<?>[] databases = ReadWriter.loadDatabases();
        UserDatabase userDatabase = (UserDatabase) databases[0];
        PlayerDatabase playerDatabase = (PlayerDatabase) databases[1];
        TeamDatabase teamDatabase = (TeamDatabase) databases[2];

        // Check if the player database is empty, in which case we must use CSVAdapter to add players (and teams)
        if (playerDatabase.getEntities().isEmpty()) {
            adapter.processFile("dataset(s)/players_20.csv", playerDatabase, teamDatabase);
        }

        // Run prompts
        runPrompts(userDatabase, playerDatabase, teamDatabase);
    }

    /**
     * Runs through all user prompts
     */
    private static void runPrompts(UserDatabase userDatabase, PlayerDatabase playerDatabase,
                                   TeamDatabase teamDatabase) {
        // Run the login prompt
        runLoginPrompt(userDatabase);
        while (!stop) {
            // Run the search prompts
            runSelectSearchPrompt(playerDatabase, teamDatabase);
            // Once the user completes their search...
            runContinuePrompt(userDatabase, playerDatabase, teamDatabase);
        }
    }

    private static void runLoginPrompt(UserDatabase userDatabase) {
        // Build the correct InputData class for this prompt
        InputLogin inputLogin = (InputLogin) InputBuilder.getInputType(InputType.LOGIN_DETAILS);
        // Print out instructions
        System.out.println("Please enter your login details.");
        System.out.println("If you enter a username that does not currently exist in the system, " +
                "a new account with that name will be created.");
        // Run the prompt
        inputLogin.run(userDatabase);
        // Decide what to do
        while (inputLogin.loginResult == LoginUseCase.LoginResult.FAILURE) {
            LoginPresenter.printFailedLogin();
            // User must try to log in again
            inputLogin.run(userDatabase);
        }
        if (inputLogin.loginResult == LoginUseCase.LoginResult.SUCCESS) {
            LoginPresenter.printSuccessfulLogin(inputLogin.username);
        } else {
            LoginPresenter.printNoUser(inputLogin.username);
        }
    }

    private static void runSelectSearchPrompt(PlayerDatabase playerDatabase, TeamDatabase teamDatabase) {
        // Initialize new BufferedReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // List of valid inputs for this prompt
        List<String> searchTypes = Arrays.asList("players", "teams");

        String searchType = null;
        // This loops until we get a valid input ("players" or "teams")
        while (!searchTypes.contains(searchType)) {
            // Print out instructions
            System.out.println("Would you like to search for players or for teams? " +
                    "(please input 'players' or 'teams')");
            // Allows user to repeat input if IOException occurs
            while (true) {
                try {
                    searchType = reader.readLine();
                    break;
                } catch (IOException e) { System.out.println("An error occurred, please try again."); }
            }
        }

        // TODO: Implement runPlayerSearchPrompt and runTeamSearchPrompt
        // assert searchType != null;
        // if (searchType.equals("players")) {
        //     runPlayerSearchPrompt();
        // } else {
        //     runTeamSearchPrompt();
        // }
    }

    private static void runContinuePrompt(UserDatabase userDatabase, PlayerDatabase playerDatabase,
                                          TeamDatabase teamDatabase) {
        // Initialize new BufferedReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // List of valid inputs for this prompt
        List<String> inputs = Arrays.asList("", "exit");

        String input = null;
        // This loops until we get a valid input ("exit" or "")
        while (!inputs.contains(input)) {
            // Print out instructions
            System.out.println("Press enter if you would like to continue searching, " +
                    "or type 'exit' if you would like to save and exit.");
            // Allows user to repeat input if IOException occurs
            while (true) {
                try {
                    input = reader.readLine();
                    break;
                } catch (IOException e) { System.out.println("An error occurred, please try again."); }
            }
        }

        assert input != null;
        if (input.equals("exit")) {
            boolean saved = ReadWriter.saveDatabases(userDatabase, playerDatabase, teamDatabase);
            if (saved) { stop = true; }
            else {
                System.out.println("An unknown error has occured! Unable to save!");
            }
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
