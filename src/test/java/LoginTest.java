import entities.User;
import data.UserDatabase;
import io.LoginController;
import io.LoginUseCase;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    UserDatabase userDatabase;
    LoginUseCase loginUseCase;
    LoginController loginController;

    @Before
    public void setUp() {
        userDatabase = new UserDatabase();
        loginUseCase = new LoginUseCase(userDatabase);
        loginController = new LoginController(loginUseCase);
    }

    @Test
    public void testSuccessfulLogin(){
        userDatabase.addEntity(new User("Test1", "12"));
        LoginUseCase.LoginResult result = loginController.runLogin("Test1", "12");
        assertEquals(LoginUseCase.LoginResult.SUCCESS, result);
    }

    @Test
    public void testFailedLogin(){
        userDatabase.addEntity(new User("Test2", "34"));
        LoginUseCase.LoginResult result = loginController.runLogin("Test2", "35");
        assertEquals(LoginUseCase.LoginResult.FAILURE, result);
    }

    @Test
    public void testNoUser(){
        LoginUseCase.LoginResult result = loginController.runLogin("Test2", "35");
        assertEquals(LoginUseCase.LoginResult.NO_SUCH_USER, result);
    }
}
