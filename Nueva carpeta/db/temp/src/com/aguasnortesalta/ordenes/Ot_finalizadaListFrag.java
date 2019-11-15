package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.Ot_finalizadaListAdapter;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.model.Ot_finalizadaList;

import com.aguasnortesalta.ordenes.utils.Util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Ot_finalizadaListFrag extends Fragment{
	private ListView lista;
	private Ot_finalizadaListAdapter adapter2;
	protected Ot_finalizadaList list = new Ot_finalizadaList();
	private DatabaseManager dm;

	private static OnListaSelectOt_finalizada onListaSelectOt_finalizada;

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
		 list.data=dm.getOt_finalizadas();
		 if(list.data!=null){
			  adapter2=new Ot_finalizadaListAdapter(getActivity(),R.layout.usuario_list_item,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 //lista.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
		 /*
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					    //Ot_finalizadaListFrag.onListaSelectOt_finalizada.onClickList(list.data.get(position));
				}


			});
		 */
		 }
		
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			adapter2.filter(arg0);
		}
	 public  interface OnListaSelectOt_finalizada  {
		    // you can define any parameter as per your requirement
			void onClickList(Ot_finalizada  er);
	}
	public  OnListaSelectOt_finalizada getOnListaSelectOt_finalizada() {
		return onListaSelectOt_finalizada;
	}
	public  void setOnListaSelectOt_finalizada(
			OnListaSelectOt_finalizada onListaSelectOt_finalizada) {
		Ot_finalizadaListFrag.onListaSelectOt_finalizada = onListaSelectOt_finalizada;
	}


}