package com.dev.marc.ims.controller;

import com.dev.marc.ims.utils.ImageUploader;
import com.dev.marc.ims.utils.SceneSwitcher;
import com.dev.marc.ims.utils.TabSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class DashboardController implements Initializable{
	private static final String ASSETS_PATH = "/assets/";
	public static final String VIEW_PATH = "/com/dev/marc/ims/view/";

	@FXML
	private Button bt;

	@FXML
	private Button uploadButton;

	@FXML
	private ImageView imageView;

	@FXML
	public Pane paneContainer;

	@FXML
	private MenuBar menuBar;

	ImageUploader imageUploader = new ImageUploader();
	LoginController loginController = new LoginController();

	@FXML
	public void home(ActionEvent event) throws IOException {
		loginController.switchToDashboard(event);}

	@FXML
	public void goToSalesMngmt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Sales-Management.fxml", 1300, 650, true);}

	@FXML
	public void goToUserMngmnt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"User-Management.fxml", 1300, 650, true);}


	@FXML
	public void goToReturnMngmnt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Returns-Management.fxml", 1300, 650, true);}

	@FXML
	public void goToProductMngmnt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Product-Management.fxml", 1300, 650, true);}
	//Product methods
		public void ImageUpload(ActionEvent event) throws IOException {
			// Create a FileChooser
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(
					new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
			);

			File file = fileChooser.showOpenDialog(null); // Replace null with your Stage if needed
			if (file != null) {
				Image image = new Image(file.toURI().toString());
				imageView.setImage(image);
		}
	}


	@FXML
	public void goToOrderMngmnt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Order-Management.fxml", 1300, 650, true);}

	@FXML
	public void goToSupplierMngmnt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Supplier-Management.fxml", 1300, 650, true);}

	@FXML
	public void goToInventoryTracking(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Inventory-Tracking.fxml", 1300, 650, true);}

	@FXML
	public void goToReportingAnalytics(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Reporting and Analytics.fxml", 1300, 650, true);}

	@FXML
	public void goToFinancialMngmnt(ActionEvent BURAT) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Financial-Management.fxml", 1300, 650, true);}

	@FXML
	public void goToSecurityBackup(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Security-Backup.fxml", 1300, 650, true);}

	@FXML
	public void goToSettings(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Settings.fxml", 1300, 650, true);}

	@FXML
	public void goToIntegrations(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Integrations.fxml", 1300, 650, true);}

	@FXML
	public void backToLogin(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
		SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchScene(stage, VIEW_PATH + "Login.fxml",
				500, 600, false);}


	@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {}}
