package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bomb extends MapCell{
	ImageView bombImageView;
	protected int damage;
	BombState bombState;
	public boolean available = true;
	public Bomb(ImageView bombImageView,double x, double y, Image image,int damage,BombState bombState) {
		super(x, y, image);
		this.bombImageView = bombImageView;
		this.bombImageView.setFitHeight(ICell.WALL_HEIGHT-5);
		this.bombImageView.setFitWidth(ICell.WALL_WIDTH-5);
		this.getChildren().add(this.bombImageView);
		this.damage = damage;
		this.bombState = bombState;
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), bombImageView);
		translateTransition.setFromX(0);
		translateTransition.setToX(bombImageView.getLayoutX() + 2);
		translateTransition.setCycleCount(1);
		translateTransition.setRate(1);
		translateTransition.setAutoReverse(true);

		SequentialTransition sequentialTransition = new SequentialTransition();
		sequentialTransition.getChildren().addAll(translateTransition);
		sequentialTransition.setCycleCount(Timeline.INDEFINITE);
		sequentialTransition.setAutoReverse(true);
		sequentialTransition.play();
	}
	public int getDecreaseHealth() {
		return damage;
	}
	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "Bomb";
	}
	public void triggerBomb() {
		bombState.triggerBomb(damage);
		Pane root = (Pane) this.bombImageView.getParent();
		root.getChildren().remove(bombImageView);
		//play collide music
		ImagePattern image = new ImagePattern(new Image(MapCell.class.getClassLoader()
				.getResource("images/collision.png").toExternalForm()));
		Rectangle collision=new Rectangle(ICell.WALL_WIDTH , ICell.WALL_HEIGHT);
		collision.setLayoutX(bombImageView.getLayoutX());
		collision.setLayoutY(bombImageView.getLayoutY());
		collision.setTranslateX(bombImageView.getTranslateX());
		collision.setTranslateY(bombImageView.getTranslateY());
		root.getChildren().add(collision);
		collision.setFill(image);
		final FadeTransition collisionFade = new FadeTransition(Duration.millis(500), collision);
		collisionFade.setFromValue(collision.getOpacity());
		collisionFade.setToValue(0);
		collisionFade.setInterpolator(Interpolator.EASE_BOTH);
		collisionFade.setOnFinished((event) -> {
			root.getChildren().remove(collision);
		});
        collisionFade.play();
		available = false;
	}
}
