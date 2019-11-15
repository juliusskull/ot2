package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.ConfigWEB;
import com.aguasnortesalta.ordenes.model.Confirmacion;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.utils.Util;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class Ot_FinalizarFrag extends Fragment{
	
	private WebView wvOT;
	private Ot ot_actual;
	private Gson gson = new Gson();
	private Button btn_finalizar;
	private DatabaseManager mg;
	private Button btn_observacion;
	private static OnOt_FinalizarClick onOt_FinalizarClick;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.ot_one_finalizar, container,
				false);
		 Util.Log("=>(0)onCreateView") ;
		return rootView;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	        wvOT= (WebView)getActivity().findViewById(R.id.wvOT);
	        Util.Log("OT =>----------");
	        Util.Log("OT (3)=>"+Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL ));
	    	ver_ot();
	        btn_finalizar=(Button) getActivity().findViewById(R.id.btn_fin_ot);
	        btn_observacion=(Button) getActivity().findViewById(R.id.btn_observacion_finalizar_ot);
	        
	        mg= new DatabaseManager(getActivity(),"OT-ONE-FIN");
	        LatLng latlng= mg.get_pto_actual();
	        
	        btn_finalizar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					onOt_FinalizarClick.onClickMap(ot_actual);
					
					
					//LatLng latlng= new LatLng(-24.8465554209979,-65.4637724567982);
					//mg.Ot_finalizarAdd(ot_actual, latlng, "", DatabaseManager.ESTADO_FINALIZAR,5);
				}
			});
	        btn_observacion.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					get_show_observacion();
				}
			});
	  }
	 public void get_show_observacion(){
		 final EditText txtUrl = new EditText(getActivity());

		// Set the default text to a link of the Queen
		//txtUrl.setHint("http://www.librarising.com/astrology/celebs/images2/QR/queenelizabethii.jpg");
		txtUrl.setText(ot_actual.getObservacion());
		new AlertDialog.Builder(getActivity())
		  .setTitle("OT")
		  .setMessage("Cargue una nueva observacion")
		  .setView(txtUrl)
		  .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int whichButton) {
		      String observacion = txtUrl.getText().toString();
		      //moustachify(null, url);
		      ot_actual.setObservacion(observacion);
		      String json = gson.toJson(ot_actual, Ot.class);
		      Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL,json );
		      wvOT.loadData(Util.justifyText(ot_actual.getStringPrecentacion()),"text/html;charset=utf-8", null);
				
		    }
		  })
		  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int whichButton) {
		    }
		  })
		  .show(); 
	 }
	 
	 public void ver_ot(){
		 Util.Log("OT =>----------2");
		 setOtson();		 
		 wvOT.loadData(Util.justifyText(ot_actual.getStringPrecentacion()),"text/html;charset=utf-8", null);
		   
		 
	 }
	 public void setOtson(){
		 Util.Log("OT=>"+Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL ));
		 ot_actual= gson.fromJson(Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.OT_ACTUAL ),Ot.class);
			
	 }
	 
	public  interface OnOt_FinalizarClick  {
		    // you can define any parameter as per your requirement
			void onClickMap(Ot  ot);
	}
	public  OnOt_FinalizarClick getOnOt_FinalizarClick() {
		return onOt_FinalizarClick;
	}
	public  void setOnOt_FinalizarClick(OnOt_FinalizarClick onOt_FinalizarClick) {
		Ot_FinalizarFrag.onOt_FinalizarClick = onOt_FinalizarClick;
	}
	public boolean  get_en_el_perimetro(Ot ot, Context context,LatLng latlng, int metros_fin	){
		
		if (DatabaseManager.GET_MODO_SEGURO_ACTIVADO(context)){
			return true;
		}
		
		try {
			/*
			Util.Log("distancia->LAT=>"+ot.getLat());
			
			Util.Log("distancia->(2)");
			final LatLng latlng= mg.get_pto_actual();
			Util.Log("distancia->(3)");
			*/
			LatLng ot_pto= new LatLng(Double.parseDouble(ot.getLat()),Double.parseDouble(ot.getLng()));
			Util.Log("distancia->(4)");
			
			//ConfigWEB conf=DatabaseManager.GET_ULTIMA_CONFIG(getActivity());
			double distancia = Util.getDistanceBetween(ot_pto.latitude, ot_pto.longitude, latlng.latitude, latlng.longitude);
			
			if(metros_fin<0){
				return true;
			}			
			double PERIMETRO = metros_fin;
			PERIMETRO=metros_fin;
		
			
			Util.Log("distancia=>"+distancia);
			if(distancia<PERIMETRO){
				//mg.add_confirmacion_distancia(String.valueOf(distancia),String.valueOf(ot.getNro_ot()));
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("error=>"+e.getMessage());
			return false;
		}
	}
	 
}
