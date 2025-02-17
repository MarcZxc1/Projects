package com.dev.marc.ims.controller;

import com.dev.marc.ims.dbConfig.databaseConnection;
import com.dev.marc.ims.model.Product;
import com.dev.marc.ims.model.Supplier;
import com.dev.marc.ims.utils.TabSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.sql.*;


public class ProductManagementController{
	public static final String VIEW_PATH = "/com/dev/marc/ims/view/";


	@FXML private TextField productId;
	@FXML private TextField productName;
	@FXML private TextField productSku;
	@FXML private TextField productPrice;
	@FXML private TextField productQuantity;
	@FXML private ComboBox productCategory;
	@FXML private TextField productSupplierID;
	@FXML private ComboBox<String> comboBox;
	@FXML private ImageView imageView;
	@FXML private Pane paneContainer;
	String barcode = "1234567433223";

	@FXML private TableView<Product> productTableView;
	@FXML private TableColumn<Product, Integer> colID;
	@FXML private TableColumn<Product, String> colName;
	@FXML private TableColumn<Product, String> colSKU;
	@FXML private TableColumn<Product, String> colCategory;
	@FXML private TableColumn<Product, String> colPrice;
	@FXML private TableColumn<Product, Timestamp> colCreatedAt;
	@FXML private TableColumn<Product, Timestamp> colUpdatedAt;

	private ObservableList<Product> productList = FXCollections.observableArrayList();

	public void setPaneContainer(Pane paneContainer) {
		this.paneContainer = paneContainer;
	}

	@FXML
	public void goToProductMngmnt1(ActionEvent event) throws IOException {
		TabSwitch.switchTab(paneContainer, VIEW_PATH +
				"Product-Management.fxml", 1250, 600, true);}


//	public void ImageUpload(ActionEvent event) throws IOException {
//		// Create a FileChooser
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.getExtensionFilters().add(
//				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
//		File file = fileChooser.showOpenDialog(null); // Replace null with your Stage if needed
//		if (file != null) {
//			Image image = new Image(file.toURI().toString());
//			imageView.setImage(image);
//		}
//	}

	@FXML
	public void addProduct() {
		String query = "INSERT INTO products(id, name, sku, category, price, quantity, supplier_id, barcode) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection connect = databaseConnection.getConnection();
			PreparedStatement psmt = connect.prepareStatement(query);

			// Validate & Parse Input Fields
			if (productId.getText().isEmpty() || productPrice.getText().isEmpty() || productQuantity.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "ID, Price, and Quantity are required!");
				alert.showAndWait();
				return;
			}

			int id = Integer.parseInt(productId.getText());  // Convert to int
			String name = productName.getText();
			String sku = productSku.getText();
			String category = (productCategory.getValue() != null) ? productCategory.getValue().toString() : "Unknown";
			double price = Double.parseDouble(productPrice.getText());  // Convert to double
			int quantity = Integer.parseInt(productQuantity.getText());  // Convert to int

			// Convert supplier_id to integer, handling possible empty input
			Integer supplierId = (productSupplierID.getText().isEmpty()) ? null : Integer.parseInt(productSupplierID.getText());

			// Debugging: Print query values
			System.out.println("Executing query with values: ID=" + id + ", Name=" + name + ", Category=" + category +
					", Price=" + price + ", Quantity=" + quantity  + "supplierId" + supplierId +"Barcode=" + barcode);

			// Set parameters with correct data types
			psmt.setInt(1, id);
			psmt.setString(2, name);
			psmt.setString(3, sku);
			psmt.setString(4, category);
			psmt.setDouble(5, price);
			psmt.setInt(6, quantity);

			// Handle nullable supplier_id
			if (supplierId == null) {
				psmt.setNull(7, java.sql.Types.INTEGER);
			} else {
				psmt.setInt(7, supplierId);
			}

			psmt.setString(8, barcode);

			psmt.executeUpdate();
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product added successfully!");
			alert.showAndWait();
			loadProducts();


		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number format. Check ID, Price, Quantity, and Supplier ID.");
			alert.showAndWait();
			e.printStackTrace();
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}}

	@FXML
	public void initialize() {
		productCategory.getItems().addAll("Electronics", "Furniture", "Appliances", "Food");

		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colSKU.setCellValueFactory(new PropertyValueFactory<>("sku"));
		colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
		colUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
		loadProducts();
	}

	@FXML
	private void loadProducts() {
		productList.clear();
		String query = "SELECT id, name, sku, category, price, created_at, updated_at FROM products";

		try (Connection connect = databaseConnection.getConnection();
			 PreparedStatement pstmt = connect.prepareStatement(query);
			 ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
				productList.add(new Product(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("sku"),
						rs.getString("category"),
						rs.getDouble("price"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at")
				));
			}

			productTableView.setItems(productList);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
