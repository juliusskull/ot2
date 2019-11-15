package com.aguasnortesalta.ordenes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.aguasnortesalta.ordenes.web.InputStreamVolleyRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivityDownload extends ActionBarActivity {
	String mUrl=Configuracion.server+Configuracion.service_donwload;
	String name_file=Configuracion.service_donwload_file;
	
	InputStreamVolleyRequest request = new InputStreamVolleyRequest(Request.Method.GET, mUrl,
	        new Response.Listener<byte[]>() { 
	             @Override 
	             public void onResponse(byte[] response) { 
	           // TODO handle the response 
	            	 File file_z = null;
	            	 File file_db= null;
	            	 File data  = Environment.getDataDirectory();
	            try { 
	            if (response!=null) {
	            	
	              FileOutputStream outputStream;
	              String name=name_file;
	              outputStream = openFileOutput(name, Context.MODE_PRIVATE);
	              outputStream.write(response);
	              outputStream.close();
	            
	              file_z = new File(data,"//data//" + Configuracion.PAQUETE
	                        + "//files//"+name_file);
	        
	              Utils.showDialog(MainActivityDownload.this, "Download complete.");
	              
	            }
	         String  currentDBPath= "//data//" +Configuracion.PAQUETE
                        + "//databases//" ;
	         file_db= new File(data,currentDBPath);
	         Log.v("DownloadFile","DownloadFile=>descompresion iniciada");	
	         progress.dismiss();
	  		  try {
	  			  
	  			  
					unzip(file_z, file_db);					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.v("DownloadFile","DownloadFile=>error en descompresion");
					Log.v("DownloadFile","DownloadFile=>error:"+e.getMessage());
					
				}
	  		  
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
	            Utils.showDialog(MainActivityDownload.this, "Download complete.");
	            progress.dismiss();
	            e.printStackTrace();
	        }
	  }
	} ,new Response.ErrorListener() {
		
	

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				 progress.dismiss();
			}
	}, null);
	private Button btn_download;
	public ProgressDialog progress;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		Util.Log("mUrl=>"+mUrl);
		progress=new ProgressDialog(this, ProgressDialog.THEME_HOLO_DARK);
		btn_download= (Button)findViewById(R.id.download);
		btn_download.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 progress.show();
				 RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new HurlStack());
				 mRequestQueue.add(request);
			}
		});
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            finish();
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	public  void unzip(File zipFile, File targetDirectory) throws IOException {
	    ZipInputStream zis = new ZipInputStream(
	            new BufferedInputStream(new FileInputStream(zipFile)));
	   
	    try {
	        ZipEntry ze;
	        int count;
	        byte[] buffer = new byte[8192];
	        while ((ze = zis.getNextEntry()) != null) {
	        	
	            File file = new File(targetDirectory, ze.getName());
	            File dir = ze.isDirectory() ? file : file.getParentFile();
	            if (!dir.isDirectory() && !dir.mkdirs())
	                throw new FileNotFoundException("Failed to ensure directory: " +
	                        dir.getAbsolutePath());
	            if (ze.isDirectory())
	                continue;
	            FileOutputStream fout = new FileOutputStream(file);
	            try {
	                while ((count = zis.read(buffer)) != -1){	                	
	                    fout.write(buffer, 0, count);
	                }
	                Log.v("DownloadFile","DownloadFile=>descompresion finalizada");	
	                Utils.showDialog(MainActivityDownload.this, "zip complete.");
	                execFinal();
	            } finally {
	                fout.close();
	                
	            }
	            /* if time should be restored as well
	            long time = ze.getTime();
	            if (time > 0)
	                file.setLastModified(time);
	            */
	        }
	    } finally {
	        zis.close();
	    }
	}

	private void execFinal() {
		// TODO Auto-generated method stub
		DatabaseManager mg= new DatabaseManager(this,"DOWNLOAD");
		Utils.showDialog(this, "Cantidad de geometrias "+mg.probarGeometrias() );
	}
}
