package io.output;

import data.PlayerStatsCalculator;
import entities.Player;
import ui.CommandLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayersPresenter implements PresentData<Player> {
    /* Create an instance of PlayerStatsCalculator for the purpose of
     aggregated statistics calculation. */
    final PlayerStatsCalculator calculator = new PlayerStatsCalculator();

    /**
     * Outputs the results of a search for players by their skill attributes
     * to the console in a tabular format. Does not return anything to the
     * calling method.
     *
     * @param playerList a list of Player entities that were yielded by the search.
     */
    @Override
    public void outputResults(List<Player> playerList) {
        if (playerList.isEmpty()) {
            System.out.println("No matching players found.");
            return;
        }
        // Output the header of the table.
        printHeader(playerList);

        // Loop through all players in the list to get their skill values.
        printRows(playerList);
    }

    /**
     * Prints a nicely-formatted row containing all the relevant details for
     * each player returned by the search.
     *
     * @param players a list of Player entities that were yielded by the search.
     */
    private void printRows(List<Player> players) {
        int playerCount = 0;
        for (Player p : players) {
            /* Build a row in the table corresponding to a given player and the
            values for their skill attributes. */
            StringBuilder playerRow = new StringBuilder();

            /* Only output players with Latin characters in their name (other kinds of
            characters break formatting) */
            if (!containsNonLatinCharacters(p.getName())) {
                // Print Player name, age, and aggregated ratings first
                playerRow.append(String.format("%40s", playerCount)).append("|");
                playerRow.append(String.format("%20s", p.getName())).append("|");
                playerRow.append(String.format("%20s", p.getAge())).append("|");
                playerRow.append(String.format("%20s", calculator.generateOffensiveRating(p))).append("|");
                playerRow.append(String.format("%20s", calculator.generateDefensiveRating(p))).append("|");

                // Print user's individual skill attributes
                for (Integer skillValue : p.getSkills().values()) {
                    playerRow.append(String.format("%25d", skillValue)).append("|");
                }
                System.out.println(playerRow);

                playerCount += 1;
            }

            // Pause the output after 10 players each time, so the user can look
            // through the list of players properly.
            if (pauseOutput(playerCount, players)) {
                break;
            }
        }
    }

    /**
     * Prints a header for the tabular output of all players returned by the search,
     * which contains the names of all the attributes of the players being listed
     * in each column.
     *
     * @param players a list of Player entities that were yielded by the search.
     */
    private void printHeader(List<Player> players) {
        if (players.size() > 0) {
            // Get the first map for the first player in the list, so we
            // can model the header for the table on this.
            HashMap<String, Integer> attributes = players.get(0).getSkills();

            // Prefix the header with the most basic data about the player.

            StringBuilder header = new StringBuilder();
            header.append(String.format("%40s", "ID")).append("|");
            header.append(String.format("%20s", "Name")).append("|");
            header.append(String.format("%20s", "Age")).append("|");
            header.append(String.format("%20s", "Offensive Rating")).append("|");
            header.append(String.format("%20s", "Defensive Rating")).append("|");

            // Use the player's individual skill attributes to head the rest of the columns.
            for (String skill : attributes.keySet()) {
                header.append(String.format("%25s", skill)).append("|");
            }
            System.out.println(header);
        } else {
            System.out.println("No players found.");
        }
    }

    /**
     * Checks an individual string to determine if it contains non-latin based
     * characters (particularly those in eastern Asian scripts). Serves as a helper method
     * to the output functions, determines if strings contain these characters, which
     * typically break the format of the output.
     * <p>
     * Using a solution designed and shared by Joop Eggen on Stack Overflow.
     * https://stackoverflow.com/questions/26357938/detect-chinese-character-in-java/26358371
     *
     * @param s the individual string being checked.
     * @return whether the string contains characters that will not be output properly to the console.
     */
    private boolean containsNonLatinCharacters(String s) {
        for (int i = 0; i < s.length(); ) {
            int codepoint = s.codePointAt(i);
            i += Character.charCount(codepoint);

            if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
                return true;
            }
        }
        return false;
    }

    /**
     * Halts the presenter from outputting function after any multiple of
     * 10 players have been output to the screen until user presses Next
     * to resume it or player to view a player individually.
     * Allows for pagination of output, so user can peruse small
     * sets of players at their leisure, and are not overloaded with too many at once.
     *
     * @param playerCount the number of players that have been output to the console so far.
     */
    private boolean pauseOutput(int playerCount, List<Player> players) {
        if (playerCount % 10 == 0) {
            try {
                String userChoice = CommandLine.userChoiceOutput();

                if (userChoice.equals("player")) {
                    displayPlayer(players);
                    return true;

                } else if (userChoice.equals("menu")) {
                    return true;
                } else {
                    CommandLine.resumeOutput();
                    return false;
                }
            } catch (IOException e) {
                // Even if user enters the wrong key, we can still let it
                // go to the next page of output.
                System.out.print("");

            }
        }
        return false;
    }

    /**
     * Displays the attributes of the player chosen to be viewed by the user
     *
     * @param players the list of players displayed by the printRows.
     */


    private void displayPlayer(List<Player> players) {
        try {
            String userChoice = CommandLine.individualPlayerOutput();
            int playerID = Integer.parseInt(userChoice);

            List<Player> playersDisplayed = new ArrayList<>();

            playersDisplayed.add(players.get(playerID));


            PlayerPresenter playerPresenter = new PlayerPresenter();

            playerPresenter.outputResults(playersDisplayed);


        } catch (IOException e) {
            System.out.print("");
        }
    }
}
