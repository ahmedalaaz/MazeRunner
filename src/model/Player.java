
package model;

import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends MapCell implements Subject {
	
	public int playerScoreCounter=0;
	public ImageView playerImageView;
	SpriteSheet spriteSheet;
	private int lives = 2;
	private int health = 100;
	public SpriteAnimation animation;
	private int score = 0;
	public static double speed = 0.3;
	ArrayList<Observer> observers = new ArrayList<>();
	Image collisionImage = COLLISON_IMAGE;
	public Player(ImageView imageView, double x, double y, Image image) {
		super(x, y, image);
		this.playerImageView = imageView;
		this.playerImageView.setViewport(new Rectangle2D(0, 0, 32, 48));
		spriteSheet = new SpriteSheet(this.playerImageView, Duration.millis(250), 3, 3, 0, 0, 32, 48);
		animation = new SpriteAnimation(spriteSheet);
		// TODO add to view here
	}
	public void updateSpriteSheet(ImageView newImageView) {
		this.spriteSheet = new SpriteSheet(newImageView, this.spriteSheet.getDuration(),
				this.spriteSheet.getCount(), this.spriteSheet.getColumns()
				,this.spriteSheet.getOffsetX(), this.spriteSheet.getOffsetY(), this.spriteSheet.getWidth()
				,this.spriteSheet.getHeight());
		this.animation.stop();
		this.animation = new SpriteAnimation(spriteSheet);
		ImageView hold = playerImageView;
		playerImageView = newImageView;
		playerImageView.setFitHeight(ICell.WALL_HEIGHT-8);
		playerImageView.setFitWidth(ICell.WALL_WIDTH-8);
		playerImageView.maxHeight(ICell.WALL_HEIGHT-8);
		playerImageView.maxWidth(ICell.WALL_WIDTH-8);
		playerImageView.setLayoutX(getX()+8);
		playerImageView.setLayoutY(getY() +4);
		playerImageView.setTranslateX(hold.getTranslateX());
		playerImageView.setTranslateY(hold.getTranslateY());
		Pane root = (Pane) hold.getParent();
		root.getChildren().remove(hold);
		root.getChildren().add(playerImageView);
	}
	@Override
	public void addToView(Object map) {
		// TODO Auto-generated method stub
		super.addToView(map);
		Pane root = (Pane) map;
		root.getChildren().add(playerImageView);
		this.playerImageView.setFitHeight(ICell.WALL_HEIGHT-8);
		this.playerImageView.setFitWidth(ICell.WALL_WIDTH-8);
		playerImageView.maxHeight(ICell.WALL_HEIGHT-8);
		playerImageView.maxWidth(ICell.WALL_WIDTH-8);
		playerImageView.setLayoutX(x+8);
		playerImageView.setLayoutY(y+4);
	}
	public void MoveX(double x) {
		boolean right = x > 0 ? true : false;
		for (double i = 0; i < Math.abs(x); i += 0.1) {
			if (right) {
				playerImageView.setTranslateX(playerImageView.getTranslateX() + Math.min(0.1,Math.abs((x))));
				playerImageView.toFront();
			} else {
				playerImageView.setTranslateX(playerImageView.getTranslateX() - Math.min(0.1,Math.abs((x))));
				playerImageView.toFront();

			}
		}
	}

	public void MoveY(double y) {
		boolean right = y > 0 ? true : false;
		for (double i = 0; i < Math.abs(y); i += 0.1) {
			if (right) {
				playerImageView.setTranslateY(playerImageView.getTranslateY() + Math.min(0.1,Math.abs((y))));
				playerImageView.toFront();
				
			} else {
				playerImageView.setTranslateY(playerImageView.getTranslateY() - Math.min(0.1,Math.abs((y))));
				playerImageView.toFront();

			}
		}

	}

	@Override
	public void addObserver(Observer ob) {
		observers.add(ob);
	}

	@Override
	public void removeObserver(Observer ob) {
		observers.remove(ob);
	}

	@Override
	public void notifyObservers(int health, int score,int lives) {
		for (Observer b : observers) {
			b.update(health, score,lives);
		}
	}
	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String getCellName() {
		return "Player";
	}

	public void increaseHealth(int increase) {
		this.setHealth(Math.min(100, this.getHealth() + increase));
		notifyObservers(getHealth(), getScore(),getLives());
	}
	public int getLives() {
		return lives;
	}
	public void decreaseHealth(int decrease) {
		this.setHealth(Math.max(0, this.getHealth() -decrease));
		MusicPlayer player = new MusicPlayer(new File("resources/music/damage.wav"));
		player.playAsync();
		if(this.getHealth() == 0 && lives != 1) {
			returnToCheckPoint(new CheckPoints().getLastCheckPoint());
		}else if(this.getHealth() ==0) {
			//Gameover :)
			MusicPlayer bg = MusicPlayer.bgMusic;
			if(bg != null) {
				bg.stopBgMusic();
			}
			MusicPlayer losingPlayer = new MusicPlayer(new File("resources/music/lose.wav"));
			losingPlayer .playAsync();
			 notifyObservers(0, getScore(),0);

		}
		else notifyObservers(getHealth(), getScore(),getLives());
		
	}
	public void increaseScore(int increase) {
		this.setScore( this.getScore() + increase );
		playerScoreCounter+=increase;
		notifyObservers(getHealth(), getScore(),getLives());	
	}
	public void returnToCheckPoint(Player test) {
		Player currentPlayer = MapGenerator.getInstance().getPlayer();
		currentPlayer.setScore(test.getScore());
		currentPlayer.setHealth(test.getHealth());
		currentPlayer.setLives(test.getLives());
		Pane root = (Pane) currentPlayer.playerImageView.getParent();
		root.getChildren().remove(currentPlayer.playerImageView);
		currentPlayer.playerImageView = test.playerImageView;
		root.getChildren().add(currentPlayer.playerImageView);
		currentPlayer.playerImageView.setFitHeight(ICell.WALL_HEIGHT-8);
		currentPlayer.playerImageView.setFitWidth(ICell.WALL_WIDTH-8);
		currentPlayer.playerImageView.maxHeight(ICell.WALL_HEIGHT-8);
		currentPlayer.playerImageView.maxWidth(ICell.WALL_WIDTH-8);
		currentPlayer.playerImageView.setLayoutX(currentPlayer.getX()+8);
		currentPlayer.playerImageView.setLayoutY(currentPlayer.getY() +4);
		currentPlayer.playerImageView.setTranslateX(test.getX());
		currentPlayer.playerImageView.setTranslateY(test.getY());
		currentPlayer.spriteSheet = new SpriteSheet(currentPlayer.playerImageView, Duration.millis(250), 3, 3, 0, 0, 32, 48);
		currentPlayer.animation = new SpriteAnimation(currentPlayer.spriteSheet);
		currentPlayer.animation.stop();
		currentPlayer.notifyObservers(currentPlayer.getHealth(),currentPlayer.getScore(),currentPlayer.getLives());
	}
}
