import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*
* Author: Malik Kouyate
* Created: 4/13/2026
* Purpose:
*
**/

public class UserDatabase{
	
	private String dbName = "jdbc:sqlite:userDB";
	private Connection dbConnection;
	
	String userTable = "CREATE TABLE IF NOT EXISTS userInfo ("
						   + "userId INTEGER PRIMARY KEY,"
						   + "name TEXT NOT NULL UNIQUE,"
						   + "password TEXT NOT NULL,"
						   + ");";
	
	String userReviews = """
			CREATE TABLE IF NOT EXISTS userReviews (
			reviewId INTEGER PRIMARY KEY,
			review TEXT NOT NULL,
			album TEXT NOT NULL,
			author INTEGER FOREIGN KEY(author) REFERENCES userInfo(userId)
			ON DELETE CASCADE
			);
			""";
	//albumId TEXT FOREIGN KEY(albumId) REFERENCES userAlbums(faveId),
	String userFavorites = """
			CREATE TABLE IF NOT EXISTS userAlbums(
			faveId INTEGER PRIMARY KEY,
			albumId TEXT NOT NULL,
			artistId TEXT NOT NULL,
			album TEXT NOT NULL,
			artist TEXT NOT NULL
			);
			""";
	
	UserDatabase(){
		try(Connection con = DriverManager.getConnection(dbName)){
			if(con != null){
				var data = con.getMetaData();
				System.out.println("Driver name: " + data.getDriverName());
			}
			this.dbConnection = con;
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		}
	}
	
	private void createTables(){
		System.out.println(this.dbConnection);
	
	}
	
	public static void main() {
	
		UserDatabase db = new  UserDatabase();
		System.out.println(db);
		db.createTables();
	
	
	}

}
