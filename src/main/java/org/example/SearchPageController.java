package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

/**
*
* Author: Malik Kouyate
* Created: 4/16/2026
* Purpose:
*
**/

public class SearchPageController {
	
	@FXML
	private TextField searchText;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private ToggleButton searchToggle;
	
	@FXML
	private ListView<String> albumList;
	
	@FXML
	private ObservableList<String> albums = FXCollections.observableArrayList();
	
	@FXML
	protected void addToObservable(String s) {
		albums.add(s);
		albumList.setItems(albums);
		albumList.refresh();
	}
	
	@FXML
	public void handleSearch(MouseEvent event){
		String s = searchText.getText();
		System.out.println(s);
		SpotRequests req = new SpotRequests();
		addToObservable(req.search(s, "album"));
		
	}
	
	@FXML
	private void initialize() {
	
	}
}
	
	
	
	
	




