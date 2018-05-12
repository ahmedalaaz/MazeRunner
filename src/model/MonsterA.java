package model;

import java.net.URL;
import java.util.ResourceBundle;

import controller.GameViewController;
import javafx.fxml.Initializable;
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
		
		/*
		while(CollisionChecker.getInstance().monsterWillStop(monsterImageView)) {
			monsterImageView.setTranslateY(monsterImageView.getTranslateY() - Math.min(0.1,Math.abs((y))));
			monsterImageView.toFront();
		}
		*/
		/*
		if(CollisionChecker.getInstance().monsterCanGoLeft(imageViewA)) {
			imageViewA.setTranslateX(imageViewA.getTranslateX() - this.speed);
			imageViewA.toFront();
		}
		*/
		/*
		if(CollisionChecker.getInstance().monsterWillStop(imageViewA)) {
			imageViewA.setTranslateX(imageViewA.getTranslateX() - this.speed);
			imageViewA.toFront();
		}
		*/
		
	}


}
