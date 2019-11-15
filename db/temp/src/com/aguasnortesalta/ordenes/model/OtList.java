package com.aguasnortesalta.ordenes.model;

import java.util.ArrayList;
import java.util.List;

import com.aguasnortesalta.ordenes.utils.Util;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OtList extends BaseList{
	public static class OtWEB {
		
		  String id;	
		  String nro_ot;		  
		  String id_loc;		  
		  String id_bar;		  
		  String barrio;		  
		  String id_cal;		  
		  String calle;		  
		  String altura;		  
		  String id_motivo;
		  String motivo;
		  String cod_empleado_asig;		   
		  String nombre_empleado_asig;
		  String cod_cuadrilla_asig;
		  String fchalta;
		  String lat;
		  String lng;
		  String prioridad;
		  String id_seg;
		  String nro_sec;
		  String id_txc;
		  String id_inm;
		  String nro_form;
		  String fchregistracion;
		  String observacion;
		  String geren;

		public Ot getOt(){
			//Util.Log("idSeg="+id_seg);
		
			
			return new Ot(  
					   Integer.parseInt( id),
					   Integer.parseInt( nro_ot),
					   Integer.parseInt( id_loc),
					   /*Integer.parseInt( id_bar),*/0,
					    barrio,
					   /*Integer.parseInt( id_cal),*/0,
					    calle,
					    altura,
					   Integer.parseInt( id_motivo),
					    motivo,
					   Integer.parseInt( cod_empleado_asig),
					    nombre_empleado_asig,
					   Integer.parseInt( cod_cuadrilla_asig),
					   fchalta,
					    lat,
					    lng,
					   Integer.parseInt( (String) (!prioridad.equals("null")?prioridad:'0' )),
					  /* Integer.parseInt( (String) (id_seg!=null?id_seg:'0')),*/1,
					  /* Integer.parseInt( (String) (nro_sec!=null?nro_sec:'0') ),*/1,
					   /*Integer.parseInt( (String) (id_txc!=null?id_txc:'0') ),*/1,
					   /*Integer.parseInt( (String) (id_inm!=null?id_inm:'0') ),*/1,
					   /*Integer.parseInt( (String) (nro_form!=null?nro_form:'0') ),*/1,
					   fchregistracion,
					    observacion,
					    geren);
		}
		
	}
	public List<OtWEB> data= null;
	public List<Ot> datos= new ArrayList<Ot>();
	
	public void actualizar( ) {
		// TODO Auto-generated method stub
		for(int i=0;i<data.size();i++){
			
			try {
				datos.add(data.get(i).getOt());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Util.Log("Error ot=>ot("+i+")");
			//	e.printStackTrace();
			}
			
		}
		
	} ;
	
}
