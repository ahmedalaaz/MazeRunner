package model;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpeedPotion extends Gift {
	private ImageView speedPotionImageView;
	public SpeedPotion(Image speedPotionImage,double x, double y, Image image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		this.speedPotionImageView =  new ImageView(speedPotionImage);
		this.getChildren().add(speedPotionImageView);
		this.speedPotionImageView.setFitHeight(ICell.WALL_HEIGHT);
		this.speedPotionImageView.setFitWidth(ICell.WALL_WIDTH);
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), speedPotionImageView);
		translateTransition.setFromX(0);
		translateTransition.setToX(speedPotionImageView.getLayoutX() + 2);
		translateTransition.setCycleCount(1);
		translateTransition.setRate(1);
		translateTransition.setAutoReverse(true);

		SequentialTransition sequentialTransition = new SequentialTransition();
		sequentialTransition.getChildren().addAll(translateTransition);
		sequentialTransition.setCycleCount(Timeline.INDEFINITE);
		sequentialTransition.setAutoReverse(true);
		sequentialTransition.play();
	}
	

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		
		return "SpeedPotion";
	}


	@Override
	public void onGiftTaken() {
		// TODO implement this
		super.onGiftTaken();
		FadeTransition fadeTransition =  new FadeTransition(Duration.millis(300), this.speedPotionImageView);
		fadeTransition.setFromValue(this.speedPotionImageView.getOpacity()); 
		fadeTransition.setToValue(0);
		fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
		fadeTransition.setOnFinished((event) -> {
		this.getChildren().remove(this.speedPotionImageView);
		});
		fadeTransition.play();
		long start = System.currentTimeMillis();
		Player.speed = 1;
		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				long current = System.currentTimeMillis();
				if(current - start >= 10000) {
					Player.speed = 0.3;
					this.stop();
					System.out.println("Stoped!!");
				}
			}
		};
		timer.start();
		System.out.println("Speed potion taken !");
	}

}
