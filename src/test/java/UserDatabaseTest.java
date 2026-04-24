

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    //creates the UserDatabase object so we can call the instance method since not static
    UserDatabase db = new UserDatabase();

    //creates the test data to avoid duplicate usernames
    String username = "testUser" + System.currentTimeMillis();
    String password = "testPassword";

    //calls insertUser method
    db.insertUser(username, password);

    try (Connection connection = DriverManager.getConnection(
        "jdbc:sqlite:userDb.db")) {

      //the SQL query to find the user that was just added
      String sql = "SELECT name, password FROM userInfo WHERE name = ?";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, username);

      //executes the query and stores the result in ResultSet
      ResultSet rs = pstmt.executeQuery();

      //compares the database values with expected values
      assertTrue(rs.next(), "User not found in database");
      assertEquals(username, rs.getString("name"));
      assertEquals(password, rs.getString("password"));

    } catch (Exception e) {
      fail("Database query failed: " + e.getMessage());
    }

  }

  /**
   * Test for insertAlbum:
   * Verified that an album can be added to the database without errors
   * This test creates a unique album name and ID to prevent duplicates
   * if no exception occurs, the test is successful.
   */
  @Test
  public void testInsertAlbum() {

    //creates the UserDatabase object so we can call the instance method
    UserDatabase db = new UserDatabase();

    //creates the test data to avoid duplicates
    String albumName = "testAlbum" + System.currentTimeMillis();
    String artistName = "testArtist";
    String albumId = "album" + System.currentTimeMillis();

    //calls the insertAlbum method to add the album into the database
    db.insertAlbum(albumName, artistName, albumId);

    try (Connection connection = DriverManager.getConnection(
        "jdbc:sqlite:userDb.db")) {

      //the SQL query to find the album that was just added
      String sql = "SELECT album, artist, albumId FROM storedAlbums WHERE albumId = ?";
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, albumId);

      //executes the query and stores the result in ResultSet
      ResultSet rs = pstmt.executeQuery();

      //compares the database values with expected values
      assertTrue(rs.next(), "Album not found in database");
      assertEquals(albumName, rs.getString("album"));
      assertEquals(artistName, rs.getString("artist"));
      assertEquals(albumId, rs.getString("albumId"));

      } catch (Exception e) {
        fail("Database query failed: " + e.getMessage());
    }
  }

  //Test for a valid login:
  //Verifies that validateUser returns true when correct credentials are entered
  @Test
  public void testValidLogin() {

    //creates the database object
    UserDatabase db = new UserDatabase();

    //creates a username to avoid duplicates
    String username = "validUser" + System.currentTimeMillis();
    String password = "password";

    //Adds the user into the database
    db.insertUser(username, password);

    //logging in with correct credentials
    boolean result = db.validateUser(username, password);

    //should be true since the credentials are correct
    assertTrue(result, "Expected valid login to return true");
  }

  //Test for invalid login:
  //Verified that validateUser returns false when incorrect credentials are entered
  @Test
  public void testInvalidLogin() {

    //creates the database object
    UserDatabase db = new UserDatabase();

    //creates a username to avoid duplicates
    String username = "invalidUser" + System.currentTimeMillis();
    String password = "password";

    //Adds the user into the database
    db.insertUser(username, password);

    //logging in with incorrect credentials
    boolean result = db.validateUser(username, "wrongPassword");

    //should be true since the credentials are correct
    assertFalse(result, "Expected invalid login to return false");

  }
}

