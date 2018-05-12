package model;

import java.util.Random;

import javafx.scene.image.ImageView;

public class BombsFactory {

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
			ret = new Bomb(new ImageView(MapCell.LOWER_SPEED_BOMB_IMAGE), x, y, MapCell.WAY_IMAGE,25, new LowerSpeed());
			break;
		case 2:
			ret = new Bomb(new ImageView(MapCell.POISION_BOMB_IMAGE), x, y, MapCell.WAY_IMAGE,25, new Poison());
			break;	
		}
		return ret;

	}
}
