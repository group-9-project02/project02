package org.example;




//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Import JavaFX classes needed for the UI
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


//Main entry point, this sets up the initial window and basic layout/shell
public class Main extends Application {

	//This method is called when the app starts
	//It builds the UI
	@Override
	public void start(Stage stage) throws Exception {

		//loads the login layout from the FXML file
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/login.fxml"));

		//creates a scene from the loaded FXML
		Scene scene = new Scene(fxmlLoader.load());

		//Set the window title
		stage.setTitle("SoundCritic");

		//Attach the scene to the new window
		stage.setScene(scene);

		//Autosize the window to fit the content
		stage.sizeToScene();

		//Shows the window
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
