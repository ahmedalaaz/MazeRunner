package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BombsFactory;
import model.Bullet;
import model.CheckPoints;
import model.CollisionChecker;
import model.ICell;
import model.MapCell;
import model.MapGenerator;
import model.Observer;
import model.Monster;
import model.Player;
import view.MainView;

public class GameViewController implements Initializable, Observer {
	@FXML
	private AnchorPane infoPanel;
	@FXML
	private AnchorPane gamePane;
	@FXML
	private StackPane losingPane;
	@FXML
	private AnchorPane losingBox;
	@FXML
	private JFXButton tryAgainBtn;
	@FXML
	private JFXButton mainMenuBtn;
	@FXML
	private Label finalScoreLabel;
	@FXML
	private Label healthLabel;
	@FXML
	private Label scoreLabel;
	@FXML
	private Label livesLabel;
	@FXML
	private ImageView heartImageView;
	int cdx[] = { -1, 0, 0, 1 };
	int cdy[] = { 0, -1, 1, 0 };
	double dx[] = { -Monster.speed, 0, 0, Monster.speed };
	double dy[] = { 0, -Monster.speed, Monster.speed, 0 };
	static RandomMoves prevMove = null;
	static ArrayList<RandomMoves> moves = new ArrayList<>();
	private AnimationTimer playerAnimationTimer;
	private AnimationTimer monster1AnimationTimer;
	private AnimationTimer monster2AnimationTimer;
	private Player player;
	private Image heartImage = new Image(
			MapCell.class.getClassLoader().getResource("images/heart.png").toExternalForm());
	private static Monster monster1, monster2;
	private static MapCell[][] map;
	private KeyCode lastPressed;
	private Image bulletImage;
	private long currentTime = System.currentTimeMillis();
	private long prevShoot = System.currentTimeMillis();

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		losingPane.setAlignment(losingBox,Pos.CENTER);
		tryAgainBtn.setOnAction((event)->{
			startGame();
		});
		mainMenuBtn.setOnAction((event)->{
			viewMainMenu(event);
		});
		startGame();
	}
	public void startGame() {
		losingPane.setVisible(false);
		gamePane.setVisible(true);
		CollisionChecker.removeInstance();
		BombsFactory.loweSpeedCnt = 0;
		Player.speed = 0.3;
		Monster.speed = 0.2;
		if(map!=null) {
			gamePane.getChildren().removeAll(gamePane.getChildren());
			map = null;
		}
		heartImageView.setImage(heartImage);
		heartImageView.setFitHeight(ICell.WALL_HEIGHT * 2);
		heartImageView.setFitWidth(ICell.WALL_WIDTH * 2);
		healthLabel.setText(String.valueOf(100));
		scoreLabel.setText(String.valueOf(0));
		MapGenerator mapGenerator = MapGenerator.newInstance();
		map = mapGenerator.getAllCells(MainView.getController().getSelectedLevel());
		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 30; j++) {
				map[i][j].addToView(gamePane);
			}
		mapGenerator.addStartEndToView(gamePane);
		player = mapGenerator.getPlayer();
		livesLabel.setText(String.valueOf(String.valueOf(player.getLives())));
		new CheckPoints().addNewCheckPoint(player);
		player.addObserver(this);
		monster1 = mapGenerator.getFirstMonster();
		monster2 = mapGenerator.getSecondMonster();
		playerAnimationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		playerAnimationTimer.start();
		gamePane.setLayoutY(68);
		gamePane.setLayoutX(5);
		infoPanel.setLayoutY(5);
		// infoPanel.getChildren().add(new Properties());
		monster1AnimationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (!monster1.alive)
					monster1AnimationTimer.stop();
				monster1.move();
			}
		};

		monster1AnimationTimer.start();
		for (int i = 0; i < 4; i++)
			moves.add(new RandomMoves(cdx[i], cdy[i], dx[i], dy[i]));
	
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

		} else if (isPressed(KeyCode.SPACE) && currentTime - prevShoot >= 200) {
			prevShoot = currentTime;
			Bullet bullet = new Bullet(lastPressed, bulletImage);
			bullet.fire(player.playerImageView.getLayoutX(), player.playerImageView.getLayoutY(),
					player.playerImageView.getTranslateX(), player.playerImageView.getTranslateY(), gamePane);
		} else {
			player.animation.stop();
		}
		CollisionChecker.getInstance(map).takeGift(player.playerImageView);
		CollisionChecker.getInstance(map).checkBomb(player.playerImageView);
		if (player.playerScoreCounter >= 35) {
			CheckPoints cp = new CheckPoints();
			player.playerScoreCounter -= 35;
			cp.addNewCheckPoint(player);
		}

	}

	public void stopMovingMonster1() {
		if (monster1AnimationTimer != null)
			monster1AnimationTimer.stop();
	}

	public void stopMovingMonster2() {
		if (monster2AnimationTimer != null)
			monster2AnimationTimer.stop();
	}

	public static void moveMonsterA() {
		if (prevMove != null && can(prevMove)) {
			monster1.MoveX(prevMove.dx);
			monster1.MoveY(prevMove.dy);
			return;
		}
		for (int i = 0; i < 4; i++) {
			monster1.MoveY(moves.get(i).cdy);
			monster1.MoveX(moves.get(i).cdx);
			System.out.println(i);
			if (!CollisionChecker.getInstance(map).monsterWillStop(monster1.monsterImageView)) {
				monster1.MoveY(-1 * moves.get(i).cdy);
				monster1.MoveX(-1 * moves.get(i).cdx);
			} else {
				monster1.MoveY(moves.get(i).cdy);
				monster1.MoveX(moves.get(i).cdx);
				monster1.MoveX(moves.get(i).dx);
				monster1.MoveY(moves.get(i).dy);
				Collections.shuffle(moves);
				prevMove = moves.get(i);
				break;
			}
		}

	}

	private static boolean can(RandomMoves move) {
		monster1.MoveY(move.cdy);
		monster1.MoveX(move.cdx);

		if (!CollisionChecker.getInstance(map).monsterWillStop(monster1.monsterImageView)) {
			monster1.MoveY(-1 * move.cdy);
			monster1.MoveX(-1 * move.cdx);
			return false;
		}
		monster1.MoveY(-1 * move.cdy);
		monster1.MoveX(-1 * move.cdx);

		return true;
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

	public Monster getFirstMonster() {
		return GameViewController.monster1;
	}

	public Monster getSecondMonster() {
		return GameViewController.monster2;
	}

	@Override
	public void update(int health, int score, int lives) {
		this.healthLabel.setText(String.valueOf(health));
		this.scoreLabel.setText(String.valueOf(score));
		this.livesLabel.setText(String.valueOf(lives));
		if(livesLabel.getText().equals("0")) {
			viewLosingPane();
		}
	}
	private void viewLosingPane() {
		losingPane.setVisible(true);
		FadeTransition transition = new FadeTransition(Duration.millis(1000), losingPane);
		transition.setFromValue(0);
		transition.setToValue(0.97);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setOnFinished((eve) -> {
			gamePane.setVisible(false);
		});
		transition.play();
	
	}
	private void viewMainMenu(ActionEvent event) {
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			File fxml = new File("resources/views/" + "main_view.fxml");
			MainViewController.loader = new FXMLLoader();

			try {
				Parent Root = MainViewController.loader.load(fxml.toURI().toURL());
				Scene Scene = new Scene(Root);
				primaryStage.hide();
				primaryStage.setWidth(1300);
				primaryStage.setHeight(600);
				primaryStage.setScene(Scene);
				primaryStage.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

class RandomMoves {
	double cdx, cdy, dx, dy;

	public RandomMoves(double cdx, double cdy, double dx, double dy) {
		this.cdx = cdx;
		this.cdy = cdy;
		this.dx = dx;
		this.dy = dy;
	}
}
