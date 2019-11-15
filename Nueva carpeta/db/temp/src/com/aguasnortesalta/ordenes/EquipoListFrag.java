package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.EquipoListAdapter;
import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter;
import com.aguasnortesalta.ordenes.adapter.EquipoListAdapter.OnAdapterListaSelectEquipo;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Equipo;
import com.aguasnortesalta.ordenes.model.EquipoList;
import com.aguasnortesalta.ordenes.model.OperarioList;

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

public class EquipoListFrag extends Fragment{
	private ListView lista;
	private EquipoListAdapter adapter2;
	protected EquipoList list = new EquipoList();
	private DatabaseManager dm;

	private static OnListaSelectEquipo onListaSelectEquipo;

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
		 dm= new DatabaseManager(getActivity(),"EQUIPO LIST");
		 list.data=dm.getEquipos();
		 adapter2=new EquipoListAdapter(getActivity(),R.layout.check_list_item,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					  //  EquipoListFrag.onListaSelectEquipo.onClickList(list.data.get(position));					    
					   // getOnListaSelectEquipo().onClickList(list.data.get(position));
				}


			});
		 adapter2.setOnAdapterListaSelectEquipo(new OnAdapterListaSelectEquipo(){

			@Override
			public void onClickList(int er) {
				// TODO Auto-generated method stub
				if(er==EquipoListAdapter.ERROR_MAX_SELECCIONADOS){
					 Utils.showDialog(getActivity(), "Error maximo de seleccionados");
				}
			}});
	 }
	 public void total_selecionados(){
		 Utils.showDialog(getActivity(), "Se realizaron "+adapter2.getSeleccionados().size()+" selecciones");
		 EquipoList operarios_list= new EquipoList();
		 operarios_list.data=adapter2.getSeleccionados();
		 dm.setEquiposActuales(operarios_list);
		 
		 
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			adapter2.filter(arg0);
		}
	 public  interface OnListaSelectEquipo  {
		    // you can define any parameter as per your requirement
			void onClickList(Equipo  er);
	}
	public  OnListaSelectEquipo getOnListaSelectEquipo() {
		return onListaSelectEquipo;
	}
	public  void setOnListaSelectEquipo(
			OnListaSelectEquipo onListaSelectEquipo) {
		EquipoListFrag.onListaSelectEquipo = onListaSelectEquipo;
	}


}