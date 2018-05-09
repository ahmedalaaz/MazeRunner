package model;

public interface IPlayer {
	public void moveX(int x);
	public void moveY(int y);
	public void checkBomb();
	public void setHealth();
	public int gethealth();
	public void setScore();
	public int getScore();
	public void checkPower();

}
