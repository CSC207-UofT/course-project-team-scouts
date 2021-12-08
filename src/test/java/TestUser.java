import entities.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUser {
    User testUser;

    @Before
    public void setup() {
        this.testUser = new User("User1", "123");
    }

    @Test
    public void testPasswordMatches() {
        assertTrue(this.testUser.passwordMatches("123"));
    }

    @Test
    public void testGetUsername() {
        String expectedUsername = "User1";
        String actualUsername = this.testUser.getUsername();
        assertEquals(expectedUsername, actualUsername);
    }
}
