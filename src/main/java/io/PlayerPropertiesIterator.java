package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Iterates through a list of String prompts
 */
public class PlayerPropertiesIterator implements Iterator<String> {
    private List<String> properties = new ArrayList<>();
    private int current = 0;

    /**
     * The prompt Strings are read from a file, player_properties.txt,
     * and added to the list of player properties.
     */
    public PlayerPropertiesIterator() {
        //open file and read from it
        try {
            Scanner myReader = new Scanner(new File("player_properties.txt"));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                properties.add(data + ": ");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("player_properties.txt is missing");
            e.printStackTrace();
        }
    }

    /**
     * Checks for subsequent prompts.
     *
     * @return true if there is prompt that has not yet been returned.
     */
    @Override
    public boolean hasNext() {
        return current < properties.size();
    }

    /**
     * Returns the next prompt to be printed.
     *
     * @return the next prompt.
     */
    @Override
    public String next() {
        String res;

        // List.get(i) throws an IndexOutBoundsException if
        // we call it with i >= properties.size().
        // But Iterator's next() needs to throw a
        // NoSuchElementException if there are no more elements.
        try {
            res = properties.get(current);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        current += 1;
        return res;
    }

    /**
     * Removes the prompt just returned. Unsupported.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
