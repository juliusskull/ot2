package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Ot_aperturas;
import com.aguasnortesalta.ordenes.model.Ot_aperturas_superficie;
import com.aguasnortesalta.ordenes.utils.Util;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

public class AperturaFrag extends Fragment {
	/*
	 * estado:       Tapada , semi-tapada, abierta
		senializacion:si no 
	 * */
	private static final String ARG_SECTION_NUMBER = "";
	private EditText txt_ancho;
	private EditText txt_largo;
	private EditText txt_profundidad;
	private Spinner sp_tipo_apertura;
	private Spinner sp_tipo_superficie;
	private DatabaseManager mg;
	private Ot_aperturas_superficie[] superficies;
	private String[] calzadas;
	private Ot_aperturas ot_apertura= new Ot_aperturas();
	private ArrayAdapter<Ot_aperturas_superficie> adapter_superficies;
	private String[] aperturas_estado;
	private String[] aperturas_senializacion;
	private Spinner sp_estado_apertura;
	private Spinner sp_senializacion_apertura;
	private ArrayAdapter adapter_senializacion;
	private ArrayAdapter adapter_estado;
	private ArrayAdapter adapter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.aperturas_alta, container,
				false);
		 Util.Log("=>(0)onCreateView") ;
		return rootView;
	}
	public String getCalzadaActual(int arg2){
		String f="";
		if(calzadas[arg2].startsWith("Calzada")){
			f="C";
		}else{
			f="V";
		}	
		return f;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	        mg= new DatabaseManager(getActivity(),"APERTURA");
	        sp_tipo_apertura=(Spinner)getActivity().findViewById(R.id.sp_tipo_apertura);
	        sp_tipo_superficie=(Spinner)getActivity().findViewById(R.id.sp_superficies);
	        sp_estado_apertura=(Spinner)getActivity().findViewById(R.id.sp_estado_apertura);
	        sp_senializacion_apertura=(Spinner)getActivity().findViewById(R.id.sp_senializacion_apertura);
	        
	        txt_ancho=(EditText)getActivity().findViewById(R.id.txt_ancho);
	        txt_largo=(EditText)getActivity().findViewById(R.id.txt_largo);
	        txt_profundidad=(EditText)getActivity().findViewById(R.id.txt_profundidad);
	        
	        txt_ancho.setText("");
	        txt_largo.setText("");
	        txt_profundidad.setText("");
	        
	        aperturas_estado= Configuracion.GET_APERTURA_ESTADO();
	        aperturas_senializacion= Configuracion.GET_APERTURA_SENIALIZACION();	        
	        Resources res = getResources();
	        
	        calzadas = Configuracion.GET_TIPO_CALZADA();//res.getStringArray(R.array.tipo_calzadas_array);
	        adapter =  new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, calzadas);
	        
	        adapter_estado =  new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, aperturas_estado);
	        sp_estado_apertura.setAdapter(adapter_estado);
	        
	        adapter_senializacion =  new ArrayAdapter(getActivity(),  android.R.layout.simple_spinner_item, aperturas_senializacion);
	        sp_senializacion_apertura.setAdapter(adapter_senializacion);
	        
	        sp_tipo_apertura.setAdapter(adapter);	        
	        sp_tipo_apertura.performClick();
	        
	        superficies=mg.getOT_Aperturas_superficie("C");
	        adapter_superficies =  new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, superficies);
	        sp_tipo_superficie.setAdapter(adapter_superficies);
	        
	        sp_tipo_apertura.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub									
					  ot_apertura.setTipo_apertura(getCalzadaActual(arg2));
					  superficies=mg.getOT_Aperturas_superficie(getCalzadaActual(arg2));					
					  adapter_superficies =  new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, superficies);
				      sp_tipo_superficie.setAdapter(adapter_superficies);
				      sp_tipo_superficie.performClick();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}});
	        
	        
	 }
	 
	 @Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		txt_ancho.setText("");
	    txt_largo.setText("");
	    txt_profundidad.setText("");
	}
	public void add_apertura(){
		 
		 Ot_aperturas_superficie superficie = superficies[sp_tipo_superficie.getSelectedItemPosition()];
		 
		 ot_apertura.setId_tipo_material(superficie.getId_superficie());
		 ot_apertura.setAncho(txt_ancho.getText().toString());
		 ot_apertura.setLargo(txt_largo.getText().toString());
		 ot_apertura.setProfundidad(txt_profundidad.getText().toString());
		 ot_apertura.setEstado_apertura(aperturas_estado[sp_estado_apertura.getSelectedItemPosition()]);		 
		 ot_apertura.setId_tipo_senial(sp_estado_apertura.getSelectedItemPosition());
		 ot_apertura.setTipo_apertura(getCalzadaActual(sp_tipo_apertura.getSelectedItemPosition()));
		 ot_apertura.setFchalta(Util.getFechaActualFormat());
		 mg.setNuevaApertura(ot_apertura);
		 
	 }
}
