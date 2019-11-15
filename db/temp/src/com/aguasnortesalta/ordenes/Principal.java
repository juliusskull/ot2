package com.aguasnortesalta.ordenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aguasnortesalta.ordenes.adapter.EditorListAdapter;
import com.aguasnortesalta.ordenes.adapter.UsuarioListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.model.EditorList;
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
import com.google.gson.Gson;

import android.widget.AdapterView.OnItemClickListener;

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

public class Principal extends Fragment{
	private static final String ARG_SECTION_NUMBER = "section_number";
	protected static final String NOTIFICACIONES = null;
	private Gson gson = new Gson();
	private ListView lista;	
	private EditorListAdapter adapter2;
	protected EditorList editorlist;
	private String url;
	
	private static OnListaSelect onEventListener;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.ppal_list, container,
				false);
		return rootView;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);		 
	        url = Configuracion.get_Url_Usuarios();
	        requestEditor();
	       
	        
	 }

	 public void verLista(){
		 adapter2=new EditorListAdapter(getActivity(),R.layout.usuario_list_item,  editorlist.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
						
						Principal.onEventListener.onClickList(1);
					
				}

			
			});   
	 } 
	 
		
		public void requestEditor() {      
		    Util.Log("Actualizando....");	    
		    Util.Log("API=>"+Configuracion.getToken(getActivity()));
		    
		    RequestQueue queue = Volley.newRequestQueue(getActivity());
		    String url = Configuracion.get_Url_Editor();
		    Util.Log("url=>"+url);
		    Util.Log("url-api=>"+Configuracion.getToken(getActivity()));
		    
		    StringRequest postRequest = new StringRequest(Request.Method.GET, url, 
		        new Response.Listener<String>() 
		        {
		            @Override
		            public void onResponse(String response) {
		                // response
		                Util.Log("response=>"+response);		              
		                editorlist= gson.fromJson(response,EditorList.class);
		                Util.Log("cant usuario=>"+editorlist.data.size());		                
		                verLista();        
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
		              /*  params.put("Authorization", Configuracion.getToken(getActivity()));
		                params.put("Usuario", "1");	        
		                */        
		                return params;  
		        }	        
		        
		    };	
		    queue.add(postRequest);	 

		}
		
		public static Principal newInstance(int sectionNumber) {
			Principal fragment = new Principal();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);			
			return fragment;
		}
	
				
		public  interface OnListaSelect  {
		    // you can define any parameter as per your requirement
			void onClickList(int  er);
		}

		public OnListaSelect getOnEventListener() {
			return onEventListener;
		}
		public void setOnEventListener(OnListaSelect onEventListener) {
			this.onEventListener = onEventListener;
		}
		public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);
			
			try {
				adapter2.filter(arg0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Util.Log("Error=>"+e.getMessage());
			}
			
		}
	
	
		
	
}
