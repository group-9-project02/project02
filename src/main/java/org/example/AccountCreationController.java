package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Name: Xiomara Turpin
 * Date:04/17/2026
 * Description: This class handles the user input and button actions for the account creation scene
 */
public class AccountCreationController {

    //stores input from the text fields: username, password, confirm password
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    //This method will run when the "Create Account" button is clicked
    @FXML
    private void handleCreateAccount() {

      System.out.println("Create account clicked");
      System.out.println("Username: " + usernameField.getText());

      //later add validate username/password with the database here
    }
  }
