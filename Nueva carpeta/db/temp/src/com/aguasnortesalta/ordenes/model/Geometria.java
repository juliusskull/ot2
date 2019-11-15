package com.aguasnortesalta.ordenes.model;

public class Geometria extends Base {
	long featid;
	String type;
	String properties;
	String geometry;
	int actualizar;
	String lat;
	String lng;
	
	
	public Geometria() {
		super();

	}

	public Geometria(int featid, String type, String properties,
			String geometry, int actualizar) {
		super();
		this.featid = featid;
		this.type = type;
		this.properties = properties;
		this.geometry = geometry;
		this.actualizar = actualizar;

	}

	public long getFeatid() {
		return featid;
	}

	public void setFeatid(long featid) {
		this.featid = featid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

	public int getActualizar() {
		return actualizar;
	}

	public void setActualizar(int actualizar) {
		this.actualizar = actualizar;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Geometria [featid=" + featid + ", type=" + type
				+ ", properties=" + properties + ", geometry=" + geometry
				+ ", actualizar=" + actualizar + ", lat=" + lat + ", lng="
				+ lng + "]";
	}

	
	

}