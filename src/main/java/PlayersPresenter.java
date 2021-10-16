import java.util.List;
import java.util.Map;

public class PlayersPresenter implements PresentData<Player> {
    @Override
    public void outputResults(List<Player> playerList) {
        // Output the header of the table.
        if (playerList.size() > 0) {
            // Get the first map for the first player in the list, so we
            // can model the header for the table on this.
            Map<String, Integer> attributes = playerList.get(0).getSkills();

            // Use all keys in HashMap as the column names for the table.
            StringBuilder header = new StringBuilder();
            header.append(String.format("%20s", "Name")).append("|");
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

            playerRow.append(String.format("%20s", p.getName())).append("|");
            playerRow.append(String.format("%20s", p.getAge())).append("|");

            for (Integer skillValue : p.getSkills().values()) {
                playerRow.append(String.format("%20d", skillValue)).append("|");
            }
            System.out.println(playerRow);
        }
    }
}
