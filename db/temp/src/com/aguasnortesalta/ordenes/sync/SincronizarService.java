package com.aguasnortesalta.ordenes.sync;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Sincronizar;
import com.aguasnortesalta.ordenes.utils.Util;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class SincronizarService extends IntentService {

	private DatabaseManager dm;
	private List<Sincronizar> no_enviados;

	public SincronizarService() {
		super("sincronizando");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		dm=new  DatabaseManager(getApplicationContext());
		
		Util.Log("sincronizando=>----");
		Util.Log("sincronizando=>token:"+Configuracion.getToken(getApplicationContext()));
		
		Tarea_web tarea= new Tarea_web();
		tarea.execute("");
		
		
	}
	class Tarea_web extends  AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			no_enviados= dm.get_sin_sincronizar();
			Util.Log("sincronizando=>----cant="+no_enviados.size());	
			for (Sincronizar sincronizar : no_enviados) {
				Util.Log("sincronizando=>----"+sincronizar.getId());				
				enviar(sincronizar);
				Util.Log("sincronizando=>----ok");
			}
			return null;
		}
		
		
	}
	
	public void enviar(final Sincronizar sincro){
		RequestQueue queue = Volley.newRequestQueue(getApplicationContext());	
	    Util.Log("log-post=>"+Util.getDateNowFormat()+ "-"+ Configuracion.get_Url_Post());
		StringRequest postRequest = new StringRequest(Request.Method.POST, Configuracion.get_Url_Post(), 
		        new Response.Listener<String>() 
		        {
		            @Override
		            public void onResponse(String response) {
		                // response
		                Util.Log("response=>"+response);
		                dm.sincronizar_set_enviado(sincro);		                
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
						return _getBody(sincro,getApplication());
					}
			        
		        @Override
		        public Map<String, String> getHeaders() throws AuthFailureError {
		        	 //ba4d1a5f35f8cf5600a74fa4e80a6fff 
		        	//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
		        	
		               Map<String, String>  params = new HashMap<String, String>();  
		               params.put("Authorization", Configuracion.getToken(getApplicationContext()));
		                /* params.put("Usuario", "1");	        
		                */        
		               return params;  
		        }	        
		        
		    };	
		    queue.add(postRequest);
		
	}
	
	public  static byte[] _getBody(Sincronizar obj, Context getApplicationContext) throws AuthFailureError {
		JSONObject jsonObject = new JSONObject();
        String body = null;
        try {
        	 jsonObject.put("envio", obj.getId());
        	 jsonObject.put("tipo", obj.getTipo());        
        	 jsonObject.put("valor", obj.getValor());
        	 jsonObject.put("lat", obj.getLat());
        	 jsonObject.put("lng", obj.getLng());
        	 jsonObject.put("usuario", Util.SpGet(getApplicationContext, Configuracion.BIBLIOTECA, Configuracion.USUARIO,""));
        	 jsonObject.put("imei", Util.getIMEI(getApplicationContext));
        	 jsonObject.put("precision", obj.getPrecision());
        	 jsonObject.put("gps", obj.getGps());
        	 jsonObject.put("red", obj.getRed());
        	 jsonObject.put("version", Util.getAppVersion(getApplicationContext));
        	 jsonObject.put("aplicacion", Configuracion.APLICACION);   
        	         	
            body = jsonObject.toString();
        } catch (JSONException e){
            // TODO Auto-generated catch block
        	Log.d("ERROR","error-Body(1) => "+e.toString());
        	Util.Log("error=>sincronozar error-Body(1) => "+e.toString());
        }

        try{
            return body.toString().getBytes("utf-8");
         }catch (UnsupportedEncodingException e){
            // TODO Auto-generated catch block
            //e.printStackTrace();
            Log.d("ERROR","error-Body(2) => "+e.toString());
            Util.Log("error=>sincronozar error-Body(2) => "+e.toString());
        }
        return null;

	}
	

}
