package model;

import javafx.scene.canvas.Canvas;

public class CheckPoints {
	public static int counter = -1;
	public void addNewCheckPoint(Canvas canvas) {
		Caretaker ct = new Caretaker();
		Originator or = new Originator();
		or.set(canvas);
		ct.addMemento(or.storedInMemento());
		counter++;
	}
	public Canvas getLastCheckPoint() {
		Caretaker ct = new Caretaker();
		Originator or = new Originator();
		return or.restoreFromMemento(ct.getMemento(counter));
	}
}
