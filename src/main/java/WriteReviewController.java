import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
		Integer userId = User.currUserId; //placeholder
		if(User.currAlbum.getReview() == null) {
			db.insertReview(User.currAlbum.artist, User.currAlbum.name, review, userId, User.currAlbum.albumKey);
		}else{
			db.updateReview(review , db.getReviewId(User.currAlbum.albumKey, User.currUserId));
		};
	}
	@FXML
	public void switchScene(ActionEvent event, SceneType newScene) throws IOException {
		// Load the new FXML file
		
		FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
		Parent root;
		switch (newScene) {
			case LOGIN -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("login.fxml")));
			case ACCOUNT_CREATION -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("accountCreation.fxml")));
			case HOME -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("home.fxml")));
			case SEARCH -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("searchPage.fxml")));
			case ALBUM -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("writeReview.fxml")));
			case REVIEWS -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
			case ACCOUNT -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("userPage.fxml")));
			default -> throw new IllegalStateException("Unexpected value: " + newScene);
		};
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		// Create and set the new scene
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	TextArea userReview;
	
	@FXML
	Button userHome;
	public void handleHome(ActionEvent event) throws IOException {
		System.out.println("home");
		switchScene(event, SceneType.HOME);
	}
	@FXML
	Button userPage;
	public void handleUser(ActionEvent event) throws IOException {
		System.out.println("user");
		switchScene(event, SceneType.ACCOUNT);
		
	}
	
	
	@FXML
	VBox albumInfo;
	
	
	@FXML
	Label album;
	
	@FXML
	Label artist;
	UserDatabase db = new UserDatabase();
	@FXML
	private void initialize(){
		System.out.println(User.currAlbum.name);
		System.out.println((User.currAlbum.artist));
		System.out.println((User.currAlbum.id));
		album.setText(User.currAlbum.name);
		artist.setText(User.currAlbum.artist);
		String review = db.getReview(User.currAlbum.albumKey, User.currUserId);
		if(review != null){
			userReview.setText(review);
		}
		
	};
	
	
	
}
