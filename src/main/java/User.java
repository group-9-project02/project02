/**
*
* Author: Malik Kouyate
* Created: 4/21/2026
* Purpose:
*
**/

class User {
	static Integer currUserId;
	static Boolean loggedIn;
	static Album currAlbum;
	static String currUserName;
	
	static void setAlbum(Album alb){
		currAlbum  = new Album(alb);
	}
	static void setUser(Integer id){
		currUserId= id;
	}
	static void setLoggedIn(Boolean in){
		loggedIn = in;
	}
	static void setUserName(String userName) {
		currUserName = userName;
		
	}
}
