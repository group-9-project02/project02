package org.example;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Name: Xiomara Turpin
 * Date: 04/15/2026
 * Description: This class handles user input and button actions from the login.fxml file
 */
public class LoginController {

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  //This method will run when the login button is clicked
  //*connected to onAction="#handleLogin" in the FXML
  @FXML
  private void handleLogin() {
    System.out.println("Login clicked");
    System.out.println("Username: " + usernameField.getText());

    //later add validate username/password with the database here
  }

  //This is a temporary method to test scene transitions will be adapted for correct useage in future version
  //*connected to onAction="#sceneTransition" in the FXML
  @FXML
  private void transitionAccountCreation(ActionEvent event) throws IOException{
    System.out.println("transition to account creation clicked");

    switchScene(event, SceneType.ACCOUNT_CREATION);
  }

  //this is an example method of how to switch scenes using an action from the fxml file in each scene it will be adjusted to only utilize that scenes capabilities ie. this login scene should not have the ability to transition directly to the reviews page
  //this method is called by the above method due to fxml "onAction" event not being able to throw in parameters
  @FXML
  public void switchScene(ActionEvent event, SceneType newScene) throws IOException {
    // Load the new FXML file

    //default scene if load fails
    Parent root = FXMLLoader.load(getClass().getResource("NewScene.fxml"));;
    switch (newScene){
      case LOGIN -> root = FXMLLoader.load(getClass().getResource("login.fxml"));
      case ACCOUNT_CREATION -> root = FXMLLoader.load(getClass().getResource("accountCreation.fxml"));
      case HOME -> root = FXMLLoader.load(getClass().getResource("NewScene.fxml"));
      case SEARCH -> root = FXMLLoader.load(getClass().getResource("searchPage.fxml"));
      case ALBUM -> root = FXMLLoader.load(getClass().getResource("NewScene.fxml"));
      case REVIEWS -> root = FXMLLoader.load(getClass().getResource("NewScene.fxml"));
      case ACCOUNT -> root = FXMLLoader.load(getClass().getResource("NewScene.fxml"));
    };


    // Get the current stage from the event source
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Create and set the new scene
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
