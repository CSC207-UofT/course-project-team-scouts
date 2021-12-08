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
