package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Bullet;
import model.CollisionChecker;
import model.ICell;
import model.MapCell;
import model.MapGenerator;
import model.Monster;
import model.Player;
import model.Properties;
import view.MainView;

public class GameViewController implements Initializable {
	@FXML
	private AnchorPane infoPanel;
	@FXML
	private AnchorPane gamePane;
	int cdx[] = {-1,0,0,1};
	int cdy[] = {0,-1,1,0};
	double dx[] = {-0.3,0,0,0.3};
	double dy[] = {0,-0.3,0.3,0};
	static RandomMoves prevMove = null;
	static ArrayList<RandomMoves> moves =  new ArrayList<>();	
	private AnimationTimer playerAnimationTimer;
	private AnimationTimer monster1AnimationTimer;
	private AnimationTimer monster2AnimationTimer;
	
	private Player player;
	private static Monster monster1, monster2;
	private static MapCell[][] map;
	private KeyCode lastPressed;
	private Image bulletImage;
	private long  currentTime = System.currentTimeMillis();
	private long prevShoot = System.currentTimeMillis();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		MapGenerator mapGenerator = MapGenerator.getInstance();
		map = mapGenerator.getAllCells();
		for (int i = 0; i < 30; i++)
			for (int j = 0; j < 30; j++) {
				map[i][j].addToView(gamePane);
			}
		player = mapGenerator.getPlayer();
		monster1 = mapGenerator.getFirstMonster();
		monster2 = mapGenerator.getSecondMonster();
		playerAnimationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		
		playerAnimationTimer.start();
		monster1AnimationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				monster1.move();
			}
		};
		
		monster1AnimationTimer.start();
		for(int i = 0 ; i <4;i++)moves.add(new RandomMoves(cdx[i], cdy[i], dx[i], dy[i]));

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
		
	}

	public static void moveMonsterA() {
		
		Random rand = new Random();

		int  n = rand.nextInt(50) + 1;
		if(prevMove!=null && can(prevMove)) {
			monster1.MoveX(prevMove.dx);
			monster1.MoveY(prevMove.dy);
			return;
		}
		for(int i = 0 ; i< 4; i++) {
			monster1.MoveY(moves.get(i).cdy);
			monster1.MoveX(moves.get(i).cdx);
			if(!CollisionChecker.getInstance(map).monsterWillStop(monster1.monsterImageView)) {
				monster1.MoveY(-1*moves.get(i).cdy);
				monster1.MoveX(-1*moves.get(i).cdx);
			}else {
				monster1.MoveX(moves.get(i).dx);
				monster1.MoveY(moves.get(i).dy);
				Collections.shuffle(moves);
				prevMove = moves.get(i);
				break;
			}
		}
		
	/*	if(n%2==0) {
		monster1.MoveY(-1);
		if(!CollisionChecker.getInstance(map).canMove(monster1.monsterImageView)) {
			monster1.MoveY(1);
		}else {
			monster1.MoveY(1);
			monster1.MoveY(0.3);
		}
		
		
		
		monster1.MoveX(-1);
		if (!CollisionChecker.getInstance(map).canMove(monster1.monsterImageView)) {
			monster1.MoveX(1);
		}else {

			monster1.MoveX(1);
			monster1.MoveX(-0.3);
			
		}
		}
		
		if(n%2==1) {
		monster1.MoveY(1);
		if (!CollisionChecker.getInstance(map).canMove(monster1.monsterImageView)) {
			monster1.MoveY(-1);
		}else {

			monster1.MoveY(-1);
			monster1.MoveY(0.3);
			
		}
		monster1.MoveX(1);
		if(!CollisionChecker.getInstance(map).canMove(monster1.monsterImageView)) {
			monster1.MoveX(-1);
		}else {
			monster1.MoveX(-1);		
			monster1.MoveX(0.3);		
			
		}
		}
		*/
	}
	
	private static boolean can(RandomMoves move) {
		monster1.MoveY(move.cdy);
		monster1.MoveX(move.cdx);
		
		if(!CollisionChecker.getInstance(map).monsterWillStop(monster1.monsterImageView)) {
			monster1.MoveY(-1*move.cdy);
			monster1.MoveX(-1*move.cdx);
			return false;
		}		
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
		return this.monster1;
	}
	
	public Monster getSecondMonster() {
		return this.monster2;
	}

}
class RandomMoves{
	double cdx,cdy,dx,dy;
	public RandomMoves(double cdx,double cdy,double dx, double dy) {
		this.cdx = cdx;
		this.cdy = cdy;
		this.dx = dx;
		this.dy = dy;
	}
}
