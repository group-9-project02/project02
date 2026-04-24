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
	private String review;
	private Integer reviewId;
	public Integer albumKey;
	
	Album(String name , String artist, String id) {
		this.name = name;
		this.artist = artist;
		this.id = id;
	 };
	
	Album(String name, String artist, String id, Integer reviewId, String review, Integer albumKey){
		this.name = name;
		this.artist = artist;
		this.id = id;
		this.reviewId= reviewId;
		this.review = review;
		this.albumKey = albumKey;
	}
	public void setReview(String s){
		review = s;
	}
	public String getReview(){
		return review;
	}
	public void setReviewId(Integer id){
		reviewId = id;
	}
	public Integer getReviewId(){
		return reviewId;
	}
	
	public void setAlbumKey(Integer key){
		albumKey = key;
	}
	public Integer getAlbumKey(){
		return albumKey;
	}
}
