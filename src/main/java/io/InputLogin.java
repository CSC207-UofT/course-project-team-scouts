package io;

import data.Database;
import data.UserDatabase;
import entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputLogin implements InputData<User> {
    public LoginUseCase.LoginResult loginResult;
    public String username;

    @Override
    public String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    @Override
    public void run(Database<User> userDatabase) {
        System.out.println("Username: ");
        while (true) {
            try {
                username = getInput();
                break;
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.");
            }
        }

        System.out.println("Password: ");
        String password;
        while (true) {
            try {
                password = getInput();
                break;
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.");
            }
        }

        LoginUseCase loginUseCase = new LoginUseCase((UserDatabase) userDatabase);
        LoginController loginController = new LoginController(loginUseCase);
        loginResult = loginController.runLogin(username, password);
    }
}
