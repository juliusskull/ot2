package com.aguasnortesalta.ordenes;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.sync.SincronizarFotosService;
import com.aguasnortesalta.ordenes.sync.SincronizarService;
import com.aguasnortesalta.ordenes.utils.Util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class MyBroadcastReceiver extends BroadcastReceiver{
Activity activity;

private SQLiteDatabase db;
//private LocationManager milocManager;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Util.Log("enviar rastreo=>"+Configuracion.APLICACION);
		try {
			DatabaseManager dm= new DatabaseManager(context);
			dm.add_confirmacion();
			Intent intent1 = new Intent(context, SincronizarService.class);
			context.startService(intent1);
			/*
			 *  * sacado por uso de la bateria
			ConnectivityManager cm =
			        (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			boolean isConnected = activeNetwork != null &&
			                      activeNetwork.isConnectedOrConnecting();
			boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
			if(isConnected && isWiFi){
				
				
				Intent intent2 = new Intent(context, SincronizarFotosService.class);
				context.startService(intent2);
				
				
			}
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Util.Log("Error =>BroadcastReceiver:"+e.getMessage());
		}
	
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	} 
	 
	
	
}
