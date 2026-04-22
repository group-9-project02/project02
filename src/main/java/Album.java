/**
*
* Author: Malik Kouyate
* Created: 4/16/2026
* Purpose:
*
**/
public class Album {
	
	public String name = "name";
	public String artist = "artist";
	public String id ="id";
	
	Album(String name , String artist, String id) {
		this.name = name;
		this.artist = artist;
		this.id = id;
	 };
	
	Album(Album alb){
		this.name = alb.name;
		this.artist = alb.artist;
		this.id = alb.id;
	}
}
