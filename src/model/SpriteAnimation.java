package model;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class SpriteAnimation extends Transition {

	private  ImageView imageView;
	private int count;
	private int columns;
	private int offsetX;
	private int offsetY;
	private int width;
	private int height;

	public SpriteAnimation(SpriteSheet spriteSheet) {
		this.imageView = spriteSheet.getImageView();
		this.count = spriteSheet.getCount();
		this.columns = spriteSheet.getColumns();
		this.offsetX = spriteSheet.getOffsetX();
		this.offsetY = spriteSheet.getOffsetY();
		this.width = spriteSheet.getWidth();
		this.height = spriteSheet.getHeight();
		setCycleDuration(spriteSheet.getDuration());
		setCycleCount(INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
	}
	@Override
	protected void interpolate(double k) {
		int index = Math.min((int) Math.floor(k * count), count - 1);
		int x = (index % columns) * width + offsetX;
		int y = (index / columns) * height + offsetY;
		imageView.setViewport(new Rectangle2D(x, y, width, height));

	}
	public ImageView getImageView() {
		return imageView;
	}
	public int getCount() {
		return count;
	}
	public int getColumns() {
		return columns;
	}
	public int getOffsetX() {
		return offsetX;
	}
	public int getOffsetY() {
		return offsetY;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}