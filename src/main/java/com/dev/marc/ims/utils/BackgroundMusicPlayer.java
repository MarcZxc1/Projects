package com.dev.marc.ims.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusicPlayer {
	private static BackgroundMusicPlayer instance;
	private MediaPlayer mediaPlayer;

	private BackgroundMusicPlayer() {}

	public static BackgroundMusicPlayer getInstance() {
		if (instance == null) {
			instance = new BackgroundMusicPlayer();
		}
		return instance;
	}

	public void playMusic(String filePath, boolean loop) {
		try {
			if (mediaPlayer == null || !mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
				// Load the resource as a stream
				Media media = new Media(getClass().getResource(filePath).toExternalForm());
				mediaPlayer = new MediaPlayer(media);

				if (loop) {
					mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
				}

				mediaPlayer.play();
			}
		} catch (Exception e) {
			System.out.println("IDIOT ADD SOME MUSIC STUPID ASSHOLE");
			System.err.println("Error playing background music: " + e.getMessage());
		}
	}

	public void stopMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}
}
