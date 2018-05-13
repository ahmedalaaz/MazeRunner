package model;

import controller.MainViewController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MonsterB extends Monster{

	public MonsterB(ImageView imageView, double x, double y, Image image) {
		super(imageView, x, y, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeMonster() {
		// TODO Auto-generated method stub
		super.removeMonster();
		MainViewController.getGameController().stopMovingMonster2();
	}

}
