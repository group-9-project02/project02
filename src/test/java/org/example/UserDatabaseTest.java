package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    String username = "testUser" + System.currentTimeMillis();

    //calls insertUser method
    db.insertUser(username, "password123");

    System.out.println("Inserted user: " + username);
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
        "jdbc:sqlite:src/main/java/org/example/userDb.db")) {

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
}

