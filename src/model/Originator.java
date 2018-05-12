
package model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class Originator {
	private Canvas canvas;

	public void set(Canvas canvas) {
		this.canvas = canvas;
	}

	public Memento storedInMemento() {
		return new Memento(canvas);
	}

	public Canvas restoreFromMemento(Memento m) {
		canvas = m.getSavedCanvas();
		return canvas;
	}
}