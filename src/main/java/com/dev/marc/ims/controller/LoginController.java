package com.dev.marc.ims.controller;

import com.dev.marc.ims.dbConfig.databaseConnection;
import com.dev.marc.ims.utils.PasswordVisibilityHandler;
import com.dev.marc.ims.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	private String userRole;

	@FXML
	public void initialize() {
		passwordVisibilityHandler = new PasswordVisibilityHandler(password, visiblePasswordField, closedEye);
		passwordVisibilityHandler.hidePassword();
		closedEye.setImage(loadImageSafely(ASSETS_PATH + "hide.png"));

		showPasswordCheckBox.selectedProperty().addListener((observable,
															 oldValue, newValue) -> {
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
	public void login(ActionEvent event) {
		String usernameInput = username.getText().trim();
		String passwordInput = password.getText().trim();

		if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
			showAlert("Login Failed", "Please enter both username and password.");
			return;
		}

		if (authenticateUser(usernameInput, passwordInput)) {
			switchToDashboard(event);
		} else {
			showAlert("Login Failed", "Invalid username or password.");
		}
	}

	private boolean authenticateUser(String username, String password) {
		String query = "SELECT password, role FROM users WHERE username = ?";

		try (Connection conn = databaseConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String storedPassword = rs.getString("password");
				userRole = rs.getString("role");
				return storedPassword.equals(password);
			}
		} catch (SQLException e) {
			System.err.println("Database error: " + e.getMessage());
		}
		return false;
	}

//	@FXML
//	public void switchToDashboard1(ActionEvent event){
//		try {
//			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//			SceneSwitcher.switchScene(stage, VIEW_PATH + "Dashboard.fxml",
//					1315, 690, false);
//		} catch (IOException e) {
//			showAlert("Navigation Error", "Cannot load the Register scene.");
//		}
//
//	}

	@FXML
	public void switchToDashboard(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.centerOnScreen();
			if ("Admin".equals(userRole)) {
				SceneSwitcher.switchScene(stage, VIEW_PATH + "Dashboard.fxml", 1315, 690, false);
			} else if ("Staff".equals(userRole)) {
				SceneSwitcher.switchScene(stage, VIEW_PATH + "StaffDashboard.fxml", 1315, 690, false);
			} else {
				showAlert("Access Denied", "You do not have permission to access this application.");
			}System.out.println("User role: " + userRole);

		} catch (IOException e) {
			e.printStackTrace();
			showAlert("Navigation Error", "Cannot load the Dashboard scene.");
		}
	}

	@FXML
	public void goToRegister(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			SceneSwitcher.switchScene(stage, VIEW_PATH + "Register.fxml",
					500, 600, false);
		} catch (IOException e) {
			showAlert("Navigation Error", "Cannot load the Register scene.");
		}
	}

	private Image loadImageSafely(String path) {
		try {
			return new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
		} catch (Exception e) {
			System.err.println("Error: Could not load image " + path);
			return null;
		}
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
		System.err.println(message);  // Log the error for debugging
	}
}