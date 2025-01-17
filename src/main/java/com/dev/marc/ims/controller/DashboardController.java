package com.dev.marc.ims.controller;

import com.almasb.fxgl.entity.action.Action;
import com.dev.marc.ims.util.SceneSwitcher;
import com.dev.marc.ims.util.TabSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class DashboardController implements Initializable{
	private static final String ASSETS_PATH = "/assets/";
	private static final String VIEW_PATH = "/com/dev/marc/ims/view/";

	@FXML
	private Button bt;

	@FXML
	public Pane paneContainer;

	@FXML
	private Button homeBtn;

	@FXML
	private Button SalesMngmntBtn;

	@FXML
	private Button returnMngmntBtn;

	@FXML
	private Button userMngmntBtn;

	@FXML
	private MenuBar menuBar;

	LoginController loginController = new LoginController();

	@FXML
	public void home(ActionEvent event) throws IOException {
		loginController.switchToDashboard(event);
	}

	@FXML
	public void goToSalesMngmt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Sales-Management.fxml", 1300, 650, true);
	}

	@FXML
	public void goToUserMngmnt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"User-Management.fxml", 1300, 650, true);
	}


	@FXML
	public void goToReturnMngmnt(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Returns-Management.fxml", 1300, 650, true);

	}
	@FXML
	public void backToLogin(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
		SceneSwitcher sceneSwitcher = new SceneSwitcher();
		sceneSwitcher.switchScene(stage, VIEW_PATH + "Login.fxml",
				500, 600, false);
	}


	@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
		}
	}
