package io;

public class LoginPresenter {
    public static void printFailedLogin() {
        System.out.println("\nFailed to login, incorrect password! Please try again.\n");
    }

    public static void printSuccessfulLogin(String username) {
        System.out.println("\nUser '" + username + "' successfully logged in.");
    }

    public static void printNoUser(String username) {
        System.out.println("\nNew user created with username '" + username + "'.");
    }
}
