package com.aguasnortesalta.ordenes.model.geometrias;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.style.BulletSpan;

import com.aguasnortesalta.ordenes.utils.Util;
import com.google.gson.Gson;



public class GeoList {
	
	public String type;	
	//public  Features features= null;
	public List<Features> features= new ArrayList<Features>();
	private Gson gson = new Gson();
	public GeoList(String response) {
		// TODO Auto-generated constructor stub
		
		try {
			JSONObject jsonObject = new JSONObject(response);
			type =jsonObject.getString("type");
		    JSONArray jsonArray = jsonObject.getJSONArray("features");
		   // Util.Log("json=>"+jsonArray.toString());
			    
		    for(int i=0;i<jsonArray.length();i++){
		    	features.add(new Features(jsonArray.getJSONObject(i)));
		    }
		    
		    
		    
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public GeoList(StringBuilder response) {
		// TODO Auto-generated constructor stub
		
		try {
			JSONObject jsonObject = new JSONObject(response.toString());
			type =jsonObject.getString("type");
		    JSONArray jsonArray = jsonObject.getJSONArray("features");
		    Util.Log("json=>"+jsonArray.toString());
			    
		    for(int i=0;i<jsonArray.length();i++){
		    	features.add(new Features(jsonArray.getJSONObject(i)));
		    }
		    
		    
		    
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
