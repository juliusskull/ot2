package com.aguasnortesalta.ordenes.model;


public abstract class DetailedPlace {

	private String url;
	private String phoneNumber;
	private String description;
	private String[] imageFiles;

	public String getDescription() {
		return description;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(String[] imageFiles) {
		this.imageFiles = imageFiles;
	}



}
