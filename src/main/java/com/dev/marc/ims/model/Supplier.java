package com.dev.marc.ims.model;

public class Supplier {
	private Integer id;
	private String name;
	private String contact;
	private String email;
	private String address;

	public Supplier(Integer id, String name, String contact, String email, String address) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
	}

	// Getters
	public Integer getId() { return id; }
	public String getName() { return name; }
	public String getContact() { return contact; }
	public String getEmail() { return email; }
	public String getAddress() { return address; }
}
