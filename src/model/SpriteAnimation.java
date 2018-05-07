package model;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class SpriteAnimation extends Transition {

	private final ImageView imageView;
	private final int count;
	private final int columns;
	private final int offsetX;
	private final int offsetY;
	private final int width;
	private final int height;

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
}