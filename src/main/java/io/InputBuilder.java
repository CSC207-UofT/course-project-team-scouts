package io;

public class InputBuilder {


    /**
     * @param i an enum representing the type of search
     * @return InputData a concrete instance of InputData corresponding
     * to the type of search
     */
    public static InputData<?> getInputType(InputType i) {
        switch (i) {
            case LOGIN_DETAILS:
                return new InputLogin();
            case PLAYER_NAME:
                return new InputPlayerName();
            case PLAYER_ATTRIBUTES:
                return new InputPlayerAttributes();
            case TEAM_NAME:
                return new InputTeamName();
            case TEAM_RATINGS:
                return new InputTeamRating();
            default:
                return null;
        }
    }
}
