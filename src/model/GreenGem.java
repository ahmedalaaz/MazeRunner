package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		System.out.println("Green Gem taken !");
		
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "GreenGem";
	}

}
