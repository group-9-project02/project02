package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

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
      case SEARCH -> buildSearchScene(scene);
      case ALBUM -> null;
      case REVIEWS -> null;
      case ACCOUNT -> null;
    };
  }


  //these are format examples for how we will construct our scenes
  private static Scene buildLoginScene(Stage scene) throws Exception {
    //loads the login layout from the FXML file
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/login.fxml"));

    //creates a scene from the loaded FXML
    Scene loginScene = new Scene(fxmlLoader.load());

    //Autosize the window to fit the content
    scene.sizeToScene();
    
    return loginScene;
  }

  private static Scene buildAccountCreationScene(Stage scene) throws Exception {
    //loads the login layout from the FXML file
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/accountCreation.fxml"));

    //creates a scene from the loaded FXML
    Scene accountCreationScene = new Scene(fxmlLoader.load());

    //Autosize the window to fit the content
    scene.sizeToScene();

    return accountCreationScene;
  }

  private static Scene buildSearchScene(Stage scene) throws Exception {
    //loads the login layout from the FXML file
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/searchPage.fxml"));

    //creates a scene from the loaded FXML
    Scene searchScene = new Scene(fxmlLoader.load());

    //Autosize the window to fit the content
    scene.sizeToScene();

    return searchScene;
  }


}