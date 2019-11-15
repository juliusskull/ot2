package com.aguasnortesalta.ordenes.model;

public class Sincronizar extends Base {
	String id;
	String tipo;
	String valor;
	String lat;
	String lng;
	int enviado;
	String precision;
	int gps;
	int red;
	
	public Sincronizar() {
		super();

	}
	public Sincronizar(String id, String tipo, String valor) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.lat = "";
		this.lng = "";
		this.enviado = 0;

	}
	public Sincronizar(String id, String tipo, String valor, String lat,
			String lng, int enviado) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.lat = lat;
		this.lng = lng;
		this.enviado = enviado;

	}

	public Sincronizar(String id, String tipo, String valor, String lat,
			String lng, int enviado, String precision, int gps, int red) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.lat = lat;
		this.lng = lng;
		this.enviado = enviado;
		this.precision = precision;
		this.gps = gps;
		this.red = red;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public int getGps() {
		return gps;
	}

	public void setGps(int gps) {
		this.gps = gps;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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

	public int getEnviado() {
		return enviado;
	}

	public void setEnviado(int enviado) {
		this.enviado = enviado;
	}

	public CharSequence getSincronizar() {
		// TODO Auto-generated method stub
		return id + "-" + tipo;
	}
	@Override
	public String toString() {
		return "Sincronizar [id=" + id + ", tipo=" + tipo + ", valor=" + valor
				+ ", lat=" + lat + ", lng=" + lng + ", enviado=" + enviado
				+ ", precision=" + precision + ", gps=" + gps + ", red=" + red
				+ "]";
	}

}