package io.input;

import data.Database;
import data.UserDatabase;
import entities.User;
import io.LoginController;
import io.LoginUseCase;

import java.io.IOException;

public class InputLogin implements InputData<User> {
    public LoginUseCase.LoginResult loginResult;
    public String username;

    /**
     * Runs two separate prompts in the command line, the first asking for the
     * user's username, and the second asking for the user's password. Forwards
     * user input and database of users along to the login use case and stores
     * the result of the login attempt.
     *
     * @param userDatabase database of existing users in the program so far.
     */
    @Override
    public void run(Database<User> userDatabase) {
        System.out.print("Username: ");
        while (true) {
            try {
                username = getInput();
                break;
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.\n");
            }
        }

        System.out.print("Password: ");
        String password;
        while (true) {
            try {
                password = getInput();
                break;
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.\n");
            }
        }

        LoginUseCase loginUseCase = new LoginUseCase((UserDatabase) userDatabase);
        LoginController loginController = new LoginController(loginUseCase);
        loginResult = loginController.runLogin(username, password);
    }
}
