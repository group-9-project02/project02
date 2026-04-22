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
/**
*
* Author: Malik Kouyate
* Created: 4/16/2026
* Purpose:
*
**/
class Album {
	
	public String name = "name";
	public String artist = "artist";
	public String id ="id";
	
	Album(String name , String artist, String id) {
		this.name = name;
		this.artist = artist;
		this.id = id;
	 }
}


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
				storeAlbum(alb);
				
		});
		
	}
	void storeAlbum(Album alb){
		UserDatabase db = new UserDatabase();
		db.insertAlbum(alb.name, alb.artist, alb.id);
		//use this and add stage.getUserData()
		setUserData(alb);
		
		
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
	
	
	
	
	




