import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


  /**
   * Test for deleteReview:
   * Verifies that an existing review can be deleted successfully.
   */
  @Test
  public void testDeleteExistingReview() {
    UserDatabase db = new UserDatabase();

    db.dropTables();
    db.createTables();

    db.insertUser("testUser", "password123");
    db.insertAlbum("testAlbum", "testArtist");
    db.insertReview("testArtist", "testAlbum", "delete this review", 1, 1);

    boolean deleted = db.deleteReview(1);

    assertTrue(deleted);
    System.out.println("Existing review deleted successfully");
  }

  /**
   * Test for deleteReview:
   * Verifies that deleting a review that does not exist returns false.
   */
  @Test
  public void testDeleteNonexistentReview() {
    UserDatabase db = new UserDatabase();

    db.dropTables();
    db.createTables();

    boolean deleted = db.deleteReview(999);

    assertFalse(deleted);
    System.out.println("Nonexistent review was not deleted, as expected");
  }
}

}
