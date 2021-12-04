package data;

import entities.User;

public class UserDatabase extends Database<User> {
    /**
     * Gets a specific user from the user database
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
