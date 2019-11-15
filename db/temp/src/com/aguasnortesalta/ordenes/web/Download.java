package com.aguasnortesalta.ordenes.web;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.model.OtList;
import com.aguasnortesalta.ordenes.utils.Util;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Download {
	public Context activity;
	public String url="";
	public Download(String url){
		this.url=url;
	}
	
	public void exec() {      
	    Util.Log("Actualizando....");	    
	    Util.Log("API=>"+Configuracion.getToken(getActivity()));		    
	    RequestQueue queue = Volley.newRequestQueue(getActivity());		   
	    Util.Log("url=>"+url);
	    Util.Log("url-api=>"+Configuracion.getToken(getActivity()));
	    
	    StringRequest postRequest = new StringRequest(Request.Method.GET, url, 
	        new Response.Listener<String>() 
	        {
	            @Override
	            public void onResponse(String response) {
	                // response
	                   
	            }
	        }, 
	        new Response.ErrorListener() 
	        {
	            @Override
	            public void onErrorResponse(VolleyError error) {
	                // TODO Auto-generated method stub
	                Log.d("ERROR","error => "+error.toString());	              
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
	public Context getActivity() {
		return activity;
	}
	public void setActivity(Context activity) {
		this.activity = activity;
	}
	

}
