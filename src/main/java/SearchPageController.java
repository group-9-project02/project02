import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		});
		
	}
	void storeAlbum(Album alb)  {
		UserDatabase db = new UserDatabase();
		try{
			Integer albumKey = (Integer) db.getAlbumKey(alb.id);
			System.out.println("albKey");
			System.out.println((albumKey));
			if (albumKey == null) {
				db.insertAlbum(alb.name, alb.artist, alb.id.trim());
			}
			User.currAlbum = new Album(alb);
			SceneFactory.createScene(SceneType.WRITEREVIEW,(Stage) button.getScene().getWindow());
			
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
	
	
	
	
	




