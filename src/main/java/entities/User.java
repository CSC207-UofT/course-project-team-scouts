package entities;

import java.io.Serializable;

/**
 * A user with a username and a password.
 */
public class User implements Serializable {
    // User's username
    private final String username;
    // User's password
    private final String password;

    /**
     * Construct a new user with username and password.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Return whether the password parameter matches this user's password.
     *
     * @param password the password guess
     * @return whether the guess matches the real password.
     */
    public boolean passwordMatches(String password) {
        return this.password.equals(password);
    }

    /**
     * Return the user's username.
     *
     * @return the username of the User
     */
    public String getUsername() {
        return username;
    }
}
