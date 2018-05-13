package model;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bullet extends ImageView {
	private KeyCode lastPressed;
	private ImageView bulletImageView;
	private AnimationTimer animationTimer;

	public Bullet(KeyCode lastPressed, Image bulletImage) {
		this.lastPressed = lastPressed;
		this.bulletImageView = new ImageView(bulletImage);
		
		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
	}

	public void fire(double startX, double startY, double translateX, double translateY, Pane root) {
		root.getChildren().add(bulletImageView);
		File music = new File("resources/music/laser.wav");
		MusicPlayer player = new MusicPlayer(music);
		player.playAsync();
		this.bulletImageView.setLayoutX(startX);
		this.bulletImageView.setLayoutY(startY);
		this.bulletImageView.setTranslateX(translateX);
		this.bulletImageView.setTranslateY(translateY);
		this.bulletImageView.setFitHeight(ICell.WALL_HEIGHT - 8);
		this.bulletImageView.setFitWidth(ICell.WALL_WIDTH - 8);
		bulletImageView.maxHeight(ICell.WALL_HEIGHT - 8);
		bulletImageView.maxWidth(ICell.WALL_WIDTH - 8);
		animationTimer.start();
	}

	public void update() {
		if (lastPressed == KeyCode.UP) {
			bulletImageView.setTranslateY(bulletImageView.getTranslateY() - 1);
			bulletImageView.toFront();
			if (!CollisionChecker.getInstance().bulletWillStop(bulletImageView)) {
				// stop bullet
				stopBullet();
			} else {
				bulletImageView.setTranslateY(bulletImageView.getTranslateY() - 0.2);
				bulletImageView.toFront();

			}
		} else if (lastPressed == KeyCode.DOWN) {
			bulletImageView.setTranslateY(bulletImageView.getTranslateY() + 1);
			bulletImageView.toFront();
			if (!CollisionChecker.getInstance().bulletWillStop(bulletImageView)) {
				// stop bullet
				stopBullet();
			} else {
				bulletImageView.setTranslateY(bulletImageView.getTranslateY() + 0.2);
				bulletImageView.toFront();

			}
		} else if (lastPressed == KeyCode.LEFT) {
			bulletImageView.setTranslateX(bulletImageView.getTranslateX() - 1);
			bulletImageView.toFront();
			if (!CollisionChecker.getInstance().bulletWillStop(bulletImageView)) {
				// stop bullet
				stopBullet();
			} else {
				bulletImageView.setTranslateX(bulletImageView.getTranslateX() - 0.2);
				bulletImageView.toFront();

			}
		} else if (lastPressed == KeyCode.RIGHT) {
			bulletImageView.setTranslateX(bulletImageView.getTranslateX() + 1);
			bulletImageView.toFront();
			if (!CollisionChecker.getInstance().bulletWillStop(bulletImageView)) {
				// stop bullet
				stopBullet();
			} else {
				bulletImageView.setTranslateX(bulletImageView.getTranslateX() + 0.2);
				bulletImageView.toFront();
			}

		}
		if(CollisionChecker.getInstance().bulletWillShootMonster(bulletImageView)) {
			stopBullet();
		}
	}

	public void stopBullet() {
		animationTimer.stop();
		Pane root = (Pane) this.bulletImageView.getParent();
		root.getChildren().remove(bulletImageView);
		// play collide music
		ImagePattern image = new ImagePattern(
				new Image(MapCell.class.getClassLoader().getResource("images/bulletHit.png").toExternalForm()));
		Rectangle bulletHit = new Rectangle(ICell.WALL_WIDTH-8, ICell.WALL_HEIGHT-8);
		bulletHit.setLayoutX(bulletImageView.getLayoutX());
		bulletHit.setLayoutY(bulletImageView.getLayoutY());
		bulletHit.setTranslateX(bulletImageView.getTranslateX());
		bulletHit.setTranslateY(bulletImageView.getTranslateY());
		root.getChildren().add(bulletHit);
		
		bulletHit.setFill(image);
		final FadeTransition removeTransition = new FadeTransition(Duration.millis(500), bulletHit);
		removeTransition.setFromValue(bulletHit.getOpacity());
		removeTransition.setToValue(0);
		removeTransition.setInterpolator(Interpolator.EASE_BOTH);
		removeTransition.setOnFinished((event) -> {
			root.getChildren().remove(bulletHit);
		});
		removeTransition.play();
		File music = new File("resources/music/glassBreak.wav");
		MusicPlayer player = new MusicPlayer(music);
		player.playAsync();
	}
}
