package com.aguasnortesalta.ordenes.sync;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Archivo;
import com.aguasnortesalta.ordenes.model.Confirmacion;
import com.aguasnortesalta.ordenes.model.Sincronizar;
import com.aguasnortesalta.ordenes.sync.SincronizarService.Tarea_web;
import com.aguasnortesalta.ordenes.utils.Util;
import com.squareup.picasso.Picasso;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SincronizarFotosService extends IntentService{
	private DatabaseManager dm;
	private List<Archivo> no_enviados;
	ConnectivityManager cm;
	NetworkInfo activeNetwork;

	public SincronizarFotosService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	
	}
	public SincronizarFotosService() {
		super("sincronizando fotos");
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		no_enviados=dm.getArchivos();
		cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		activeNetwork = cm.getActiveNetworkInfo();
	
		Tarea_web tarea= new Tarea_web();
		tarea.execute("");
		
	}
	class Tarea_web extends  AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			Util.Log("sincronizando=>----cant="+no_enviados.size());	
			for (Archivo archivo : no_enviados) {
				Util.Log("sincronizando=>----"+archivo.get_id());
				
				try {
					String root = Environment.getExternalStorageDirectory().getAbsolutePath();
					String imageFolderPath = root + "/ot_img";
					
					//File image = new File(imageFolderPath, archivo.getArchivo()); 
					archivo.setNombre(root + "/ot_img/"+archivo.getArchivo());
					
					File file = new File(archivo.getArchivo());
					if(file.exists()){
						boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
						boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
						if(isConnected && isWiFi){
							ENVIAR(archivo,dm);
						}
					}else{
						dm.archivos_enviado(archivo);
						dm.add_confirmacion_foto(archivo.getArchivo(), "Error archivo borrado");
					}
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					Util.Log("error imagen "+ archivo.getArchivo());
				}
				
				ENVIAR(archivo,dm);
				Util.Log("sincronizando=>----ok");
			}
			return null;
		}
		
		
	}
	public static void ENVIAR(final Archivo fotos,DatabaseManager dm) {
		// TODO Auto-generated method stub
			Util.Log("Enviando archivo=>" + fotos.getArchivo());
			String ba1;
			
	        try {
	        	BitmapFactory.Options options = new BitmapFactory.Options();
	        	options.inSampleSize = 8;
	        	Bitmap bm = BitmapFactory.decodeFile(fotos.getArchivo(),options);
	        		        	
		        ByteArrayOutputStream bao = new ByteArrayOutputStream();
		        
		        bm.compress(Bitmap.CompressFormat.JPEG, 90, bao);
		        
		        byte[] ba = bao.toByteArray();
		        ba1 = Base64.encodeToString(ba, Base64.DEFAULT);  
		       
		        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		        
		        nameValuePairs.add(new BasicNameValuePair("base64", ba1));
		      
		        nameValuePairs.add(new BasicNameValuePair("ImageName", fotos.getNombre()));
		     
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(Configuracion.URL_EXPORT);
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            HttpResponse response = httpclient.execute(httppost);
	            String st = EntityUtils.toString(response.getEntity());
	            dm.archivos_enviado(fotos);
	            Util.Log("Enviando archivo fin=>" + fotos.getArchivo());
	            //mg.cambiar_enviado(fotos.getId());
	
	        } catch (Exception e) {
	            Log.v("log_tag", "Error in http connection " + e.toString());
	            Util.Log("Error Enviando archivo=>" + fotos.getArchivo()+ ", error="+e.toString());
	            
	        }
	
		
	}

}
