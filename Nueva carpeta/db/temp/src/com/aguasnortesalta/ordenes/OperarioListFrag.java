package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter;
import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter.OnAdapterListaSelectOperario;
import com.aguasnortesalta.ordenes.adapter.OperarioListArrayAdapter;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Operario;
import com.aguasnortesalta.ordenes.model.OperarioList;

import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class OperarioListFrag extends Fragment{
	private ListView lista;
	private OperarioListAdapter adapter2;
	private OperarioListArrayAdapter adapter3;
	protected OperarioList list = new OperarioList();
	private DatabaseManager mg;
	private Operario[] operarios_array;

	private static OnListaSelectOperario onListaSelectOperario;

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
		 mg= new DatabaseManager(getActivity());
		 list.data=mg.getOperarios();
		 //operarios_array= dm.getOperariosArray();
		 adapter2=new OperarioListAdapter(getActivity(),R.layout.check_list_item,  list.data);
		 //adapter3=new OperarioListArrayAdapter(getActivity(),R.layout.usuario_list_item,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);		
		 lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		 //lista.setItemsCanFocus(false);
		 /*
		 ArrayAdapter<Operario> adapter = new ArrayAdapter<Operario>(this,
	                android.R.layout.simple_list_item_1,  operarios_array);
		 */
		 lista.setAdapter(adapter2);
		 /**/
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
		
				}


			});
		 adapter2.setOnAdapterListaSelectOperario(new OnAdapterListaSelectOperario(){

			@Override
			public void onClickList(int er) {
				// TODO Auto-generated method stub
				if(er==OperarioListAdapter.ERROR_MAX_SELECCIONADOS){
					 Utils.showDialog(getActivity(), "Error maximo de seleccionados");
				}
			}});
			
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			adapter2.filter(arg0);
		}
	 public void total_selecionados(){
		 Utils.showDialog(getActivity(), "Se seleccionaron "+adapter2.getSeleccionados().size()+" operarios");
		 OperarioList operarios_list= new OperarioList();
		 operarios_list.data=adapter2.getSeleccionados();
		 mg.setOperariosActuales(operarios_list);
		 
		 
	 }
	 public  interface OnListaSelectOperario  {
		    // you can define any parameter as per your requirement
			void onClickList(Operario  er);
	}
	public  OnListaSelectOperario getOnListaSelectOperario() {
		return onListaSelectOperario;
	}
	public  void setOnListaSelectOperario(
			OnListaSelectOperario onListaSelectOperario) {
		OperarioListFrag.onListaSelectOperario = onListaSelectOperario;
	}


}