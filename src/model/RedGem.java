package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		System.out.println("Red Gem taken !");
		
	}

	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "RedGem";
	}

}
