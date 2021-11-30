package io;

import data.UserDatabase;
import entities.User;
import java.io.IOException;

public class LoginUseCase {

    /**
     * List of users organized by username
     */
    private final UserDatabase users;

    /**
     * Serializes and deserializes list of users
     */
    ReadWriter readWriter = new ReadWriter();

    /**
     * The "output" of this use case.
     */
    // Note: This could also be a fully-fledged class if we need to return
    // information to the controller.
    public enum LoginResult {
        SUCCESS, FAILURE, NO_SUCH_USER // Should we do NO_SUCH_USER as well as SUCCESS and FAILURE?
    }

    public LoginUseCase(UserDatabase users) {
        this.users = users;
        try {
            String filepath = "dataset(s)/users.ser";
            readWriter.saveToFile(filepath, users);
        } catch (IOException e) {
            System.out.println("User list did not save.");
        }
    }

    /**
     * Run the login use case.
     * @param username the username
     * @param password the password attempt
     * @return whether the attempt matches the password associated with username
     */
    public LoginResult logIn(String username, String password) {
        User user = users.getUser(username);
        if (user == null) {
            users.addEntity(new User(username, password));
            return LoginResult.NO_SUCH_USER;
        }
        else if (user.passwordMatches(password)) {
            return LoginResult.SUCCESS;
        } else {
            return LoginResult.FAILURE;
        }
    }
}

