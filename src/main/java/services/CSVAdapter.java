package services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import data.PlayerDatabase;
import data.TeamDatabase;
import entities.Player;
import entities.PlayerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class reads the player data csv into a Java object. Subsets and returns the data
 * depending on the use case.
 */
public class CSVAdapter implements InputAdapter {

    /**
     * Helper method that converts strings to integers. Tries to catch NumberFormatException.
     *
     * @param intAsString csv string cell value
     * @return value the sole parameter now as an int
     */
    public int stringToInt(String intAsString) {
        int value;
        try {
            value = Integer.parseInt(intAsString);
        } catch (NumberFormatException e) {
            value = 0;
        }
        return value;
    }

    /**
     * Helper method that converts strings to doubles. Tries to catch NumberFormatException.
     *
     * @param doubleAsString csv string cell value
     * @return value the sole parameter now as a double
     */
    public double stringToDouble(String doubleAsString) {
        double value;
        try {
            value = Double.parseDouble(doubleAsString);
        } catch (NumberFormatException e) {
            value = 0.0;
        }
        return value;
    }

    /**
     * Returns a hashmap, mapping the string describing a player attribute to the value of
     * that players attribute as an int.
     *
     * @param row is an array of strings of player attribute ratings
     * @return hashMap is a mapping of skillTypes to the players attribute rating in that skill
     */
    public HashMap<String, Integer> makeHashMap(String[] row) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] skillTypes = {"crossing", "finishing",
                "heading accuracy", "short passing",
                "volleys", "dribbling", "curve",
                "fk accuracy", "long passing",
                "ball control", "acceleration", "sprint speed",
                "agility", "reactions", "balance",
                "shot_power", "jumping", "stamina", "strength",
                "long_shots", "aggression", "interceptions",
                "positioning", "vision", "penalties",
                "composure", "marking", "standing tackle",
                "sliding tackle", "goalkeeping diving", "goalkeeping handling",
                "goalkeeping kicking", "goalkeeping positioning", "goalkeeping reflexes"};

        for (int i = 0; i <= skillTypes.length - 1; i = i + 1) {
            hashMap.put(skillTypes[i], stringToInt(row[i]));
        }
        return hashMap;
    }

    public String isolatePosition(String compPosition) {
        String position;
        String comma = ",";
        if (compPosition.contains(comma)) {
            position = compPosition.substring(0, compPosition.indexOf(comma));
        } else {
            position = compPosition;
        }
        return position;
    }

    /**
     * The method responsible for initializing the Player Database. Reads csv file data
     * concerning the player, reformats it where appropriate and feeds it the PlayerDatabase
     *
     * @param databaseFile the path of the database file (CSV)
     */
    @Override
    public void dataDump(String databaseFile, PlayerDatabase playerDatabase, TeamDatabase teamDatabase)
            throws IOException {
        try {
            // Create fileReader and CSVReader objects
            FileReader fileReader = new FileReader(databaseFile);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

            // Read all the data at once into a list of string arrays
            List<String[]> entries = csvReader.readAll();

            // Iterate through each row and process it
            for (String[] row : entries) {

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

                playerDatabase.addEntity(player);
                updateTeamsDatabase(team, teams_accumulator, player, teamDatabase);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

