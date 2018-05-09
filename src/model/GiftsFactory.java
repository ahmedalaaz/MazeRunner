package model;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GiftsFactory {

	public GiftsFactory() {
		
	}
	public Gift getGiftInstance(double x , double y,Image image) {
		Gift ret = null;
		Random rand = new Random();
		int choice = rand.nextInt(ICell.GIFTS_NUMBER);
		switch(choice) {
		case ICell.ARMOR_GIFT :
			ret  = new Armor(MapCell.ARMOR_IMAGE, x, y, image);
			break;
		case ICell.HEALTH_POTION_GIFT:
			ret  = new HealthPotion(MapCell.HEALTH_POTION_IMAGE, x, y, image);
			
			break;
		case ICell.SPEED_POTION_GIFT :
			ret  = new SpeedPotion(MapCell.SPEED_POTION_IMAGE, x, y, image);
			break;
		case ICell.BLUE_GEM_COIN:
			ret  = new BlueGem(MapCell.BLUE_GEM_IMAGE, x, y, image);
			break;
		case ICell.GREEN_GEM_COIN:
			ret  = new GreenGem(MapCell.GREEN_GEM_IMAGE, x, y, image);
			break;
		case ICell.RED_GEM_COIN:
			ret  = new RedGem(MapCell.RED_GEM_IMAGE, x, y, image);
			break;
			
		}
		return ret;
	}
}
