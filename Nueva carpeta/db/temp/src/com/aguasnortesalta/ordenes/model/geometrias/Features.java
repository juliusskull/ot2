package com.aguasnortesalta.ordenes.model.geometrias;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.google.gson.Gson;

public class Features {
	public static final int ACTUALIZADO = 0;
	public String type;
	public Properties properties= null;
	public Geometry geometry=null; 
	
	public String s_properties= "";
	public String s_geometry= "";
	public long featid= 0;
	public String type_geometry;
	
	public String lat;
	public String lng;
	
	public String descripcion;
	
	private Gson gson = new Gson();
	public Features() {
		super();
	}
	/*
	public Features(long featid, String type_geometry, String s_properties,
			String s_geometry) {
		super();
		this.featid = featid;
		this.type_geometry = type_geometry;
		this.s_properties = s_properties;
		this.s_geometry = s_geometry;
		
	}
	*/
	/*
	public Features(long featid, String type_geometry, String s_properties,
			String s_geometry,Geometry g) {
		super();
		this.featid = featid;
		this.type_geometry = type_geometry;
		this.s_properties = s_properties;
		this.s_geometry = s_geometry;
		this.geometry = g;
	}
	*/
	/*
	public Features(long featid, String type_geometry, String s_properties,
			String s_geometry,Geometry g,String lat, String lng) {
		super();
		this.featid = featid;
		this.type_geometry = type_geometry;
		this.s_properties = s_properties;
		this.s_geometry = s_geometry;
		this.geometry = g;
		this.lat=lat;
		this.lng=lng;
	}*/
	public Features(long featid, String type_geometry, String s_properties,
			String s_geometry,Geometry g,Properties p,String lat, String lng) {
		super();
		this.featid = featid;
		this.type_geometry = type_geometry;
		this.s_properties = s_properties;
		this.s_geometry = s_geometry;
		this.geometry = g;
		this.properties=p;
		this.lat=lat;
		this.lng=lng;
	}
	public static String get_json_properties(String s_properties){
		
		String s="<table>";
		 s+="<tr><td>Atributo</td><td>Valor</td></tr>";
		try {
			JSONObject jsonObject = new JSONObject(s_properties);
			Iterator<String> iter = jsonObject.keys();
			while (iter.hasNext()) {
			    String key = iter.next();
			    try {
			        Object value = jsonObject.get(key);
			        s+="<tr><td>"+key+"</td><td>"+value+"</td></tr>";
			        
			    } catch (JSONException e) {
			        // Something went wrong!
			    	Util.Log("error=>"+ e.getMessage());
			    }
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s+="</table>";
		return s; 
	}


	public Features(String response) {
		// TODO Auto-generated constructor stub
		try {
			
			JSONObject jsonObject = new JSONObject(response);
			type=jsonObject.getString("type");
			String s=jsonObject.getString("properties");
			properties=gson.fromJson(s,Properties.class);		
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Features(JSONObject o) {
		try {			 
				  type=o.getString("type");
				  //Util.Log("json=>Features:"+type);				
				  s_properties=o.getString("properties");
				  properties=gson.fromJson(s_properties,Properties.class);	
				  //Util.Log("json=>Features:-------"+properties.featid);
				  featid=properties.featid;
				  Util.Log("json=>Features.featid:-------"+featid);
				  //Util.Log("json=>Features:-------"+s_properties);
				  s_geometry=o.getString("geometry");
				  geometry=gson.fromJson(s_geometry,Geometry.class);				  
				  //Util.Log("json=>geometry:-------"+o.getString("geometry")); 
				  type_geometry=geometry.getType();
			 	
				  
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Util.Log("json=>"+e.getMessage());
				e.printStackTrace();
			}
		
	}
	
	public Features(JSONArray jsonArray) {
		// TODO Auto-generated constructor stub
	try {
		  for(int i=0;i<jsonArray.length();i++){
			  JSONObject o = jsonArray.getJSONObject(i);
			  type=o.getString("type");
			  Util.Log("json=>Features:"+type);
			  String s=o.getString("properties");
			  properties=gson.fromJson(s,Properties.class);	
			  Util.Log("json=>Features:-------"+properties.featid);
			  Util.Log("json=>Features:-------"+s);
			  geometry=gson.fromJson(o.getString("geometry"),Geometry.class);		  
			  Util.Log("json=>geometry:-------"+o.getString("geometry")); 
		  }
		  
		  
		 
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Util.Log("json=>"+e.getMessage());
			e.printStackTrace();
		}
	}
	
}
