package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.SincronizarListAdapter;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Sincronizar;
import com.aguasnortesalta.ordenes.model.SincronizarList;

import com.aguasnortesalta.ordenes.utils.Util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SincronizarListFrag extends Fragment{
	private ListView lista;
	private SincronizarListAdapter adapter2;
	protected SincronizarList list = new SincronizarList();
	private DatabaseManager dm;

	private static OnListaSelectSincronizar onListaSelectSincronizar;

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
		 list.data=dm.getSincronizars();
		 adapter2=new SincronizarListAdapter(getActivity(),R.layout.usuario_list_item,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					    SincronizarListFrag.onListaSelectSincronizar.onClickList(list.data.get(position));
				}


			});
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			adapter2.filter(arg0);
		}
	 public  interface OnListaSelectSincronizar  {
		    // you can define any parameter as per your requirement
			void onClickList(Sincronizar  er);
	}
	public  OnListaSelectSincronizar getOnListaSelectSincronizar() {
		return onListaSelectSincronizar;
	}
	public  void setOnListaSelectSincronizar(
			OnListaSelectSincronizar onListaSelectSincronizar) {
		SincronizarListFrag.onListaSelectSincronizar = onListaSelectSincronizar;
	}


}