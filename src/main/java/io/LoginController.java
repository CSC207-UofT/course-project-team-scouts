package io;

/**
 * Controls the process for logging in.
 */
// If we handled logging out as well, this would be a good controller
// to do it in. It could have runLogin and runLogout methods.
public class LoginController {
    final LoginUseCase loginUseCase;

    public LoginController(LoginUseCase useCase) {
        loginUseCase = useCase;
    }

    /**
     * Run the login use case where username is attempting to log into their
     * account with a password attempt.
     *
     * @param username the username
     * @param password the password attempt
     */
    public LoginUseCase.LoginResult runLogin(String username, String password) {
        // Note: hands off the work to the use case class.
        return loginUseCase.logIn(username, password);
    }
}
