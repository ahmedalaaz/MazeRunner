package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
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
		//TODO play sound
		});
		fadeTransition.play();

		System.out.println("Speed potion taken !");
	}

}
