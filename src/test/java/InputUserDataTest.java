import entities.User;
import entities.UserList;
import io.LoginController;
import io.LoginUseCase;
import org.junit.*;

public class InputUserDataTest {
    @Test
    public void testSuccessfulLogin(){
        UserList users = new UserList();
        users.add(new User("Test1", "12"));
        LoginUseCase useCase = new LoginUseCase(users);
        LoginController controller = new LoginController(useCase);
        controller.runLogin("Test1", "12");
    }

    @Test
    public void testFailedLogin(){
        UserList users = new UserList();
        users.add(new User("Test2", "34"));
        LoginUseCase useCase = new LoginUseCase(users);
        LoginController controller = new LoginController(useCase);
        controller.runLogin("Test2", "35");
    }

    @Test
    public void testNoUserLogin(){
        UserList users = new UserList();
        users.add(new User("Test3", "56"));
        LoginUseCase useCase = new LoginUseCase(users);
        LoginController controller = new LoginController(useCase);
        controller.runLogin("Test4", "78");
    }
}
