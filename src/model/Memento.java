
package model;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class Memento {
	//private MapCell[][] map;
	private Player player;
	

	public Memento(Player player) {
		//this.map=map;
		this.player=player;
	}

	public Player getSavedPlayer() {
		return player;
	}
	/*public MapCell[][] getSavedMap(){
		return map;
	}*/
}
