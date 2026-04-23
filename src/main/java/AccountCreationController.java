//import java.sql.SQLOutput;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Label;

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

    @FXML
    private Label messageLabel;

    //This method will run when the "Create Account" button is clicked
    @FXML
    private void handleCreateAccount(ActionEvent event) {

      messageLabel.setText("");

      String username = usernameField.getText();
      String password = passwordField.getText();
      String confirmPassword = confirmPasswordField.getText();

      //checks if any field is empty
      if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
        messageLabel.setText("Please fill in all fields");
        return;
      }
      //checks if password and the confirm password fields match
      if (!password.equals(confirmPassword)) {
        messageLabel.setText("Passwords do not match");
        return;
      }
      //handles any errors that may happen when connecting to the db or
      //adding the user
      try {
        UserDatabase db = new UserDatabase();

        db.insertUser(username, password);

        messageLabel.setText("Account created successfully");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneFactory.createScene(SceneType.LOGIN, stage));
        stage.show();

      } catch (Exception e) {
        System.out.println("An error occurred while creating the account: " + e.getMessage());
      }
    }
  }
