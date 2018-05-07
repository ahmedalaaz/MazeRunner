package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class MapCell extends AnchorPane implements ICell{
	protected double x;
	protected double y;
	final Image TREE_IMAGE = new Image(getClass().getResource("/images/tree.png").toString());
	final Image ROCKS_IMAGE = new Image(getClass().getResource("/images/rocks.png").toString());
	final Image ARMOR_IMAGE = new Image(getClass().getResource("/images/armor.png").toString());
	final Image BLUE_GEM_IMAGE = new Image(getClass().getResource("/images/blueGem.png").toString());
	final Image CHECK_POINT_IMAGE = new Image(getClass().getResource("/images/checkPoint.png").toString());
	final Image GREEN_GEM_IMAGE = new Image(getClass().getResource("/images/greenGem.png").toString());
	final Image HEALTH_POTION_IMAGE = new Image(getClass().getResource("/images/healthPotion.png").toString());
	final Image MUD_IMAGE = new Image(getClass().getResource("/images/mud.png").toString());
	final Image RED_GEM_IMAGE = new Image(getClass().getResource("/images/redGem.png").toString());
	final Image SPEED_POTION_IMAGE = new Image(getClass().getResource("/images/speedPotion.png").toString());
	final Image STONE_IMAGE = new Image(getClass().getResource("/images/stone.png").toString());
	final Image WATER_IMAGE = new Image(getClass().getResource("/images/water.png").toString());
	
	protected Image image;
	protected ImageView imageView;

	abstract public String getCellName();
	
	
	public MapCell(double x, double y, Image image) {
		super();
		this.x = x;
		this.y = y;
		this.image = image;
		this.imageView = new ImageView(image);
	}

	@Override
	public void addToView(Object map) {

	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

}
