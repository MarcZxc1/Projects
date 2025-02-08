package com.dev.marc.ims.controller;

import com.dev.marc.ims.utils.PasswordVisibilityHandler;
import com.dev.marc.ims.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
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

	@FXML
	public void initialize() {
		// Initialize PasswordVisibilityHandler
		passwordVisibilityHandler = new PasswordVisibilityHandler(password, visiblePasswordField, closedEye);

		// Set default state to hide the password
		passwordVisibilityHandler.hidePassword();
		closedEye.setImage(loadImageSafely("/assets/hide.png"));

		// Add a listener to handle password visibility toggle
		showPasswordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				passwordVisibilityHandler.showPassword();
				closedEye.setImage(loadImageSafely("/assets/view.png"));
			} else {
				passwordVisibilityHandler.hidePassword();
				closedEye.setImage(loadImageSafely("/assets/hide.png"));
			}
		});
	}

	@FXML
	public void switchToDashboard(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//		stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() / 4);
//		stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() / 4);
		stage.setResizable(false);
		SceneSwitcher.switchScene(stage, "/com/dev/marc/ims/view/Dashboard.fxml", "Dashboard", 800, 600); // Replace with your dashboard size
	}

	@FXML
	public void goToRegister(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//		stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() / 4);
//		stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() / 4);
		stage.setResizable(false);
		SceneSwitcher.switchScene(stage, "/com/dev/marc/ims/view/Register.fxml", "Register", 500, 600); // Replace with your dashboard size
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
