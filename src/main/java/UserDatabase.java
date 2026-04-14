import java.sql.Connection;
import java.sql.DriverManager;
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

public class UserDatabase{
	
	private static String dbName = "jdbc:sqlite:userDb.db";
	
	static String userInfo= "CREATE TABLE IF NOT EXISTS userInfo( userId INTEGER PRIMARY KEY, name TEXT NOT NULL UNIQUE, password TEXT NOT NULL )";
	
	static String storedAlbums =
			"""
			CREATE TABLE IF NOT EXISTS storedAlbums(
			albumId INTEGER PRIMARY KEY,
			album TEXT NOT NULL,
			artist TEXT NOT NULL
			);
			""";
	//albumId TEXT FOREIGN KEY(albumId) REFERENCES userAlbums(faveId),
	static String userReviews =
			"""
			CREATE TABLE IF NOT EXISTS userReviews(
			reviewId INTEGER PRIMARY KEY,
			artist TEXT NOT NULL,
			album TEXT TEXT NOT NULL,
			review TEXT,
			author INTEGER FOREIGN KEY(author) REFERENCES userInfo(userId),
			albumId INTEGER FOREIGN KEY(albumId) REFERENCES storedAlbums(albumId)
			)
			""";
	
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
	private static void createTables(){
		try(Connection connection = DriverManager.getConnection(dbName)){
			
			Statement createTable  = connection.createStatement();
			createTable.executeUpdate("drop table if exists userInfo");
			int i = createTable.executeUpdate("create table userInfo (userId integer PRIMARY KEY, name TEXT NOT NULL , password TEXT NOT NULL)");
			System.out.println(i);
			
			
			
		}catch (SQLException e){
			System.out.println("Could not create tables\nError: " + e.toString());
		}
	}
	
	public static void main() {
		UserDatabase.getDbConnection();
		UserDatabase.createTables();
	
	
	}

}
