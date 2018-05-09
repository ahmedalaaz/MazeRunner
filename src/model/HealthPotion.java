package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		System.out.println("Health potion taken !");
		
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "HealthPotion";
	}
	

}
