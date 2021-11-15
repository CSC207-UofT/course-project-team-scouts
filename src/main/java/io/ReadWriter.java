package io;

import entities.UserList;

import java.io.IOException;

public interface ReadWriter {
    /**
     * @param filepath location of information file
     * @param o object to be serialized
     */
    void saveToFile(String filepath, Object o) throws IOException;

    /**
     * @param filepath location of login information file
     */
    UserList readFromFile(String filepath) throws IOException, ClassNotFoundException;
}
