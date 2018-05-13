package model;

import controller.GameViewController;
import controller.MainViewController;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HealthPotion extends Gift{
	private ImageView healthPotionImageView;
	public HealthPotion(Image healthPotionImage,double x, double y, Image image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		this.healthPotionImageView = new ImageView(healthPotionImage);
		this.healthPotionImageView.setFitHeight(ICell.WALL_HEIGHT);
		this.healthPotionImageView.setFitWidth(ICell.WALL_WIDTH);
		this.getChildren().add(this.healthPotionImageView);
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), healthPotionImageView);
		translateTransition.setFromX(0);
		translateTransition.setToX(healthPotionImageView.getLayoutX() + 2);
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
	public void onGiftTaken() {
		// TODO implement this
		super.onGiftTaken();
		FadeTransition fadeTransition =  new FadeTransition(Duration.millis(300), this.healthPotionImageView);
		fadeTransition.setFromValue(this.healthPotionImageView.getOpacity()); 
		fadeTransition.setToValue(0);
		fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
		fadeTransition.setOnFinished((event) -> {
		this.getChildren().remove(this.healthPotionImageView);
		//TODO play sound
		});
		fadeTransition.play();
		MapGenerator.getInstance().getPlayer().increaseHealth(15);
		System.out.println("Health potion taken !");
		
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "HealthPotion";
	}
	

}
