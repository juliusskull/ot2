package com.aguasnortesalta.ordenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aguasnortesalta.ordenes.adapter.EditorListAdapter;
import com.aguasnortesalta.ordenes.adapter.OtListAdapter;
import com.aguasnortesalta.ordenes.adapter.UsuarioListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.ConfigWEB;
import com.aguasnortesalta.ordenes.model.EditorList;
import com.aguasnortesalta.ordenes.model.OperarioList;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.OtList;
import com.aguasnortesalta.ordenes.model.Sincronizar;
import com.aguasnortesalta.ordenes.model.Usuario;
import com.aguasnortesalta.ordenes.model.UsuarioList;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import android.widget.AdapterView.OnItemClickListener;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap.Config;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class OTListFrag extends Fragment{
	private static final String ARG_SECTION_NUMBER = "section_number";
	protected static final String NOTIFICACIONES = null;
	private Gson gson = new Gson();
	private ListView lista;	
	private OtListAdapter adapter2;
	protected OtList otlist;
	private String url;
	
	private static OnListaSelectOt onEventListener;
	private DatabaseManager dm;
	private ProgressDialog progress;
	NetworkInfo activeNetwork;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.ppal_list, container,
				false);
		return rootView;
	}
	 @Override
	 public void onActivityCreated(Bundle state) {
	       super.onActivityCreated(state);		
	       dm = new DatabaseManager(getActivity(),"OT-LIST");
	       otlist = new OtList();
	       url = Configuracion.get_Url_Ot(Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.USUARIO, ""));
	       Util.Log("Url OT=>"+url);
	       
	   
	       ultima_actualizacion();
	       int pantalla=Integer.parseInt(Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.ULTIMA_PANTALLA, "0"));
	       if(!Configuracion.OT_isExists(getActivity())){
	    	   if(pantalla!=MainActivity.PANTALLA_OT){
	    		   
	    		   String actualizacion_obligatoria=Util.SpGet(getActivity(), Configuracion.BIBLIOTECA,
										Configuracion.ACTUALIZACION_OBLIGATORIA, "true");
		    	   if(actualizacion_obligatoria.endsWith("true")){
		    		   	Util.SpSet(getActivity(), Configuracion.BIBLIOTECA,	Configuracion.ACTUALIZACION_OBLIGATORIA, "false");
		    		   	requestOT();
		    	   }else{
		    		    exec_lista();
		    	   }
		       }else{

		    	  
		    	   verLista();
		       }   
	       }
	           
	       
	 }
	 public void exec_lista(){
		 if(Util.isOnline(getActivity())){
        	 Util.Log("Url OT=>conexion true");        	 
        	 //Utils.showDialog(getActivity(), "Se descargarï¿½n las ot, esta accion puede durar varios minutos");
        	 if(dm.getOTList_cant()>0){
        		
				Utils.showDialog(
						getActivity(),
						"¿Quiere actualizar la OT?",
						"Si",
						"No",
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								requestOT();

							}
						}
						,
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								verLista();

							}
						});
        	 }else{
        		 requestOT();
        	 }
        	 
        }else{
        	 Util.Log("Url OT=>conexion false");
        	 otlist.datos=dm.getOTList();
        	 verLista();
        }
		 
	 }

	 public void verLista(){
		 otlist.datos=dm.getOTList();
		 adapter2=new OtListAdapter(getActivity(),R.layout.ot_list_item,  otlist.datos, dm.get_pto_actual());
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
						OTListFrag.onEventListener.onClickList(otlist.datos.get(position));

				}

			});
	 } 
	 
		
		public void requestOT() {
		    Util.Log("Actualizando....");	    
		    Util.Log("API=>"+Configuracion.getToken(getActivity()));
		    RequestQueue queue = Volley.newRequestQueue(getActivity());		   
		    Util.Log("url=>"+url);
		    Util.Log("url-api=>"+Configuracion.getToken(getActivity()));
		    progress=new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
		    progress.setTitle("Actualizando OT...");
		    progress.show();
		    StringRequest postRequest = new StringRequest(Request.Method.GET, url, 
		        new Response.Listener<String>() 
		        {
		            @Override
		            public void onResponse(String response) {
		                // response
		                Util.Log("response=>(url)"+response);		              
		                try {
							otlist= gson.fromJson(response,OtList.class);
						} catch (JsonSyntaxException e) {
							// TODO Auto-generated catch block
							Util.Log("error=>"+e.getMessage());
						} 
		                Util.Log("cant usuario=>"+otlist.data.size());	
		                otlist.actualizar();
		                dm.setOTaddList(otlist.datos);
		                progress.dismiss();
		                verLista();        
		            }
		        }, 
		        new Response.ErrorListener() 
		        {
		            @Override
		            public void onErrorResponse(VolleyError error) {
		                // TODO Auto-generated method stub
		                Log.d("ERROR","error(list) => "+error.toString());
		                Utils.showDialog(getActivity(), "Error:"+error.toString());
		                progress.dismiss();
		                //fin_barradeprogreso();
		            }
		        }
		        
		    ) { 	    	  
		        @Override
		        public Map<String, String> getHeaders() throws AuthFailureError {
		        	 //ba4d1a5f35f8cf5600a74fa4e80a6fff 
		        	
		               Map<String, String>  params = new HashMap<String, String>();  
		               params.put("Authorization", Configuracion.getToken(getActivity()));
		                /* params.put("Usuario", "1");	        
		                */        
		               return params;  
		        }	        
		        
		        
		    };	
		    queue.add(postRequest);	 

		}
		
		public static OTListFrag newInstance(int sectionNumber) {
			OTListFrag fragment = new OTListFrag();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);			
			return fragment;
		}
				
		public  interface OnListaSelectOt  {
		    // you can define any parameter as per your requirement
			void onClickList(Ot  er);
		}

		public OnListaSelectOt getOnEventListener() {
			return onEventListener;
		}
		public void setOnEventListener(OnListaSelectOt onEventListener) {
			this.onEventListener = onEventListener;
		}
		public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);
			
			try {
				adapter2.filter(arg0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		public void ultima_actualizacion(){
			RequestQueue queue = Volley.newRequestQueue(getActivity());	
		    Util.Log("log-get-Config=>"+Util.getDateNowFormat()+ "-"+ Configuracion.get_Url_config_db(Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.USUARIO, "")));
			StringRequest postRequest = new StringRequest(Request.Method.GET, Configuracion.get_Url_config_db(Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.USUARIO, "")), 
			        new Response.Listener<String>() 
			        {
			            @Override
			            public void onResponse(String response) {
			                // response
			            	
			                Util.Log("log-get-Config=>response=>"+response);
			                com.aguasnortesalta.ordenes.model.ConfigWEB config= gson.fromJson(response.replace("[", "").replace("]", ""),com.aguasnortesalta.ordenes.model.ConfigWEB.class);
			                Util.Log("log-get-Config=>response=>"+config.getVersion_db());
			                com.aguasnortesalta.ordenes.model.Config config1= dm.get_ultima_actualizacion();
			                if(config.getVersion_db()>config1.getVersion_db() ){
			                	Utils.showDialog(getActivity(), "Existe una nueva version de la base de datos de tramos y componentes");
			                }			               
			                DatabaseManager.SET_ULTIMA_CONFIG(getActivity(), config);
			            	
			                
			            }
			            
			        }, 
			        new Response.ErrorListener() 
			        {
			            @Override
			            public void onErrorResponse(VolleyError error) {
			                // TODO Auto-generated method stub
			                try {
								Log.d("ERROR","error => "+error.getMessage());	
								final String statusCode = String.valueOf(error.networkResponse.statusCode);
								Util.Log("error =>statusCode=> "+statusCode);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								Log.d("ERROR","error => "+e.getMessage());	
							}
			            }
			        }
			        
			    ) {
			    	 @Override
			         protected Map<String,String> getParams(){		            	            
			            
						return null; /*dm.get_parameter_sincronizacion(conf);*/
			         }	
			    		@Override
						public byte[] getBody() throws AuthFailureError {
							// TODO Auto-generated method stub
							return null;/*_getBody(conf,getActivity());*/
						}
				        
			        @Override
			        public Map<String, String> getHeaders() throws AuthFailureError {
			        	 //ba4d1a5f35f8cf5600a74fa4e80a6fff 
			        	//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
			        	
			               Map<String, String>  params = new HashMap<String, String>();  
			               params.put("Authorization", Configuracion.getToken(getActivity()));
			                /* params.put("Usuario", "1");	        
			                */        
			               return params;  
			        }	        
			        
			    };	
			    queue.add(postRequest);
			
		}
		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			//Util.Log("=>On Start ");
			verLista();
			
		}
		
}
