package model;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class MapCell extends AnchorPane implements ICell{
	protected double x;
	protected double y;
	static File img = new File("resources/images/tree.png");
	
	final static Image TREE_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/tree.jpg").toExternalForm());
	final static Image ROCKS_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/rocks.png").toExternalForm());
	final static Image ARMOR_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/armor.png").toExternalForm());
	final static Image BLUE_GEM_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/blueGem.png").toExternalForm());
	final static Image CHECK_POINT_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/checkPoint.png").toExternalForm());
	final static Image GREEN_GEM_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/greenGem.png").toExternalForm());
	final static Image HEALTH_POTION_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/healthPotion.png").toExternalForm());
	final static Image MUD_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/mud.png").toExternalForm());
	final static Image RED_GEM_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/redGem.png").toExternalForm());
	final static Image SPEED_POTION_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/speedPotion.png").toExternalForm());
	final static Image STONE_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/stone.png").toExternalForm());
	final static Image WATER_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/water.png").toExternalForm());
	final static Image COLLISON_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/collision.png").toExternalForm());
	final static Image WAY_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/way.jpg").toExternalForm());
	
	protected Image image;
	protected ImageView imageView;

	abstract public String getCellName();
	
	
	public MapCell(double x, double y, Image image) {
		super();
		this.x = x;
		this.y = y;
		this.image = image;
		this.imageView = new ImageView(image);
		this.imageView.setFitWidth(ICell.WALL_WIDTH);
		this.imageView.setFitHeight(ICell.WALL_HEIGHT);
		this.setPrefSize(ICell.WALL_WIDTH, ICell.WALL_HEIGHT);
		this.getChildren().add(imageView);
	}

	@Override
	public void addToView(Object map) {
		Pane root = (Pane) map;
		root.getChildren().add(this);
		this.setLayoutX(x);
		this.setLayoutY(y);
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
