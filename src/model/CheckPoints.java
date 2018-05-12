package model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class CheckPoints {
	public static int counter = -1;
	public void addNewCheckPoint(Player player) {
		Caretaker ct = new Caretaker();
		Originator or = new Originator();
		Player savedPlayer = new Player(player.playerImageView, player.getTranslateX(), player.getTranslateY(), player.image);
		savedPlayer.setHealth(player.getHealth());
		savedPlayer.setScore(player.getScore());
		savedPlayer.observers=(ArrayList<Observer>) player.observers.clone();
		savedPlayer.animation=player.animation;
		or.setPlayer(savedPlayer);
		ct.addMemento(or.storedInMemento());
		counter++;
	}
	public Player getLastCheckPoint() {
		Caretaker ct = new Caretaker();
		Originator or = new Originator();
		return or.restorePlayerFromMemento(ct.getMemento(counter));
	}
}
