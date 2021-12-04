package io;

public class LoginPresenter {
    public static void printFailedLogin() {
        System.out.println("Failed to login, incorrect password! Please try again.");
    }

    public static void printSuccessfulLogin(String username) {
        System.out.println("User '" + username + "' successfully logged in.");
    }

    public static void printNoUser(String username) {
        System.out.println("New user created with username '" + username + "'.");
    }
}
