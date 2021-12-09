package data;

import entities.User;

/**
 * Responsible for storing User data as a list of User entities.
 */
public class UserDatabase extends Database<User> {
    /**
     * Gets a specific user from the user database.
     *
     * @param username a users username
     * @return a user if the user was found, null otherwise
     */
    public User getUser(String username) {
        for (User user : getEntities()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
