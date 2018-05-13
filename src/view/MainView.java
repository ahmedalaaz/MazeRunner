package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.LevelsReader;
import java.io.File;
import java.util.HashMap;

import controller.*;
public class MainView extends Application{
	private static FXMLLoader myLoader;
	public static HashMap<String, Character[][]> levels;
	public static void main(String[] args) {
		LevelsReader reader =  new LevelsReader();
		try {
			levels = reader.readLevels();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(getMainScene());
		primaryStage.setResizable(true);
		primaryStage.setTitle("Maze");
		primaryStage.show();
	}

	public  Scene getMainScene()throws Exception {
		File fxml =  new File("resources/views/main_view.fxml");
		FXMLLoader loader =  new FXMLLoader(fxml.toURI().toURL());
		myLoader = loader;
		Parent mainViewRoot = loader.load();
		Scene scene = new Scene(mainViewRoot);
				return scene;
	}
	public static MainViewController getController() {
		return myLoader.getController();
	}
	
}
