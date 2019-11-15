package com.aguasnortesalta.ordenes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
/*
import com.google.android.gms.internal.dg;
import com.google.android.gms.internal.it;
*/
public class Confirmacion  extends Base{
	public String nro_ot;
	private String valor;
	private int enviado=0;
	private Ot ot_relacionada= new Ot();
	private List< Serviciosxmotivo> trabajos_ejecutados=  new ArrayList<Serviciosxmotivo>();
	private List<Ot_aperturas> aperturas_calzada= new ArrayList<Ot_aperturas>();
	private List<Ot_aperturas> aperturas_vereda= new ArrayList<Ot_aperturas>();
	private List<Equipo> equipos= new ArrayList<Equipo>();
	private List<Operario> operarios= new ArrayList<Operario>();
	private List<Materialesot> materiales = new ArrayList<Materialesot>();
	private List<Derivacionesot> derivacionesot = new ArrayList<Derivacionesot>();
	private long id_res=0;
	
	public Confirmacion(){
		//trabajos_ejecutados.add(new Serviciosxmotivo(238, "ACCIONAMIENTO VALVULA ESCLUSA", 1, "ACCIONAMIENTO VALVULA ESCLUSA", 1));
	}
	
	public Confirmacion(Ot ot_relacionada){
			this.ot_relacionada=ot_relacionada;
	}

	public Ot getOt_relacionada() {
		return ot_relacionada;
	}

	public void setOt_relacionada(Ot ot_relacionada) {
		this.ot_relacionada = ot_relacionada;
	}

	public List<Ot_aperturas> getAperturas_calzada() {
		return aperturas_calzada;
	}
	

	public void setAperturas_calzada(List<Ot_aperturas> aperturas_calzada) {
		this.aperturas_calzada = aperturas_calzada;
	}

	public List<Ot_aperturas> getAperturas_vereda() {
		return aperturas_vereda;
	}

	public void setAperturas_vereda(List<Ot_aperturas> aperturas_vereda) {
		this.aperturas_vereda = aperturas_vereda;
	}

	public List<Operario> getOperarios() {
		return operarios;
	}

	public void setOperarios(List<Operario> operarios) {
		this.operarios = operarios;
	}

	public List<Serviciosxmotivo> getTrabajos_ejecutados() {
		return trabajos_ejecutados;
	}

	public void setTrabajos_ejecutados(List<Serviciosxmotivo> trabajos_ejecutados) {
		this.trabajos_ejecutados = trabajos_ejecutados;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public void setMaterialesotList(List<Materialesot> data) {
		// TODO Auto-generated method stub
		this.materiales = data;
	}

	public List<Materialesot> getMateriales() {
		return materiales;
	}

	public void borrar_operario(int id) {
		// TODO Auto-generated method stub
		ListIterator<Operario> it = operarios.listIterator();
		
		while(it.hasNext()){			
			if(((Operario)it.next()).getId()==id){
				it.remove();
			}
				
		}
	}

	public void borrar_trabajos_ejecutados(int id) {
		// TODO Auto-generated method stub
		ListIterator<Serviciosxmotivo> it = trabajos_ejecutados.listIterator();
		while(it.hasNext()){			
			if(((Serviciosxmotivo)it.next()).getId_servicio()==id){
				it.remove();
			}
				
		}
		
	}

	public void borrar_equipo(int id) {
		// TODO Auto-generated method stub
		ListIterator<Equipo> it = equipos.listIterator();
		while(it.hasNext()){			
			if(((Equipo)it.next()).getId()==id){
				it.remove();
			}
				
		}
	}

	public void borrar_materiales(int id) {
		// TODO Auto-generated method stub
		ListIterator<Materialesot> it = materiales.listIterator();
		while(it.hasNext()){			
			if(((Materialesot)it.next()).getId()==id){
				it.remove();
			}
				
		}
	}


	public long getId_res() {
		return id_res;
	}

	public void setId_res(long id_res) {
		this.id_res = id_res;
	}

	public List<Derivacionesot> getDerivacionesot() {
		return derivacionesot;
	}

	public void setDerivacionesot(List<Derivacionesot> derivacionesot) {
		this.derivacionesot = derivacionesot;
	}

	public void borrar_derivaciones_ot(int id) {
		// TODO Auto-generated method stub
		ListIterator<Derivacionesot> it = derivacionesot.listIterator();
		while(it.hasNext()){			
			if(((Derivacionesot)it.next()).getId_mot_prox()==id){
				it.remove();
			}
				
		}
	}
	//aperturas_calzada
	public void borrar_aperturas_vereda(int id) {
		// TODO Auto-generated method stub
		ListIterator<Ot_aperturas> it = aperturas_vereda.listIterator();
		while(it.hasNext()){			
			if(((Ot_aperturas)it.next()).get_id()==id){
				it.remove();
			}
				
		}
	}
	public void borrar_aperturas_calzada(int id) {
		// TODO Auto-generated method stub
		ListIterator<Ot_aperturas> it = aperturas_calzada.listIterator();
		while(it.hasNext()){			
			if(((Ot_aperturas)it.next()).get_id()==id){
				it.remove();
			}
				
		}
	}

	public String getNro_ot() {
		
		return String.valueOf(ot_relacionada.getNro_ot());
	}

	public void setNro_ot(String nro_ot) {
		this.nro_ot = nro_ot;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getEnviado() {
		return enviado;
	}

	public void setEnviado(int enviado) {
		this.enviado = enviado;
	}
	
	
	
}
