package com.dev.marc.ims.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StaffController extends DashboardController {

	@FXML
	private Button viewProductsButton;
	ProductManagementController productManagementController = new ProductManagementController();

	public void goToProductMngmntz(ActionEvent event) throws IOException {
		productManagementController.setPaneContainer(paneContainer);
		productManagementController.goToProductMngmnt1(event);
	}


	@FXML
	public void initialize() {
		// Initialize staff-specific functionalities
	}

	@FXML
	public void viewProducts() {
		// Implement view products functionality
	}
}