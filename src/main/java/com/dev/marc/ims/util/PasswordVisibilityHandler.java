package com.dev.marc.ims.util;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PasswordVisibilityHandler {
	private final PasswordField passwordField;
	private final TextField visiblePasswordField;
	private final ImageView eyeIcon;

	public PasswordVisibilityHandler(PasswordField passwordField, TextField visiblePasswordField, ImageView eyeIcon) {
		this.passwordField = passwordField;
		this.visiblePasswordField = visiblePasswordField;
		this.eyeIcon = eyeIcon;

		// Initialize the visiblePasswordField's text with the passwordField's value
		visiblePasswordField.setText(passwordField.getText());

		// Sync text between the two fields
		passwordField.textProperty().addListener((observable, oldValue, newValue) -> visiblePasswordField.setText(newValue));
		visiblePasswordField.textProperty().addListener((observable, oldValue, newValue) -> passwordField.setText(newValue));

		// Set initial visibility states
		hidePassword();
	}
	@FXML
	public void showPassword() {
		visiblePasswordField.setVisible(true);
		passwordField.setVisible(false);
	}
   	@FXML
	public void hidePassword() {
		visiblePasswordField.setVisible(false);
		passwordField.setVisible(true);
	}
}
