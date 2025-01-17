package com.dev.marc.ims.controller;

import com.dev.marc.ims.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

	@FXML
	public void backToLogin(ActionEvent event) throws IOException {
	DashboardController dashboardController = new DashboardController();
		dashboardController.backToLogin(event);
	}
}
