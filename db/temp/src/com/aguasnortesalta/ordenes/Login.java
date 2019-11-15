package com.aguasnortesalta.ordenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.EditorList;
import com.aguasnortesalta.ordenes.model.OtList;
import com.aguasnortesalta.ordenes.model.UsuarioList;
import com.aguasnortesalta.ordenes.model.geometrias.GeoList;
import com.aguasnortesalta.ordenes.model.geometrias2.Geometrias2;
import com.aguasnortesalta.ordenes.sync.SincronizarService;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Login extends Activity
{
	
	private static final int requestCode_main_activity = 1002;

	public ProgressDialog progress;

	private ImageView img;
	private Bitmap p_img;
	private File file;
	private TextView txt_usuario;
	private TextView txt_password;
	private Gson gson = new Gson();
	private DatabaseManager dm;
	
	/* (non-Javadoc)
	 * @see com.chatt.custom.CustomActivity#onCreate(android.os.Bundle)
	 */

	private Button btn_login;

	private Button btn_salir;

	private boolean isConnected;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
				
		txt_usuario=(TextView)findViewById(R.id.user);
		txt_password=(TextView)findViewById(R.id.pwd);
		
		txt_usuario.setText("");
		txt_password.setText("");
		btn_login=(Button)findViewById(R.id.btnLogin);
		btn_salir=(Button)findViewById(R.id.btnSalir);
		Util.Log("=>login");
		dm= new DatabaseManager(this);
		ConnectivityManager cm =
		        (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
		
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				requestLoguin();
			}
		});
		btn_salir.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Login.this.finish();
				}
			});
			
		//requestLoguin();
	}

	/* (non-Javadoc)
	 * @see com.chatt.custom.CustomActivity#onClick(android.view.View)
	 */

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 10 && resultCode == RESULT_OK)
			finish();
		
		
		
		String session=Util.SpGet(Login.this, Configuracion.BIBLIOTECA, Configuracion.SESSION, "");
		if(session.equals(Configuracion.FIN_DE_DIA)){
			dm.add_fin_de_dia();
			Intent intent = new Intent(this, SincronizarService.class);
			startService(intent);
		}
	}
	
	
	protected void volver() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivityForResult(intent, requestCode_main_activity);
	}

	public byte[] _getBody() throws AuthFailureError {
		JSONObject jsonObject = new JSONObject();
        String body = null;
        try {         	
            jsonObject.put("usuario", txt_usuario.getText().toString());
            jsonObject.put("password", txt_password.getText().toString());        	
            body = jsonObject.toString();
            Util.Log("body=>"+body);
            
        } catch (JSONException e){
            // TODO Auto-generated catch block
        	Log.d("ERROR","error-Body(1) => "+e.toString());
        }

        try{
            return body.toString().getBytes("utf-8");
        }
        catch (UnsupportedEncodingException e){
            // TODO Auto-generated catch block
            //e.printStackTrace();
            Log.d("ERROR","error-Body(2) => "+e.toString());
        }
        return null;

	}
	public boolean loguin_sin_conexion(){
		 
		 String usuario=Util.SpGet(this,   Configuracion.BIBLIOTECA, Configuracion.USUARIO);
    	 String password=Util.SpGet(this,  Configuracion.BIBLIOTECA, Configuracion.PASSWOR);
    	 if(usuario.equals(txt_usuario.getText().toString())){
    		 if(password.equals(txt_password.getText().toString())){
    			 ini_dia();
    			 volver();
    			
	    	 }else{
	    		 Utils.showDialog(Login.this,"Password incorrecto" );
	    		 return false;
	    	 }
    	 }else{
    		 
    		 Utils.showDialog(Login.this,"Usuario incorrecto" );
    		 return false;
    	 }
    	 return true;
	}
	public boolean requestUsuario() {
		 RequestQueue queue = Volley.newRequestQueue(this);

		 
		 if(!isConnected){		    	
		     return loguin_sin_conexion();
		 }
		 
		 progress= ProgressDialog.show(Login.this, null,getString(R.string.alert_wait));	    
		 Util.SpSet(Login.this, Configuracion.BIBLIOTECA,
					Configuracion.USUARIO, txt_usuario.getText().toString());
		 Util.Log("URL=>(GERENCIA)"+Configuracion.get_Url_Usuarios(Configuracion.get_usuario_actual(Login.this)));
		 StringRequest postRequest = new StringRequest(Request.Method.GET, Configuracion.get_Url_Usuarios(Configuracion.get_usuario_actual(Login.this)), 
			        new Response.Listener<String>(){
						@Override
							public void onResponse(String response) {
								Util.Log("response usuario=>" + response);
								progress.dismiss();
								UsuarioList list= gson.fromJson(response,UsuarioList.class);						
								Util.SpSet(Login.this, Configuracion.BIBLIOTECA, Configuracion.GEREN,list.data.get(0).geren );
								
								volver();
								
								

							}						
			        }, 
			        new Response.ErrorListener() 
			        {
			            @Override
			            public void onErrorResponse(VolleyError error) {
			                // TODO Auto-generated method stub
			            	progress.dismiss();
			        		com.aguasnortesalta.ordenes.utils.Utils.showDialog(Login.this,"Error  conexion con el servidor al traer la gerencia");             	                
			    		
			            }	            
			        }
			    ) {     
			    	 @Override
			         protected Map<String,String> getParams(){
			             Map<String,String> params = new HashMap<String, String>();
			             return params;
			         }	    
			        @Override
			        public Map<String, String> getHeaders() throws AuthFailureError { 
			                Map<String, String>  params = new HashMap<String, String>();  
			                params.put("Content-Type", "application/json");	               
			                return params;  
			        }
			        @Override
			        public String getBodyContentType() {
			            return "application/json";
			        }
					@Override
					public byte[] getBody() throws AuthFailureError {
						// TODO Auto-generated method stub
						return _getBody();
					}
			        
			    };
			    int socketTimeout = 30000;//30 seconds - change to what you want
			    postRequest.setRetryPolicy(new DefaultRetryPolicy(
			    		socketTimeout, 
				            DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
				            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));  
			    queue.add(postRequest);
		 
		 return true; 
		
	}
	public void ini_dia(){
		String session=Util.SpGet(Login.this, Configuracion.BIBLIOTECA, Configuracion.SESSION, "");
		
		if(!session.equals(Configuracion.INICIO_DE_DIA)){
			Util.SpSet(Login.this, Configuracion.BIBLIOTECA,
				Configuracion.SESSION,Configuracion.INICIO_DE_DIA);
			
			Util.SpSet(Login.this, Configuracion.BIBLIOTECA,
					Configuracion.SESSION_FECHA,Util.getFechaActualFormat()+" "+Util.getHoraActualFormat());
			dm.add_ini_de_dia();
		}
		
	}
	public boolean requestLoguin() {
		
	    RequestQueue queue = Volley.newRequestQueue(this);
	    final String url = Configuracion.getAuthorizationLoguin();
	    Util.Log("url-=>"+url);	
	    if(!isConnected){	    	
	    	com.aguasnortesalta.ordenes.utils.Utils.showDialog(Login.this,"Conexion Offline");    	                
						
	    	return loguin_sin_conexion();
	    }
	
	    try {
			progress= ProgressDialog.show(Login.this, null,	getString(R.string.alert_wait));	    
			  
			StringRequest postRequest = new StringRequest(Request.Method.POST, url, 
			    new Response.Listener<String>(){
					@Override
						public void onResponse(String response) {
							Util.Log("response=>(login)" + response);
							progress.dismiss();		
														
							
							Util.SpSet(Login.this, Configuracion.BIBLIOTECA,
									Configuracion.TOKEN, response.replaceAll("\"", ""));
							
							String usuario_anterior= Util.SpGet(Login.this, Configuracion.BIBLIOTECA, Configuracion.USUARIO, "");
							
							Util.SpSet(Login.this, Configuracion.BIBLIOTECA,
									Configuracion.USUARIO_ANTERIOR, usuario_anterior);
							
							Util.SpSet(Login.this, Configuracion.BIBLIOTECA,
									Configuracion.USUARIO, txt_usuario.getText().toString());
							
							Util.SpSet(Login.this, Configuracion.BIBLIOTECA,
									Configuracion.PASSWOR, txt_password.getText().toString());
							Util.Log(Login.this, "Bienvenido: inicio nuevo");
							ini_dia();
							borrar_anterior();
							Util.Log("=>login -fin");	
							
							
							if(!usuario_anterior.equals(txt_usuario.getText().toString())){
								Util.SpSet(Login.this, Configuracion.BIBLIOTECA,
										Configuracion.ACTUALIZACION_OBLIGATORIA, "true");
							}
							
							requestUsuario();

						}

					
			    }, 
			    new Response.ErrorListener() 
			    {
			        @Override
			        public void onErrorResponse(VolleyError error) {
			            // TODO Auto-generated method stub
			        	progress.dismiss();
			    		com.aguasnortesalta.ordenes.utils.Utils.showDialog(Login.this,"Error  conexion con el servidor.  Se loguea de forma offline:"+error.toString());
			    		loguin_sin_conexion();
					
			        }	            
			    }
			) {     
				 @Override
			     protected Map<String,String> getParams(){
			         Map<String,String> params = new HashMap<String, String>();
			         return params;
			     }	    
			    @Override
			    public Map<String, String> getHeaders() throws AuthFailureError { 
			            Map<String, String>  params = new HashMap<String, String>();  
			            params.put("Content-Type", "application/json");	               
			            return params;  
			    }
			    @Override
			    public String getBodyContentType() {
			        return "application/json";
			    }
				@Override
				public byte[] getBody() throws AuthFailureError {
					// TODO Auto-generated method stub
					return _getBody();
				}
			    
			};
			int socketTimeout = 30000;//30 seconds - change to what you want
			postRequest.setRetryPolicy(new DefaultRetryPolicy(
					socketTimeout, 
			            DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
			            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));  
			queue.add(postRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			return loguin_sin_conexion();
		}
	    return true;

	}
	
	   public static String AssetJSONFile (String filename, Context context) throws IOException {
	        AssetManager manager = context.getAssets();
	        InputStream file = manager.open(filename);
	        byte[] formArray = new byte[file.available()];
	        file.read(formArray);
	        file.close();         
	        
	        return new String(formArray, "UTF-8");
	    }
	   public static StringBuilder AssetJSONFile2 (String filename, Context context) throws IOException {
	        AssetManager manager = context.getAssets();
	        InputStream file = manager.open(filename);
	        byte[] formArray = new byte[file.available()];
	        file.read(formArray);
	        file.close();
	        StringBuilder str=null;
	        str.append(formArray);
	        return str;
	    }

	protected void borrar_anterior() {
		// TODO Auto-generated method stub
		
	}

	 
	 public boolean isConnectingToInternet(){
	        ConnectivityManager connectivity = (ConnectivityManager) Login.this.getSystemService(Context.CONNECTIVITY_SERVICE);
	          if (connectivity != null)
	          {
	              NetworkInfo[] info = connectivity.getAllNetworkInfo();
	              if (info != null)
	                  for (int i = 0; i < info.length; i++)
	                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
	                      {
	                          try
	                            {
	                                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
	                                urlc.setRequestProperty("User-Agent", "Test");
	                                urlc.setRequestProperty("Connection", "close");
	                                urlc.setConnectTimeout(500); //choose your own timeframe
	                                urlc.setReadTimeout(500); //choose your own timeframe
	                                urlc.connect();
	                                int networkcode2 = urlc.getResponseCode();
	                                return (urlc.getResponseCode() == 200);
	                            } catch (IOException e)
	                            {
	                                return (false);  //connectivity exists, but no internet.
	                            }
	                      }

	          }
	          return false;
	    }
		public boolean isInternetAvailable() {
		    try {
		        InetAddress ipAddr = InetAddress.getByName("google.com"); 
		        //You can replace it with your name
		            return !ipAddr.equals("");

		        } catch (Exception e) {
		            return false;
		    }
		}
		 public static boolean isConnected(Context context) {
		        ConnectivityManager cm = (ConnectivityManager)context
		                .getSystemService(Context.CONNECTIVITY_SERVICE);

		    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		    if (activeNetwork != null && activeNetwork.isConnected()) {
		        try {
		            URL url = new URL("http://www.google.com/");
		            HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
		            urlc.setRequestProperty("User-Agent", "test");
		            urlc.setRequestProperty("Connection", "close");
		            urlc.setConnectTimeout(1000); // mTimeout is in seconds
		            urlc.connect();
		            if (urlc.getResponseCode() == 200) {
		                return true;
		            } else {
		                return false;
		            }
		        } catch (IOException e) {
		            Log.i("warning", "Error checking internet connection", e);
		            return false;
		        }
		    }

		    return false;

		}
		 public Boolean isOnline() {
			    try {
			        Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
			        int returnVal = p1.waitFor();
			        boolean reachable = (returnVal==0);
			        return reachable;
			    } catch (Exception e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
			    return false;
		}
}
