package model;

public class CellsFactory {

	public CellsFactory() {
		
	}
	public MapCell getMapCell(char symbol,double x,double y) {
		GiftsFactory giftsFactory =  new GiftsFactory();
		MapCell ret = null;
		switch(symbol) {
		case MapCell.BOMB_SYMBOL : ret = new Test(x, y, MapCell.TREE_IMAGE);
			break;
		case MapCell.COIN_SYMBOL :
			ret = giftsFactory.getGiftInstance(x, y,MapCell.WAY_IMAGE);
			break;
		case MapCell.END_SYMBOL :ret = new Test(x, y, MapCell.WAY_IMAGE);
			break;
		case MapCell.GIFT_SYMBOL: 
			ret = giftsFactory.getGiftInstance(x, y,MapCell.WAY_IMAGE);
			break;
		case MapCell.MONSTER_SPAWN_SYMBOL: ret = new Test(x, y, MapCell.TREE_IMAGE);
			break;
		case MapCell.ROCKET_MONSTER_SYMBOL: ret = new Test(x, y, MapCell.TREE_IMAGE);
			break;
		case MapCell.START_SYMBOL: ret = new Test(x, y, MapCell.WAY_IMAGE);
			break;
			
		case MapCell.TREE_SYMBOL:
			ret = new Wall(x, y, MapCell.STONE_IMAGE, true, 100, 0.8);
			break;
		case MapCell.WALL_SYMBOL : ret = new Test(x, y, MapCell.STONE_IMAGE);
			break;
		case MapCell.WATER_SYMBOL : ret = new Test(x, y, MapCell.WATER_IMAGE);
			break;
		case MapCell.WAY_SYMBOL: ret = new Test(x, y, MapCell.WAY_IMAGE);
			break;
		case MapCell.CHECK_POINT_SYMBOL: ret = new Test(x, y, MapCell.CHECK_POINT_IMAGE);
		break;
				
		}
		
		return ret;
	}
}
