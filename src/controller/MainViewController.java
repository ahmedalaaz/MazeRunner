package controller;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MusicPlayer;

public class MainViewController implements Initializable{
	@FXML
	private AnchorPane difficultyChooserPane;
	@FXML
	private AnchorPane levelChooserPane;
	@FXML
	private ToggleGroup levelsToggleGroup;
	@FXML
	private ToggleGroup difficultyToggleGroup;
	@FXML
	private JFXButton playBtn;
	@FXML
	private AnchorPane rootPane;
	static FXMLLoader loader;
	public HashMap < KeyCode,Boolean> keyCodeMap =new HashMap<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playBtn.setOnAction((event)->{
			MainViewController.this.startGame(event);
		});
	}
	private void startGame(ActionEvent event) {
		FadeTransition transition = new FadeTransition(Duration.millis(1000), rootPane);
		transition.setFromValue(rootPane.getOpacity());
		transition.setToValue(0);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setOnFinished((eve) -> {
			viewNewScene("game_view.fxml", event);
		       
		});
		
		RotateTransition rotate = new RotateTransition(Duration.millis(1000), rootPane);
		rotate.setByAngle(360f);
		rotate.setCycleCount(1);
		rotate.setAutoReverse(true);
		rotate.setOnFinished((eve) -> {
			transition.play();
		});
		rotate.play();
		//TODO take levels and difficulty here
	}
	private void viewNewScene(String name,ActionEvent event) {
		try {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		File fxml =  new File("resources/views/" + name);
		loader = new FXMLLoader(); 
		Parent Root =loader.load(fxml.toURI().toURL());
		Scene Scene = new Scene(Root);
		primaryStage.hide();
		primaryStage.setScene(Scene);
		Scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
			MainViewController.this.keyCodeMap.put(event.getCode(), true);

			}
		});
		Scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				 MainViewController.this.keyCodeMap.put(event.getCode(), false);

			}
		});
		MusicPlayer player = new MusicPlayer(new File("resources/music/bgMusic.wav"));
		player.playAsyncIndefinite();
		primaryStage.show();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	public static GameViewController getGameController() {
		return MainViewController.loader.getController();
	}
}
