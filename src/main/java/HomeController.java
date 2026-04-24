

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class HomeController implements Initializable {


  @FXML
  public Button accountButton;
  @FXML
  private Button searchButton;
  @FXML
  public ListView<String> albumList;
  @FXML
  public ListView<String> reviewsList;



  @FXML
  private void transitionAlbumSearch(ActionEvent event) throws Exception{
    System.out.println("transition to search clicked");

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    stage.setScene(SceneFactory.createScene(SceneType.SEARCH, stage));
    stage.show();
  }

  //This is a temporary method to test scene transitions will be adapted for correct usage in future version
  //*connected to onAction="#sceneTransition" in the FXML
  @FXML
  private void transitionAccount(ActionEvent event) throws Exception{
    System.out.println("transition to account creation clicked");

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    stage.setScene(SceneFactory.createScene(SceneType.ACCOUNT, stage));
    stage.show();
  }

  @FXML
  //updated to using SceneFactory to handle scene switching instead of manually loading FXML files
  public void switchScene(ActionEvent event, SceneType newScene) throws Exception {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    stage.setScene(SceneFactory.createScene(newScene, stage));
    stage.show();
  }


  //need to find a way to pass in the current users id   * 1 is a temp placeholder *
  private int curUser = 2;
  @FXML
  public void initialize(URL url, ResourceBundle resourceBundle) {
    UserDatabase db = new UserDatabase();

    albumList.getItems().add(db.userReviewedAlbums(curUser));

    reviewsList.getItems().add(db.getAllUserReviews(curUser));

  }
}
