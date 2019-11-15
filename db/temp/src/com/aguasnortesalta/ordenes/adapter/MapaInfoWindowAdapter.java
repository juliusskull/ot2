package com.aguasnortesalta.ordenes.adapter;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aguasnortesalta.ordenes.MapActivity;
import com.aguasnortesalta.ordenes.utils.Util;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MapaInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
	    Context context;
	    
	    public MapaInfoWindowAdapter(Context context){
	    	this.context=context;
	    }

	    public View getInfoWindow(Marker marker) {  	  
		    
	         return null;
	      }

	      @Override
	      public View getInfoContents(Marker marker) {

	        LinearLayout info = new LinearLayout(context);
	        info.setOrientation(LinearLayout.VERTICAL);

	        TextView title = new TextView(context);
	        title.setTextColor(Color.BLACK);
	        title.setGravity(Gravity.CENTER);
	        title.setTypeface(null, Typeface.BOLD);
	        title.setText(marker.getTitle());
	        info.addView(title);
	        
	       // Util.Log("Snippet=>"+marker.getSnippet());		
	        String featid="";
	        try {
				JSONObject jsonObject = new JSONObject(marker.getSnippet());
				Iterator<String> iter = jsonObject.keys();
				while (iter.hasNext()) {
					TextView snippet = new TextView(context);
				    snippet.setTextColor(Color.GRAY);
				    String key = iter.next();
				   
				    	  try {
				    		    Object value=null;
						    	if(!key.equals("featid")){
						    		value = jsonObject.get(key);						      
						    		snippet.setText(key +":"+value);	
						    		info.addView(snippet);
						    	 }else{
								    	featid=String.valueOf(value);
								 }
						        
						    } catch (JSONException e) {
						        // Something went wrong!
						    	Util.Log("error=>"+ e.getMessage());
						    }
				    
				  
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			      

	      return info;
	    }
	
}
