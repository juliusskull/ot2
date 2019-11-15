package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.EquipoListAdapter;
import com.aguasnortesalta.ordenes.adapter.ServiciosxmotivoListAdapter;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.adapter.EquipoListAdapter.OnAdapterListaSelectEquipo;
import com.aguasnortesalta.ordenes.adapter.ServiciosxmotivoListAdapter.OnAdapterListaSelectSerXMot;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.EquipoList;
import com.aguasnortesalta.ordenes.model.Serviciosxmotivo;
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

public class ServiciosxmotivoListFrag extends Fragment{
	private ListView lista;
	private ServiciosxmotivoListAdapter adapter2;
	protected ServiciosxmotivoList list = new ServiciosxmotivoList();
	private DatabaseManager dm;

	private static OnListaSelectServiciosxmotivo onListaSelectServiciosxmotivo;

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
		 list.data=dm.getServiciosxmotivo();
		 adapter2=new ServiciosxmotivoListAdapter(getActivity(),R.layout.check_list_item,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					   // ServiciosxmotivoListFrag.onListaSelectServiciosxmotivo.onClickList(list.data.get(position));
				}


			});
		 adapter2.setOnAdapterListaSelectSerXMot(new OnAdapterListaSelectSerXMot(){

				@Override
				public void onClickList(int er) {
					// TODO Auto-generated method stub
					if(er==ServiciosxmotivoListAdapter.ERROR_MAX_SELECCIONADOS){
						 Utils.showDialog(getActivity(), "Error maximo de seleccionados");
					}
				}});
	 }
	 public void total_selecionados(){
		 Utils.showDialog(getActivity(), "Se seleccionaron "+adapter2.getSeleccionados().size()+" servicios");
		 ServiciosxmotivoList _list= new ServiciosxmotivoList();
		 _list.data=adapter2.getSeleccionados();
		 dm.setServiciosxmotivoActuales(_list);
		 
		 
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			//adapter2.filter(arg0);
		}
	 public  interface OnListaSelectServiciosxmotivo  {
		    // you can define any parameter as per your requirement
			void onClickList(Serviciosxmotivo  er);
	}
	public  OnListaSelectServiciosxmotivo getOnListaSelectServiciosxmotivo() {
		return onListaSelectServiciosxmotivo;
	}
	public  void setOnListaSelectServiciosxmotivo(
			OnListaSelectServiciosxmotivo onListaSelectServiciosxmotivo) {
		ServiciosxmotivoListFrag.onListaSelectServiciosxmotivo = onListaSelectServiciosxmotivo;
	}


}