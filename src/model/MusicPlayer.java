package model;

import java.io.File;

import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

public class MusicPlayer {
	File music;

	public MusicPlayer(File music) {
		this.music = music;
	}
	public void playAsyncIndefinite() {
		final Task<?> task = new Task<Object>() {

			@Override
			protected Object call() throws Exception {
				AudioClip audio = new AudioClip(music.toURI().toString());
				audio.setCycleCount(Integer.MAX_VALUE);
				audio.setRate(1);
				audio.setVolume(0.2f);
				audio.play();
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.start();

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
