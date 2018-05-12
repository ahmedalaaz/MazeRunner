package model;

import controller.GameViewController;
import controller.MainViewController;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
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
