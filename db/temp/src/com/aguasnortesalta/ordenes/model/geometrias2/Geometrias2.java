package com.aguasnortesalta.ordenes.model.geometrias2;

import java.util.List;

import com.aguasnortesalta.ordenes.utils.Util;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometrias2 {
private Gson gson = new Gson();
@SerializedName("type")
@Expose
private String type;
@SerializedName("features")
@Expose
private List<Feature> features = null;

public void load(){
	Util.Log("json=>---");
	
	for(int i=0; i<features.size();i++){
		Util.Log("json=>---"+i);
		//Util.Log(features.get(i).toString());
		Util.Log("json=>---"+features.get(i).getProperties().getFeatid());
		Util.Log("json=>---"+gson.toJson(features.get(i).getProperties()));
		//gson.toJson(src)
	}
	
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public List<Feature> getFeatures() {
return features;
}

public void setFeatures(List<Feature> features) {
this.features = features;
}

}