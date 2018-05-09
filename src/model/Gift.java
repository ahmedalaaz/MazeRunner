package model;

import javafx.scene.image.Image;

public abstract class Gift extends MapCell{
	
	public Gift(double x, double y, Image image) {
		super(x, y, image);
	}
	
	public abstract void onGiftTaken();
	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "Gift";
	}

}
