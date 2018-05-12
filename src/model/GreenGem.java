package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GreenGem extends Gift {
	private ImageView greenGemImageView;
	public GreenGem(Image greenGemImage,double x, double y, Image image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		this.greenGemImageView = new ImageView(greenGemImage);
		this.greenGemImageView.setFitHeight(ICell.WALL_HEIGHT);
		this.greenGemImageView.setFitWidth(ICell.WALL_WIDTH);
		this.getChildren().add(this.greenGemImageView);
	}

	@Override
	public void onGiftTaken() {
		// TODO implement this
		super.onGiftTaken();
		FadeTransition fadeTransition =  new FadeTransition(Duration.millis(300), this.greenGemImageView);
		fadeTransition.setFromValue(this.greenGemImageView.getOpacity()); 
		fadeTransition.setToValue(0);
		fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
		fadeTransition.setOnFinished((event) -> {
		this.getChildren().remove(this.greenGemImageView);
		//TODO play sound
		});
		fadeTransition.play();

		MapGenerator.getInstance().getPlayer().increaseScore(10);
		System.out.println("Green Gem taken !");
		
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "GreenGem";
	}

}
