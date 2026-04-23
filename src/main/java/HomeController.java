//package org.example;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.text.html.ImageView;

public class HomeController implements Initializable {

  @FXML
  public Button accountButton;
  @FXML
  public ListView<String> albumList;
  @FXML
  public ListView<String> reviewsList;
  @FXML
  private Button searchButton;


  @FXML
  private void transitionAlbumSearch(ActionEvent event) throws IOException{
    System.out.println("transition to search clicked");

    switchScene(event, SceneType.SEARCH);
  }

  //This is a temporary method to test scene transitions will be adapted for correct useage in future version
  //*connected to onAction="#sceneTransition" in the FXML
  @FXML
  private void transitionAccount(ActionEvent event) throws IOException{
    System.out.println("transition to account creation clicked");

    switchScene(event, SceneType.ACCOUNT);
  }

  //this is an example method of how to switch scenes using an action from the fxml file in each scene it will be adjusted to only utilize that scenes capabilities ie. this login scene should not have the ability to transition directly to the reviews page
  //this method is called by the above method due to fxml "onAction" event not being able to throw in parameters
  @FXML
  public void switchScene(ActionEvent event, SceneType newScene) throws IOException {
    // Load the new FXML file

    //default scene if load fails
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewScene.fxml")));;
    switch (newScene){
      case LOGIN -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
      case ACCOUNT_CREATION -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accountCreation.fxml")));
      case HOME -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
      case SEARCH -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchPage.fxml")));
      case ALBUM -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
      case REVIEWS -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
      case ACCOUNT -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
    };


    // Get the current stage from the event source
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Create and set the new scene
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  //need to find a way to pass in the current users id
  private int curUser;
  @FXML
  public void initialize(URL url, ResourceBundle resourceBundle) {
    UserDatabase db = new UserDatabase();

    albumList.getItems().add(db.userReviewedAlbums(curUser));

    reviewsList.getItems().add(db.getAllUserReviews(curUser));

  }
}
