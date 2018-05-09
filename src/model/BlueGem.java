package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlueGem extends Gift {

	private ImageView blueGemImageView;
	public BlueGem(Image blueGemImage,double x, double y, Image image) {
		super(x, y, image);
		// TODO Auto-generated constructor stub
		this.blueGemImageView = new ImageView(blueGemImage);
		this.blueGemImageView.setFitHeight(ICell.WALL_HEIGHT);
		this.blueGemImageView.setFitWidth(ICell.WALL_WIDTH);
		this.getChildren().add(this.blueGemImageView);
	}

	@Override
	public void onGiftTaken() {
		// TODO implement this
		System.out.println("Blue Gem taken !");
		
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "BlueGem";
	}

}
