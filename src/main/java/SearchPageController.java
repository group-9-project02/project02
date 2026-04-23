import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


class AlbumCell extends ListCell<Album>{
	private Button button;
	private VBox box;
	private HBox inner;
	private Label album;
	private Label artist;
	private Label id;
	
	
	public AlbumCell(){
		button = new Button("review");
		album = new Label();
		artist = new Label();
		id = new Label();
		box = new VBox(10,album,artist,id );
		inner = new HBox( box, button);
		
		
		
		button.setOnAction(event -> {
				Album alb = getItem();
				System.out.println((alb.id));
				System.out.println(("out"));
			try {
				storeAlbum(alb);
				switchScene(event,SceneType.WRITEREVIEW);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		});
		
	}
	void storeAlbum(Album alb)  {
		UserDatabase db = new UserDatabase();
		Integer albumKey;
		Integer reviewId;
		String review;
		Integer albKey;
		try{
			albumKey = (Integer) db.getAlbumKey(alb.id);
			System.out.println("albKey");
			System.out.println((albumKey));
			albKey = albumKey;
			if (albumKey == 0) {
				db.insertAlbum(alb.name, alb.artist, alb.id.trim());
				alb.setAlbumKey(db.getAlbumKey(alb.id));
			}
			
			alb.setReview(db.getReview(albumKey, User.currUserId));
			alb.setReviewId(db.getReviewId(albumKey,User.currUserId));
			review = alb.getReview();
			reviewId = alb.getReviewId();
			
			User.currAlbum = new Album(alb.name, alb.artist, alb.id, reviewId, review, albKey);
		
		}catch(Exception e){
			System.out.println(("error: "+ e.toString()));
			
		}
		//use this and add stage.getUserData()
	}
	

	
	@Override
	public void updateItem(Album alb , boolean empty){
		super.updateItem(alb, empty);
		if(alb == null || empty){
			setText(null);
			setGraphic(null);
		}else{
			album.setText(alb.name);
			artist.setText(alb.artist);
			id.setText(alb.id);
			setGraphic(inner);
		}
		
	}
	@FXML
	public void switchScene(ActionEvent event, SceneType newScene) throws IOException {
		// Load the new FXML file
		
		//default scene if load fails
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("writeReview.fxml")));;
		switch (newScene){
			case LOGIN -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
			case ACCOUNT_CREATION -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accountCreation.fxml")));
			case HOME -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewScene.fxml")));
			case SEARCH -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchPage.fxml")));
			case ALBUM -> root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("writeReview.fxml")));
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

public class SearchPageController {
	
	@FXML
	private TextField searchText;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private ToggleButton searchToggle;
	
	@FXML
	private ListView<Album> albumList ;
//	private ListView<String> albumList;
	@FXML
	private ObservableList<Album> albums = FXCollections.observableArrayList();
//	private ObservableList<String> albums = FXCollections.observableArrayList();
	
	@FXML
	protected void addToObservable(Album a) {
		System.out.println(a.name);
		albums.add(a);
		albumList.setCellFactory(lv -> new AlbumCell());
		albumList.setItems(albums);
		albumList.refresh();
	}
	
	@FXML
	public void handleSearch(MouseEvent event){
		String s = searchText.getText();
		System.out.println(s);
		SpotRequests req = new SpotRequests();
		String[] alb = req.search(s, "album").split("\\R");
		System.out.println(alb.toString());
		if(alb[0] != null) {
			addToObservable(new Album(alb[0], alb[1], alb[2]));
		};
		
//		addToObservable(req.search(s, "album"));
		
		searchText.clear();
		
	}
	
	@FXML
	private void initialize() {
		albumList.setCellFactory(lv -> new AlbumCell());
	}
}
	
	
	
	
	




