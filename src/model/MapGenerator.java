package model;

import javafx.scene.layout.Pane;
import view.MainView;

public class MapGenerator {
	private Player player;
	private Monster monster1,monster2;
	private static MapGenerator mInstance;
	private MapCell start,end;
	private MapGenerator() {
		//TODO get selected level and diff
		
	}
	public static MapGenerator getInstance() {
		return mInstance == null ? mInstance = new MapGenerator() : mInstance;
	}
	public static MapGenerator newInstance() {
		return mInstance = new MapGenerator();
	}
	public MapCell[][] getAllCells(String level){
		//Testing ..
		CellsFactory cellsFactory = new CellsFactory();
		MapCell[][] map =  new MapCell[30][30];
		Character[][] test = MainView.levels.get(level);
		for(int i = 0 ; i <30 ; i++) {
			for(int j = 0 ; j < 30 ; j++) {
				if(i==13 && j ==6)System.out.println(test[i][j]);
				map[i][j] = cellsFactory.getMapCell(test[i][j], (j)*ICell.WALL_WIDTH,(i)*ICell.WALL_HEIGHT);
				if(test[i][j] == ICell.START_SYMBOL) {
					player = (Player)map[i][j];
					start = cellsFactory.getMapCell(ICell.WALL_SYMBOL, (j-1)*ICell.WALL_WIDTH, (i)*ICell.WALL_HEIGHT);
				}
				if(test[i][j] == ICell.END_SYMBOL) {
					end = cellsFactory.getMapCell(ICell.WALL_SYMBOL, (j+1)*ICell.WALL_WIDTH, (i)*ICell.WALL_HEIGHT);
						
				}
				else if(test[i][j] == ICell.MONSTER_SPAWN_SYMBOL1) {
					monster1 = (Monster)map[i][j];
				}
				else if(test[i][j] == ICell.MONSTER_SPAWN_SYMBOL2) {
					monster2 = (Monster) map[i][j];
				}
				System.out.println( "r : " + i + " c : " + j + "  " + test[i][j]);
			}
		}	
		return map;
	}
	public Player getPlayer() {
		return this.player;
	}
	public void addStartEndToView(Pane root) {
		start.addToView(root);
		end.addToView(root);
	}
	public MapCell getStart() {
		return start;
	}
	public MapCell getEnd() {
		return end;
	}
	
	public Monster getFirstMonster() {
		return this.monster1;
	}
	public Monster getSecondMonster() {
		return this.monster2;
	}
	
}
