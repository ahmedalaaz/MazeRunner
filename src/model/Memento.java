
package model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class Memento {
	private Canvas canvas;

	public Memento(Canvas canvas) {
		this.canvas = canvas;
	}

	public Canvas getSavedCanvas() {
		return canvas;
	}
}
