package com.dev.marc.ims.controller;

import com.dev.marc.ims.dbConfig.databaseConnection;
import com.dev.marc.ims.model.Supplier;
import com.dev.marc.ims.utils.TabSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierManagementController {

	public static final String VIEW_PATH = "/com/dev/marc/ims/view/";

	@FXML private TextField SupplierID;
	@FXML private TextField SupplierName;
	@FXML private TextField SupplierContact;
	@FXML private TextField SupplierEmail;
	@FXML private TextField SupplierAddress;
	@FXML private Pane paneContainer;

	// TableView & Columns
	@FXML private TableView<Supplier> supplierTable;
	@FXML private TableColumn<Supplier, Integer> colId;
	@FXML private TableColumn<Supplier, String> colName;
	@FXML private TableColumn<Supplier, String> colContact;
	@FXML private TableColumn<Supplier, String> colEmail;
	@FXML private TableColumn<Supplier, String> colAddress;

	private ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

	public void setPaneContainer(Pane paneContainer) {
		this.paneContainer = paneContainer;
	}

	@FXML
	public void initialize() {
		// Initialize TableView columns
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

		loadSuppliers(); // Load data from database
	}

	@FXML
	public void goToSupplierMngmnt1(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH + "Supplier-Management.fxml", 1250, 600, true);
	}

	public void addSupplier() {
		String query = "INSERT INTO suppliers(ID, NAME, CONTACT, EMAIL, ADDRESS) VALUES (?, ?, ?, ?, ?)";

		try (Connection connect = databaseConnection.getConnection();
			 PreparedStatement psmt = connect.prepareStatement(query)) {

			if (SupplierID.getText().isEmpty() || SupplierName.getText().isEmpty() || SupplierContact.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "ID, Name, and Contact are required!");
				alert.showAndWait();
				return;
			}

			int id = Integer.parseInt(SupplierID.getText());
			String name = SupplierName.getText();
			String contact = SupplierContact.getText();  // Changed to String to avoid NumberFormatException
			String email = SupplierEmail.getText();
			String address = SupplierAddress.getText();

			psmt.setInt(1, id);
			psmt.setString(2, name);
			psmt.setString(3, contact);  // Store contact as a String to prevent errors
			psmt.setString(4, email);
			psmt.setString(5, address);

			psmt.executeUpdate();
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "Supplier Added!");
			alert.showAndWait();

			loadSuppliers(); // Refresh TableView after inserting new data

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void loadSuppliers() {
		supplierList.clear(); // Clear existing data

		String query = "SELECT * FROM suppliers";
		try (Connection conn = databaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				supplierList.add(new Supplier(
						rs.getInt("ID"),
						rs.getString("NAME"),
						rs.getString("CONTACT"),
						rs.getString("EMAIL"),
						rs.getString("ADDRESS")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		supplierTable.setItems(supplierList); // Set data to TableView
	}
}
