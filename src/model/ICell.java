package model;


public interface ICell {
	final String WALL = "Wall";
	final String PLAYER = "Player";
	final String MONSTER = "Monster";
	final String GIFT = "Gift";
	final String BOMB = "Bomb";
	final String EMPTY= "EmptyCell";
	final double WALL_WIDTH = 40;
	final double WALL_HEIGHT= 20;
	
	public void addToView(Object map);
	
	public double getX();
	public double getY();
	
	public void setX(double x);
	public void setY(double y);
	
}
