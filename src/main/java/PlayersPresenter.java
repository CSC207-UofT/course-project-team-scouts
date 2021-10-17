import java.util.HashMap;
import java.util.List;

public class PlayersPresenter implements PresentData<Player> {
    /**
     * Outputs the results of a search for players by their skill attributes
     * to the console in a tabular format. Does not return anything to the
     * calling method.
     * @param playerList a list of Player entities that were yielded by the search.
     */
    @Override
    public void outputResults(List<Player> playerList) {
        // Output the header of the table.
        if (playerList.size() > 0) {
            // Get the first map for the first player in the list, so we
            // can model the header for the table on this.
            HashMap<String, Integer> attributes = playerList.get(0).getSkills();

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

        // Loop through all players in the list to get their skill values.
        for (Player p: playerList) {
            // Build a row in the table corresponding to a given player and the
            // values for their skill attributes.
            StringBuilder playerRow = new StringBuilder();

            playerRow.append(String.format("%40s", p.getName())).append("|");
            playerRow.append(String.format("%20s", p.getAge())).append("|");

            for (Integer skillValue : p.getSkills().values()) {
                playerRow.append(String.format("%20d", skillValue)).append("|");
            }
            System.out.println(playerRow);
        }
    }
}
