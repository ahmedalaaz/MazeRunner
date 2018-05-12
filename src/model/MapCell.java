package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class MapCell extends AnchorPane implements ICell{
	protected double x;
	protected double y;	
	public final static Image TREE_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/tree.jpg").toExternalForm());
	public final static Image ROCKS_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/rocks.png").toExternalForm());
	public final static Image ARMOR_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/armor.png").toExternalForm());
	public final static Image BLUE_GEM_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/blueGem.png").toExternalForm());
	public final static Image CHECK_POINT_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/checkPoint.png").toExternalForm());
	public final static Image GREEN_GEM_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/greenGem.png").toExternalForm());
	public final static Image HEALTH_POTION_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/healthPotion.png").toExternalForm());
	public final static Image MUD_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/mud.png").toExternalForm());
	public final static Image RED_GEM_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/redGem.png").toExternalForm());
	public final static Image SPEED_POTION_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/speedPotion.png").toExternalForm());
	public final static Image STONE_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/stone.png").toExternalForm());
	public final static Image WATER_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/water.png").toExternalForm());
	public final static Image COLLISON_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/collision.png").toExternalForm());
	public final static Image WAY_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/way.jpg").toExternalForm());
	public final static Image WINGER_PLAYER_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/winger.png").toExternalForm());
	public final static Image WIZARD_PLAYER_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/wizard.png").toExternalForm());
	public final static Image LEFT_BULLET_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/leftBullet.png").toExternalForm());
	public final static Image UP_BULLET_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/upBullet.png").toExternalForm());
	public final static Image RIGHT_BULLET_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/rightBullet.png").toExternalForm());
	public final static Image DOWN_BULLET_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/downBullet.png").toExternalForm());
	public final static Image LOWER_SPEED_BOMB_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/lowerSpeedBomb.png").toExternalForm());
	public final static Image POISION_BOMB_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/posionBomb.png").toExternalForm());
	public final static Image EXPLOSION_BOMB_IMAGE = new Image(MapCell.class.getClassLoader().getResource("images/explosionBomb.png").toExternalForm());
	
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
		this.imageView.maxHeight(ICell.WALL_HEIGHT);
		this.imageView.maxWidth(ICell.WALL_WIDTH);
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
