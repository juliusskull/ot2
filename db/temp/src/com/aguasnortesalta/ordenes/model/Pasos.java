package com.aguasnortesalta.ordenes.model;

public class Pasos extends Base{
	int id_paso;
	String desc_paso;
	String tipo;
	int foto;
	int obligatorio;
	String valor="";
	
	public Pasos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pasos(int id_paso, String desc_paso, String tipo, int foto,
			int obligatorio) {
		super();
		this.id_paso = id_paso;
		this.desc_paso = desc_paso;
		this.tipo = tipo;
		this.foto = foto;
		this.obligatorio = obligatorio;
	}
	public int getId_paso() {
		return id_paso;
	}
	public void setId_paso(int id_paso) {
		this.id_paso = id_paso;
	}
	public String getDesc_paso() {
		return desc_paso;
	}
	public void setDesc_paso(String desc_paso) {
		this.desc_paso = desc_paso;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getFoto() {
		return foto;
	}
	public void setFoto(int foto) {
		this.foto = foto;
	}
	public int getObligatorio() {
		return obligatorio;
	}
	public void setObligatorio(int obligatorio) {
		this.obligatorio = obligatorio;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
