package model;

import java.io.File;

import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

public class MusicPlayer {
	File music;
	public static MusicPlayer bgMusic;
	private static AudioClip bgClip;

	public MusicPlayer(File music) {
		this.music = music;
	}
	public void playAsyncIndefinite() {
		bgMusic = this;
		final Task<?> task = new Task<Object>() {
			
			@Override
			protected Object call() throws Exception {
				 bgClip= new AudioClip(music.toURI().toString());
				 bgClip.setCycleCount(Integer.MAX_VALUE);
				 bgClip.setRate(1);
				 bgClip.setVolume(0.2f);
				 bgClip.play();
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.start();
	}
	public void stopBgMusic() {
		bgClip.stop();
	}
	public void playAsync() {
		final Task<?> task = new Task<Object>() {

			@Override
			protected Object call() throws Exception {
				AudioClip audio = new AudioClip(music.toURI().toString());
				audio.setCycleCount(1);
				audio.setRate(2);
				audio.play();
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.start();

	}

}
