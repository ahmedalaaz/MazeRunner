package model;

import javafx.stage.Screen;

public interface ICell {
	final String STONE = "Stone";
	final String PLAYER = "Player";
	final String MONSTER = "Monster";
	final String GIFT = "Gift";
	final String BOMB = "Bomb";
	final String WAY= "Way";
	final String TREE= "Tree";
	final String WATER= "Water";
	final String WALL = "Wall";
	final String ROCKET_MONSTER= "RocketMonster";
	
	final int GIFTS_NUMBER = 6;
	final int HEALTH_POTION_GIFT = 0;
	final int SPEED_POTION_GIFT = 1;
	final int ARMOR_GIFT = 2;
	final int BLUE_GEM_COIN = 3;
	final int RED_GEM_COIN = 4;
	final int GREEN_GEM_COIN = 5;
	
	final char WALL_SYMBOL = '.';
	final char WAY_SYMBOL = 'W';
	final char START_SYMBOL = 'S';
	final char END_SYMBOL = 'E';
	final char COIN_SYMBOL = 'C';
	final char TREE_SYMBOL = 'T';
	final char BOMB_SYMBOL = 'B';
	final char GIFT_SYMBOL = 'G';
	final char ROCKET_MONSTER_SYMBOL = 'R';
	final char MONSTER_SPAWN_SYMBOL1 = 'M';
	final char MONSTER_SPAWN_SYMBOL2 = 'N';
	final char WATER_SYMBOL = '-';
	
	final double WALL_WIDTH = (Screen.getPrimary().getVisualBounds().getWidth() -20) / 30;
	final double WALL_HEIGHT= (Screen.getPrimary().getVisualBounds().getHeight() -100) / 30;
	
	public void addToView(Object map);
	
	public double getX();
	public double getY();
	
	public void setX(double x);
	public void setY(double y);
	
}
