package io;

import data.Database;
import data.PlayerDatabase;
import data.TeamDatabase;
import data.UserDatabase;

import java.io.*;

public class ReadWriter {
    /**
     * Writes the entities to file at filePath.
     *
     * @param filePath the file to write the records to
     * @param database stores the list of entities to be serialized
     * @throws IOException can throw input/output exception
     */
    private static void saveToFile(String filePath, Database<?> database) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(database);
        output.close();
    }

    /**
     * Store the entities to file at filePath.
     *
     * @param filePath file where the entities are stored
     * @return list of entities
     * @throws IOException can throw input/output exception
     */
    private static Database<?> readFromFile(String filePath) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        Database<?> database = (Database<?>) input.readObject();
        input.close();
        return database;
    }

    public static Database<?>[] loadDatabases() {
        try {
            UserDatabase userDatabase = (UserDatabase) readFromFile("dataset(s)/users.ser");
            PlayerDatabase playerDatabase = (PlayerDatabase) readFromFile("dataset(s)/players.ser");
            TeamDatabase teamDatabase = (TeamDatabase) readFromFile("dataset(s)/teams.ser");
            return new Database<?>[]{userDatabase, playerDatabase, teamDatabase};
        } catch (IOException | ClassNotFoundException e) {
            // If we are in this catch, then one of the files above is missing
            // We must rebuild the databases from scratch
            UserDatabase userDatabase = new UserDatabase();
            PlayerDatabase playerDatabase = new PlayerDatabase();
            TeamDatabase teamDatabase = new TeamDatabase();
            return new Database<?>[]{userDatabase, playerDatabase, teamDatabase};
        }
    }

    public static boolean saveDatabases(UserDatabase users, PlayerDatabase players, TeamDatabase teams) {
        try {
            saveToFile("dataset(s)/users.ser", users);
            saveToFile("dataset(s)/players.ser", players);
            saveToFile("dataset(s)/teams.ser", teams);
            return true;
        } catch (IOException e) {
            // Some error has occurred! We did not successfully save!
            return false;
        }
    }
}
