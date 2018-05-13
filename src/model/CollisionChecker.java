package model;

import javafx.scene.image.ImageView;

public class CollisionChecker {
	private static CollisionChecker mInstance;
	private MapCell[][] map;
	private CollisionChecker(MapCell[][] map){
		this.map  = map;
	}
	public static void removeInstance() {
		mInstance = null;
	}
	public static CollisionChecker getInstance() {
		return mInstance;
	}
	public static CollisionChecker getInstance(MapCell[][] map) {
		return mInstance == null ? mInstance = new CollisionChecker(map) : mInstance;
	}
	
	public boolean canMove(ImageView movingImage) {
		boolean can = true;
		MapCell start = MapGenerator.getInstance().getStart();
		MapCell end = MapGenerator.getInstance().getEnd();
		
		if(movingImage.getBoundsInParent().intersects(start.getBoundsInParent()) 
				|| movingImage.getBoundsInParent().intersects(end.getBoundsInParent())) {
			return false;
		}
		for(int i = 0 ; i <30 ; i++) {
			for(int j = 0 ; j<30 ;j++) {
			if(map[i][j].getCellName().equals(ICell.WALL)&& movingImage.getBoundsInParent().intersects(map[i][j].getBoundsInParent())  ) {
				can  = false;
				break;
			}
		}
			if(!can)break;
		}
		
		
		return can;
	}
	public void takeGift(ImageView movingImage) {
		boolean done = false;
		for(int i = 0 ; i <30 ; i++) {
			for(int j = 0 ; j<30 ;j++) {
			if(map[i][j] instanceof Gift && movingImage.getBoundsInParent().intersects(map[i][j].getBoundsInParent())  ) {
				//System.out.println("Intersect  Gift !!! " + " i : " + i + " j : " + j);
				Gift gift = (Gift)map[i][j];
				if(!gift.available)continue;
				gift.onGiftTaken();
				done = true;
				break;
						
			}
		}
			if(done)break;
		}	
	}

	public boolean bulletWillStop(ImageView bulletImageView) {
		boolean can = true;
		for(int i = 0 ; i <30 ; i++) {
			for(int j = 0 ; j<30 ;j++) {
			if(map[i][j] instanceof Wall && bulletImageView.getBoundsInParent().intersects(map[i][j].getBoundsInParent())  ) {
				//System.out.println("Intersect !!! " + " i : " + i + " j : " + j);
				Wall wall = (Wall) map[i][j];
				wall.decreaseHealth(50,map,i,j);
				can  = false;
				break;
			}
		}
			if(!can)break;
		}
		
		
		return can;
	}
	
	public boolean monsterWillStop(ImageView monsterImageView) {
		boolean can = true;
		MapCell start = MapGenerator.getInstance().getStart();
		MapCell end = MapGenerator.getInstance().getEnd();
		
		if(monsterImageView.getBoundsInParent().intersects(start.getBoundsInParent()) 
				|| monsterImageView.getBoundsInParent().intersects(end.getBoundsInParent())) {
			return false;
		}
	
		for(int i = 0 ; i <30 ; i++) {
			for(int j = 0 ; j<30 ;j++) {
			if(map[i][j] instanceof Wall ) {
				Wall x = (Wall)map[i][j];
				if(!x.destroyable && monsterImageView.getBoundsInParent().intersects(map[i][j].getBoundsInParent())) {
				can  = false;
				break;
				}
			}
		}
			if(!can)break;
		}
		
		
		return can;
	}
	

	public void checkBomb(ImageView playerImageView) {
		boolean done = false;
		for(int i = 0 ; i <30 ; i++) {
			for(int j = 0 ; j<30 ;j++) {
			if(map[i][j] instanceof Bomb && playerImageView.getBoundsInParent().intersects(map[i][j].getBoundsInParent())  ) {
				//System.out.println("Intersect  Bomb !!! " + " i : " + i + " j : " + j);
				Bomb bomb = (Bomb)map[i][j];
				if(!bomb.available)continue;
				bomb.triggerBomb();
				done = true;
				break;						
			}
		}
			if(done)break;
		}

	}
	public boolean bulletWillShootMonster(ImageView bullet) {
		boolean yes = false;
		Monster monster1 = MapGenerator.getInstance().getFirstMonster();
		Monster monster2 = MapGenerator.getInstance().getSecondMonster();
		if(monster1.alive && bullet.getBoundsInParent().intersects(monster1.monsterImageView.getBoundsInParent())  ) {
			yes = true;
			monster1.removeMonster();
		}else if(monster2.alive && bullet.getBoundsInParent().intersects(monster2.monsterImageView.getBoundsInParent())  ) {
			yes = true;
			monster2.removeMonster();
		}

		return yes;
	}
	
	
}
