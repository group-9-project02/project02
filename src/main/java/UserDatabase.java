import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

class UserDatabase {
	
	private String dbName = "jdbc:sqlite:userDb.db";
//	private String dbName = "jdbc:sqlite:src/main/java/org/example/userDb.db";
	
	static String userInfo= "CREATE TABLE IF NOT EXISTS userInfo( userId INTEGER PRIMARY KEY, name TEXT NOT NULL UNIQUE, password TEXT NOT NULL )";
	static String storedAlbums = "CREATE TABLE IF NOT EXISTS storedAlbums(albumId INTEGER PRIMARY KEY, album TEXT NOT NULL, artist TEXT NOT NULL)";
	//albumId TEXT FOREIGN KEY(albumId) REFERENCES userAlbums(faveId),
	static String userReviews = "CREATE TABLE IF NOT EXISTS userReviews(reviewId INTEGER PRIMARY KEY, artist TEXT NOT NULL,album TEXT NOT NULL, review TEXT, author INTEGER FOREIGN KEY(author) REFERENCES userInfo(userId), albumId INTEGER FOREIGN KEY(albumId) REFERENCES storedAlbums(albumId))";
	//If database isn't present, creates database.
	UserDatabase(){
		getDbConnection();
	}
	
	public void getDbConnection(){
		try(Connection connection = DriverManager.getConnection(dbName)){
			if(connection!= null){
				var data = connection.getMetaData();
				System.out.println("Driver name: " + data.getDriverName());
				System.out.println((dbName));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		createTables();
//		dropTables();
	}
	//Creates tables in database.
	public void createTables(){
		try(Connection connection = DriverManager.getConnection(dbName)){
			
			Statement createTable  = connection.createStatement();
			int j = createTable.executeUpdate("create table if not exists userInfo ("
												  + "userId INTEGER PRIMARY KEY, "
												  + "name TEXT NOT NULL UNIQUE,"
												  + "password TEXT NOT NULL)");
			
			createTable.executeUpdate("CREATE TABLE if not exists storedAlbums("
										  + "albumKey INTEGER PRIMARY KEY, "
										  + "album TEXT NOT NULL UNIQUE, "
										  + "artist TEXT NOT NULL,"
										  + "albumId TEXT NOT NULL)");
				
			
			createTable.executeUpdate("CREATE TABLE IF NOT EXISTS userReviews("
										  + "reviewId INTEGER PRIMARY KEY, "
										  + "artist TEXT NOT NULL,"
										  + "album TEXT NOT NULL,"
										  + "review TEXT, "
										  + "author INTEGER REFERENCES userInfo(userId),"
										  + "albumKey INTEGER REFERENCES storedAlbums (albumKey))");
		}catch (SQLException e){
			System.out.println("Could not create tables\nError: " + e.toString());
		}
	}

	/**
	 * Inserts a new user into the userInfo table
	 * @param name the username
	 * @param password the user's password
	 */
	public void insertUser(String name, String password){

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
	 *
	 * @param album  the album name
	 * @param artist the artist name
	 * @param id
	 */
	public void insertAlbum(String album, String artist, String id) {

		//SQL command to insert into storedAlbums
		String sql = "INSERT INTO storedAlbums(album, artist, albumId) VALUES(?, ?, ?)";

		//Try-with-resources to ensure connection is closed after performed operation.
		try (Connection connection = DriverManager.getConnection(dbName);
				var pstmt = connection.prepareStatement(sql)) {

			//sets album name
			pstmt.setString(1, album);

			//sets the artist name
			pstmt.setString(2, artist);
			
			//sets album id
			pstmt.setString(3, id);

			//executes the insert statement
			pstmt.executeUpdate();

			System.out.println("Album inserted successfully");

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public Integer getAlbumKey(String albumSpotId){
		String getAlbum = "SELECT albumKey "
							  + "FROM storedAlbums "
							  + "WHERE albumId=?";
		Integer val = 0;
		try(Connection connection = DriverManager.getConnection(dbName)){
			PreparedStatement query  = connection.prepareStatement(getAlbum);
			query.setString(1, albumSpotId.trim());
			System.out.println(albumSpotId.trim());
			ResultSet rs = query.executeQuery();
			if(rs.next()){
				val = rs.getInt("albumKey");
				System.out.println((val));
				if(rs.wasNull()){
					System.out.println("null val");
					return null;
				}
			}
			
			return val;
		} catch (SQLException e) {
			return null;
		}
	};
	/**
	 * Inserts a new review into the userReviews table
	 * @param artist the artist name
	 * @param album the album name
	 * @param review the user written review text
	 * @param author the userId of the user who wrote the review
	 * @param albumKey the albumId of the album that's being reviewed
	 */
public void insertReview(String artist, String album, String review, int author, int albumKey) {
	//inserts a new row into userReviews table
	String sql = "INSERT INTO userReviews(artist, album, review, author, albumKey) VALUES(?, ?, ?, ?, ?)";

	//try with resources so connection closes properly
	try (Connection connection = DriverManager.getConnection(dbName);

			//adds the placeholders
			var pstmt = connection.prepareStatement(sql)) {

		//sets the parameters for each statement
		pstmt.setString(1, artist);
		pstmt.setString(2, album);
		pstmt.setString(3, review);
		pstmt.setInt(4, author);
		pstmt.setInt(5, albumKey);

		pstmt.executeUpdate();

		System.out.println("Review inserted successfully");

	} catch (SQLException e) {
		System.out.println("Error: " + e.getMessage());
	}

}

public void updateReview(String update, int reviewId){
	String sql = "UPDATE userReviews SET review = ? WHERE reviewId = ?" ;
	
	try(Connection connection = DriverManager.getConnection(dbName)){
		
		PreparedStatement query = connection.prepareStatement(sql);
		query.setString(1, update);
		query.setInt(2, reviewId);
		query.executeUpdate();
	
	} catch (SQLException e) {
		
		System.out.println("Error: "+e.toString());
		
	}
	
	
}
	public String getReview(Integer albumInt, Integer userId){
		String check = "SELECT review "
						   + "FROM userReviews "
						   + "WHERE albumKey = ? "
						   + "AND author = ?";
			
		try(Connection connection = DriverManager.getConnection(dbName)){
			PreparedStatement query = connection.prepareStatement(check);
			query.setInt(1, albumInt);
			query.setInt(2, userId);
			ResultSet rs = query.executeQuery();
			return rs.getString(1);
		} catch (SQLException e) {
			return null;
		}
		
	}

	public String getAllUserReviews(Integer userId){
		String check = "SELECT review "
				+ "FROM userReviews "
				+ "WHERE author = ?";

		try(Connection connection = DriverManager.getConnection(dbName)){
			PreparedStatement query = connection.prepareStatement(check);
			query.setInt(1, userId);
			ResultSet rs = query.executeQuery();
			return rs.getString(1);
		} catch (SQLException e) {
			return null;
		}

	}

	public String userReviewedAlbums(Integer userId){
		String check = "SELECT album "
				+ "FROM userReviews "
				+ "WHERE author = ?";

		try(Connection connection = DriverManager.getConnection(dbName)){
			PreparedStatement query = connection.prepareStatement(check);
			query.setInt(1, userId);
			ResultSet rs = query.executeQuery();
			return rs.getString(1);
		} catch (SQLException e) {
			return null;
		}

	}

	public Integer getReviewId(Integer albumInt, Integer userId){
		String check = "SELECT reviewId "
						   + "FROM userReviews "
						   + "WHERE albumKey = ? "
						   + "AND author = ?";
		
		try(Connection connection = DriverManager.getConnection(dbName)){
			PreparedStatement query = connection.prepareStatement(check);
			query.setInt(1, albumInt);
			query.setInt(2, userId);
			ResultSet rs = query.executeQuery();
			return rs.getInt(1);
		} catch (SQLException e) {
			return null;
		}
		
	}

	//Means of removing all data from database.
	public void dropTables(){
		try(Connection connection = DriverManager.getConnection(dbName)){
			System.out.println(connection);
			Statement createTable = connection.createStatement();
			createTable.executeUpdate("drop table if exists userInfo");
			createTable.executeUpdate("drop table if exists storedAlbums");
			createTable.executeUpdate("drop table if exists userReviews");
		}catch (SQLException e){
			System.out.println("Could not drop tables\nError: " + e.toString());
		}
		createTables();
		
	}
	
		
		
	public void registerUser(String user, String password) {
			String check = "SELECT name "
						   +"FROM userInfo "
						   +"WHERE name = ?";
//			String add = "INSERT INTO userInfo(name, password) VALUES(? , ?)";
			
			try (Connection connection = DriverManager.getConnection(dbName)){
				PreparedStatement query = connection.prepareStatement(check);
				query.setString(1, user);
				ResultSet res = query.executeQuery();
				System.out.println("query");
				if(res.getObject(1) == null){
					res.close();
					System.out.println("prep");
					insertUser(user, password);
				}
				
			}catch (SQLException e ){
				System.out.println(("Couldnt add user: "+ e.toString()));
				}
			};
	
	public void getCurrUser(){
		//need to implement
	
	}
	

	//function to read the database for a display all usernames and their associated password
	public void readDatabase(){

		try(Connection connection = DriverManager.getConnection(dbName)){
			Statement query = connection.createStatement();
			ResultSet res = query.executeQuery("SELECT * FROM userInfo");
			while(res.next()){
				System.out.println("username: " + res.getString("name") + "\tpassword: " + res.getString("password") + "\tuserID: " + res.getString("userID"));
			}

		}catch (SQLException e){
			System.out.println(("Couldn't find any users: "+ e.toString()));
		}

	}


}


		

//	public void main(String[] args) {
//
//	UserDatabase db = new UserDatabase();
//
//    db.dropTables();
//    db.createTables();
//
//    //test for insertUser
//    db.insertUser("testUser", "testPassword");
//
//    //test for insertAlbum
//    db.insertAlbum("testAlbum", "testArtist");
//
//    //test for registeruser
//    db.insertReview("testArtist", "testAlbum", "This is a test review", 1, 1);
//
//		String user = "user";
//		String user1 = "user1";
//		String pass = "pass1";
//		db.createTables();
//		db.registerUser(user, pass);
//		db.registerUser(user1,pass);
//		db.registerUser("bill","12345");
//		db.registerUser("bill","12345");
//		db.registerUser("jacob","pizza");
//
//		db.readDatabase();
//
//		//db.dropTables();
//
//
//    }



