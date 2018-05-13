package model;

import java.util.Random;

import javafx.scene.image.ImageView;

public class BombsFactory {
	public static int loweSpeedCnt = 0;
	public BombsFactory() {
		
	}
	public Bomb getInstance(double x,double y) {
		Bomb ret = null;
		Random rand = new Random();
		int choice = rand.nextInt(3);
		switch(choice) {
		case 0:
			ret = new Bomb(new ImageView(MapCell.EXPLOSION_BOMB_IMAGE), x, y, MapCell.WAY_IMAGE,25, new Explosion());
			break;
		case 1:
			if(loweSpeedCnt == 2)break;
			ret = new Bomb(new ImageView(MapCell.LOWER_SPEED_BOMB_IMAGE), x, y, MapCell.WAY_IMAGE,25, new LowerSpeed());
			loweSpeedCnt++;
			break;
		case 2:
			ret = new Bomb(new ImageView(MapCell.POISION_BOMB_IMAGE), x, y, MapCell.WAY_IMAGE,25, new Poison());
			break;	
		}
		if(ret == null)return getInstance(x, y);
		return ret;

	}
}
