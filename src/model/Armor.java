package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		System.out.println("Armor Taken !");
		
	}

}
