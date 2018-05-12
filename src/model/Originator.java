
package model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class Originator {
	private Player player;
	//private MapCell[][] map;

	public void setPlayer(Player player) {
		this.player=player;
	}
	/*public void setMap(MapCell[][]map) {
		this.map=map;
	}*/

	public Memento storedInMemento() {
		return new Memento(player);
	}

	public Player restorePlayerFromMemento(Memento m) {
		player = m.getSavedPlayer();
		return player;
	}
}