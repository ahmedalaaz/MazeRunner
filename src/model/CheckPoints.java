package model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

public class CheckPoints {
	public static int counter = -1;
	public void addNewCheckPoint(Player player) {
		Caretaker ct = new Caretaker();
		Originator or = new Originator();
		Player savedPlayer = new Player(new ImageView(MapCell.WIZARD_PLAYER_IMAGE), player.playerImageView.getTranslateX()
				, player.playerImageView.getTranslateY(), player.image);
		savedPlayer.setHealth(player.getHealth());
		savedPlayer.setScore(player.getScore());
		savedPlayer.setLives(player.getLives() -1);
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
