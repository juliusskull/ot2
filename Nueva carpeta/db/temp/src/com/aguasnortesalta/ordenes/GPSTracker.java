package com.aguasnortesalta.ordenes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.aguasnortesalta.ordenes.model.Usuario;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener{

	 private final Context mContext;
	 public String legajo;
	 	 
	    // flag for GPS status
	 public   boolean isGPSEnabled = false;
	 
	    // flag for network status
	 public  boolean isNetworkEnabled = false;
	 
	    // flag for GPS status
	    boolean canGetLocation = false;
	 
	    Location location; // location
	    double latitude; // latitude
	    double longitude; // longitude
	 
	    // The minimum distance to change Updates in meters
	    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	 
	    // The minimum time between updates in milliseconds
	    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	    
	    private static final int TWO_MINUTES = 1000 * 60 * 2;
	 
	    // Declaring a Location Manager
	    protected LocationManager locationManager;
	    public static final float  precision_no= 0.0f;
	    public float precision=precision_no;
	 
	    public GPSTracker(Context context) {
	        this.mContext = context;
	        getLocation();
	    }
	 
	    public Location getLocation() {
	        try {
	            locationManager = (LocationManager) mContext
	                    .getSystemService(LOCATION_SERVICE);
	            
	            // getting GPS status
	            isGPSEnabled = locationManager
	                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
	            /*
	            // getting network status
	            isNetworkEnabled = locationManager
	                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	            */
	            isNetworkEnabled=false;
	            
	            Log.v("GPS Enabled", "GPS=>isGPSEnabled:"+isGPSEnabled);
	            Log.v("GPS Enabled", "GPS=>isNetworkEnabled:"+isNetworkEnabled);
	            if (!isGPSEnabled && !isNetworkEnabled) {
	                // no network provider is enabled
	            } else {
	                canGetLocation = true;	                
	                // if GPS Enabled get lat/long using GPS Services	                
	                if (isGPSEnabled) {
	                	
	                    //if (location == null) {
	                			/*
	                        locationManager.requestLocationUpdates(
	                                LocationManager.GPS_PROVIDER,
	                                MIN_TIME_BW_UPDATES,
	                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
	                        */
	                	locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,  0, 0,this);
	                        Log.d("GPS Enabled", "GPS Enabled");
	                        if (locationManager != null) {
	                        	Log.v("GPS Enabled", "GPS=>ini");
	                            location = locationManager
	                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                            precision=location.getAccuracy();
	                            
	                            Log.d("GPS Enabled", "GPS Enabled");
	                            if (location != null) {
	                                latitude = location.getLatitude();
	                                longitude = location.getLongitude();
	                                Log.v("GPS Enabled", "GPS=>"+latitude);
	                                Log.v("GPS Enabled", "GPS=>"+longitude);
	                                isNetworkEnabled=false;
	                            }
	                        }
	                   // }
	                    
	                }
	                // First get location from Network Provider
	                if (isNetworkEnabled) {
	                    locationManager.requestLocationUpdates(
	                            LocationManager.NETWORK_PROVIDER,
	                            MIN_TIME_BW_UPDATES,
	                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
	                    Log.d("Network", "Network");
	                    if (locationManager != null) {
	                        location = locationManager
	                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                        precision=location.getAccuracy();
	                        if (location != null) {
	                        	Log.v("GPS Enabled", "GPS=>w:ini");
	                            latitude = location.getLatitude();
	                            longitude = location.getLongitude();
	                            Log.v("GPS Enabled", "GPS=>w:"+latitude);
                                Log.v("GPS Enabled", "GPS=>w:"+longitude);
	                        }
	                    }
	                }
	               
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return location;
	    }
	     
	    /**
	     * Stop using GPS listener
	     * Calling this function will stop using GPS in your app
	     * */
	    public void stopUsingGPS(){
	        if(locationManager != null){
	            locationManager.removeUpdates(GPSTracker.this);
	        }       
	    }
	     
	    /**
	     * Function to get latitude
	     * */
	    public double getLatitude(){
	        if(location != null){
	            latitude = location.getLatitude();
	        }
	         
	        // return latitude
	        return latitude;
	    }
	     
	    /**
	     * Function to get longitude
	     * */
	    public double getLongitude(){
	        if(location != null){
	            longitude = location.getLongitude();
	        }
	         
	        // return longitude
	        return longitude;
	    }
	     
	    /**
	     * Function to check GPS/wifi enabled
	     * @return boolean
	     * */
	    public boolean canGetLocation() {
	        return this.canGetLocation;
	    }
	     
	    /**
	     * Function to show settings alert dialog
	     * On pressing Settings button will lauch Settings Options
	     * */
	    public void showSettingsAlert(){
	        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
	      
	        // Setting Dialog Title
	        alertDialog.setTitle("GPS is settings");
	  
	        // Setting Dialog Message
	        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
	  
	        // On pressing Settings button
	        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int which) {
	                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	                mContext.startActivity(intent);
	            }
	        });
	  
	        // on pressing cancel button
	        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            dialog.cancel();
	            }
	        });
	  
	        // Showing Alert Message
	        alertDialog.show();
	    }
	 
	    @Override
	    public void onLocationChanged(Location location) {
	    }
	 
	    @Override
	    public void onProviderDisabled(String provider) {
	    }
	 
	    @Override
	    public void onProviderEnabled(String provider) {
	    }
	 
	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    }
	 
	    @Override
	    public IBinder onBind(Intent arg0) {
	        return null;
	    }
	    public void  enviar() {
	    	
			new Thread(new Runnable() {
			    public void run() {			    	
			    	String s=getClient(getUrl());
			    	if(s!=null && s.length()>0){
				    	try {     			        	 
				        	 JSONObject obj = new JSONObject(s);		
				        	 if(obj.getBoolean("status")){
				        		 //Util.SpSet(mContext, Configuracion.getBiblio(), Configuracion.getSincronizacion(),(Util.getFechaActualFormat()+Util.getHoraActualFormat()));			        		 
				        		 //Util.SpSet(mContext, Configuracion.getBiblio(), Configuracion.getSincronizacion(),(Util.getFechaActualFormat()+Util.getHoraActualFormat()));
				        		 
				        	 }
				        	 					
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Util.Log("error");
							//e.printStackTrace();
						}
			    	}
			    	
			    }
			}).start();
		}
	    
	   public void addLatLng(Activity activity,Usuario usuario){
		    /*
		    Rastreo rastreo= new Rastreo(activity);
			rastreo.setLat(String.valueOf(getLatitude()));
			rastreo.setLng(String.valueOf(getLongitude()));
			rastreo.setT(usuario.getUsu());
			rastreo.setFch(Util.getDateNow());
			rastreo.insert();
			*/
		   
	   }
	   public void addLatLng(Activity activity,String usuario){
		   /*
		   Rastreo rastreo= new Rastreo(activity);
			rastreo.setLat(String.valueOf(getLatitude()));
			rastreo.setLng(String.valueOf(getLongitude()));
			rastreo.setT(usuario);
			rastreo.setFch(Util.getDateNow());
			rastreo.insert();
			*/
		   
		   
	   }
	    public String getUrl(){
	    	/*total inactivo por ahora */
	    	return "";
	    	/*
	    	Log.v("OT", "gps:"+"&isgps="+isGPSEnabled+"&isnetwork="+isNetworkEnabled);
	    	String movilidad=Util.SpGet(mContext, Configuracion.getBiblio(), Configuracion.getEquipoActual(0),"0");
	    	
	    	
	    	boolean v=Util.SpGetB(mContext, Configuracion.getBiblio(), Configuracion.getUsuarioActivo());
	    		    	
	    	String inidia_1=Util.SpGet(mContext, Configuracion.getBiblio(), Configuracion.getUltimoIniDia(),"0");
	    	
	    	String inidia="{\"h\":  \"" +inidia_1+ "\",\"v\":"+String.valueOf(v)+"}";
	    	
	    	String login ="{\"h\": \""+inidia_1+"\"}";
	    	
	    	String ant=Util.SpGet(mContext, Configuracion.getBiblio(), Configuracion.getUsuarioActivo_ant(),"");
	    	if(ant.equals("")){
	    		Util.SpSet(mContext, Configuracion.getBiblio(), Configuracion.getUsuarioActivo_ant(),String.valueOf(v));
	    	}else{
	    		if(v==Boolean.getBoolean(ant)){
	    			inidia="";	    			
	    		}
	    		Util.SpSet(mContext, Configuracion.getBiblio(), Configuracion.getUsuarioActivo_ant(),String.valueOf(v));
	    	}	    	
	    	String e="";	    	
	    	try {
	    		if(!Util.SpGetB(getApplication(), Configuracion.getBiblio(), Configuracion.getBiblioEnvioCuadrillaDatos())){
	    			e="&xequipo="+URLEncoder.encode(Util.SpGet(mContext, Configuracion.getBiblio(), Configuracion.equipos,"{\"mb\": 0, \"mc\": 0, \"de\": "+movilidad+", \"re\": 0}"), "UTF-8");
	    		}
				inidia=URLEncoder.encode(inidia, "UTF-8");
				login=URLEncoder.encode(login, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
	    	String imei=Util.getIMEI(mContext);
	    	String s_precision="";
	    	if(precision!=precision_no){
	    		s_precision="&precision="+precision;
	    	}
	    	return "http://ot.aguasdelnortesalta.com.ar/wses.php?x=0&lat="+latitude+"&lng="+longitude+s_precision+"&legajo="+getLegajo()+"&fch="+Util.getFechaActual()+Util.getHoraActual()+"&isgps="+isGPSEnabled+"&isnetwork="+isNetworkEnabled+"&version="+Util.getAppVersion(mContext)+e+"&imei="+imei+"&inidia="+inidia+"&login="+login+"&aplicacion="+Configuracion.APLICACION;
			    */	
	    }    
	    
	    public String getClient(String url){
		 	Log.v("alarma", url);
			HttpGet request = new HttpGet(url);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse;
			int responseCode;
			String message;
			try {
				httpResponse = client.execute(request);//Bloqueante!!!!!
				responseCode = httpResponse.getStatusLine().getStatusCode();
				message = httpResponse.getStatusLine().getReasonPhrase();
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					String response = EntityUtils.toString(entity);
				return response;
				}else{
				return null;
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		public String getLegajo() {
			return legajo;
		}

		public void setLegajo(String legajo) {
			this.legajo = legajo;
		}
		protected boolean isBetterLocation(Location location, Location currentBestLocation) {
		    if (currentBestLocation == null) {
		        // A new location is always better than no location
		        return true;
		    }

		    // Check whether the new location fix is newer or older
		    long timeDelta = location.getTime() - currentBestLocation.getTime();
		    boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		    boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		    boolean isNewer = timeDelta > 0;

		    // If it's been more than two minutes since the current location, use the new location
		    // because the user has likely moved
		    if (isSignificantlyNewer) {
		        return true;
		    // If the new location is more than two minutes older, it must be worse
		    } else if (isSignificantlyOlder) {
		        return false;
		    }

		    // Check whether the new location fix is more or less accurate
		    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
		    boolean isLessAccurate = accuracyDelta > 0;
		    boolean isMoreAccurate = accuracyDelta < 0;
		    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		    // Check if the old and new location are from the same provider
		    boolean isFromSameProvider = isSameProvider(location.getProvider(),
		            currentBestLocation.getProvider());

		    // Determine location quality using a combination of timeliness and accuracy
		    if (isMoreAccurate) {
		        return true;
		    } else if (isNewer && !isLessAccurate) {
		        return true;
		    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
		        return true;
		    }
		    return false;
		}

		/** Checks whether two providers are the same */
		private boolean isSameProvider(String provider1, String provider2) {
		    if (provider1 == null) {
		      return provider2 == null;
		    }
		    return provider1.equals(provider2);
		}
}
