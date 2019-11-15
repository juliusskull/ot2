package com.aguasnortesalta.ordenes.model;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.google.android.gms.maps.model.LatLng;

public class Template extends Base {
	int id_template;
	String Titulo;
	String desc_template;
	String observacion;
	public Template() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Template(int id_template, String titulo, String desc_template,
			String observacion) {
		super();
		this.id_template = id_template;
		Titulo = titulo;
		this.desc_template = desc_template;
		this.observacion = observacion;
	}
	public int getId_template() {
		return id_template;
	}
	public void setId_template(int id_template) {
		this.id_template = id_template;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDesc_template() {
		return desc_template;
	}
	public void setDesc_template(String desc_template) {
		this.desc_template = desc_template;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getStringPrecentacion() {
		// TODO Auto-generated method stub
			return "<h3>Titulo:"+this.Titulo+"</h3>"
					+"<b>Descripcion:</b>"+this.getDesc_template()+" </br>"	
					+"<b>Observacion:</b>"+this.getObservacion()+"</br>"					
			;
			

		 
	}
	
}
