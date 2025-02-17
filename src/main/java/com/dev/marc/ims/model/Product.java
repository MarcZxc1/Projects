package com.dev.marc.ims.model;

import java.sql.Timestamp;

public class Product {
	private int id;
	private String name;
	private String sku; // stands for Stock Keeping Unit
	private String category;
	private double price;
	private Timestamp createdAt; // ✅ Use Timestamp instead of String
	private Timestamp updatedAt; // ✅ Use Timestamp instead of String

	private String description;
	private int stockLevel;
	private int recordPoint;
	private int maxStockThreshold;
	private String supplier;
	private String  barCode;
	private String imagePath;

	public Product(int productID, String name, String sku, String category, double price, String description,
				   int stockLevel, int recordPoint, int maxStockThreshold, String supplier,
				   String barCode, String imagePath){
		this.id = productID;
		this.name = name;
		this.sku = sku;
		this.category = category;
		this.price = price;
//		this.description = description;
		this.stockLevel = stockLevel;
//		this.recordPoint = recordPoint;
//		this.maxStockThreshold = maxStockThreshold;
//		this.supplier = supplier;
//		this.barCode = barCode;
//		this.imagePath = imagePath;
	}

	public Product(int id, String name, String sku, String category, double price,
				   Timestamp createdAt, Timestamp updatedAt){
		this.id = id;
		this.name = name;
		this.sku = sku;
		this.category = category;
		this.price = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
//		this.description = description;
//		this.stockLevel = stockLevel;

//		this.recordPoint = recordPoint;
//		this.maxStockThreshold = maxStockThreshold;
//		this.supplier = supplier;
//		this.barCode = barCode;
//		this.imagePath = imagePath;
	}

	public Integer getId(){
		return id;
	}

	public void setProductID(int productID){
		this.id = productID;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getSku(){
		return sku;
	}
	public void setSku(String sku){
		this.sku = sku;
	}
	public String getCategory(){
		return category;
	}
	public void setCategory(String category){
		this.category = category;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public Timestamp getCreatedAt() {return createdAt;}
	public Timestamp getUpdatedAt() {return updatedAt;}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public int getStockLevel(){
		return stockLevel;
	}
	public void setStockLevel(int stockLevel){
		this.stockLevel = stockLevel;
	}
	public int  getRecordPoint(){
		return recordPoint;
	}
	public void setRecordPoint(int recordPoint){
		this.recordPoint = recordPoint;
	}
	public int getMaxStockThreshold(){
		return maxStockThreshold;
	}
	public void setMaxStockThreshold(int maxStockThreshold){
		this.maxStockThreshold = maxStockThreshold;
	}
	public String getSupplier(){
		return supplier;
	}
	public void setSupplier(String supplier){
		this.supplier = supplier;
	}
	public String getBarCode(){
		return barCode;
	}
	public void setBarcode(String barCode){
		this.barCode = barCode;
	}
	public String getImagePath(){
		return imagePath;
	}
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}
}
