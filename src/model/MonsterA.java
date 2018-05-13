package model;

import controller.GameViewController;
import controller.MainViewController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MonsterA extends Monster{
	ImageView imageViewA;
	Image imageA;
	
	private static MonsterA mInstance;
	
	private MonsterA(ImageView imageView, double x, double y, Image image) {
		super(imageView, x, y, image);
		imageViewA=imageView;
		imageA=image;
		//move();
		// TODO Auto-generated constructor stub
	}
	
	public static MonsterA getInstance(ImageView imageView, double x, double y, Image image){
        if(mInstance == null){
            mInstance = new MonsterA(imageView, x,  y,image);
        }
        return mInstance;
    }

	@Override
	public void move() {
		GameViewController.moveMonsterA();
	
		
	}

	@Override
	public void removeMonster() {
		// TODO Auto-generated method stub
		super.removeMonster();
		if(MainViewController.getGameController()!=null)
		MainViewController.getGameController().stopMovingMonster1();
	}


}
