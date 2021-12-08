package io;

import org.javatuples.Pair;

public class InputHelper {
    public static Pair<Number, Number> splitInput(String input) {
        String[] splitInput = input.split(" ");
        int[] splitInputInts = new int[splitInput.length];
        try {
            for (int i = 0; i < splitInput.length; i++) {
                // Convert the input to an integer
                splitInputInts[i] = Integer.parseInt(splitInput[i]);
            }
        } catch (NumberFormatException e) {
            // User has input something other than an integer
            return null;
        }
        // User has entered 1 value, we use that as the min. and use the default max.
        if (splitInputInts.length == 1) {
            return new Pair<>(splitInputInts[0], 250);
        }
        // User has entered 2 values, we use those as the min. and max.
        else if (splitInputInts.length == 2) {
            return new Pair<>(splitInputInts[0], splitInputInts[1]);
        }
        // User has entered more than 2 values
        return null;
    }
}
