package com.aguasnortesalta.ordenes;

import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter;
import com.aguasnortesalta.ordenes.adapter.OtListAdapter;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter.OnAdapterListaSelectOperario;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter.OnAdapterListaSelectRespuestasot;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.OtList;
import com.aguasnortesalta.ordenes.model.Respuestasot;
import com.aguasnortesalta.ordenes.model.RespuestasotList;
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

public class RespuestaListFrag extends Fragment{
	private ListView lista;	
	private RespuestasotListAdapter adapter2;
	protected RespuestasotList list = new RespuestasotList();
	private DatabaseManager dm;
	
	private static OnListaSelectRespuesta onListaSelectRespuesta;
	
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
		 list.data=dm.getRespuestas();
		 adapter2=new RespuestasotListAdapter(getActivity(),R.layout.check_list_item,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {						
						//Principal_OT.onEventListener.onClickList(list.datos.get(position));
					  //  RespuestaListFrag.onListaSelectRespuesta.onClickList(list.data.get(position));
				}

			
			}); 
		 adapter2.setOnAdapterListaSelectRespuestasot(new OnAdapterListaSelectRespuestasot(){

				@Override
				public void onClickList(int er) {
					// TODO Auto-generated method stub
					if(er==RespuestasotListAdapter.ERROR_MAX_SELECCIONADOS){
						 Utils.showDialog(getActivity(), "Error maximo de seleccionados");
					}
				}});
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);
			
			adapter2.filter(arg0);
		}
	 public  interface OnListaSelectRespuesta  {
		    // you can define any parameter as per your requirement
			void onClickList(Respuestasot  er);
	}
	public  OnListaSelectRespuesta getOnListaSelectRespuesta() {
		return onListaSelectRespuesta;
	}
	public  void setOnListaSelectRespuesta(
			OnListaSelectRespuesta onListaSelectRespuesta) {
		RespuestaListFrag.onListaSelectRespuesta = onListaSelectRespuesta;
	}
	public void total_selecionados() {
		// TODO Auto-generated method stub
		if(adapter2.getSeleccionados().size()>0){
			dm.RESPUESTA_ULTIMA_SET(getActivity(),adapter2.getSeleccionados().get(0).getId_res());
		}
		
		
	}
	 

}
