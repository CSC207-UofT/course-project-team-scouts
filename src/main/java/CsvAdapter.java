import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.*;

/**
 * This class reads the player data csv into a java object. Subsets and returns the data
 * depending on the use case.
 */
public class CsvAdapter {

    public CsvAdapter() {
    }

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
     * Returns a hashmap, mapping the string describing a player attribute to the value of
     * that players attribute as an int
     * @param row is an array of strings of player attribute ratings
     * @return sexyHash is a mapping of skillTypes to the players attribute rating in that skill
     */
    public HashMap<String, Integer> makeHashMap(String[] row){
        HashMap<String, Integer> sexyHash = new HashMap<>();
        String[] skillTypes = {"crossing" ,"finishing",
                "heading accuracy","short passing",
                "volleys","dribbling" ,"curve",
                "fk accuracy", "long passing",
                "ball control", "acceleration" , "sprint speed"
                , "agility", "reactions", "balance",
                "shot_power", "jumping", "stamina", "strength",
                "long_shots", "aggression", "interceptions",
                "positioning", "vision", "penalties",
                "composure","marking","standing tackle",
                "sliding tackle","goalkeeping diving","goalkeeping handling",
                "goalkeeping kicking","goalkeeping positioning","goalkeeping reflexes"};

        for (int i = 0; i <= skillTypes.length - 1; i = i + 1){
            sexyHash.put(skillTypes[i], stringToInt(row[i]));
        }
        return sexyHash;
    }

    /**
     * The method responsible for initializing the Player Database. Reads csv file data
     * concerning the player, reformats it where appropriate and feeds it the PlayerDatabase
     */
    public void data_dump() {
        try {
            // Create fileReader and CsvReader objects
            String file = "dataset(s)/players_20.csv";
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

            // Read all the data at once into a list of string arrays
            List<String[]> entries = csvReader.readAll();

            // Iterate through each row representing a player, reformat player data and
            // pass it to
            for (String[] row : entries){
                String name = row[3];

                int age = stringToInt(row[4]);
                double height;
                double weight;

                try {
                    height = Double.parseDouble(row[6]);
                }
                catch (NumberFormatException e) {
                    height = 0.0;
                }
                try {
                    weight = Double.parseDouble(row[7]);
                }
                catch (NumberFormatException e) {
                    weight = 0.0;
                }

                String team = row[9];

                boolean scouted = false;

                String position = row[14];

                String[] skillAttributes = Arrays.copyOfRange(row, 44, 78);
                HashMap<String, Integer> skills = makeHashMap(skillAttributes);

                PlayerDatabase.add_entity(name, age, height, weight, team, scouted,
                        position, skills);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

