package io.input;

import data.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interface implemented by all classes that prompt user for input.
 *
 * @param <T> type of entity relevant to the input
 */
public interface InputData<T> {
    /**
     * Takes in a single line of user input from the console.
     *
     * @return a string of the input entered by user in the console.
     * @throws IOException for when reader takes input from console that cannot be handled.
     */
    default String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    /**
     * Executes one or several prompts for user to enter input, while also
     * accepting and handling the input.
     *
     * @param database a database of entities typically passed along to a use case.
     */
    void run(Database<T> database);
}