package model;

import java.io.File;

import javafx.animation.AnimationTimer;

public interface BombState {
	public void triggerBomb(int damage);
}
class Explosion implements BombState{

	@Override
	public void triggerBomb(int damage) {
		MapGenerator.getInstance().getPlayer().decreaseHealth(damage);
		MusicPlayer musicPlayer = new MusicPlayer(new File("resources/music/bomb.wav"));
		musicPlayer .playAsync();
	}
}
class Poison implements BombState{
	private AnimationTimer timer;
	private long current = System.currentTimeMillis();
	private long previousDecrease = current;
	private int currentDamageDealt = 0;
	private int totalDamage;
	@Override
	public void triggerBomb(int damage) {
		MusicPlayer musicPlayer  = new MusicPlayer(new File("resources/music/bomb.wav"));
		musicPlayer .playAsync();
		totalDamage = damage;
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				decreaseHealth(5);
			}
		};
		timer.start();
	}
	private void decreaseHealth(int damage) {
		current = System.currentTimeMillis();
		if(current - previousDecrease <= 1000)return;
		previousDecrease = current;
		Player player =  MapGenerator.getInstance().getPlayer();
		if(player.getHealth() <= 0 || currentDamageDealt == totalDamage) {
			timer.stop();
		}else {
			currentDamageDealt += 5;
			player.decreaseHealth(damage);
		}
	}
	
}
class LowerSpeed implements BombState{
	private long total = 5000;
	private long currentTime = System.currentTimeMillis();
	private long start = System.currentTimeMillis();
	private AnimationTimer timer;
	@Override
	public void triggerBomb(int damage) {
		
		MusicPlayer musicPlayer  = new MusicPlayer(new File("resources/music/bomb.wav"));
		musicPlayer .playAsync();
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				check();
			}
		};
		start= System.currentTimeMillis();
		System.out.println("LoweringSpeed");
		Player.speed = 0.05;
		timer.start();
		
	}
	private void check() {
		currentTime = System.currentTimeMillis();
		
		if(currentTime - start >= total) {
			Player.speed = 0.3;
			System.out.println("Stop lowering speed");
			timer.stop();
		}
	}
	
}




