package io;

public class LoginPresenter {
    /**
     * Outputs a message notifying user that program was not able to log them in.
     */
    public static void printFailedLogin() {
        System.out.println("\nFailed to login, incorrect password! Please try again.\n");
    }

    /**
     * Outputs a message to the specific user that program was able to log them in.
     * @param username the name of the specific user who was logged in.
     */
    public static void printSuccessfulLogin(String username) {
        System.out.println("\nUser '" + username + "' successfully logged in.");
    }

    /**
     * Notifies the user that a new user with the username that they entered has
     * been created, since there is no pre-existing one.
     *
     * @param username the username entered by the user.
     */
    public static void printNoUser(String username) {
        System.out.println("\nNew user created with username '" + username + "'.");
    }
}
