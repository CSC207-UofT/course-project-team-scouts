package io;

/**
 * The interface that the LogInUseCase implements. Will act as the
 * input boundary for login use cases.
 */
public interface LoginInputBoundary {
    LoginUseCase.LoginResult logIn(String username, String password);
}
