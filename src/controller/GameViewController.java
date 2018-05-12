package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Bullet;
import model.CheckPoints;
import model.CollisionChecker;
import model.ICell;
import model.MapCell;
import model.MapGenerator;
import model.Player;
import model.Properties;
import view.MainView;

public class GameViewController implements Initializable {
	@FXML
	private AnchorPane infoPanel;
	@FXML
	private AnchorPane gamePane;
	private AnimationTimer playerAnimationTimer;
	private Player player;
	private MapCell[][] map;
	private KeyCode lastPressed;
	private Image bulletImage;
	private long  currentTime = System.currentTimeMillis();
	private long prevShoot = System.currentTimeMillis();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		MapGenerator mapGenerator = MapGenerator.getInstance();
		map = mapGenerator.getAllCells(MainView.getController().getSelectedLevel());
		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 30; j++) {
				map[i][j].addToView(gamePane);
			}
		mapGenerator.addStartEndToView(gamePane);
		player = mapGenerator.getPlayer();
		playerAnimationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		playerAnimationTimer.start();
		gamePane.setLayoutY(45);
		infoPanel.setLayoutY(0);
		infoPanel.getChildren().add(new Properties());
	}

	public void update() {
		currentTime = System.currentTimeMillis();
		if (isPressed(KeyCode.UP)) {
			lastPressed = KeyCode.UP;
			bulletImage = MapCell.UP_BULLET_IMAGE;
			player.animation.setOffsetY(3 * 48);
			player.animation.play();
			player.MoveY(-1);
			if (!CollisionChecker.getInstance(map).canMove(player.playerImageView)) {
				player.MoveY(1);
			} else {
				player.MoveY(1);

				player.MoveY(-Player.speed);

			}
		} else if (isPressed(KeyCode.DOWN)) {
			lastPressed = KeyCode.DOWN;
			bulletImage = MapCell.DOWN_BULLET_IMAGE;
			player.animation.setOffsetY(0);
			player.animation.play();
			player.MoveY(1);
			if (!CollisionChecker.getInstance(map).canMove(player.playerImageView)) {
				player.MoveY(-1);
			} else {
				player.MoveY(-1);

				player.MoveY(Player.speed);

			}

		} else if (isPressed(KeyCode.LEFT)) {
			lastPressed = KeyCode.LEFT;
			bulletImage = MapCell.LEFT_BULLET_IMAGE;
			player.animation.setOffsetY(1 * 48);
			player.animation.play();
			player.MoveX(-1);
			if (!CollisionChecker.getInstance(map).canMove(player.playerImageView)) {
				player.MoveX(1);
			} else {
				player.MoveX(1);

				player.MoveX(-Player.speed);

			}

		} else if (isPressed(KeyCode.RIGHT)) {
			lastPressed = KeyCode.RIGHT;
			bulletImage = MapCell.RIGHT_BULLET_IMAGE;
			player.animation.setOffsetY(2 * 48);
			player.animation.play();
			player.MoveX(1);
			if (!CollisionChecker.getInstance(map).canMove(player.playerImageView)) {
				player.MoveX(-1);
			} else {
				player.MoveX(-1);
				player.MoveX(Player.speed);

			}

		}else if (isPressed(KeyCode.SPACE) && currentTime - prevShoot >= 200) {
			prevShoot = currentTime;
			Bullet bullet = new Bullet(lastPressed, bulletImage);
			bullet.fire(player.playerImageView.getLayoutX(), player.playerImageView.getLayoutY(),player.playerImageView.getTranslateX()
					,player.playerImageView.getTranslateY(),gamePane);
		}
		else {
			player.animation.stop();
		}
		CollisionChecker.getInstance(map).takeGift(player.playerImageView);
		CollisionChecker.getInstance(map).checkBomb(player.playerImageView);
		if(player.playerScoreCounter>=35) {
			CheckPoints cp = new CheckPoints(); 
			player.playerScoreCounter-=35;
			cp.addNewCheckPoint(player);	
		}
		
	}
	public boolean isPressed(KeyCode c) {
		return MainView.getController().keyCodeMap.getOrDefault(c, false);
	}
	public AnchorPane getGameRoot() {
		return this.gamePane;
	}
	public Player getPlayer() {
		return this.player;
	}
}
