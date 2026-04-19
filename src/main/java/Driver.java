/**
*
* Author: Malik Kouyate
* Created: 4/14/2026
* Purpose: Driver class for various data access classes.
*
**/

public class Driver{
	
	public static void main(String[] args) {
		
		UserDatabase db = new UserDatabase();
		
		db.dropTables();
		db.createTables();
		
		//test for insertUser
		db.insertUser("testUser", "testPassword");
		
		//test for insertAlbum
		db.insertAlbum("testAlbum", "testArtist");
		
		//test for registeruser
		db.insertReview("testArtist", "testAlbum", "This is a test review", 1, 1);
		
		String user = "user";
		String user1 = "user1";
		String pass = "pass1";
		db.createTables();
		db.registerUser("bill","12345");
		db.registerUser("bill","12345");
		db.registerUser("jacob","pizza");
		//Test update review
		db.updateReview("New updated review", 1 );
		//Test Register user for duplicate usernames
		db.registerUser(user, pass);
		db.registerUser(user1,pass);
		db.registerUser(user, pass);
		
		db.readDatabase();
		
		//db.dropTables();
		
		
	}
}
