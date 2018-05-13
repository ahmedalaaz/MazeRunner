package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Armor extends Gift{
	private ImageView armorImageView;
	
	public Armor(Image armorImage,double x, double y, Image image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		this.armorImageView = new ImageView(armorImage);
		this.armorImageView.setFitHeight(ICell.WALL_HEIGHT);
		this.armorImageView.setFitWidth(ICell.WALL_WIDTH);
		this.getChildren().add(armorImageView);
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), armorImageView);
		translateTransition.setFromX(0);
		translateTransition.setToX(armorImageView.getLayoutX() + 2);
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
		return "Armor";
	}

	@Override
	public void onGiftTaken() {
		// TODO implement this
		super.onGiftTaken();
		FadeTransition fadeTransition =  new FadeTransition(Duration.millis(300), this.armorImageView);
		fadeTransition.setFromValue(this.armorImageView.getOpacity()); 
		fadeTransition.setToValue(0);
		fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
		fadeTransition.setOnFinished((event) -> {
		this.getChildren().remove(this.armorImageView);
		//TODO play sound
		});
		fadeTransition.play();


		System.out.println("Armor Taken !");
		
	}

}
