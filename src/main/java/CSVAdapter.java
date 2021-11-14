import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.*;

/**
 * This class reads the player data csv into a java object. Subsets and returns the data
 * depending on the use case.
 */
public class CSVAdapter implements InputAdapter {

    /**
     * Helper method that converts string to integers. Tries to catch NumberFormatException
     * @param intAsString csv string cell value
     * @return value the sole parameter now as an int
     */
    public int stringToInt(String intAsString){
        int value;
        try {
            value = Integer.parseInt(intAsString);
        }
        catch (NumberFormatException e)
        {
            value = 0;
        }
        return value;
    }

    /**
     * Helper method that converts strings to Doubles. Tries to catch NumberFormatException
     * @param doubleAsString csv string cell value
     * @return value the sole parameter now as a double
     */
    public double stringToDouble(String doubleAsString){
        double value;
        try {
            value = Double.parseDouble(doubleAsString);
        }
        catch (NumberFormatException e) {
            value = 0.0;
        }
        return value;
    }

    /**
     * Helper method that checks if a Team is already in the Team database.
     * Adds it if not, updates it with player if it is.
     * @param t_name team name as string
     * @param teams collection of team names as string so far
     * @param player a Player object that belongs to this team
     */
    public void updateTeamsDatabase(String t_name, ArrayList<String> teams, Player player){
        if (teams.contains(t_name)){
            TeamDatabase.updateRoster(t_name, player);
        }
        else{
            teams.add(t_name);
            List<Player> roster = new ArrayList<>();
            roster.add(player);
            TeamDatabase.addEntity(t_name, roster);
        }
    }


    /**
     * Returns a hashmap, mapping the string describing a player attribute to the value of
     * that players attribute as an int
     * @param row is an array of strings of player attribute ratings
     * @return hashMap is a mapping of skillTypes to the players attribute rating in that skill
     */
    public HashMap<String, Integer> makeHashMap(String[] row){
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] skillTypes = {"crossing" ,"finishing",
                "heading accuracy","short passing",
                "volleys","dribbling" ,"curve",
                "fk accuracy", "long passing",
                "ball control", "acceleration" , "sprint speed",
                "agility", "reactions", "balance",
                "shot_power", "jumping", "stamina", "strength",
                "long_shots", "aggression", "interceptions",
                "positioning", "vision", "penalties",
                "composure","marking","standing tackle",
                "sliding tackle","goalkeeping diving","goalkeeping handling",
                "goalkeeping kicking","goalkeeping positioning","goalkeeping reflexes"};

        for (int i = 0; i <= skillTypes.length - 1; i = i + 1){
            hashMap.put(skillTypes[i], stringToInt(row[i]));
        }
        return hashMap;
    }

    public String isolatePosition(String compPosition){
        String position;
        String comma = ",";
        if (compPosition.contains(comma)){
            position = compPosition.substring(0, compPosition.indexOf(comma));
        }
        else{
            position = compPosition;
        }
        return position;
    }

    /**
     * The method responsible for initializing the Player Database. Reads csv file data
     * concerning the player, reformats it where appropriate and feeds it the PlayerDatabase
     */
    @Override
    public void dataDump(String databaseFile) {
        try {
            // Create fileReader and CsvReader objects
            FileReader fileReader = new FileReader(databaseFile);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

            // Read all the data at once into a list of string arrays
            List<String[]> entries = csvReader.readAll();

            // Iterate through each row representing a player, reformat player data and
            // pass it to PlayerFactory, update database with new player
            for (String[] row : entries){

                // list of teams accumulator
                ArrayList<String> teams_accumulator = new ArrayList<>();

                String name = row[2];
                int age = stringToInt(row[4]);
                double height = stringToDouble(row[6]);
                double weight = stringToDouble(row[7]);
                String team = row[9];

                int rating = stringToInt(row[10]);
                double value = stringToDouble(row[12]);
                String position = isolatePosition(row[14]);

                String[] skillAttributes = Arrays.copyOfRange(row, 44, 78);
                HashMap<String, Integer> skills = makeHashMap(skillAttributes);

                Player player = PlayerFactory.makePlayer(name, age, height, weight, team, rating,
                        value, position, skills);

                PlayerDatabase.add_entity(player);
                updateTeamsDatabase(team, teams_accumulator, player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

