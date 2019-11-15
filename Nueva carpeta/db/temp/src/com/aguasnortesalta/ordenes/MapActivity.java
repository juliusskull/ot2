package com.aguasnortesalta.ordenes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.aguasnortesalta.ordenes.adapter.MapaInfoWindowAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Place;
import com.aguasnortesalta.ordenes.model.Tipo_componente;
import com.aguasnortesalta.ordenes.model.geometrias.Features;
import com.aguasnortesalta.ordenes.model.geometrias.GeoList;
import com.aguasnortesalta.ordenes.model.geometrias.GeometriaFiltro;
import com.aguasnortesalta.ordenes.model.geometrias.Geometry;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapActivity extends ActionBarActivity implements
		ConnectionCallbacks, OnConnectionFailedListener, LocationListener {
	
	protected final static String REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key";
	private static final int requestCode = 1001;
	private ToggleButton btn_mode;
	private ProgressBar pb_loading;
	private Document document;
	private LatLng placePosition;
	private LatLng userPosition;
	private GoogleMap map;
	private Place place;
	private GoogleApiClient locClient;
	private Location location;
	private boolean requestingLocationUpdates;
	private LocationRequest mLocationRequest;
		  
	boolean imageCreated = false;

	Bitmap bmp = null;
	Marker currentLocationMarker;

	private boolean filtrado=false;

	private String filtrado_fecha;

	private String filtrado_codigo;

	private LocationListener highAccuracyListener;
	private GeoList geoList;
	private DatabaseManager dm;
	private List<Polygon>  mPoligonos= new ArrayList<Polygon>();
	private double lat_actual;
	private double lng_actual;
	private boolean VIENE_OT=false;
	private Marker marker_actual;
	private String stringPosition="Aqui";
	private String ot_actual;
	public List<Features> features;
	static String[] filtros_posibles= null;
	public GeometriaFiltro filtro_actual;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);	
		
		dm = new DatabaseManager(this,"MAP ACTIVITY");
		filtro_actual=Configuracion.FILTROS_GEOMETRIAS[0];
		cargar_filtros();
		
		if (savedInstanceState != null) {
			if (savedInstanceState.keySet().contains(
					REQUESTING_LOCATION_UPDATES_KEY)) {
				requestingLocationUpdates = savedInstanceState
						.getBoolean(REQUESTING_LOCATION_UPDATES_KEY);
			}
		}
		
		pb_loading=(ProgressBar)this.findViewById(R.id.pb_loading);		
		
		// add google maps service
		Integer resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		
		//final Mostrar_tramos mt= new Mostrar_tramos();
		if (resultCode == ConnectionResult.SUCCESS) {
		
			map = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();	
			
			buildGoogleApiClient();
			location = LocationServices.FusedLocationApi.getLastLocation(locClient);					
			map.setMyLocationEnabled(true);				
			PopupAdapter customInfoWindow = new PopupAdapter(getLayoutInflater());			 
			map.setInfoWindowAdapter(customInfoWindow);		
			try {
					lat_actual =location.getLatitude();
					lng_actual =location.getLongitude();					
					mostrar_tramos_cercanos_exe ();
			} catch (Exception e2) {
					// TODO Auto-generated catch block			
					if(dm.get_ultima_posicion().latitude!=0){
						lat_actual = dm.get_ultima_posicion().latitude;
						lng_actual=  dm.get_ultima_posicion().longitude;						
						mostrar_tramos_cercanos_exe ();
					}else{
						lat_actual = Double.parseDouble("-24.789107");
						lng_actual=  Double.parseDouble("-65.410366");
					}					
			}
			
			Intent intent = getIntent();
			final Bundle bundle = getIntent().getExtras();
			if(bundle != null) {				
				try {					
					
					lat_actual =  Double.parseDouble(intent.getStringExtra(MainActivity.VER_MAPA_LAT));
					lng_actual=   Double.parseDouble(intent.getStringExtra(MainActivity.VER_MAPA_LNG));
					
					Utils.showDialog(this,"Ot actual="+intent.getStringExtra(MainActivity.VER_MAPA_OT));
					
					ot_actual=	  String.valueOf(intent.getStringExtra(MainActivity.VER_MAPA_OT));				
					
					stringPosition="Ot "+ot_actual;	
					
					VIENE_OT=true;					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Utils.showDialog(this, "No se puede encontrar la ubicacion de la OT:'"+e.getMessage()+"'");
					Util.Log( "Error=> "+e.getMessage());
				}
			}		   
			if(!VIENE_OT){
				try {				
					Util.Log("Prueba=>"+location.getLatitude());
					lat_actual=location.getLatitude();
					lng_actual=location.getLongitude();
					mostrar_tramos_cercanos_exe ();					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						Util.Log("Error=> no se pudo encontrar la localizacion actual:"+e1.getMessage());
					}
			}else{				
				mostrar_tramos_cercanos_exe ();
			}			
						
			Util.Log("LAT=>"+lat_actual);
			placePosition = new LatLng(lat_actual,lng_actual);
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(placePosition,20));			
			marker_actual = map.addMarker(new MarkerOptions().title(String.valueOf(stringPosition)).snippet(" ")
			.position(placePosition)
			.icon(getMarkerIcon("#ff2299")));
			
			map.setOnInfoWindowClickListener(onInfoWindow );
			MapaInfoWindowAdapter mapaInfoWindowAdapter= new MapaInfoWindowAdapter(this);			
			map.setInfoWindowAdapter(mapaInfoWindowAdapter);
			map.setOnMyLocationButtonClickListener(onMyLocation);
		} 
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setSubtitle(filtro_actual.toString());
		
		
		
	}
	

	
	GoogleMap.OnMyLocationButtonClickListener onMyLocation =new GoogleMap.OnMyLocationButtonClickListener() {	               

		@Override
        public boolean onMyLocationButtonClick() {
             if (locClient != null) {
                 //LocationServices.FusedLocationApi.requestLocationUpdates(locClient,location,MapActivity.this);
             }
             
             lat_actual= location.getLatitude();
             lng_actual=location.getLongitude();
             
             placePosition = new LatLng(lat_actual,lng_actual);
             
             dm.set_ultima_posicion(placePosition);

 			 map.moveCamera(CameraUpdateFactory.newLatLngZoom(placePosition,					
 					18));
 			 
 			
 			 marker_actual = map.addMarker(new MarkerOptions()		    
			 .title( 
					String.valueOf(stringPosition)).snippet(" ")
			.position(placePosition)
			.icon(getMarkerIcon("#ff2299")));	
 				         			
            // mostrar_tramos_cercanos();
 			 mostrar_tramos_cercanos_exe ();
             //mt.execute("");
             return true;
        }
    };
	
	OnInfoWindowClickListener onInfoWindow =new OnInfoWindowClickListener(){

		@Override
		public void onInfoWindowClick(Marker marker) {
			// TODO Auto-generated method stub
			final String featid = marker.getTitle();
			
			if(dm.getComponentesxAtributos_cant(featid)>0){
				set_edit_componente(featid);
			}else{
				Utils.showDialog(MapActivity.this,"El featid "+featid +" no tiene Atributos relacionados");
			}
			/*
			Utils.showDialog(MapActivity.this, "Esta por abrir featid:"+marker.getTitle(),"Ok","Cancelar", 
					new android.content.DialogInterface.OnClickListener(){

					@Override-
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						//Utils.showDialog(MapActivity.this, "ok");
						if(dm.getComponentesxAtributos_cant(featid)>0){
							set_edit_componente(featid);
						}else{
							Utils.showDialog(MapActivity.this,"El featid "+featid +" no tiene Atributos relacionados");
						}
						
					}});
			*/
		}} ;
	private MenuItem filterItem;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            finish();
	            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	public void set_edit_componente(String featid){		
		try {
			Intent intent = new Intent(MapActivity.this, GeometriaActivity.class);
			dm.set_ultimo_featid(featid);
			
			startActivityForResult(intent, requestCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Utils.showDialog(this, "Recurso no disponible");
		}
	}
	private Location getLocation() {
	    LocationManager locationManager = (LocationManager)
	            getSystemService(Context.LOCATION_SERVICE);
	    Criteria criteria = new Criteria();
	    return locationManager.getLastKnownLocation(locationManager
	            .getBestProvider(criteria, false));

	}
	public void mostrar_tramos_cercanos_exe(){
		ConnectivityManager cm =
		        (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
		if(!isConnected){
			Utils.showDialog(this, "La aplicacion se encuentra en modo offline "+lat_actual +","+lng_actual, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 mostrar_tramos_cercanos_exe2();
		}});
		}else{
			 mostrar_tramos_cercanos_exe2();
		}
		
		
	
		
	}
	public void mostrar_tramos_cercanos_exe2(){
		LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if(isGPSEnabled){			
			map.clear();
			
			new Mostrar_tramos().execute("");
			/*
			 * new Mostrar_Geometrias().execute(1);			
			new Mostrar_Geometrias().execute(3);
			new Mostrar_Geometrias().execute(4);
			new Mostrar_Geometrias().execute(7);
			new Mostrar_Geometrias().execute(20);
			new Mostrar_Geometrias().execute(25);
			new Mostrar_Geometrias().execute(34);
			new Mostrar_Geometrias().execute(44);
			*/
			
		}else{
			Utils.showDialog(this, "El GPS se encuentra apagado");
		}
	}
	//-------------------------------------------
	public class Mostrar_Geometrias extends  AsyncTask<Integer,Integer,String>{
		private List<LatLng> coor= new ArrayList<LatLng>();
		private boolean is_datos=false;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			pb_loading.setVisibility(View.VISIBLE);
			super.onPreExecute();
			
			
		}
		@Override
		protected String doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			 features = dm.getGeometrias(lat_actual,lng_actual,params[0]);
			 if(features.size()>0){
				 is_datos=true;
			 } 
			return null;
		}
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pb_loading.setVisibility(View.INVISIBLE);
			if(is_datos){				
				/* revisar el componente  http://geojsonlint.com/*/
			for(int i=0; i<features.size();i++){
				Features f = features.get(i);
				
				 coor= new ArrayList<LatLng>();					
				 for (int k=0;k<features.get(i).geometry.getCoordinates().get(0).size();k++){
					 coor.add(new LatLng(features.get(i).geometry.getCoordinates().get(0).get(k).get(1), features.get(i).geometry.getCoordinates().get(0).get(k).get(0)));
								 
				 }	
				 
				 int mcolor;
				 String mcolor_hexa="";
				 String servicio_name="";
				 
				 if(features.get(i).properties.id_tipo_servicio==1){
					 mcolor=Color.BLUE;
					 mcolor_hexa="#0000FF";
					 servicio_name="agua";
				 }else{
					 mcolor_hexa="#ff0000";				
					 mcolor=Color.RED;
					 servicio_name="cloaca";					 
				 }
				 Util.Log("Componente=>(1)"+f.properties.id_tipo_componente );
				if(f.properties.id_tipo_componente==1){
					addPolinea(coor,mcolor);
				}else{					 
					addPoligono(coor, mcolor);				
				}				 		
				 
				    LatLng currentLocation= new LatLng(Double.parseDouble(features.get(i).lat),Double.parseDouble(features.get(i).lng));
			
					String descripcion = features.get(i).properties.titulo;
				
					BitmapDescriptor miicon = createPureTextIcon(descripcion);
					Marker marker1 = null;
					// if(features.get(i).properties.id_tipo_componente==1){
					marker1 = map.addMarker(new MarkerOptions()
							.title(String.valueOf(features.get(i).featid))
							.snippet(get_regla(features.get(i).s_properties))
							.position(
									new LatLng(Double.parseDouble(features
											.get(i).lat), Double
											.parseDouble(features.get(i).lng)))
							.icon(miicon));
				
						
			}
			
			}else{
				//Utils.showDialog(MapActivity.this, "No se encontraron tramos cercanos");
				}
		}
		

		
	}
	
	//-----------------------------------------------------
	public class Mostrar_tramos extends  AsyncTask<String,String,String>{

		private List<LatLng> coor= new ArrayList<LatLng>();
		private boolean is_datos=false;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			pb_loading.setVisibility(View.VISIBLE);
			super.onPreExecute();
			
			
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			 features = dm.getGeometrias(lat_actual,lng_actual,filtro_actual.getFiltro());
			 if(features.size()>0){
				 is_datos=true;
				 dibujar_geometrias();
			 } 
			 
			return "";
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			pb_loading.setVisibility(View.INVISIBLE);
			
			if(is_datos){				
				/* revisar el componente  http://geojsonlint.com/*/
				
				//dibujar_geometrias();
			
				/*
			for(int i=0; i<features.size();i++){
				Features f = features.get(i);
				
				 coor= new ArrayList<LatLng>();					
				 for (int k=0;k<features.get(i).geometry.getCoordinates().get(0).size();k++){
					 coor.add(new LatLng(features.get(i).geometry.getCoordinates().get(0).get(k).get(1), features.get(i).geometry.getCoordinates().get(0).get(k).get(0)));
								 
				 }	
				 
				 int mcolor;
				 String mcolor_hexa="";
				 String servicio_name="";
				 
				 if(features.get(i).properties.id_tipo_servicio==1){
					 mcolor=Color.BLUE;
					 mcolor_hexa="#0000FF";
					 servicio_name="agua";
				 }else{
					 mcolor_hexa="#ff0000";				
					 mcolor=Color.RED;
					 servicio_name="cloaca";					 
				 }
				 Util.Log("Componente=>(1)"+f.properties.id_tipo_componente );
				if(f.properties.id_tipo_componente==1){
					addPolinea(coor,mcolor);
				}else{
					 
					addPoligono(coor, mcolor);
				//	addPolinea(coor,mcolor);
				}				 		
				 
				    LatLng currentLocation= new LatLng(Double.parseDouble(features.get(i).lat),Double.parseDouble(features.get(i).lng));
				   //if(filtro_actual.id_tipo==1){
					 
					 //features.get(i).getProperties()
					String descripcion = features.get(i).properties.titulo;
					// String
					// descripcion=servicio_name+"-"+dm.get_tramos_descripicion(String.valueOf(features.get(i).featid));

					BitmapDescriptor miicon = createPureTextIcon(descripcion);
					Marker marker1 = null;
					// if(features.get(i).properties.id_tipo_componente==1){
					marker1 = map.addMarker(new MarkerOptions()
							.title(String.valueOf(features.get(i).featid))
							.snippet(get_regla(features.get(i).s_properties))
							.position(
									new LatLng(Double.parseDouble(features
											.get(i).lat), Double
											.parseDouble(features.get(i).lng)))
							.icon(miicon));
				
					
			
						
			}
			*/
			}else{
				Utils.showDialog(MapActivity.this, "No se encontraron tramos cercanos");
				}
		}
		
	}
	public void dibujar_geometrias(){
		
	
		 runOnUiThread(new Runnable() {
                @Override
                public void run() {
		        //Aquí ejecutamos nuestras tareas costosas
		    	List<LatLng> coor= new ArrayList<LatLng>();
		    	for(int i=0; i<features.size();i++){
					Features f = features.get(i);
					
					 coor= new ArrayList<LatLng>();					
					 for (int k=0;k<features.get(i).geometry.getCoordinates().get(0).size();k++){
						 coor.add(new LatLng(features.get(i).geometry.getCoordinates().get(0).get(k).get(1), features.get(i).geometry.getCoordinates().get(0).get(k).get(0)));
									 
					 }	
					 
					 int mcolor;
					 String mcolor_hexa="";
					 String servicio_name="";
					 
					 if(features.get(i).properties.id_tipo_servicio==1){
						 mcolor=Color.BLUE;
						 mcolor_hexa="#0000FF";
						 servicio_name="agua";
					 }else{
						 mcolor_hexa="#ff0000";				
						 mcolor=Color.RED;
						 servicio_name="cloaca";					 
					 }
					 Util.Log("Componente=>(1)"+f.properties.id_tipo_componente );
					if(f.properties.id_tipo_componente==1){
						addPolinea(coor,mcolor);
					}else{
						 
						addPoligono(coor, mcolor);
					//	addPolinea(coor,mcolor);
					}				 		
					 
					    LatLng currentLocation= new LatLng(Double.parseDouble(features.get(i).lat),Double.parseDouble(features.get(i).lng));
					   //if(filtro_actual.id_tipo==1){
						 
						 //features.get(i).getProperties()
						String descripcion = features.get(i).properties.titulo;
						// String
						// descripcion=servicio_name+"-"+dm.get_tramos_descripicion(String.valueOf(features.get(i).featid));

						BitmapDescriptor miicon = createPureTextIcon(descripcion);
						Marker marker1 = null;
						// if(features.get(i).properties.id_tipo_componente==1){
						marker1 = map.addMarker(new MarkerOptions()
								.title(String.valueOf(features.get(i).featid))
								.snippet(get_regla(features.get(i).s_properties))
								.position(
										new LatLng(Double.parseDouble(features
												.get(i).lat), Double
												.parseDouble(features.get(i).lng)))
								.icon(miicon));
					
						
				
							
				}
		    }
		});
		
	}
	public String get_regla(String g){
		String s =g.replace("\"id_tipo_servicio\":\"2\"", "\"servicio\":\"Cloaca\"");
			   s =s.replace("\"id_tipo_servicio\":\"1\"", "\"servicio\":\"Agua\"");
			   s =s.replace("\"id_tipo_componente\":\"1\"", "\"componente\":\"Tramo\"");
			   s =s.replace("\"id_tipo_componente\":\"7\"", "\"componente\":\"Boca de Registro\"");
			   s =s.replace("\"id_tipo_componente\":\"34\"", "\"componente\":\"camara desague\"");
			 //  s=g;
			   Util.Log("g=>"+ g);
		return s;
	}
	public BitmapDescriptor createPureTextIcon(String text) {

	    Paint textPaint = new Paint(); // Adapt to your needs

	    float textWidth = textPaint.measureText(text);
	    float textHeight = textPaint.getTextSize();
	    int width = (int) (textWidth);
	    int height = (int) (textHeight);
	    textPaint.setStrokeWidth(20);
	    Bitmap image = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    Canvas canvas = new Canvas(image);

	    canvas.translate(0, height);

	    // For development only:
	    // Set a background in order to see the
	    // full size and positioning of the bitmap.
	    // Remove that for a fully transparent icon.
	    canvas.drawColor(Color.WHITE);//Color.LTGRAY);

	    canvas.drawText(text, 0, 0, textPaint);
	    
	    BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(image);
	    return icon;
	}
	public void mostrar_tramos_cercanos(){	
		
		 for(int i=0; i<features.size();i++){
			
			 Features f = features.get(i);
			 List<LatLng> coor= new ArrayList<LatLng>();
			 for (int k=0;k<features.get(i).geometry.getCoordinates().get(0).get(0).size();k++){
				 coor.add(new LatLng(features.get(i).geometry.getCoordinates().get(0).get(k).get(1), features.get(i).geometry.getCoordinates().get(0).get(k).get(0)));
								 
			 }					 
		 }
        
	}
	public BitmapDescriptor getMarkerIcon(String color) {
	    float[] hsv = new float[3];
	    Color.colorToHSV(Color.parseColor(color), hsv);
	    return BitmapDescriptorFactory.defaultMarker(hsv[0]);
	}
	public void addPoligono(Iterable<LatLng> coor, int mcolor){
		
	
		 PolygonOptions op= new PolygonOptions();
		 op.strokeColor(mcolor);
		 op.fillColor(mcolor);
		 op.addAll(coor);
		 
		 Polygon poly= map.addPolygon(op);
		
		 mPoligonos.add(poly);
		 
	}
	
	public void addPolinea(Iterable<LatLng> coor, int mcolor){
	 PolylineOptions  op= new  PolylineOptions();
	 op.width(5);
	 op.color(mcolor);
	 op.geodesic(true);
	 op.addAll(coor);
	 Polyline line = map.addPolyline(op);
	 
	}
	
	public void requestGeoJson() {      
	    Util.Log("Actualizando....");
	    RequestQueue queue = Volley.newRequestQueue(this);
	    String url ="http://ot.aguasdelnortesalta.com.ar/gis/tramos2.json";// Configuracion.getAuthorization();
	    Util.Log("url=>"+url);
	    	     
	    StringRequest postRequest = new StringRequest(Request.Method.GET, url, 
	        new Response.Listener<String>() 
	        {	            

				@Override
	            public void onResponse(String response) {
	                // response					
	                Util.Log("response=>"+response.replaceAll("\\\\", ""));	 
	                
	                geoList= new GeoList(response.replaceAll("\\\\", ""));
	                /*
	                for (Geo geo : geoList.getGeos()) {
	                	
	                	List<LatLng> points= geo.geometry.coordinates;
						Polyline line = map.addPolyline(new PolylineOptions().addAll(points)	     			    	    
	     			    .width(5)	    
	     			    .color(Color.BLUE)
	     			    .geodesic(true));
	     			   placePosition = points.get(0);	     			   
	     			 
	     			   Marker marker = map.addMarker(new MarkerOptions()
	     				.title("tramo").snippet(geo.descripcion)				
	     				.position(placePosition));	
	     			   
	                }
	                */
	                
	                	                
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
	                //params.put("Authorization", Usuario.getApi(MainActivity.this));
	                //params.put("Usuario", Usuario.getUsuarioActivo(MainActivity.this));	                
	                return params;  
	        }	        
	        
	    };	
	    queue.add(postRequest);	 

	}

	public static String AssetJSONFile (String filename, Context context) throws IOException {
	        AssetManager manager = context.getAssets();
	        InputStream file = manager.open(filename);
	        byte[] formArray = new byte[file.available()];
	        file.read(formArray);
	        file.close();

	        return new String(formArray);
	    }

	
	public void dibujar_puntos1(){
		/*
		Util.Log("cantidad=>"+lista.size());
	    for (int i = 0; i < lista.size(); i++) {	    	
	    	placePosition = new LatLng(lista.get(i).getDLat(),
	    			lista.get(i).getDLng());	    	
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(placePosition,
					13));
			Marker marker = map.addMarker(new MarkerOptions()
					.title(lista.get(i).getNro_ot()).snippet(lista.get(i).getMotivo()+" "+lista.get(i).getObservacion())
					.position(placePosition));
				
			Bitmap icon = resizeMapIcons(R.drawable.pin_green,96,96);
			if(lista.get(i).getActual().equals("SI")){
				icon = resizeMapIcons(R.drawable.pin_blue,96,96);				
			}
			marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon));
			marker.showInfoWindow();
			
		}
	    */
	    
	    //----------------------------
	    
	    Polygon poly= map.addPolygon(new PolygonOptions()
        .add(new LatLng(-24.7882588761669, -65.4150558240206)
        ,new LatLng(-24.7881950247009, -65.4157520299747)
        ,new LatLng(-24.7881513360908, -65.4163636963502)
        )
        .strokeColor(Color.BLUE)
        .fillColor(Color.BLUE));
       
	   
	    pb_loading.setVisibility(View.GONE);
		btn_mode.setVisibility(View.VISIBLE);
		btn_mode.setText(getDiaActual()+"-"+getTipoMapaActualName());
	}
	private String getTipoMapaActualName() {
		// TODO Auto-generated method stub
		return "";
	}
	public Bitmap resizeMapIcons(String iconName,int width, int height){
	    Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
	    Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
	    return resizedBitmap;
	}
	public Bitmap resizeMapIcons(int i_icono,int width, int height){
		 Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), i_icono);
		 Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
		 return imageBitmap;//resizedBitmap;
	}
	private class GetRouteTask extends AsyncTask<String, Void, String> {

		String response;
		GMapDirectionManager directionManager;

		@Override
		protected void onPreExecute() {
			btn_mode.setVisibility(View.GONE);
			pb_loading.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... urls) {
			while (location == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			userPosition = new LatLng(location.getLatitude(),
					location.getLongitude());
			/*
			directionManager = new GMapDirectionManager(btn_mode.isChecked());
			document = directionManager
					.getDocument(userPosition, placePosition);
			*/
			response = "Success";
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			if (response.equalsIgnoreCase("Success")) {
				if (document != null) {
					ArrayList<LatLng> directionPoint = directionManager
							.getDirection(document);
					PolylineOptions rectLine = new PolylineOptions().width(
							convertDpToPixels(MapActivity.this, 5))
							.color(Color.BLUE);

					if (btn_mode.isChecked())
						rectLine.color(Color.RED);

					for (int i = 0; i < directionPoint.size(); i++) {
						rectLine.add(directionPoint.get(i));
					}
					map.clear();
					Marker marker = map.addMarker(new MarkerOptions()
							.title(place.getName()).snippet(place.getAddress())
							.position(placePosition));
					marker.showInfoWindow();
					map.addPolyline(rectLine);
				}
			} 

			pb_loading.setVisibility(View.GONE);
			btn_mode.setVisibility(View.VISIBLE);
		}
	}

	protected synchronized void buildGoogleApiClient() {
		locClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API)
				.build();
		mLocationRequest = LocationRequest.create()
		        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
		        .setInterval(10000).setFastestInterval(5000);
	}

	public String getUrl(String fch, String codigo) {
		// TODO Auto-generated method stub
		return "";
	}
	
	public String getUrlUbc(String fch, String codigo) {
		// TODO Auto-generated method stub
		//Util.Log("url-ubc=>"+Configuracion.URL_RASTREO  + "&pfecha="+fch+"&servicio="+codigo);
		return "";//Util.getClient(Configuracion.URL_RASTREO + "&pfecha="+fch+"&servicio="+codigo);
	}
	
	
	public String getDiaActual(){		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		Log.v("",dateFormat.format(date));
		return dateFormat.format(date);
	} 

	public boolean isOnline() {
		// TODO Auto-generated method stub
		return true;
	}

	protected void startLocationUpdates() {
		LocationRequest locationRequest = new LocationRequest();
		
		//locationRequest.setInterval(10);
		//locationRequest.setFastestInterval(50);
		locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		LocationServices.FusedLocationApi.requestLocationUpdates(locClient,
				locationRequest, this);
	}

	protected void stopLocationUpdates() {
		LocationServices.FusedLocationApi
				.removeLocationUpdates(locClient, this);
		locClient.disconnect();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_map, menu);
		filterItem = menu.findItem(R.id.action_filter);
		cargar_filtros();
		filterItem.setOnMenuItemClickListener(onFilterMenuItemClickListener);
		return true;
	}
	
	OnMenuItemClickListener onFilterMenuItemClickListener=new OnMenuItemClickListener(){

		@Override
		public boolean onMenuItemClick(MenuItem arg0) {
			// TODO Auto-generated method stub			
			cargar_filtros();
			AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
			builder.setTitle("Filtros");
			builder.setItems(filtros_posibles, new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        // the user clicked on colors[which]
			    	filtro_actual=Configuracion.FILTROS_GEOMETRIAS[which];
			    	getActionBar().setSubtitle(filtro_actual.toString());
			    	mostrar_tramos_cercanos_exe ();
			    }
			});
			builder.show();
			return false;
		}};
	
	@Override
	protected void onStart() {
		GoogleAnalytics.getInstance(getBaseContext()).reportActivityStart(this);
		///locClient.connect();
		if (locClient != null) {
			locClient.connect();
			Util.Log("onStart=>onStart");
					
	    }		
		super.onStart();
	}

	@Override
	protected void onStop() {
		
		try {
			GoogleAnalytics.getInstance(getBaseContext()).reportActivityStop(this);
			if (locClient.isConnected()) {
				locClient.disconnect();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.onStop();
	}

	@Override
	public void onPause() {
		super.onPause();
		try {
			stopLocationUpdates();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		
		try {
			if (locClient.isConnected() && !requestingLocationUpdates) {
				startLocationUpdates();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnected(Bundle connectionHint) {
		if (location == null) {
			location = LocationServices.FusedLocationApi
					.getLastLocation(locClient);
		}
		if (requestingLocationUpdates) {
			startLocationUpdates();
		}
	}

	@Override
	public void onConnectionSuspended(int cause) {
		locClient.connect();
	}

	@Override
	public void onLocationChanged(Location location) {
		this.location = location;
		Util.Log("locClient.isConnected()=>"+locClient.isConnected());
		if (locClient.isConnected() && locClient != null) {
		    Log.e("LocationChanged", "Location=>"+String.valueOf(location.getLatitude()) + " Longitude: " + String.valueOf(location.getLongitude()));
		    LocationServices.FusedLocationApi.removeLocationUpdates(locClient, this);
		    //locClient.disconnect();
		    
		}
	}

	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
				requestingLocationUpdates);
		super.onSaveInstanceState(savedInstanceState);
	}
	private void doSomeCustomizationForMarker(LatLng currentLocation) {
	    if (!imageCreated) {
	        imageCreated = true;
	        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
	        bmp = Bitmap.createBitmap(400, 400, conf);
	        Canvas canvas1 = new Canvas(bmp);

	        Paint color = new Paint();
	        color.setTextSize(30);
	        color.setColor(Color.WHITE);

	        BitmapFactory.Options opt = new BitmapFactory.Options();
	        opt.inMutable = true;

	        Bitmap imageBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.car,opt);
	        
	        Bitmap resized = Bitmap.createScaledBitmap(imageBitmap, 320, 320, true);
	        
	        canvas1.drawBitmap(resized, 40, 40, color);

	        canvas1.drawText("Le Messi", 30, 40, color);

	        currentLocationMarker = map.addMarker(new MarkerOptions().position(currentLocation).icon(BitmapDescriptorFactory.fromBitmap(bmp))               // Specifies the anchor to be at a particular point in the marker image.
	                .anchor(0.5f, 1));
	    } else {
	        currentLocationMarker.setPosition(currentLocation);
	    }

	}
	
	class PopupAdapter implements InfoWindowAdapter {
		  LayoutInflater inflater=null;

		  PopupAdapter(LayoutInflater inflater) {
		    this.inflater=inflater;
		  }

		  @Override
		  public View getInfoWindow(Marker marker) {
		    return(null);
		  }

		  @Override
		  public View getInfoContents(Marker marker) {
		    View popup=inflater.inflate(R.layout.popup, null);
		    TextView tv=(TextView)popup.findViewById(R.id.title);
		    tv.setText(marker.getTitle());
		    tv=(TextView)popup.findViewById(R.id.snippet);
		    tv.setText(marker.getSnippet());

		    return(popup);
		  }
		}
	
	@Override
	public void onActivityResult(int r, int	resultCode, Intent data) {
		 if (r != requestCode) {
			 this.finish();
		 }
		
	}
	@Override 
	public void onDestroy() {
		Util.Log("Error=>onDestroy");
		 super.onDestroy();

	}
	public static int convertPixelsToDp(Context context, int px) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		int dp = (int) (px / (metrics.densityDpi / 160f));
		return dp;
	}
	public static int convertDpToPixels(Context context, int dp) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		int px = (int) (dp * (metrics.densityDpi / 160f));
		return px;
	}
	
	private void cargar_filtros(){		
		try {
			 filtros_posibles= new String[Configuracion.FILTROS_GEOMETRIAS.length];
			Util.Log("Entro=>"+Configuracion.FILTROS_GEOMETRIAS.length);
			
			for(int i=0; i<Configuracion.FILTROS_GEOMETRIAS.length;i++){
				Util.Log("Entro=>"+Configuracion.FILTROS_GEOMETRIAS[i].toString());
				filtros_posibles[i]= Configuracion.FILTROS_GEOMETRIAS[i].toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("error=>"+e.getMessage());
		}
	}
	
	
	
	


	
  
}
