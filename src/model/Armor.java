package model;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
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
