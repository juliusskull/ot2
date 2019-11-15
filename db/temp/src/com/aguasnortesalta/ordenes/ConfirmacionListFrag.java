package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.ConfirmacionListAdapter;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Confirmacion;
import com.aguasnortesalta.ordenes.model.ConfirmacionList;

import com.aguasnortesalta.ordenes.utils.Util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ConfirmacionListFrag extends Fragment{
	private ListView lista;
	private ConfirmacionListAdapter adapter2;
	protected ConfirmacionList list = new ConfirmacionList();
	private DatabaseManager dm;

	private static OnListaSelectConfirmacion onListaSelectConfirmacion;

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
		 dm= new DatabaseManager(getActivity(),"CONFIRMAR LIST");
		 list.data=dm.getConfirmacions();
		 adapter2=new ConfirmacionListAdapter(getActivity(),R.layout.usuario_list_item,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					    ConfirmacionListFrag.onListaSelectConfirmacion.onClickList(list.data.get(position));
				}


			});
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			adapter2.filter(arg0);
		}
	 public  interface OnListaSelectConfirmacion  {
		    // you can define any parameter as per your requirement
			void onClickList(Confirmacion  er);
	}
	public  OnListaSelectConfirmacion getOnListaSelectConfirmacion() {
		return onListaSelectConfirmacion;
	}
	public  void setOnListaSelectConfirmacion(
			OnListaSelectConfirmacion onListaSelectConfirmacion) {
		ConfirmacionListFrag.onListaSelectConfirmacion = onListaSelectConfirmacion;
	}


}