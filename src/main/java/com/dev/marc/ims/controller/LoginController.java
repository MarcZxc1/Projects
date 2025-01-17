package com.dev.marc.ims.controller;

import com.dev.marc.ims.util.PasswordVisibilityHandler;
import com.dev.marc.ims.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private TextField visiblePasswordField;
	@FXML
	private ImageView closedEye;
	@FXML
	private CheckBox showPasswordCheckBox;

	private PasswordVisibilityHandler passwordVisibilityHandler;

	private static final String ASSETS_PATH = "/assets/";
	private static final String VIEW_PATH = "/com/dev/marc/ims/view/";

	@FXML
	public void initialize() {
		// Initialize PasswordVisibilityHandler
		passwordVisibilityHandler = new PasswordVisibilityHandler(password, visiblePasswordField, closedEye);

		// Set default state to hide the password
		passwordVisibilityHandler.hidePassword();
		closedEye.setImage(loadImageSafely(ASSETS_PATH + "hide.png"));

		// Add listener to toggle password visibility
		showPasswordCheckBox.selectedProperty().addListener(
				(observable, oldValue, newValue) -> {
			if (newValue) {
				passwordVisibilityHandler.showPassword();
				closedEye.setImage(loadImageSafely(ASSETS_PATH + "view.png"));
			} else {
				passwordVisibilityHandler.hidePassword();
				closedEye.setImage(loadImageSafely(ASSETS_PATH + "hide.png"));
			}
		});
	}

	@FXML
	public void switchToDashboard(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
		stage.centerOnScreen();
		SceneSwitcher.switchScene(stage, "/com/dev/marc/ims/view/Dashboard.fxml",
				1315, 690, false);
	}


	@FXML
	public void goToRegister(ActionEvent event) {
		try {
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			SceneSwitcher.switchScene(stage, "/com/dev/marc/ims/view/Register.fxml", 500, 600, false);
		} catch (IOException e) {
			System.err.println("Error switching to Register scene: " + e.getMessage());
		}
	}




	private Image loadImageSafely(String path) {
		try {
			Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
			if (image.isError()) {
				throw new IOException("Error loading image at path: " + path);
			}
			return image;
		} catch (Exception e) {
			System.err.println("Error: Could not load image " + path);
			return null;
		}
	}
}
