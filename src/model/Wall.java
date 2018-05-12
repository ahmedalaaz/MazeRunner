package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Wall extends MapCell{
protected boolean destroyable; 
private double health; // if health < =  0 then it is not an obstacle !
private double strengthRatio;
public Wall(double x, double y, Image image, boolean destroyable, double health, double strengthRatio) {
	super(x, y, image);
	this.destroyable = destroyable;
	this.health = health;
	this.strengthRatio = strengthRatio;
}
public boolean isDestroyable() {
	return destroyable;
}
public void setDestroyable(boolean destroyable) {
	this.destroyable = destroyable;
}
public double getHealth() {
	return health;
}
public void setHealth(double health) {
	this.health = health;
}
public void decreaseHealth(double hitValue,MapCell[][] map,int i,int j) {
	if(!destroyable || health<= 0)return;
	this.health-=hitValue*strengthRatio;
	if(this.health <= 0) {
		Pane root = (Pane) this.getParent();
		root.getChildren().remove(this);
		MapCell newCell =  new Test(x, y, MapCell.WAY_IMAGE);
		map[i][j] = newCell;
		map[i][j].setLayoutX(this.getLayoutX());
		map[i][j].setLayoutY(this.getLayoutY());
		root.getChildren().add(map[i][j]);
	}
	//TODO if health < = 0 replace image with destroyed wall and remove obstacle property
}
public double getStrengthRatio() {
	return strengthRatio;
}
public void setStrengthRatio(double strengthRatio) {
	this.strengthRatio = strengthRatio;
}
@Override
public String getCellName() {
	// TODO Auto-generated method stub
	return "Wall";
}


}
