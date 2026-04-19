import org.junit.jupiter.api.Test;

/**
 * Name: Xiomara Turpin
 * Date: 04/14/2026
 * Description: Unit test for UserDatabase insert methods
 */


public class UserDatabaseTest {

  /**
   * Test for insertUser:
   * Verified that a user can be added to the database without errors
   * This test creates a username to prevent duplicates
   * if no exception occurs, the test is successful.
   */
  @Test
  public void testInsertUser() {
    String username = "testUser" + System.currentTimeMillis();

    UserDatabase.insertUser(username, "password123");

    System.out.println("Inserted user: " + username);
  }


}
