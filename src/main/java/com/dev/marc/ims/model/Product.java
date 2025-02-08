package com.dev.marc.ims.model;

public class Product {

	private String name;
	private String sku; // stands for Stock Keeping Unit
	private String category;
	private double price;
	private String description;
	private int stockLevel;
	private int recordPoint;
	private int maxStockThreshold;
	private String supplier;
	private String  barCode;
	private String imagePath;

	public Product(String name, String sku, String category, double price, String description,
				   int stockLevel, int recordPoint, int maxStockThreshold, String supplier,
				   String barCode, String imagePath){
		this.name = name;
		this.sku = sku;
		this.category = category;
		this.price = price;
		this.description = description;
		this.stockLevel = stockLevel;
		this.recordPoint = recordPoint;
		this.maxStockThreshold = maxStockThreshold;
		this.supplier = supplier;
		this.barCode = barCode;
		this.imagePath = imagePath;
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
