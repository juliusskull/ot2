package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.db.DatabaseManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
	public Intent intent1;
	public PendingIntent pendingIntent;
	public AlarmManager alarmManager;
	 @Override
	 public void onReceive(Context context, Intent intent) {
	  Log.v("Entro", "Entro");	 
	  if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
		  DatabaseManager dm= new DatabaseManager(context);
		  String legajo="";//Util.SpGet(context, "cargosp", "legajo","1");		  
		  
		  dm.ini_alarma(context, legajo);
		  ocultar();
	  }
	 }
		public static void ocultar() {
			// TODO Auto-generated method stub
			try{
			    //REQUIRES ROOT
			    Build.VERSION_CODES vc = new Build.VERSION_CODES();
			    Build.VERSION vr = new Build.VERSION();
			    String ProcID = "79"; //HONEYCOMB AND OLDER
			 
			    //v.RELEASE  //4.0.3
			    if(vr.SDK_INT >= vc.ICE_CREAM_SANDWICH){
			        ProcID = "42"; //ICS AND NEWER
			    }			 
			 
			    //REQUIRES ROOT
			    Process proc = Runtime.getRuntime().exec(new String[]{"su","-c","service call activity "+ ProcID +" s16 com.android.systemui"}); //WAS 79
			    proc.waitFor();
			 
			}catch(Exception ex){
			    //Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
			}
		}
	}