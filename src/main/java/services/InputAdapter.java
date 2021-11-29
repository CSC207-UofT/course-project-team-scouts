package services;


import java.io.IOException;
import data.TeamDatabase;
import data.PlayerDatabase;

public interface InputAdapter {
    /**
     * This method is responsible for importing all data from an external database.
     * It should call PlayerDatabase.addEntity and TeamDatabase.addEntity for each
     * item in the external database.
     *
     * @param databaseFile is a string representing path of the database file on the
     *                     local filesystem (e.g. "dataset(s)/players_20.csv")
     */
    void dataDump(String databaseFile, PlayerDatabase playerDatabase, TeamDatabase teamDatabase) throws IOException;
}
