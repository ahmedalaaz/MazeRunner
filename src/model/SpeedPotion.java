package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		System.out.println("Speed potion taken !");
	}

}
