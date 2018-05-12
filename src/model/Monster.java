package model;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Monster extends MapCell {

	public ImageView monsterImageView;
	SpriteSheet spriteSheet;
	protected boolean alive = true;
	public SpriteAnimation animation;
	protected static double speed = 1;
	
	public Monster(ImageView imageView, double x, double y, Image image) {
		super(x, y, image);
		this.monsterImageView = imageView;
		this.monsterImageView.setViewport(new Rectangle2D(0, 0, 32, 48));
		spriteSheet = new SpriteSheet(this.monsterImageView, Duration.millis(250), 3, 3, 0, 0, 32, 48);
		animation = new SpriteAnimation(spriteSheet);
	}
	
	
	@Override
	public void addToView(Object map) {
		// TODO Auto-generated method stub
		super.addToView(map);
		Pane root = (Pane) map;
		root.getChildren().add(monsterImageView);
		this.monsterImageView.setFitHeight(ICell.WALL_HEIGHT-6);
		this.monsterImageView.setFitWidth(ICell.WALL_WIDTH-3);
		monsterImageView.maxHeight(ICell.WALL_HEIGHT-6);
		monsterImageView.maxWidth(ICell.WALL_WIDTH-3);
		monsterImageView.setLayoutX(x-1.5);
		monsterImageView.setLayoutY(y+2.5);
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "Monster";
	}
	
	public abstract void move();


public void MoveX(double x) {
	boolean right = x > 0 ? true : false;
	for (double i = 0; i < Math.abs(x); i += 0.1) {
		if (right) {
			monsterImageView.setTranslateX(monsterImageView.getTranslateX() + Math.min(0.1,Math.abs((x))));
			monsterImageView.toFront();
		} else {
			monsterImageView.setTranslateX(monsterImageView.getTranslateX() - Math.min(0.1,Math.abs((x))));
			monsterImageView.toFront();

		}
	}
}

public void MoveY(double y) {
	boolean right = y > 0 ? true : false;
	for (double i = 0; i < Math.abs(y); i += 0.1) {
		if (right) {
			monsterImageView.setTranslateY(monsterImageView.getTranslateY() + Math.min(0.1,Math.abs((y))));
			monsterImageView.toFront();
			
		} else {
			monsterImageView.setTranslateY(monsterImageView.getTranslateY() - Math.min(0.1,Math.abs((y))));
			monsterImageView.toFront();

		}
	}

}
}