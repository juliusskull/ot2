package com.aguasnortesalta.ordenes.model;

import android.util.Log;



public class Place extends DetailedPlace {

	private long id;
	private String name;
	private String address;
	private String url;
	private String phoneNumber;
	private String description;
	private double latitude;
	private double longitude;
	private int parentId;
	private Place[] subplaces;
	private String[] mapaFile;

	public String[] getMapaFile() {
		return mapaFile;
	}

	public void setMapaFile(String[] mapaFile) {
		this.mapaFile = mapaFile;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Place[] getSubplaces() {
		return subplaces;
	}

	public void setSubplaces(Place[] subplaces) {
		this.subplaces = subplaces;
	}
	public String getImagePath() {
		// TODO Auto-generated method stub
		return "images/";
	}

}
