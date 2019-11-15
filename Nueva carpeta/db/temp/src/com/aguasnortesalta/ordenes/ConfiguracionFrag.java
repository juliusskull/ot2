package com.aguasnortesalta.ordenes;

import java.util.HashMap;
import java.util.Map;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Config;
import com.aguasnortesalta.ordenes.model.ConfigWEB;
import com.aguasnortesalta.ordenes.model.Sincronizar;
import com.aguasnortesalta.ordenes.sync.SincronizarService;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ConfiguracionFrag extends Fragment{
	private DatabaseManager dm;
	private TextView txt_version_db;
	private TextView txt_fch_db;
	private TextView txt_usuario_actual;
	private TextView txt_conexion;
	private ConnectivityManager cm;
	private NetworkInfo activeNetwork;
	private TextView txt_conexion_servidor;
	private Button btn_probar_conexion;
	private ProgressBar bt_progress;
	private boolean isConnected;
	private CheckBox chk_modo_seguro;
	private TextView txt_geometria;
	private ConfigWEB configweb;
	private TextView txt_foto_ini;
	private TextView txt_foto_fin;
	private TextView txt_metros_ini;
	private TextView txt_metros_fin;
	private TextView txt_intervalo_tiempo;
	private TextView txt_imei;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_configuracion, container,
				false);
		return rootView;
	}
	
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	        dm=new  DatabaseManager(getActivity(),"CONFIGURACION");
	        configweb= DatabaseManager.GET_ULTIMA_CONFIG(getActivity());
	        txt_version_db=(TextView)getActivity().findViewById(R.id.txt_version_db);
	        txt_fch_db=(TextView)getActivity().findViewById(R.id.txt_fch_actualizacion_db);
	        txt_usuario_actual=(TextView)getActivity().findViewById(R.id.txt_usuario_actual);
	        Config config=dm.get_ultima_actualizacion();
	        txt_version_db.setText(String.valueOf(config.getVersion_db()));
	        txt_fch_db.setText(config.getVersion_fch());
	        txt_usuario_actual.setText(Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.USUARIO));
	        txt_conexion_servidor=(TextView)getActivity().findViewById(R.id.txt_conexion_servidor);
	        txt_conexion=(TextView)getActivity().findViewById(R.id.txt_conexion);
	        txt_geometria=(TextView)getActivity().findViewById(R.id.txt_geometrias);
	        
	        txt_foto_ini=(TextView)getActivity().findViewById(R.id.txt_foto_ini);
	        txt_foto_fin=(TextView)getActivity().findViewById(R.id.txt_foto_fin);
	        txt_metros_ini=(TextView)getActivity().findViewById(R.id.txt_metros_ini);
	        txt_metros_fin=(TextView)getActivity().findViewById(R.id.txt_metros_fin);
	        txt_intervalo_tiempo=(TextView)getActivity().findViewById(R.id.txt_intervalo_tiempo);
	        	        
	        txt_foto_ini.setText(configweb.fotos_ini==1?"Activo":"Desactivado");
	        txt_foto_fin.setText(configweb.fotos_fin==1?"Activo":"Desactivado");
	        txt_metros_ini.setText(configweb.metros_ini==-1?"Desactivado":String.valueOf(configweb.metros_ini));
	        txt_metros_fin.setText(configweb.metros_fin==-1?"Desactivado":String.valueOf(configweb.metros_fin));		       
	        txt_intervalo_tiempo.setText(configweb.intervalo +" min");	        
	        btn_probar_conexion=(Button)getActivity().findViewById(R.id.btn_probar_conexion);
	        bt_progress=(ProgressBar)getActivity().findViewById(R.id.pb_loading_cnx);
	        bt_progress.setVisibility(ProgressBar.INVISIBLE);
	        txt_imei=(TextView)getActivity().findViewById(R.id.txt_imei);
	        
	        chk_modo_seguro=(CheckBox)getActivity().findViewById(R.id.chk_modo_seguro);
	      // configweb.modo_seguro
	        chk_modo_seguro.setChecked(configweb.modo_seguro==1?true:false);	 
	        
	        txt_imei.setText("IMEI:"+Util.getIMEI(getActivity()));
	        
	        String s=Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.MODO_SEGURO, Configuracion.MODO_SEGURO_INACTIVO);
	        if(s.equalsIgnoreCase(Configuracion.MODO_SEGURO_ACTIVO)){
	        	chk_modo_seguro.setChecked(true);
	        }
	    	cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			activeNetwork = cm.getActiveNetworkInfo();
			isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
			String cnx="";
			if(isConnected){
				cnx+="Online";
				boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
				if(isWiFi){
					cnx+="/WIFI";
				}else{
					cnx+="/RED";
				}
				
			}else{
				cnx+="Offline";
			}
			
			txt_conexion.setText(cnx);
			if(isConnected){
				//txt_conexion_servidor
				String json =  "{\"prueba\": \"prueba de conexion\"  }";	;	
				Sincronizar sincronizar = new Sincronizar("", Configuracion.ENVIO_FOTOS, json);	
				enviar(sincronizar);
			}else{
				txt_conexion_servidor.setText("No se pudo sincronizar con "+ Configuracion.server);
			}
			
			
			btn_probar_conexion.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(isConnected){
						//txt_conexion_servidor
						String json =  "{\"prueba\": \"prueba de conexion\"  }";	;	
						Sincronizar sincronizar = new Sincronizar("", Configuracion.ENVIO_FOTOS, json);	
						enviar(sincronizar);
					}else{
						txt_conexion_servidor.setText("No se pudo sincronizar con "+ Configuracion.server);
					}
				}
			});
			
			chk_modo_seguro.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					// TODO Auto-generated method stub
					/*
					if(arg1){
						DatabaseManager.SET_MODO_SEGURO(getActivity(),Configuracion.MODO_SEGURO_ACTIVO);
						Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.MODO_SEGURO, Configuracion.MODO_SEGURO_ACTIVO);
					}else{
						DatabaseManager.SET_MODO_SEGURO(getActivity(),Configuracion.MODO_SEGURO_INACTIVO);
						//Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.MODO_SEGURO, Configuracion.MODO_SEGURO_INACTIVO);

					}
					*/
					
				}});
			String geometrias_s="";
			for(int i=0;i<Configuracion.FILTROS_GEOMETRIAS.length;i++){
				geometrias_s+="<p><b>"+Configuracion.FILTROS_GEOMETRIAS[i].tipo+":</b> "+dm.get_cant_geometrias(Configuracion.FILTROS_GEOMETRIAS[i].id_tipo)+"</p>";
			}
			
			txt_geometria.setText(Html.fromHtml(geometrias_s));
	  }
	 
	 
	 public void enviar(final Sincronizar conf){
		 	btn_probar_conexion.setVisibility(Button.INVISIBLE);
		 	bt_progress.setVisibility(ProgressBar.VISIBLE);
			RequestQueue queue = Volley.newRequestQueue(getActivity());	
		    Util.Log("log-post=>"+Util.getDateNowFormat()+ "-"+ Configuracion.get_Url_Post());
			StringRequest postRequest = new StringRequest(Request.Method.POST, Configuracion.get_Url_Post(), 
			        new Response.Listener<String>() 
			        {
			            @Override
			            public void onResponse(String response) {
			                // response
			                Util.Log("response=>"+response);
			                //dm.sincronizar_set_enviado(conf);
			                txt_conexion_servidor.setText("Sincronizado con "+ Configuracion.server);
			            	btn_probar_conexion.setVisibility(Button.VISIBLE);
			    		 	bt_progress.setVisibility(ProgressBar.INVISIBLE);
			            }
			            
			        }, 
			        new Response.ErrorListener() 
			        {
			            @Override
			            public void onErrorResponse(VolleyError error) {
			                // TODO Auto-generated method stub
			                try {
								Log.d("ERROR","error => "+error.getMessage());	
								Log.d("ERROR","error => "+error.networkResponse);		
								Util.Log("error => "+error.toString());
								Util.Log("error => "+error.networkResponse);	                
								final String statusCode = String.valueOf(error.networkResponse.statusCode);
								Util.Log("error =>statusCode=> "+statusCode);
								txt_conexion_servidor.setText("No se pudo sincronizar con(1) "+ Configuracion.get_Url_Post());
								btn_probar_conexion.setVisibility(Button.VISIBLE);
								bt_progress.setVisibility(ProgressBar.INVISIBLE);
								Utils.showDialog(getActivity(),"Error"+ error.getMessage());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								Utils.showDialog(getActivity(), "Error(1):"+error.getMessage());
								e.printStackTrace();
							}
			            }
			        }
			        
			    ) { 
			    	 @Override
			         protected Map<String,String> getParams(){		            	            
			            
						return null; 
			         }
			    		@Override
						public byte[] getBody() throws AuthFailureError {
							// TODO Auto-generated method stub
							return SincronizarService._getBody(conf,getActivity());
						}
				        
			        @Override
			        public Map<String, String> getHeaders() throws AuthFailureError {	   
			        	
			               Map<String, String>  params = new HashMap<String, String>();  
			               params.put("Authorization", Configuracion.getToken(getActivity()));			                     
			               return params;  
			        }	        
			        
			    };	
			    queue.add(postRequest);
			
		}
}
