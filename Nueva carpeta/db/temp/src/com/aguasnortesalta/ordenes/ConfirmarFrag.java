package com.aguasnortesalta.ordenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Confirmacion;
import com.aguasnortesalta.ordenes.model.OperarioList;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.OtList;
import com.aguasnortesalta.ordenes.model.Ot_aperturas_superficie;
import com.aguasnortesalta.ordenes.model.Respuestasot;
import com.aguasnortesalta.ordenes.model.Serviciosxmotivo;
import com.aguasnortesalta.ordenes.model.ServiciosxmotivoList;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


public class ConfirmarFrag extends Fragment{
	private static final String ARG_SECTION_NUMBER = "";
	private LinearLayout ly;
	private ServiciosxmotivoList  motivos;
	private ImageButton btn_icon_modificar;

	public final static int OPCIONES_TRABAJOS_EJECUTADOS=0;	
	public final static int OPCIONES_APERTURA_DE_CALZADA=1;	
	public final static int OPCIONES_MANO_DE_OBRA=2;
	public static final int OPCIONES_EQUIPOS = 3;
	public static final int OPCIONES_MATERIALES = 4;	
	public static final int OPCIONES_DERIVACIONES_OT = 5;
	public static final int OPCIONES_RESPUESTAS = 6;
	public static final int OPCIONES_SACAR_FOTO = 7;
	
	private static OnOtClickOpciones onOtClickOpciones;
	private Confirmacion confirmar;
	private DatabaseManager dm;
	private ProgressDialog progress;
	/*preguntar porque las respuestas se cerraron*/
	private String[] opciones_array=new String[]{
			"Trabajos Ejecutados"
			,"Aperturas Calzada/Vereda"
			,"Mano de Obra"
			,"Movilidad/Equipos Utilizados"
			,"Materiales"
			,"Tipo de Finalizacion"
			,"Tipo de Respuesta"
			,"Sacar Foto"
			
		};
	private Ot_aperturas_superficie[] superficies;
	//private String url=Configuracion.get_Url_Confirmar();
	private Ot ot_actual;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.confirmar, container,
				false);
		return rootView;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	       super.onActivityCreated(state);
	       dm=new  DatabaseManager(getActivity(),"CONFIRMAR");
	       ly=(LinearLayout)getActivity().findViewById(R.id.ly_trabajos_ejecutados);
	       btn_icon_modificar=(ImageButton)getActivity().findViewById(R.id.btn_icon_modificar);
	     
	       confirmar= dm.getObjectConfirmacion();
	       if(dm.RESPUESTA_ULTIMA_GET(getActivity())<=0){
	    	   onOtClickOpciones.onClickOpcion(OPCIONES_RESPUESTAS);   
	       }  
	       /*
	       if(confirmar.getId_res()==0){
	    	 //  opciones_click(OPCIONES_RESPUESTAS);
	       }	
	       */
	       create_componentes();
	       
	       btn_icon_modificar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ver_opciones("Modificar");
			}
		});
	       
	 }
	private void borrar_items(){
		//ly.removeView(v);
		ly.removeAllViews(); 
		create_componentes();
	} 
	private void create_componentes() {
		// TODO Auto-generated method stub
		InterfaceDinamica interfaceDinamica = new InterfaceDinamica(getActivity());
		
		try {
			superficies=dm.getOT_Aperturas_superficieAll();		
			Util.Log("superficies=>"+superficies.length);
			ly.addView(interfaceDinamica.get_titulo_seccion("Nro Ord. Trabajo "+confirmar.getOt_relacionada().getTitulo()));	
			ly.addView(interfaceDinamica.get_espacio_en_blanco());
			ly.addView(interfaceDinamica.get_linea_secciones());	
			ly.addView(interfaceDinamica.get_titulo_seccion("Trabajos Ejecutados:"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("error=>"+e.getMessage());
		}
		
		
		for(int i=0;i<confirmar.getTrabajos_ejecutados().size();i++){
			CheckBox chk =interfaceDinamica.get_check_box(confirmar.getTrabajos_ejecutados().get(i).getId_servicio() + "-"+confirmar.getTrabajos_ejecutados().get(i).getDesc_servicio());
			chk.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					String s=(String) arg0.getText();
					int c=s.indexOf("-");
					if(c>0){
						//Utils.showDialog(getActivity(), "Esta por eliminar este item "+arg0.getText() );
						confirmar.borrar_trabajos_ejecutados(Integer.parseInt(s.substring(0, c)));
						borrar_items();
					}
					
				}});
			ly.addView(chk);

		}
				
		ly.addView(interfaceDinamica.get_espacio_en_blanco());
		ly.addView(interfaceDinamica.get_linea_secciones());	
		ly.addView(interfaceDinamica.get_titulo_seccion("Mano de Obra:"));
		
		for(int i=0;i<confirmar.getOperarios().size();i++){		
			 CheckBox chk = interfaceDinamica.get_check_box(confirmar.getOperarios().get(i).getId()+"-"+confirmar.getOperarios().get(i).getNombre());
			 chk.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
					String s=(String) arg0.getText();
					int c=s.indexOf("-");
					if(c>0){
						//Utils.showDialog(getActivity(), "Esta por eliminar este item "+arg0.getText() );
						confirmar.borrar_operario(Integer.parseInt(s.substring(0, c)));
						//dm.delete_operario(Integer.parseInt(s.substring(0, c)));
						//confirmar= dm.getObjectConfirmacion();
						OperarioList op=new OperarioList();
						op.data=confirmar.getOperarios();
						dm.setOperariosActuales(op);
						confirmar= dm.getObjectConfirmacion();
						borrar_items();
					}
				}});
			 
			 ly.addView(chk);
		}
		
		
		ly.addView(interfaceDinamica.get_espacio_en_blanco());
		ly.addView(interfaceDinamica.get_linea_secciones());	
		ly.addView(interfaceDinamica.get_titulo_seccion("Movilidad/Equipos:"));
		
		for(int i=0;i<confirmar.getEquipos().size();i++){	
			CheckBox chk = interfaceDinamica.get_check_box(confirmar.getEquipos().get(i).getId()+"-"+ confirmar.getEquipos().get(i).getDescripcion());
			chk.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
						// TODO Auto-generated method stub
						
						String s=(String) arg0.getText();
						int c=s.indexOf("-");
						if(c>0){
							//Utils.showDialog(getActivity(), "Esta por eliminar este item "+arg0.getText() );
							confirmar.borrar_equipo(Integer.parseInt(s.substring(0, c)));
							borrar_items();
						}
					}});
			ly.addView(chk);
		}
	
		ly.addView(interfaceDinamica.get_espacio_en_blanco());	
		ly.addView(interfaceDinamica.get_linea_secciones());	
		ly.addView(interfaceDinamica.get_titulo_seccion("Aperturas:"));
		for(int i=0;i<confirmar.getAperturas_calzada().size();i++){
			if(confirmar.getAperturas_calzada().get(i).getTipo_apertura().contains("C")){				
				ly.addView(interfaceDinamica.get_label("Tipo", "Calzada"));
			}else{
				ly.addView(interfaceDinamica.get_label("Tipo", "Vereda"));
			}
			ly.addView(interfaceDinamica.get_label("Ancho"      , confirmar.getAperturas_calzada().get(i).getAncho()));						
			ly.addView(interfaceDinamica.get_label("Largo"      , confirmar.getAperturas_calzada().get(i).getLargo()));
			ly.addView(interfaceDinamica.get_label("Profundidad", confirmar.getAperturas_calzada().get(i).getProfundidad()));						
			ly.addView(interfaceDinamica.get_label("Material", superficies[(int)confirmar.getAperturas_calzada().get(i).getId_tipo_material()].getDescripcion()));			
						
			CheckBox chk = interfaceDinamica.get_check_box(confirmar.getAperturas_calzada().get(i).get_id()+"-N");
			chk.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub					
					String s=(String) arg0.getText();
					int c=s.indexOf("-");
					
					if(c>0){						
						//confirmar.borrar_aperturas_calzada(Integer.parseInt(s.substring(0, c)));						
						//Utils.showDialog(getActivity(),"Borrar:"+Integer.parseInt(s.substring(0, c)) );
						dm.delete_apertura(Integer.parseInt(s.substring(0, c)));
						confirmar= dm.getObjectConfirmacion();
						//borrar_el_item(arg0.getId());
						borrar_items();
					}
				}
			});
			ly.addView(chk);
			ly.addView(interfaceDinamica.get_linea_item());	
		}
		
		
		ly.addView(interfaceDinamica.get_espacio_en_blanco());	
		ly.addView(interfaceDinamica.get_linea_secciones());	
		ly.addView(interfaceDinamica.get_titulo_seccion("Materiales:"));
		for(int i=0;i<confirmar.getMateriales().size();i++){			
			CheckBox chk = interfaceDinamica.get_check_box(confirmar.getMateriales().get(i).getId()+"-"+ confirmar.getMateriales().get(i).getMaterialesot());
			chk.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					
					String s=(String) arg0.getText();
					int c=s.indexOf("-");
					if(c>0){
						confirmar.borrar_materiales(Integer.parseInt(s.substring(0, c)));
						borrar_items();
					}
				}});
			ly.addView(chk);
			ly.addView(interfaceDinamica.get_label("Cantidad",confirmar.getMateriales().get(i).getCantidad() ));
		
		}
		ly.addView(interfaceDinamica.get_espacio_en_blanco());	
		ly.addView(interfaceDinamica.get_linea_secciones());	
		ly.addView(interfaceDinamica.get_titulo_seccion("Respuesta:"));
		Util.Log("id res=>"+confirmar.getId_res());
		if(confirmar.getId_res()!=0){
			Respuestasot respuesta=dm.getRespuestaOne(confirmar.getId_res());
			if(respuesta!=null){
				CheckBox chk = interfaceDinamica.get_check_box(respuesta.getDesc_res());
			    chk.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
	
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					dm.RESPUESTA_ULTIMA_SET(getActivity(),0);
					
				}});
			    ly.addView(chk);
			}
			
		}
		
		ly.addView(interfaceDinamica.get_espacio_en_blanco());	
		ly.addView(interfaceDinamica.get_linea_secciones());	
		ly.addView(interfaceDinamica.get_titulo_seccion("Finalizacion/Proxima Orden Trabajo:"));
		
		for(int i=0;i<confirmar.getDerivacionesot().size();i++){
			CheckBox chk = interfaceDinamica.get_check_box(confirmar.getDerivacionesot().get(i).getId_mot_prox() +"-"+ confirmar.getDerivacionesot().get(i).getDesc_motivo());
			chk.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub					
					String s=(String) arg0.getText();
					int c=s.indexOf("-");
					if(c>0){
						confirmar.borrar_derivaciones_ot(Integer.parseInt(s.substring(0, c)));
						borrar_items();
					}
				}
			});
			ly.addView(chk);
			
		}
	
	}
	protected void borrar_el_item(int id) {
		// TODO Auto-generated method stub
		ly.removeViewAt(id);
		
	}
	public void confirmacion_guardar(){		
		dm.add_confirmacion(confirmar);
	    ot_actual=dm.get_ultima_ot();	   
		dm.Ot_finalizarAdd(ot_actual,  "", DatabaseManager.ESTADO_FINALIZAR,5);
		//request_confirmar_ot();
	}
	
	
	  
	  
	 /*
	public void request_confirmar_ot() {
		
	    Util.Log("Actualizando....");	    
	    Util.Log("API=>"+Configuracion.getToken(getActivity()));		    
	    RequestQueue queue = Volley.newRequestQueue(getActivity());		   
	    Util.Log("url=>"+url);
	    Util.Log("url-api=>"+Configuracion.getToken(getActivity()));
	    progress=new ProgressDialog(getActivity());
		progress.setMessage("Actualizando...");
		progress.show();
		
	    StringRequest postRequest = new StringRequest(Request.Method.POST, url, 
	        new Response.Listener<String>() 
	        {
	            @Override
	            public void onResponse(String response) {
	                // response
	                Util.Log("response=>"+response);               
	                progress.cancel();
	            }
	        }, 
	        new Response.ErrorListener() 
	        {
	            @Override
	            public void onErrorResponse(VolleyError error) {
	                // TODO Auto-generated method stub
	                Log.d("ERROR","error => "+error.toString());	              
	                //fin_barradeprogreso();
	                progress.cancel();
	            }
	        }
	        
	    ) { 
	    	 @Override
	         protected Map<String,String> getParams(){
	            	            
	             return dm.get_parameter_confirmacion(confirmar);
	         }
	        @Override
	        public Map<String, String> getHeaders() throws AuthFailureError {
	        	 //ba4d1a5f35f8cf5600a74fa4e80a6fff 
	        	
	               Map<String, String>  params = new HashMap<String, String>();  
	               params.put("Authorization", Configuracion.getToken(getActivity()));
	                      
	                return params;  
	        }
	        
	    };	
	    queue.add(postRequest);	 

	}
	
	*/

	public void ver_opciones(CharSequence titulo){
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity()
				,
		    android.R.layout.select_dialog_item,
		    android.R.id.text1,opciones_array
		    ){
		        public View getView(int position, View convertView, ViewGroup parent) {
		            //Use super class to create the View
		            View v = super.getView(position, convertView, parent);
		            TextView tv = (TextView)v.findViewById(android.R.id.text1);
		            tv.setText(opciones_array[position]);	
		            //Put the image on the TextView
		            //tv.setCompoundDrawablesWithIntrinsicBounds(opciones_array[position], 0, 0, 0);
		            //Add margin between image and text (support various screen densities)
		            int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
		            tv.setCompoundDrawablePadding(dp5);

		            return v;
		        }
		    };
		    new AlertDialog.Builder(getActivity())
		    .setTitle(titulo)
		    .setAdapter(adapter, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int item) {
		            //validar(item);
		        	opciones_click(item);
		        }
		    }).show();
	}
	
	protected void opciones_click(int item) {
		// TODO Auto-generated method stub
		onOtClickOpciones.onClickOpcion(item);
	}
	public  interface OnOtClickOpciones  {
	    // you can define any parameter as per your requirement
		void onClickOpcion( int opcion);
	}
	public OnOtClickOpciones getOnOtClickOpciones() {
		return onOtClickOpciones;
	}
	public void setOnOtClickOpciones(OnOtClickOpciones onOtClickOpciones) {
		this.onOtClickOpciones = onOtClickOpciones;
	}

	
}
