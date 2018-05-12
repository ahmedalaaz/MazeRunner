
package model;

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
	public void notifyObservers(int health, int score) {
		for (Observer b : observers) {
			b.update(health, score);
		}
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
		// TODO Auto-generated method stub
		return "Player";
	}

	public void increaseHealth(int increase) {
		// TODO Auto-generated method stub
		
		this.setHealth(Math.min(100, this.getHealth() + increase));
		notifyObservers(getHealth(), getScore());
	}
	public void decreaseHealth(int decrease) {
		this.setHealth(Math.max(0, this.getHealth() -decrease));
		notifyObservers(getHealth(), getScore());
		
	}
	public void increaseScore(int increase) {
		this.setScore( this.getScore() + increase );
		playerScoreCounter+=increase;
		notifyObservers(getHealth(), getScore());
		
		
	}
}
