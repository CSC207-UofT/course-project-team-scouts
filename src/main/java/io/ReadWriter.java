package io;

import entities.UserList;

import java.io.IOException;

public class ReadWriter {
    /**
     * Writes the entities to file at filePath.
     *
     * @param filePath the file to write the records to
     * @param database stores the list of entities to be serialized
     * @throws IOException can throw input/output exception
     */
    public void saveToFile(String filePath, Database<?> database) throws IOException {
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
    public Database<?> readFromFile(String filePath) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        Database<?> database = (Database<?>) input.readObject();
        input.close();
        return database;
    }
}
