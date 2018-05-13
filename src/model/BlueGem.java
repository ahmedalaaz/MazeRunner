package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BlueGem extends Gift {

	private ImageView blueGemImageView;

	public BlueGem(Image blueGemImage, double x, double y, Image image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		this.blueGemImageView = new ImageView(blueGemImage);
		this.blueGemImageView.setFitHeight(ICell.WALL_HEIGHT);
		this.blueGemImageView.setFitWidth(ICell.WALL_WIDTH);
		this.getChildren().add(this.blueGemImageView);
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), blueGemImageView);
		translateTransition.setFromX(0);
		translateTransition.setToX(blueGemImageView.getLayoutX() + 2);
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
		FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), this.blueGemImageView);
		fadeTransition.setFromValue(this.blueGemImageView.getOpacity());
		fadeTransition.setToValue(0);
		fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
		fadeTransition.setOnFinished((event) -> {
			this.getChildren().remove(this.blueGemImageView);
			// TODO play sound
		});
		fadeTransition.play();
		MapGenerator.getInstance().getPlayer().increaseScore(5);
		System.out.println("Blue Gem taken !");
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "BlueGem";
	}

}
