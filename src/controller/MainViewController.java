package controller;

import java.io.File;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MapCell;
import model.MusicPlayer;

public class MainViewController implements Initializable {
	@FXML
	private AnchorPane difficultyChooserPane;
	@FXML
	private AnchorPane mainMenuPane;
	@FXML
	private AnchorPane levelChooserPane;
	@FXML
	private ToggleGroup levelsToggleGroup;
	@FXML
	private ToggleGroup difficultyToggleGroup;
	@FXML
	private JFXButton playBtn;
	@FXML
	private JFXButton startBtn;
	@FXML
	private JFXButton optionsBtn;
	@FXML
	private JFXButton loadBtn;
	@FXML
	private JFXButton highScoresBtn;
	@FXML
	private JFXButton exitBtn;
	// @FXML
	// private ImageView mazeImageView;
	@FXML
	private ImageView bgImageView;
	@FXML
	private AnchorPane rootPane;
	static FXMLLoader loader;
	private String selectedLevel = "level1";
	private String selectedDifficulty = "Amature";
	@FXML
	private JFXToggleButton level1ToggleBtn;
	@FXML
	private JFXToggleButton amatureToggleBtn;

	public HashMap<KeyCode, Boolean> keyCodeMap = new HashMap<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playBtn.setVisible(false);
		playBtn.setOnAction((event) -> {
			MainViewController.this.startGame(event);
		});
		startBtn.setOnAction((event) -> {
			if (difficultyChooserPane.isVisible())
				return;
			this.viewDifficultyPane();
		});
		// Image mazeImage = new
		// Image(MapCell.class.getClassLoader().getResource("images/maze.jpg").toExternalForm());
		Image bgImage = new Image(MapCell.class.getClassLoader().getResource("images/mazebg.jpg").toExternalForm());
		// mazeImageView.setImage(mazeImage);
		// mazeImageView.toBack();
		// mazeImageView.setOpacity(0.6);
		bgImageView.setImage(bgImage);
		bgImageView.toBack();
		bgImageView.setOpacity(0.2);
		startBtn.setOpacity(0.9);
		loadBtn.setOpacity(0.9);
		exitBtn.setOpacity(0.9);
		highScoresBtn.setOpacity(0.9);
		optionsBtn.setOpacity(0.9);
		mainMenuPane.setOpacity(0.8);
		difficultyChooserPane.setVisible(false);
		levelChooserPane.setVisible(false);
		amatureToggleBtn.setToggleColor(Paint.valueOf("#1a015e"));
		amatureToggleBtn.setToggleLineColor(Paint.valueOf("#3c7cb9"));
		level1ToggleBtn.setToggleColor(Paint.valueOf("#1a015e"));
		level1ToggleBtn.setToggleLineColor(Paint.valueOf("#3c7cb9"));
		levelsToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

				if (levelsToggleGroup.getSelectedToggle() != null) {
					JFXToggleButton btn = (JFXToggleButton) levelsToggleGroup.getSelectedToggle();
					String level = btn.getText();
					level = level.replaceAll(" ", "");
					level = level.replaceAll("L", "l");
					selectedLevel = level;
					if(!playBtn.isVisible())
					viewPlayButton();
				}else {
					removePlayButton();
				}

			}
		});
		difficultyToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

				if (difficultyToggleGroup.getSelectedToggle() != null) {
					JFXToggleButton btn = (JFXToggleButton) difficultyToggleGroup.getSelectedToggle();
					String difficulty = btn.getText();
					selectedDifficulty = difficulty;
					if (!levelChooserPane.isVisible()) {
						viewLevelsPane();
					}
				}else {
					removeLevelsPane();
				}
			}
		});

	}

	private void viewPlayButton() {
		playBtn.setVisible(true);
		TranslateTransition transition = new TranslateTransition(Duration.millis(1000), playBtn);
		playBtn.setLayoutX(playBtn.getLayoutX() - 500);
		transition.setByX(500);
		transition.setAutoReverse(true);
		transition.play();
	}

	private void removePlayButton() {
		FadeTransition transition = new FadeTransition(Duration.millis(1000), playBtn);
		transition.setFromValue(1);
		transition.setToValue(0);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setOnFinished((eve) -> {
			playBtn.setVisible(false);
			playBtn.setOpacity(1);
		});
		transition.play();
	}
	private void removeLevelsPane() {
		FadeTransition transition = new FadeTransition(Duration.millis(1000), levelChooserPane);
		transition.setFromValue(1);
		transition.setToValue(0);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setOnFinished((eve) -> {
			levelChooserPane.setVisible(false);
			levelChooserPane.setOpacity(1);
		});
		transition.play();
	}
	private void removeDifficultyPane() {
		FadeTransition transition = new FadeTransition(Duration.millis(1000), difficultyChooserPane);
		transition.setFromValue(1);
		transition.setToValue(0);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setOnFinished((eve) -> {
			difficultyChooserPane.setVisible(false);
			difficultyChooserPane.setOpacity(1);
		});
		transition.play();
	}

	private void viewDifficultyPane() {
		difficultyChooserPane.setVisible(true);
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), difficultyChooserPane);
		scaleTransition.setFromX(0);
		scaleTransition.setFromY(0);
		scaleTransition.setFromZ(0);
		scaleTransition.setToX(1);
		scaleTransition.setToY(1);
		scaleTransition.setToZ(1);
		scaleTransition.setCycleCount(1);
		scaleTransition.setAutoReverse(true);
		scaleTransition.play();

	}

	private void viewLevelsPane() {
		levelChooserPane.setVisible(true);
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), levelChooserPane);
		scaleTransition.setFromX(0);
		scaleTransition.setFromY(0);
		scaleTransition.setFromZ(0);
		scaleTransition.setToX(1);
		scaleTransition.setToY(1);
		scaleTransition.setToZ(1);
		scaleTransition.setCycleCount(1);
		scaleTransition.setAutoReverse(true);
		scaleTransition.play();

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
		// TODO take levels and difficulty here
	}

	private void viewNewScene(String name, ActionEvent event) {
		try {
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			File fxml = new File("resources/views/" + name);
			loader = new FXMLLoader();
			Parent Root = loader.load(fxml.toURI().toURL());
			Scene Scene = new Scene(Root);
			primaryStage.hide();
			primaryStage.setX(Screen.getPrimary().getVisualBounds().getMinX());
			primaryStage.setY(Screen.getPrimary().getVisualBounds().getMinY());
			primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
			primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
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
			MusicPlayer mainBgMusic  = new MusicPlayer(new File("resources/music/bgMusic.wav"));
			mainBgMusic.playAsyncIndefinite();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GameViewController getGameController() {
		return MainViewController.loader.getController();
	}

	public String getSelectedLevel() {
		return selectedLevel;
	}
}
