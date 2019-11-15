package com.aguasnortesalta.ordenes;

import java.io.File;

import com.aguasnortesalta.ordenes.adapter.ArchivoListAdapter;
import com.aguasnortesalta.ordenes.adapter.ArchivoListAdapter.OnAdapterListaSelectArchivo;
import com.aguasnortesalta.ordenes.adapter.RespuestasotListAdapter;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Archivo;
import com.aguasnortesalta.ordenes.model.ArchivoList;

import com.aguasnortesalta.ordenes.sync.SincronizarFotosService;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ArchivoListFrag extends Fragment{
	private ListView lista;
	private ArchivoListAdapter adapter2;
	protected ArchivoList list = new ArchivoList();
	private DatabaseManager dm;
	private ProgressDialog progress;
	private ConnectivityManager cm;
	public long lista_cant=0;
	public long lista_index=0;
	

	private static OnListaSelectArchivo onListaSelectArchivo;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.ppal_list, container,
				false);
		return rootView;
	}
	 @Override
	  public void onActivityCreated(Bundle state) {
	        super.onActivityCreated(state);
	    	cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			
	        verLista();

	 }
	 public void verLista(){
		 dm= new DatabaseManager(getActivity(),"ARCHIVO LIST");
		 list.data=dm.getArchivos();
		 adapter2=new ArchivoListAdapter(getActivity(),R.layout.usuario_list_item_img,  list.data);
		 lista=(ListView)getActivity().findViewById(R.id.ppal_list_id);
		 lista.setAdapter(adapter2);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					  //  ArchivoListFrag.onListaSelectArchivo.onClickList(list.data.get(position));
				}


			});
		 
		 adapter2.setOnAdapterListaSelectArchivo(new OnAdapterListaSelectArchivo(){

			@Override
			public void onClickList(final long _id) {
				// TODO Auto-generated method stub
				NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
				
				boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
				boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
				if(isConnected && isWiFi){
					Archivo archivo = dm. getArchivo(_id);	
					enviar_foto( archivo,"Enviando....1 de 1");
				}else{
					Utils.showDialog(getActivity(), "La aplicacion no esta conectada al WIFI. ¿Igualmente quiere enviar el archivo?","Si","No"
	    					, new android.content.DialogInterface.OnClickListener(){
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Archivo archivo = dm. getArchivo(_id);	
									enviar_foto( archivo,"Enviando....1 de 1");
								}
						
					}						
	    			);
				}
				
			}});
	 }
	 public void enviar_foto(Archivo archivo,String message){
		    progress=new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
			progress.setTitle("");
			progress.setMessage(message);
			
			//progress.setMessage(message);
			progress.show();
			//Utils.showDialog(getActivity(), "Enviar Fotos:"+_id);
			Tarea_web tarea = new Tarea_web();		
			tarea.execute(new Archivo[]{archivo});
		 
	 }
	 public void enviar_foto_all(){
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();			
		boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
		boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
		list.data=dm.getArchivos();
		if(isConnected && isWiFi){
			lista_index=0;
			enviar_foto_all_exe();
			/*
			 for(int i=0; i<list.data.size(); i++){
				 enviar_foto(list.data.get(i),"Enviando " + (i+1)+ " de "+list.data.size());
				 
			 }
			 */
		}else{
			Utils.showDialog(getActivity(), "La aplicacion no esta conectada al WIFI. ¿Igualmente quiere enviar los archivos?","Si","No"
					, new android.content.DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO Auto-generated method stub
							lista_index=0;
							 progress=new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
							 progress.setTitle("");
							 progress.show();
							 enviar_foto_all_exe();
							/*
							 for(int i=0; i<list.data.size(); i++){
								 enviar_foto(list.data.get(i),"Enviando " + (i+1)+ " de "+list.data.size());
								 
							 }
							 */
						}				
				}						
			);
			
		}
		 
	 }
	 public void enviar_foto_all_exe(){
		 lista_cant= list.data.size();
		 //lista_index=0;
		 Tarea_web2 tarea = new Tarea_web2();		
		 tarea.execute(new Archivo[]{null});
	 }
	 public void setFiltro(String arg0) {
			// TODO Auto-generated method stub
			Util.Log("arg0=>"+arg0);

			adapter2.filter(arg0);
		}
	 public  interface OnListaSelectArchivo  {
		    // you can define any parameter as per your requirement
			void onClickList(Archivo  er);
	}
	public  OnListaSelectArchivo getOnListaSelectArchivo() {
		return onListaSelectArchivo;
	}
	public  void setOnListaSelectArchivo(
			OnListaSelectArchivo onListaSelectArchivo) {
		ArchivoListFrag.onListaSelectArchivo = onListaSelectArchivo;
	}
    class Tarea_web2 extends  AsyncTask<Archivo,String,String>{
		

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			
			
			 progress.setMessage("Enviando " + (lista_index+1)+ " de "+list.data.size());
			 //progress.show();
			// progress.setMessage("Enviando " + (lista_index+1)+ " de "+list.data.size());
			 super.onPreExecute();
		}


		@Override
		protected String doInBackground(Archivo... params) {
			// TODO Auto-generated method stub
			Archivo archivo = list.data.get((int)lista_index);
			String root = Environment.getExternalStorageDirectory().getAbsolutePath();
			String imageFolderPath = root + "/ot_img";
			archivo.setNombre(imageFolderPath+"/"+archivo.getNombre());
			SystemClock.sleep(3000);
			try {
				Util.Log("Enviar:"+archivo.getNombre());
				File file = new File(archivo.getArchivo());
				
				if(file.exists()){
					SincronizarFotosService.ENVIAR(archivo, dm);
				}else{
					Util.Log("archivo inexistente");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Util.Log("error enviar archivo=>"+lista_index+"->"+e.getMessage()+"->"+archivo.getArchivo());
				//progress.dismiss();
			}
			return null;
		}
		

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			
			lista_index++;
			if(lista_index<list.data.size()){
				enviar_foto_all_exe();
				//Utils.showDialog(getActivity(), "ok");
			}else{
				progress.dismiss();		
				Utils.showDialog(getActivity(), "Se enviaron las fotos");
				list.data=dm.getArchivos();				
				adapter2=new ArchivoListAdapter(getActivity(),R.layout.usuario_list_item_img,  list.data);
				lista.setAdapter(adapter2);				
				adapter2.notifyDataSetChanged();
			}
			
			super.onPostExecute(result);
		}
		
		
	}
	class Tarea_web extends  AsyncTask<Archivo,String,String>{
		

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}


		@Override
		protected String doInBackground(Archivo... params) {
			// TODO Auto-generated method stub
			
			try {
				Archivo archivo = params[0];				
				SincronizarFotosService.ENVIAR(archivo, dm);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Util.Log("error enviar archivo=>"+e.getMessage());
				progress.dismiss();
			}
			return null;
		}
		

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			progress.dismiss();
			list.data=dm.getArchivos();
			adapter2=new ArchivoListAdapter(getActivity(),R.layout.usuario_list_item_img,  list.data);
			lista.setAdapter(adapter2);
			adapter2.notifyDataSetChanged();
			
			super.onPostExecute(result);
		}
		
		
	}


}