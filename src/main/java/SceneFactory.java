

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
  public static Scene createScene(SceneType type, Stage scene) {
    return switch (type){
      case LOGIN -> buildLoginScene(scene);
      case ACCOUNT_CREATION -> null;
      case HOME -> null;
      case SEARCH -> null;
      case ALBUM -> null;
      case REVIEWS -> null;
      case ACCOUNT -> buildAccountScene(scene);
    };
  }


  //these are format examples for how we will construct our scenes
  private static Scene buildLoginScene(Stage scene) {
    Label title = new Label("Login");

    Button s1Switch = new Button("Switch Scene");

    s1Switch.setOnAction(e -> {
      scene.setScene(SceneFactory.createScene(SceneType.ACCOUNT, scene));
    });


    VBox layout = new VBox(10, title, s1Switch);
    
    return new Scene(layout, 600, 400);
  }

  private static Scene buildAccountScene(Stage scene) {
    Label title = new Label("Account");

    Button s1Switch = new Button("Switch Scene");

    s1Switch.setOnAction(e -> {
      scene.setScene(SceneFactory.createScene(SceneType.LOGIN, scene));
    });

    VBox layout = new VBox(10, title, s1Switch);

    return new Scene(layout, 600, 400);
  }
}