package model;

import view.MainView;

public class MapGenerator {

	public MapGenerator() {
		//TODO get selected level and diff
		
	}
	
	public MapCell[][] getAllCells(){
		//Testing ..
		CellsFactory cellsFactory = new CellsFactory();
		MapCell[][] map =  new MapCell[30][30];
		Character[][] test = MainView.levels.get("level2");
		for(int i = 0 ; i <30 ; i++) {
			for(int j = 0 ; j < 30 ; j++) {
				map[i][j] = cellsFactory.getMapCell(test[i][j], (j)*ICell.WALL_WIDTH,(i)*ICell.WALL_HEIGHT);
				System.out.println( "r : " + i + " c : " + j + "  " + test[i][j]);
			}
		}	
		return map;
	}
	
}
