package com.dev.marc.ims.dbConfig;

import com.dev.marc.ims.controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	LoginController loginController;



	@FXML
	public void fetch(ActionEvent event) {
		String usernameInput = username.getText().trim();
		String passwordInput = password.getText().trim();

		if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
			showAlert("Login Failed", "Please enter both username and password.");
			return;
		}

		if (authenticateUser(usernameInput, passwordInput)) {
			loginController.switchToDashboard(event);
		} else {
			showAlert("Login Failed", "Invalid username or password.");
		}
	}
	private boolean authenticateUser(String username, String password) {
		String query = "SELECT password FROM users WHERE username = ?";

		try (Connection conn = databaseConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String storedPassword = rs.getString("password");
				return storedPassword.equals(password);
			}
		} catch (SQLException e) {
			System.err.println("Database error: " + e.getMessage());
		}
		return false;
	}
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
		System.err.println(message);  // Log the error for debugging
	}
}
