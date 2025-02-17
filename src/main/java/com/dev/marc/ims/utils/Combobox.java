package com.dev.marc.ims.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Combobox {

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		comboBox.getItems().addAll("Option 1", "Option 2", "Option 3");
	}


}
