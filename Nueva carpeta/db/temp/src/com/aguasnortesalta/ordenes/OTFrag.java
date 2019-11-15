package com.aguasnortesalta.ordenes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.ConfigWEB;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.model.Template;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

public class OTFrag extends Fragment {
	private static final String ARG_SECTION_NUMBER = "";
	private WebView myWebView;
	private  Ot ot_actual;

	private WebView txtView;
	private WebView txtOTDescripcion;
	private Button btn_ver_plano;
	private Button btn_comenzar;
	private Button btn_cancelar;
	private DatabaseManager mg;
	
	private static OnOtClick onOtClick;
	
	public static final int OT_INICIALIZAR=1;	
	public static final int OT_VER_MAPA=2;
	public static final int OT_FINALIZAR=3;
	protected static final int OT_CANCELAR = 4;
	public int TEMPLATE_ID=1;
	private Template template;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.ot_one, container,
				false);
		 Util.Log("=>(0)onCreateView") ;
		return rootView;
	}
	
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	       /* txt_html=(TextView)getActivity().findViewById(R.id.txt_content_html);
	        */
	      Util.Log("=>(1)onActivityCreated") ;	          
	      mg= new DatabaseManager(getActivity(),"OT-ONE");
	      
	      final LatLng latlng= mg.get_pto_actual();
	      
	      Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA,String.valueOf(latlng.latitude));
	      Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.LNG_ULTIMA,String.valueOf(latlng.longitude));
	      
	      Util.Log("distancia->(3)" +Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA));
	      
	      txtOTDescripcion=(WebView)getActivity().findViewById(R.id.txtOTDescripcion);
	      
	     // TEMPLATE_ID =mg.GET_TEMPLATE_ACTUAL();
	      
	      if(TEMPLATE_ID==1){
	    	  txtOTDescripcion.loadData(Util.justifyText(ot_actual.getStringPrecentacion(latlng)),"text/html;charset=utf-8", null);
	      
	      }else{
	    	 template=  mg.get_template(TEMPLATE_ID);
	    	 txtOTDescripcion.loadData(Util.justifyText(template.getStringPrecentacion()),"text/html;charset=utf-8", null);
		      
	      }
	      
	      btn_comenzar= (Button)getActivity().findViewById(R.id.btnComenzar);
	      btn_cancelar= (Button)getActivity().findViewById(R.id.btnCancelar);
	      
	  	  Gson gson = new Gson();
		  Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.OT_SELECCIONADA   , gson.toJson(ot_actual,Ot.class));
		
	      
	      btn_comenzar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					onOtClick.onClickMap(ot_actual,OT_INICIALIZAR);
					/* solo para no pasar el null*/				
					
					///mg.Ot_finalizarAdd(ot_actual,  "", DatabaseManager.ESTADO_ACTIVO,-1);
					
				}
				});
	      			btn_cancelar.setOnClickListener(new OnClickListener() {
				
	 				@Override
	 				public void onClick(View v) {
	 					// TODO Auto-generated method stub
	 					onOtClick.onClickMap(ot_actual,OT_CANCELAR);
	 				}
	 				});
	
	      
	      
	 }
	 
	public boolean  get_en_el_perimetro(Ot ot, Context context,LatLng latlng, int metros_ini	){
		
		if (DatabaseManager.GET_MODO_SEGURO_ACTIVADO(context)){
			return true;
		}
		
		try {
			Util.Log("distancia->LAT=>"+ot.getLat());			
			Util.Log("distancia->(2)");
			Util.Log("distancia->(3)" +Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA));
			/*
			//final LatLng latlng= mg.get_pto_actual();
			String s_lat=Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.LAT_ULTIMA);
			String s_lng=Util.SpGet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.LNG_ULTIMA);
			
			Util.Log("distancia->(3)" +s_lng);
			Util.Log("distancia->(3)" +s_lat );
			
			Util.Log("distancia->(3)" +s_lat +"-"+s_lng);		
			final LatLng latlng= new LatLng(Double.parseDouble(s_lat), Double.parseDouble(s_lng));
			
			Util.Log("distancia->(3)");		
			*/
			LatLng ot_pto= new LatLng(Double.parseDouble(ot.getLat()),Double.parseDouble(ot.getLng()));			
			
			Util.Log("distancia->(4)");
			//ConfigWEB conf=DatabaseManager.GET_ULTIMA_CONFIG(getActivity());
			
			double distancia = Util.getDistanceBetween(ot_pto.latitude, ot_pto.longitude, latlng.latitude, latlng.longitude);
	
			//double PERIMETRO = conf.metros_ini;			
		
			Util.Log("distancia=>"+distancia);
			
			if(distancia<metros_ini){
				/*mg.add_confirmacion_distancia(String.valueOf(distancia),String.valueOf(ot.getNro_ot()));*/
				return true;
			}else{
				Util.Log("distancia=>"+distancia +"->per:"+metros_ini);
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block			
			Util.Log("error=>"+e.getMessage());
			return false;
		}
	}
	/*
public boolean  get_en_el_perimetro_finalizacion(Ot ot, Context context 	){
		
		
		try {
			Util.Log("distancia->LAT=>"+ot.getLat());
			
			Util.Log("distancia->(2)");
			final LatLng latlng= mg.get_pto_actual();
			Util.Log("distancia->(3)");
			LatLng ot_pto= new LatLng(Double.parseDouble(ot.getLat()),Double.parseDouble(ot.getLng()));
			Util.Log("distancia->(4)");
			double distancia = Util.getDistanceBetween(ot_pto.latitude, ot_pto.longitude, latlng.latitude, latlng.longitude);
			
			Util.Log("distancia=>"+distancia);
			if(distancia<Configuracion.GET_PERIMETRO_FINALIZACION){
				mg.add_confirmacion_distancia(String.valueOf(distancia),String.valueOf(ot.getNro_ot()));
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
*/

public Ot getOt_actual() {
		return ot_actual;
	}

	public void setOt_actual(Ot ot_actual) {
		this.ot_actual = ot_actual;
		
	}

	public Fragment newInstance(int position, Ot ot_actual2) {
		// TODO Auto-generated method stub
		OTFrag fragment = new OTFrag();
		fragment.setOt_actual(ot_actual2);
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, position);
		fragment.setArguments(args);
		//Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.OT_SELECCIONADA, valor);
		//mg.ot_seleccionada(ot_actual2);
			return fragment;
	}
	
	public  interface OnOtClick  {
	    // you can define any parameter as per your requirement
		void onClickMap(Ot  ot, int estado);
	}

	public  OnOtClick getOnOtClick() {
		return onOtClick;
	}

	public  void setOnOtClick(OnOtClick onOtClick) {
		OTFrag.onOtClick = onOtClick;
	}
	 
}
