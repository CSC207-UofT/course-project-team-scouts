import java.util.HashMap;
import java.util.List;

public class PlayersPresenter implements PresentData<Player> {
    /**
     * Outputs the results of a search for players by their skill attributes
     * to the console in a tabular format. Does not return anything to the
     * calling method.
     *
     * @param playerList a list of Player entities that were yielded by the search.
     */
    @Override
    public void outputResults(List<Player> playerList) {
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
        for (Player p: players) {
            // Build a row in the table corresponding to a given player and the
            // values for their skill attributes.
            StringBuilder playerRow = new StringBuilder();

            // Only add the
            if (!containsNonLatinCharacters(p.getName())) {
                playerRow.append(String.format("%40s", p.getName())).append("|");
                playerRow.append(String.format("%20s", p.getAge())).append("|");

                for (Integer skillValue : p.getSkills().values()) {
                    playerRow.append(String.format("%20d", skillValue)).append("|");
                }
                System.out.println(playerRow);

                playerCount += 1;
            }

            // Pause the output after 10 players each time, so the user can look
            // through the list of players properly.
            if (playerCount % 10 == 0) {
                try {
                    CommandLine.resumeOutput();
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                }
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

            // Use all keys in HashMap as the column names for the table.
            StringBuilder header = new StringBuilder();
            header.append(String.format("%40s", "Name")).append("|");
            header.append(String.format("%20s", "Age")).append("|");

            for (String skill : attributes.keySet()) {
                header.append(String.format("%20s", skill)).append("|");
            }
            System.out.println(header);
        } else {
            System.out.println("No players found.");
        }
    }

    /**
     * Checks an individual string to determine if it contains non-latin based
     * characters (particularly in eastern Asian scripts). Serves as a helper method
     * to the output functions, determines if strings contain these characters, which
     * typically break the styling of the output.
     *
     * Using a solution designed and shared by Joop Eggen on Stack Overflow.
     * https://stackoverflow.com/questions/26357938/detect-chinese-character-in-java/26358371
     *
     * @param s the individual string being checked.
     * @return whether the string contains characters that will not be output properly to the console.
     */
    private boolean containsNonLatinCharacters(String s) {
        for (int i = 0; i < s.length(); i++) {
            int codepoint = s.codePointAt(i);
//            i += Character.charCount(codepoint);

            if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
                return true;
            }
        }
        return false;
    }
}
