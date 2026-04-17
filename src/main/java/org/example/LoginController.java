package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
