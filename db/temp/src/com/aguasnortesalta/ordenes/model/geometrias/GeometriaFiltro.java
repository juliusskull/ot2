package com.aguasnortesalta.ordenes.model.geometrias;

public class GeometriaFiltro {
	public long id_tipo;
	public String tipo;
	public GeometriaFiltro(long id_tipo, String tipo) {
		super();
		this.id_tipo = id_tipo;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return this.tipo;
	}
	public long getFiltro(){
		return id_tipo;
	}
	
	
}
