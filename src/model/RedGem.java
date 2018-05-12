package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class RedGem extends Gift{
	private ImageView redGemImageView;
	public RedGem(Image redGemImage,double x, double y, Image image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		this.redGemImageView = new ImageView(redGemImage);
		this.redGemImageView.setFitHeight(ICell.WALL_HEIGHT);
		this.redGemImageView.setFitWidth(ICell.WALL_WIDTH);
		this.getChildren().add(this.redGemImageView);
	}

	@Override
	public void onGiftTaken() {
		// TODO implement this
		super.onGiftTaken();
		FadeTransition fadeTransition =  new FadeTransition(Duration.millis(300), this.redGemImageView);
		fadeTransition.setFromValue(this.redGemImageView.getOpacity()); 
		fadeTransition.setToValue(0);
		fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
		fadeTransition.setOnFinished((event) -> {
		this.getChildren().remove(this.redGemImageView);
		//TODO play sound
		});
		fadeTransition.play();
		MapGenerator.getInstance().getPlayer().increaseScore(15);
		System.out.println("Red Gem taken !");
		
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "RedGem";
	}

}
