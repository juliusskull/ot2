package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.MaterialesotListAdapter;
import com.aguasnortesalta.ordenes.adapter.MaterialesotListAdapter.OnAdapterListaSelectMaterialesot;
import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter.OnAdapterListaSelectOperario;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Materialesot;
import com.aguasnortesalta.ordenes.model.MaterialesotList;
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

public class MaterialesotListFrag extends Fragment{
	private ListView lista;
	private MaterialesotListAdapter adapter2;
	protected MaterialesotList list = new MaterialesotList();
	private DatabaseManager dm;

	private static OnListaSelectMaterialesot onListaSelectMaterialesot;

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
		 dm= new DatabaseManager(getActivity(),"MATERIALES LIST");
		 list.data=dm.getMaterialesots();
		 adapter2=new MaterialesotListAdapter(getActivity(),R.layout.check_list_item,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					   // MaterialesotListFrag.onListaSelectMaterialesot.onClickList(list.data.get(position));
				}


			});
		 adapter2.setOnAdapterListaSelectMaterialesot(new OnAdapterListaSelectMaterialesot(){

				@Override
				public void onClickList(int er) {
					// TODO Auto-generated method stub
					if(er==OperarioListAdapter.ERROR_MAX_SELECCIONADOS){
						 Utils.showDialog(getActivity(), "Error maximo de seleccionados");
					}
				}});
	 }
	 public void total_selecionados(){
		 Utils.showDialog(getActivity(), "Se seleccionaron "+adapter2.getSeleccionados().size()+" materiales");
		 MaterialesotList _list= new MaterialesotList();
		 _list.data=adapter2.getSeleccionados();		
		 dm.setMaterialesotActuales(_list);
		 
		 
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			adapter2.filter(arg0);
		}
	 public  interface OnListaSelectMaterialesot  {
		    // you can define any parameter as per your requirement
			void onClickList(Materialesot  er);
	}
	public  OnListaSelectMaterialesot getOnListaSelectMaterialesot() {
		return onListaSelectMaterialesot;
	}
	public  void setOnListaSelectMaterialesot(
			OnListaSelectMaterialesot onListaSelectMaterialesot) {
		MaterialesotListFrag.onListaSelectMaterialesot = onListaSelectMaterialesot;
	}


}