package org.example;




//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Import JavaFX classes needed for the UI
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

//Main entry point, this sets up the initial window and basic layout/shell
public class Main extends Application {

	//This method is called when the app starts
	//It builds the UI
	@Override
	public void start(Stage stage) {

		//creates a vertical layout container
		VBox root = new VBox(10);

		//adds padding
		root.setStyle("-fx-padding: 20;");

		//adding a placeholder
		root.getChildren().add(new Label("SoundCritic"));

		//creates scene with auto-size, can change to fixed if needed (add pixel values after root)
		Scene scene = new Scene(root);

		//sets the window title
		stage.setTitle("SoundCritic");

		//attaches scene to stage window
		stage.setScene(scene);

		//auto sizes the window to fit the content
		stage.sizeToScene();

		//this shows the window
		stage.show();
	}
	//launches the app and calls start() method
	public static void main(String[] args) {
		launch(args);

	}





//	static void main() {
//		//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//		// to see how IntelliJ IDEA suggests fixing it.
//		IO.println(String.format("Hello and welcome!"));
//
//		for (int i = 1; i <= 5; i++) {
//			//TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//			// for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//			IO.println("i = " + i);
//		}
//	}
}
