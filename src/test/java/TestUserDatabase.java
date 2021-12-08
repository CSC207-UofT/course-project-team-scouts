import data.UserDatabase;
import entities.User;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class TestUserDatabase {

    String username = "Don Paul Gries";
    String password = "SolidClean";
    User user = new User(username, password);

    @Test
    public void getUserKnownUser() {
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.addEntity(user);
        assertEquals(1, userDatabase.getEntities().size());
        assertSame(user, userDatabase.getUser(username));
    }

    @Test
    public void getUserUnknownUser() {
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.addEntity(user);
        assertEquals(1, userDatabase.getEntities().size());
        assertNull(userDatabase.getUser("malarkey"));
    }

    @Test
    public void testAddEntity() {
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.addEntity(user);
        assertEquals(1, userDatabase.getEntities().size());
        assertSame(user, userDatabase.getEntities().get(0));
    }

    @Test
    public void testSetEntities() {
        List<User> users = new ArrayList<>();
        UserDatabase userDatabase = new UserDatabase();
        users.add(user);
        userDatabase.setEntities(users);
        assertSame(users, userDatabase.getEntities());
    }

    @Test
    public void testGetEntities() {
        List<User> users = new ArrayList<>();
        UserDatabase userDatabase = new UserDatabase();
        assertTrue(userDatabase.getEntities().isEmpty());
        users.add(user);
        userDatabase.setEntities(users);
        assertSame(users, userDatabase.getEntities());
    }
}