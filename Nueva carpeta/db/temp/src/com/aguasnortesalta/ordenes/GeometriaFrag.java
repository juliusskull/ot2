package com.aguasnortesalta.ordenes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.aguasnortesalta.ordenes.OTListFrag.OnListaSelectOt;
import com.aguasnortesalta.ordenes.adapter.Atributis_listasListSpinAdapter;
import com.aguasnortesalta.ordenes.adapter.Atributos_listasListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Atributos_listas;
import com.aguasnortesalta.ordenes.model.Componentesxatributos;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.Sincronizar;
import com.aguasnortesalta.ordenes.model.geometrias.Features;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class GeometriaFrag  extends Fragment{
	private TextView txt_featid;
	private DatabaseManager dm;
	private Features feature;
	private List<Componentesxatributos> atributos;
	private LinearLayout ly;
	private long TIPO_COMPONENTE=1;
	private long ID_COMPONENTE=2102;
	private String lng;
	private String valor;
	private String lat;
//	private List<Atributo> atributos= new ArrayList<Atributo>();
	private InterfaceDinamica interfaceDinamica;
	private Button btn_asociar;
	private String s_ot_existente="";
	private Button btn_nueva_distancia;
	private static OnADDDistancia onEventListener;
	
	
	//Calendar myCalendar = Calendar.getInstance();
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.geometria_one, container,
				false);
		 Util.Log("=>(0)onCreateView") ;
		return rootView;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	        dm = new DatabaseManager(getActivity(),"GEOMETRIA");
	        ly=(LinearLayout)getActivity().findViewById(R.id.ly_atributos);
	        List<Features> features = dm.getGeometrias(dm.get_ultimo_featid());
	        feature=features.get(0);
	  	    interfaceDinamica = new InterfaceDinamica(getActivity());
	        //atributos=dm.getComponentesxAtributos_view(TIPO_COMPONENTE, ID_COMPONENTE );
	        txt_featid=(TextView)getActivity().findViewById(R.id.txt_geometria_featid);	        
	    	btn_asociar=(Button) getActivity().findViewById(R.id.btn_asociar_con_ot);
	    	btn_nueva_distancia=(Button) getActivity().findViewById(R.id.btn_nueva_distancia);
	    	
	    	try {
				Ot ot=  dm.ot_get_seleccionada();			
				s_ot_existente=" Ot:"+ot.getNro_ot();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				btn_asociar.setVisibility(Button.INVISIBLE);
				e.printStackTrace();
			}    	
	       	
	    	btn_asociar.setEnabled(DatabaseManager.IsACTIVAR_BOTON_ASOCIAR(getActivity()));
	    	s_ot_existente=DatabaseManager.IsACTIVAR_BOTON_ASOCIAR(getActivity())?s_ot_existente:"";
	    	
	    	
	    	btn_asociar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Ot ot=  dm.ot_get_seleccionada();
					if(btn_asociar.getText().toString().equals(getResources().getString(R.string.asociar_con_el_tramo))){
						btn_asociar.setText(getResources().getString(R.string.disociar_con_el_tramo));
						dm.add_ot_asociado_featid(String.valueOf(ot.getNro_ot()), dm.get_ultimo_featid(), true);
					}else{
						btn_asociar.setText(getResources().getString(R.string.asociar_con_el_tramo));
						dm.add_ot_asociado_featid(String.valueOf(ot.getNro_ot()), dm.get_ultimo_featid(), true);
					}
				}
			});
	    	btn_nueva_distancia.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					onEventListener.onClickList(0);
				}
			});
	        actualizar_atributos();           
	     	      
	 }
	 
	public void actualizar_atributos(){
	    try {
  	        
  	        txt_featid.setText("FeatId:"+feature.featid + s_ot_existente);       
			atributos=dm.getComponentesxAtributos_view(dm.get_ultimo_featid() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("Error=>"+e.getMessage() +"->featid="+dm.get_ultimo_featid());
			Utils.showDialog(getActivity(), "Error al cargar featid="+dm.get_ultimo_featid()+". Error:"+e.getMessage());
		}
		   try {
				for (Componentesxatributos atributo : atributos) {
					
					ly.addView(interfaceDinamica.get_titulo_label(atributo.desc_atributo));	
					
					if(atributo.tipo_dato.endsWith("MEDIDA")){
						ly.addView(interfaceDinamica.get_edit_text(atributo.getValor(), (int)atributo.get_id())); 
					}
					if(atributo.tipo_dato.endsWith("CADENA")){
						ly.addView(interfaceDinamica.get_edit_text(atributo.getValor(), (int)atributo.get_id(),InputType.TYPE_CLASS_NUMBER)); 
					}
					if(atributo.tipo_dato.endsWith("FECHA")){
						ly.addView(interfaceDinamica.get_edit_date(atributo.getValor(), (int)atributo.get_id())); 
					}
					if(atributo.tipo_dato.endsWith("HORA")){
						ly.addView(interfaceDinamica.get_edit_time(atributo.getValor(), (int)atributo.get_id())); 
					}
					if(atributo.tipo_dato.endsWith("LISTA")){
						Util.Log("atributo => find()---------------- "+atributo.getId_tipo_atributo());
						List<Atributos_listas> data = dm.getAtributos_lista(atributo.getId_tipo_atributo());	        		
						Atributis_listasListSpinAdapter adapter = new Atributis_listasListSpinAdapter(getActivity(),R.layout.check_list_item,data);
						int index=0;
						Util.Log("atributo => find() valor:"+atributo.getValor());
						for(int i=0;i<data.size();i++){
							Util.Log("atributo => find(): "+i+":"+data.get(i).getId_lista());
							if(atributo.getValor().trim().equalsIgnoreCase(data.get(i).getId_lista().trim())){
								index=i;
								break;
							}
							
						}
						Util.Log("atributo => find() index:"+index);
						ly.addView(interfaceDinamica.get_spinner("",(int)atributo.get_id() ,index, adapter)); 
						
					}else{
						if(atributo.unidad_medida!=null && (atributo.unidad_medida.length()>0)){
							ly.addView(interfaceDinamica.get_label_referencia("("+atributo.unidad_medida+")")); 
						}
						

					}	        		        
						        	
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if(atributos!=null){
					Utils.showDialog(getActivity(), "No tiene Atributos relacionados ("+atributos.size()+")");
					Util.Log("Error geometrias=>"+atributos.size()+ " Error:"+e.getMessage());
				}	
				else{
					Utils.showDialog(getActivity(), "No tiene Atributos relacionados(Null)");
					Util.Log("Error geometrias=>null");
				}
					
					
			}	
	} 
	public void set_geometria() {
		// TODO Auto-generated method stub
		//dm.add_sincronisacion(new Sincronizar(String.valueOf(ID_COMPONENTE), String.valueOf(TIPO_COMPONENTE), valor, lat, lng, 0));
		List<Componentesxatributos> _atributos= new ArrayList<Componentesxatributos>();
		for (Componentesxatributos a : atributos) {
			Util.Log("set->atributos =>"+a.get_id() +" Tipo:"+ a.tipo_dato);
			
			try {
				if(a.tipo_dato.trim().equalsIgnoreCase("LISTA")){
					Spinner sp=(Spinner) ly.findViewById((int)a.get_id());
					
					//Spinner sp=(Spinner)ly.findViewById((int)a.get_id());
					Util.Log("set->valor =>(Spinner):"+ sp.getSelectedItem());
					Atributos_listas a_l = (Atributos_listas) sp.getSelectedItem();					
					Util.Log("set->valor =>(Spinner)"+ a_l.getId_lista());
					a.setValor(a_l.getId_lista());
				}else{
					EditText v = (EditText)ly.findViewById((int)a.get_id());
					Util.Log("set->valor =>"+v.getText().toString());
					a.setValor(v.getText().toString());
					
				}
				_atributos.add(a);
				dm.componentesxAtributos_actualizar(a);	
				
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Util.Log("set->error =>"+a.get_id()+ ":"+e.getMessage());
				e.printStackTrace();
			}
			
		}
		dm.add_sincronisacion(dm.get_ultimo_featid(),Configuracion.ENVIO_FEITID,_atributos);
		
	}
	 
	  public  interface OnADDDistancia  {
		    // you can define any parameter as per your requirement
			void onClickList(int  er);
		}
	public  OnADDDistancia getOnEventListener() {
		return onEventListener;
	}
	public  void setOnEventListener(OnADDDistancia onEventListener) {
		GeometriaFrag.onEventListener = onEventListener;
	}
	  

	
}
