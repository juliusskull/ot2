package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GeometriaDistanciaFrag    extends Fragment{
	public DatabaseManager dm;
	public EditText txt_distancia;
	public RadioButton radio_dv;
	public RadioButton radio_dlm;
	public RadioGroup radioDistancia;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.geometria_distancias, container,
				false);
		
		return rootView;
	}
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	        
	        txt_distancia=(EditText)getActivity().findViewById(R.id.distancia_txt);
	        radioDistancia=(RadioGroup) getActivity().findViewById(R.id.radioDistancia);
	        radio_dv=(RadioButton) getActivity().findViewById(R.id.radio_dv);
	        radio_dlm=(RadioButton) getActivity().findViewById(R.id.radio_dlm);
	     
	       
	        
	  }
	  public void add_distnacias(){
		  try {
			String tipo="DLM";
			  if(radio_dv.isChecked()){
				  tipo="DV";
			  }
			  String valor="0";
			  if(txt_distancia.getText().length()>0){
				  valor=txt_distancia.getText().toString();
			  }
			  dm = new DatabaseManager(getActivity(),"DISTANCIA");
			  String featid = dm.get_ultimo_featid();
			  
			  dm.add_distancia(featid, tipo, valor);
			  Utils.showDialog(getActivity(), "Se agrego la nueva distancia");
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Utils.showDialog(getActivity(), "Error:"+e.getMessage());
		}
	  }
	  

}
