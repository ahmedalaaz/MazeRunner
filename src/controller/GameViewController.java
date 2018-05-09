package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.MapCell;
import model.MapGenerator;
import model.Properties;

public class GameViewController implements Initializable{
	@FXML
	AnchorPane infoPanel;
	@FXML
	AnchorPane gamePane;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		MapGenerator mapGenerator = new MapGenerator();
		MapCell[][] map = mapGenerator.getAllCells();
		for(int i = 0 ; i <30 ; i++)for(int j = 0 ; j < 30 ; j++) {
			map[i][j].addToView(gamePane);
		}
		infoPanel.getChildren().add(new Properties());
	}

}
