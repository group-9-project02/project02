import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
*
* Author: Malik Kouyate
* Created: 4/23/2026
* Purpose:
*
**/

public class UserPageController {
	
	@FXML
	Button searchNav;
	public void navSearch(ActionEvent e) throws IOException {
		switchScene(e, SceneType.SEARCH);
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
				Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
			case SEARCH -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("searchPage.fxml")));
			case ALBUM -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("writeReview.fxml")));
			case REVIEWS -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
			case ACCOUNT -> root = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
			default -> throw new IllegalStateException("Unexpected value: " + newScene);
		};
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		// Create and set the new scene
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	void initialize() {
	
	}
	
}
