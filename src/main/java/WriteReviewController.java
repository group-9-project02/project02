import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

/**
*
* Author: Malik Kouyate
* Created: 4/19/2026
* Purpose:
*
**/

public class WriteReviewController {
	
	@FXML
	Button submitReview;
	@FXML
	public void handleSubmit(MouseEvent event) {
		String review = userReview.getText();
		String rating = userRating.getText();
		UserDatabase db = new UserDatabase();
		Integer userId = User.currUserId; //placeholder
		if(User.currAlbum.getReview() == null) {
			db.insertReview(User.currAlbum.artist, User.currAlbum.name, review, userId, db.getAlbumKey(User.currAlbum.id));
		}else{
			db.updateReview(review , User.currAlbum.getReviewId());
		};
	}
	
	@FXML
	TextArea userReview;
	@FXML
	TextField userRating;
	
	@FXML
	VBox albumInfo;
	
	@FXML
	Label album;
	
	@FXML
	Label artist;
	
	@FXML
	private void getData(){
	}

	@FXML
	private void initialize(){
		getData();
		System.out.println(User.currAlbum.name);
		System.out.println((User.currAlbum.artist));
		System.out.println((User.currAlbum.albumKey));
		System.out.println((User.currAlbum.id));
		album.setText(User.currAlbum.name);
		artist.setText(User.currAlbum.artist);
		
//		albumKey = db.getAlbumKey(User.currAlbum.id);
//		if(albumKey != null) {
////			review = new HashMap<>(db.getReview(albumKey));
//			if (!review.keySet().isEmpty()) {
//				reviewId = (Integer) review.keySet().toArray()[0];
//				userReview.setText(review.get(reviewId));
//			}
//
//		}
		
	};
	
	
	
}
