
import java.io.IOException;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

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
      case HOME -> null;
      case SEARCH -> buildSearchPage(scene);
	  case ALBUM -> buildReviewPage(scene);
	  case REVIEWS -> null;
      case ACCOUNT -> null;
    };
  }


  //these are format examples for how we will construct our scenes
  private static Scene buildLoginScene(Stage scene) throws Exception {
    //loads the login layout from the FXML file
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));

    //creates a scene from the loaded FXML
    Scene loginScene = new Scene(fxmlLoader.load());

    //Autosize the window to fit the content
    scene.sizeToScene();
    
    return loginScene;
  }

  private static Scene buildAccountCreationScene(Stage scene) throws Exception {
    //loads the login layout from the FXML file
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/accountCreation.fxml"));

    //creates a scene from the loaded FXML
    Scene accountCreationScene = new Scene(fxmlLoader.load());

    //Autosize the window to fit the content
    scene.sizeToScene();

    return accountCreationScene;
  }
  
  private static Scene buildSearchPage(Stage scene) throws IOException {
	  FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/searchPage.fxml"));
	  
//	  Parent root = (Parent) fxmlLoader.load();
//	  scene.setScene(new Scene(root));
//	  SearchPageController controller = fxmlLoader.getController();
//	  fxmlLoader.setController(controller);
	  Scene searchScene = new Scene(fxmlLoader.load());
	  scene.sizeToScene();
	  return searchScene;
  }
  
  private static Scene buildReviewPage(Stage scene) throws IOException {
	  FXMLLoader fxmlLoader = new FXMLLoader(SceneFactory.class.getResource("/writeReview.fxml"));
	  Parent root = (Parent) fxmlLoader.load();
	  scene.setScene(new Scene(root));
//	  Scene reviewScene = new Scene(fxmlLoader.load());
//	  scene.sizeToScene();
	  return new Scene(root);
  }
  
  private static Scene buildAccountScene(Stage scene) throws IOException {
	  FXMLLoader fxmlLoader = new FXMLLoader(SceneFactory.class.getResource("/writeReview.fxml"));
	  Parent root = (Parent) fxmlLoader.load();
	  scene.setScene(new Scene(root));
//	  Scene reviewScene = new Scene(fxmlLoader.load());
//	  scene.sizeToScene();
	  return new Scene(root);
  };
  
  
}