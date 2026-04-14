import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
*
* Author: Malik Kouyate
* Created: 4/13/2026
* Purpose:
*
**/

public static class UserDatabase{
	
	private String dbName = "jdbc:sqlite:userDb.db";
	private Connection connection = null;
	
	static String userInfo= "CREATE TABLE IF NOT EXISTS userInfo( userId INTEGER PRIMARY KEY, name TEXT NOT NULL UNIQUE, password TEXT NOT NULL )";
	static String storedAlbums = "CREATE TABLE IF NOT EXISTS storedAlbums(albumId INTEGER PRIMARY KEY, album TEXT NOT NULL, artist TEXT NOT NULL)";
	//albumId TEXT FOREIGN KEY(albumId) REFERENCES userAlbums(faveId),
	static String userReviews = "CREATE TABLE IF NOT EXISTS userReviews(reviewId INTEGER PRIMARY KEY, artist TEXT NOT NULL,album TEXT TEXT NOT NULL, review TEXT, author INTEGER FOREIGN KEY(author) REFERENCES userInfo(userId), albumId INTEGER FOREIGN KEY(albumId) REFERENCES storedAlbums(albumId))";
	//If database isn't present, creates database.
	UserDatabase(){
	this.connection = getDbConnection();
	}
	
	public Connection getDbConnection(){
		try{
			this.connection = DriverManager.getConnection(dbName);
			if(connection!= null){
				var data = connection.getMetaData();
				System.out.println("Driver name: " + data.getDriverName());
				return connection;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		return null;
	}
//	private static void createTables(Connection connection){
	//Creates tables in database.
	private void createTables(){
		try{
			
			Statement createTable  = connection.createStatement();
			int j = createTable.executeUpdate("create table if not exists userInfo ("
												  + "userId INTEGER PRIMARY KEY, "
												  + "name TEXT NOT NULL UNIQUE,"
												  + "password TEXT NOT NULL)");
			
			createTable.executeUpdate("CREATE TABLE if not exists storedAlbums("
										  + "albumId INTEGER PRIMARY KEY, "
										  + "album TEXT NOT NULL, "
										  + "artist TEXT NOT NULL)");
			
			createTable.executeUpdate("CREATE TABLE IF NOT EXISTS userReviews("
										  + "reviewId INTEGER PRIMARY KEY, "
										  + "artist TEXT NOT NULL,"
										  + "album TEXT TEXT NOT NULL,"
										  + "review TEXT, "
										  + "author INTEGER REFERENCES userInfo(userId),"
										  + "albumId INTEGER REFERENCES storedAlbums (albumId))");
		}catch (SQLException e){
			System.out.println("Could not create tables\nError: " + e.toString());
		}
	}
	//Means of removing all data from database.
	private void dropTables(){
		try{
			System.out.println(connection);
			Statement createTable  = connection.createStatement();
			createTable.executeUpdate("drop table if exists userInfo");
			createTable.executeUpdate("drop table if exists storedAlbums");
			createTable.executeUpdate("drop table if exists userReviews");
		}catch (SQLException e){
			System.out.println("Could not drop tables\nError: " + e.toString());
		}
		
	}
	
		
		
		
		private void registerUser(String user, String password) {
			String check = "SELECT name "
						   +"FROM userInfo "
						   +"WHERE name = ?";
			String add = "INSERT INTO userInfo(name, password) VALUES(? , ?)";
			
			
			try {
				PreparedStatement query = connection.prepareStatement(check);
				query.setString(1, user);
				ResultSet res = query.executeQuery();
				System.out.println("query");
				if(res.getObject(1) == null){
					System.out.println("prep");
					PreparedStatement register = connection.prepareStatement(add);
					register.setString(1, user);
					register.setString(2, password);
					int added = register.executeUpdate();
					System.out.println(added);
				}
				
			}catch (SQLException e ){
				System.out.println(("Couldnt add user: "+ e.toString()));
			}
			}
		}
		
	
	public static void main() {
		
		UserDatabase db = new UserDatabase();
		
		String user = "user";
		String user1 = "user1";
		String pass = "pass";
		db.registerUser(user, pass);
		db.registerUser(user1,pass);
		
		
	
	
	}


