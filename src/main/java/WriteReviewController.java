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
		Integer userId = 1; //placeholder
		Integer albumInt = 1;
		db.insertReview(alb.artist,alb.name, review, userId, albumInt);
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
	
	
	@FXML
	private void getData(){
		alb = new Album(this.alb.name, this.alb.artist, this.alb.id);
	}

	@FXML
	private void initialize(){
		album = new Label(alb.name);
		artist = new Label(alb.artist);
		
	};
	
	
	
}
