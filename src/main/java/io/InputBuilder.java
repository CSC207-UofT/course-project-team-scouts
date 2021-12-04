package io;

public class InputBuilder {


    /**
     * @param s the string representing the type of search
     * @return InputData a concrete instance of InputData corresponding
     * to the type of search
     */
    public static InputData getInputType(String s) {

        if (s.equals("name")) {
            return new InputPlayerName();
        } else if (s.equals("attributes")) {
            return new InputPlayerAttributes();
        } else {
            return null;
        }
    }
}
