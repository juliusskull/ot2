package com.aguasnortesalta.ordenes;

public class ObjectView {
	String titulo;
	String descripcion;	
	int _id;	
	public String getTitulo() {		
		return (titulo.compareTo("null")==0)?"-": titulo;
	}
	public ObjectView(int _id,String titulo, String descripcion) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;		
		this._id = _id;
		
	}
	public ObjectView() {
		super();
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion==null?"":(descripcion.equals("null")?"":descripcion);
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}


	public String toString2() {
		return "ObjectView [titulo=" + titulo + ", descripcion=" + descripcion
				+ ", drableid="  + ", _id=" + _id + "]";
	}
	@Override
	public String toString() {
		return titulo;
	}
}
