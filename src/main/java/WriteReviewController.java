import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		if(reviewId == null) {
			db.insertReview(alb.artist, alb.name, review, userId, albumKey);
		}else{
			db.updateReview(review , reviewId);
		};
	}
	
	@FXML
	TextField userReview;
	@FXML
	TextField userRating;
	
	@FXML
	VBox albumInfo;
	
	@FXML
	Label album;
	
	@FXML
	Label artist;
	
	Album alb;
	
	HashMap<Integer,String> review;
	Integer albumKey;
	Integer reviewId;
	UserDatabase db = new UserDatabase();
	
	@FXML
	private void getData(){
	}

	@FXML
	private void initialize(){
		getData();
		
		album = new Label(User.currAlbum.name);
		artist = new Label(User.currAlbum.artist);
		albumKey= db.getAlbumKey(User.currAlbum.id);
		if(albumKey != null) {
			review = new HashMap<>(db.getReview(albumKey));
			if (!review.keySet().isEmpty()) {
				reviewId = (Integer) review.keySet().toArray()[0];
				userReview.setText(review.get(reviewId));
			}
			
		}
		
	};
	
	
	
}
