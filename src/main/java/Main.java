//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Import JavaFX classes needed for the UI
import javafx.application.Application;
import javafx.stage.Stage;

//Main entry point, this sets up the initial window and basic layout/shell
public class Main extends Application {

//	//This method is called when the app starts
//	//It builds the UI
//	@Override
//	public void start(Stage stage) {
//
//		//creates a vertical layout container
//		VBox root = new VBox(10);
//
//		//adds padding
//		root.setStyle("-fx-padding: 20;");
//
//		//adding a placeholder
//		root.getChildren().add(new Label("SoundCritic"));
//
//		//creates scene with auto-size, can change to fixed if needed (add pixel values after root)
//		Scene scene = new Scene(root);
//
//		//sets the window title
//		stage.setTitle("SoundCritic");
//
//		//attaches scene to stage window
//		stage.setScene(scene);
//
//		//auto sizes the window to fit the content
//		stage.sizeToScene();
//
//		//this shows the window
//		stage.show();
//	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("SoundCritic");
		stage.setScene(SceneFactory.createScene(SceneType.LOGIN, stage));
		stage.show();
	}

	//launches the app and calls start() method
	public static void main(String[] args) {
		launch(args);

	}

}
