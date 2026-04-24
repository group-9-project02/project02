

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
 * This will act as the controller that will create and manage switching between scenes
 *
 * @author Ethan Shelley
 * @version 0.1.0
 * @since 4/16/2026
 */
public class SceneFactory{

  //this directs what scene is build when building scenes
  public static Scene createScene(SceneType type, Stage scene) throws Exception {
    return switch (type){
      case LOGIN -> buildLoginScene(scene);
      case ACCOUNT_CREATION -> buildAccountCreationScene(scene);
      case HOME -> buildHomePage(scene);
      case SEARCH -> buildSearchPage(scene);
      case WRITEREVIEW-> buildReviewPage(scene);
	    case ALBUM -> null;
	    case REVIEWS -> null;
      case ACCOUNT -> buildAccountPage(scene);
    };
  }


  //these are format examples for how we will construct our scenes
  private static Scene buildLoginScene(Stage scene) throws Exception {
    //loads the login layout from the FXML file
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));

    //creates a scene from the loaded FXML
    Scene loginScene = new Scene(fxmlLoader.load(), 400, 300);

    //Autosize the window to fit the content
    scene.sizeToScene();
    
    return loginScene;
  }

  private static Scene buildAccountCreationScene(Stage scene) throws Exception {
    //loads the login layout from the FXML file
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("accountCreation.fxml"));

    //creates a scene from the loaded FXML
    Scene accountCreationScene = new Scene(fxmlLoader.load(), 400, 300);

    //Autosize the window to fit the content
    scene.sizeToScene();

    return accountCreationScene;
  }

  private static Scene buildHomePage(Stage scene) throws Exception {
    //loads the login layout from the FXML file
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));

    //creates a scene from the loaded FXML
    Scene homeScene = new Scene(fxmlLoader.load());

    //Autosize the window to fit the content
    scene.sizeToScene();

    return homeScene;
  }
  
  private static Scene buildSearchPage(Stage scene) throws Exception {
	  FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SearchPage.fxml"));
	  
//	  Parent root = (Parent) fxmlLoader.load();
//	  scene.setScene(new Scene(root));
//	  SearchPageController controller = fxmlLoader.getController();
//	  fxmlLoader.setController(controller);
	  Scene searchScene = new Scene(fxmlLoader.load());
	  scene.sizeToScene();
	  return searchScene;
  }
  
  private static Scene buildReviewPage(Stage scene) throws Exception{
	  FXMLLoader fxmlLoader = new FXMLLoader(SceneFactory.class.getResource("writeReview.fxml"));
    //creates a scene from the loaded FXML
    Scene writeReviewsScene = new Scene(fxmlLoader.load());

    //Autosize the window to fit the content
    scene.sizeToScene();

    return writeReviewsScene;
  }
  private static Scene buildAccountPage(Stage scene) throws Exception{
    FXMLLoader fxmlLoader = new FXMLLoader(SceneFactory.class.getResource("userPage.fxml"));
    //creates a scene from the loaded FXML
    Scene accountPage = new Scene(fxmlLoader.load());

    //Autosize the window to fit the content
    scene.sizeToScene();

    return accountPage;
  }


  /**
   * Name: Xiomara Turpin
   * Date: 04/15/2026
   * Description: This class handles user input and button actions from the login.fxml file
   */
  public static class LoginController {

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
    private void transitionAccountCreation(ActionEvent event) throws IOException {
      System.out.println("transition to account creation clicked");

      switchScene(event, SceneType.ACCOUNT_CREATION);
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
  }
}