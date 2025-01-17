package com.dev.marc.ims;

import com.dev.marc.ims.util.BackgroundMusicPlayer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends javafx.application.Application {

	private static final String MUSIC_PATH = "/audio/AVANGARD.mp3";
	private static final String MAIN_VIEW = "/com/dev/marc/ims/view/Login.fxml";
	private static final String ASSETS_PATH = "/assets/application-icon.png";

	@Override
	public void start(Stage stage) {
		try {

			BackgroundMusicPlayer.getInstance().playMusic(MUSIC_PATH, true);
			Parent root = FXMLLoader.load(getClass().getResource(MAIN_VIEW));
			Scene scene = new Scene(root, 500, 600);
			Image icon = new Image(getClass().getResourceAsStream(ASSETS_PATH));
			stage.getIcons().add(icon);
			stage.setTitle("Inventory Management System");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			System.err.println("Error occurred: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	@Override
	public void stop() {
		BackgroundMusicPlayer.getInstance().stopMusic();
	}

	public static void main(String[] args) {
		launch();
	}
}
