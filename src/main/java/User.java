/**
*
* Author: Malik Kouyate
* Created: 4/21/2026
* Purpose:
*
**/

class User {
	static Integer currUserId = 1;
	static Boolean loggedIn;
	static Album currAlbum;
	static String currUserName;
	
	static void setAlbum(Album alb){
	}
	static void setUserId(Integer id){
		currUserId= id;
	}
	static void setLoggedIn(Boolean in){
		loggedIn = in;
	}
	static void setUserName(String userName) {
		currUserName = userName;
	}
}
