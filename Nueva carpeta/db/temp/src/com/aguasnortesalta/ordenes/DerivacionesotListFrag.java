package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.DerivacionesotListAdapter;
import com.aguasnortesalta.ordenes.adapter.DerivacionesotListAdapter.OnAdapterListaSelectDerivacionesot;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter.OnAdapterListaSelectRespuestasot;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Derivacionesot;
import com.aguasnortesalta.ordenes.model.DerivacionesotList;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.ServiciosxmotivoList;

import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DerivacionesotListFrag extends Fragment{
	private ListView lista;
	private DerivacionesotListAdapter adapter2;
	protected DerivacionesotList list = new DerivacionesotList();
	private DatabaseManager dm;

	private static OnListaSelectDerivacionesot onListaSelectDerivacionesot;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.ppal_list, container,
				false);
		return rootView;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	        verLista();

	 }
	 public void verLista(){
		 dm= new DatabaseManager(getActivity());
		 list.data=dm.getDerivacionesot();
		 if(list.data.size()<1){
			 Utils.showDialog(getActivity(), "Esta respuesta no tiene derivacion");
			 Ot ot=dm.get_ultima_ot();
			/*  
			 DerivacionesotList _list= new DerivacionesotList();
			_list.data=adapter2.getSeleccionados();				
			dm.setDerivacionesotActuales(_list);
			*/ 
			Util.SpSet(getActivity(), Configuracion.BIBLIOTECA, Configuracion.DERIVACIONES_OT_ACTUALES, String.valueOf(ot.getId_motivo()));
			 
		 }else{
			 adapter2=new DerivacionesotListAdapter(getActivity(),R.layout.check_list_item,  list.data);
			 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
			 lista.setAdapter(adapter2);
			 lista.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
	
						    DerivacionesotListFrag.onListaSelectDerivacionesot.onClickList(list.data.get(position));
					}


			});
		 adapter2.setOnAdapterListaSelectDerivacionesot(new OnAdapterListaSelectDerivacionesot(){

				@Override
				public void onClickList(int er) {
					// TODO Auto-generated method stub
					if(er==DerivacionesotListAdapter.ERROR_MAX_SELECCIONADOS){
						 Utils.showDialog(getActivity(), "Error maximo de seleccionados");
					}
				}});
		 }
		  
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			adapter2.filter(arg0);
		}
	 public  interface OnListaSelectDerivacionesot  {
		    // you can define any parameter as per your requirement
			void onClickList(Derivacionesot  er);
	}
	public  OnListaSelectDerivacionesot getOnListaSelectDerivacionesot() {
		return onListaSelectDerivacionesot;
	}
	public  void setOnListaSelectDerivacionesot(
			OnListaSelectDerivacionesot onListaSelectDerivacionesot) {
		DerivacionesotListFrag.onListaSelectDerivacionesot = onListaSelectDerivacionesot;
	}
	public void total_selecionados() {
		// TODO Auto-generated method stub			
		DerivacionesotList _list= new DerivacionesotList();
		_list.data=adapter2.getSeleccionados();		 
		dm.setDerivacionesotActuales(_list);
		DatabaseManager.SET_ADD_TIPO_FINALIZACION(getActivity(),_list.data.get(0).getId_mot_prox());
		
	}


}