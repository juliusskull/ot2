package com.aguasnortesalta.ordenes.model;

public class Config extends Base {
	private long version_db;
	private String version_fch;
	
	
	public Config(long version_db, String version_fch) {
		super();
		this.version_db = version_db;
		this.version_fch = version_fch;
	}
	public long getVersion_db() {
		return version_db;
	}
	public void setVersion_db(long version_db) {
		this.version_db = version_db;
	}
	public String getVersion_fch() {
		return version_fch;
	}
	public void setVersion_fch(String version_fch) {
		this.version_fch = version_fch;
	}
	
	public Config() {
		super();
		
	}
}
