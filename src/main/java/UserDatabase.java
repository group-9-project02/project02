import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import se.michaelthelin.spotify.model_objects.specification.User;


/**
*
* Author: Malik Kouyate
* Created: 4/13/2026
* Purpose:
*
**/

public class UserDatabase{
	
	private static String dbName = "jdbc:sqlite:userDb.db";
	
	static String userInfo= "CREATE TABLE IF NOT EXISTS userInfo( userId INTEGER PRIMARY KEY, name TEXT NOT NULL UNIQUE, password TEXT NOT NULL )";
	
	static String storedAlbums = "CREATE TABLE IF NOT EXISTS storedAlbums(albumId INTEGER PRIMARY KEY, album TEXT NOT NULL, artist TEXT NOT NULL)";
	
	//albumId TEXT FOREIGN KEY(albumId) REFERENCES userAlbums(faveId),
	static String userReviews = "CREATE TABLE IF NOT EXISTS userReviews(reviewId INTEGER PRIMARY KEY, artist TEXT NOT NULL,album TEXT NOT NULL, review TEXT, author INTEGER FOREIGN KEY(author) REFERENCES userInfo(userId), albumId INTEGER FOREIGN KEY(albumId) REFERENCES storedAlbums(albumId))";
	//If database isn't present, creates database.
	public static Connection getDbConnection(){
		try(Connection con = DriverManager.getConnection(dbName)){
			if(con != null){
				var data = con.getMetaData();
				System.out.println("Driver name: " + data.getDriverName());
				return con;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		return null;
	}
	
//	private static void createTables(Connection connection){
	//Creates tables in database.
	private static void createTables(){
		try(Connection connection = DriverManager.getConnection(dbName)){
			System.out.println(connection);
			
			Statement createTable  = connection.createStatement();
			int j = createTable.executeUpdate("create table if not exists userInfo ("
												  + "userId INTEGER PRIMARY KEY, "
												  + "name TEXT NOT NULL ,"
												  + " password TEXT NOT NULL)");
			
			createTable.executeUpdate("CREATE TABLE if not exists storedAlbums("
										  + "albumId INTEGER PRIMARY KEY, "
										  + "album TEXT NOT NULL, "
										  + "artist TEXT NOT NULL)");
			
			createTable.executeUpdate("CREATE TABLE IF NOT EXISTS userReviews("
										  + "reviewId INTEGER PRIMARY KEY, "
										  + "artist TEXT NOT NULL,"
										  + "album TEXT NOT NULL,"
										  + "review TEXT, "
										  + "author INTEGER REFERENCES userInfo(userId),"
										  + "albumId INTEGER REFERENCES storedAlbums (albumId))");
		}catch (SQLException e){
			System.out.println("Could not create tables\nError: " + e.toString());
		}
	}

	/**
	 * Inserts a new user into the userInfo table
	 * @param name the username
	 * @param password the user's password
	 */
	public static void insertUser(String name, String password){

		//SQL command to add a new row into userInfo table. ? = placeholders for values to be inserted.
		String sql = "INSERT INTO userInfo(name, password) VALUES(?, ?)";

		//Try-with-resources to ensure connection is closed after performed operation.
		try (Connection connection = DriverManager.getConnection(dbName);

				//prepares the SQL statement
				var pstmt = connection.prepareStatement(sql)) {

			//sets the first ? to the name parameter
			pstmt.setString(1, name);

			//sets the second ? to the password parameter
			pstmt.setString(2, password);

			//executes the insert statement
			pstmt.executeUpdate();

			System.out.println("User inserted successfully");

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	/**
	 * Inserts a new album into the storedAlbums table
	 * @param album the album name
	 * @param artist the artist name
	 */
	public static void insertAlbum(String album, String artist) {

		//SQL command to insert into storedAlbums
		String sql = "INSERT INTO storedAlbums(album, artist) VALUES(?, ?)";

		//Try-with-resources to ensure connection is closed after performed operation.
		try (Connection connection = DriverManager.getConnection(dbName);
				var pstmt = connection.prepareStatement(sql)) {

			//sets album name
			pstmt.setString(1, album);

			//sets the artist name
			pstmt.setString(2, artist);

			//executes the insert statement
			pstmt.executeUpdate();

			System.out.println("Album inserted successfully");

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Inserts a new review into the userReviews table
	 * @param artist the artist name
	 * @param album the album name
	 * @param review the user written review text
	 * @param author the userId of the user who wrote the review
	 * @param albumId the albumId of the album that's being reviewed
	 */
public static void insertReview(String artist, String album, String review, int author, int albumId) {
	//inserts a new row into userReviews table
	String sql = "INSERT INTO userReviews(artist, album, review, author, albumId) VALUES(?, ?, ?, ?, ?)";

	//try with resources so connection closes properly
	try (Connection connection = DriverManager.getConnection(dbName);

			//adds the placeholders
			var pstmt = connection.prepareStatement(sql)) {

		//sets the parameters for each statement
		pstmt.setString(1, artist);
		pstmt.setString(2, album);
		pstmt.setString(3, review);
		pstmt.setInt(4, author);
		pstmt.setInt(5, albumId);

		pstmt.executeUpdate();

		System.out.println("Review inserted successfully");

	} catch (SQLException e) {
		System.out.println("Error: " + e.getMessage());
	}

}

	//Means of removing all data from database.
	private static void dropTables(){
		try(Connection connection = DriverManager.getConnection(dbName)){
			System.out.println(connection);
			
			Statement createTable  = connection.createStatement();
			createTable.executeUpdate("drop table if exists userInfo");
			createTable.executeUpdate("drop table if exists storedAlbums");
			createTable.executeUpdate("drop table if exists userReviews");
		}catch (SQLException e){
			System.out.println("Could not drop tables\nError: " + e.toString());
		}
		
	}
	
	public static void main(String[] args) {
		UserDatabase.getDbConnection();
		UserDatabase.dropTables();
		UserDatabase.createTables();

		//test for insertUser
		UserDatabase.insertUser("testUser", "testPassword");

		//test for insertAlbum
		UserDatabase.insertAlbum("testAlbum", "testArtist");

		//test for insertReview
		UserDatabase.insertReview("testArtist", "testAlbum", "This is a test review.", 1, 1);

	}

}
