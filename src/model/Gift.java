package model;

import java.io.File;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public abstract class Gift extends MapCell{
	protected boolean available = true;
	public Gift(double x, double y, Image image) {
		super(x, y, image);
	}
	
	public void onGiftTaken() {
		this.available = false;
		File music = new File("resources/music/gift.wav");
		MusicPlayer player = new MusicPlayer(music);
		player.playAsync();
		}
	@Override
	public String getCellName() {
		// TODO Auto-generated method stub
		return "Gift";
	}

}
