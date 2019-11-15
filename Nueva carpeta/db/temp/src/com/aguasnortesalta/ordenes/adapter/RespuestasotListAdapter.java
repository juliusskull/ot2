package com.aguasnortesalta.ordenes.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.aguasnortesalta.ordenes.R;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter.OnAdapterListaSelectOperario;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.model.Operario;
import com.aguasnortesalta.ordenes.model.Respuestasot;
import com.aguasnortesalta.ordenes.model.Usuario;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class RespuestasotListAdapter extends BaseAdapter {
	public static final int ERROR_MAX_SELECCIONADOS = 1;
	private final Context context;
	int layoutResourceId;
	private List<Respuestasot>data;
	private ArrayList<Respuestasot> tipoarray;
	private ArrayList<Respuestasot> seleccionados = new ArrayList<Respuestasot>();
	private Cursor cursor;
	private int TOTAL_SELECCIONADOS_POSIBLES= Configuracion.RESPUESTA_TOTAL_SELECCIONADOS_POSIBLES;
	private int TOTOTAL_SELECCIONADOS=0;
	private static OnAdapterListaSelectRespuestasot onAdapterListaSelectRespuestasot;
	public RespuestasotListAdapter(Context context, int layoutResourceId,
			List<Respuestasot> data) {
		this.layoutResourceId=layoutResourceId;
		this.context = context;
		this.tipoarray = new ArrayList<Respuestasot>();
		//this.data = new ArrayList<Respuestasot>();
		this.data = data;//Arrays.asList(data);
		this.tipoarray.addAll(this.data);
		Util.Log("data=>"+this.data.size());
		//this.app = (TouristGuideApp) ((Activity) context).getApplication();
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final View view;

		PlaceHolder holder = new PlaceHolder();
		final int pos= position;

		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(layoutResourceId, parent, false);		
			holder.chk =(CheckBox)view.findViewById(R.id.checkBox1);					
			view.setTag(holder);

		} else {
			view = convertView;
			holder = (PlaceHolder) view.getTag();
		}
		
		view.setClickable(true);
		
		
		holder.chk.setText(data.get(position).getDesc_res());
		final PlaceHolder holder2 =holder;
		final int position_actual=position;
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Util.Log("=>clik view");
				
				//Utils.showDialog(context, "TOTOTAL_SELECCIONADOS=" +TOTOTAL_SELECCIONADOS+"/"+TOTAL_SELECCIONADOS_POSIBLES);				
				if(TOTOTAL_SELECCIONADOS< TOTAL_SELECCIONADOS_POSIBLES){
					if(holder2.chk.isChecked()){
						holder2.chk.setChecked(false);
						view.setBackgroundColor(Color.WHITE);
						TOTOTAL_SELECCIONADOS--;
						seleccionados.remove(data.get(position_actual));
					}else{
						holder2.chk.setChecked(true);
						view.setBackgroundColor(Color.RED);
						TOTOTAL_SELECCIONADOS++;
						//Utils.showDialog(context, "TOTOTAL_SELECCIONADOS=" +TOTOTAL_SELECCIONADOS+"/"+TOTAL_SELECCIONADOS_POSIBLES);
						
					}		
					
					seleccionados.add(data.get(position_actual));
									
							
					}else{
					if(holder2.chk.isChecked()){
						holder2.chk.setChecked(false);
						view.setBackgroundColor(Color.WHITE);
						seleccionados.remove(data.get(position_actual));
						TOTOTAL_SELECCIONADOS--;
					}
					if(TOTOTAL_SELECCIONADOS> TOTAL_SELECCIONADOS_POSIBLES){	
						onAdapterListaSelectRespuestasot.onClickList(ERROR_MAX_SELECCIONADOS);
					}
					
				}
			}
		});
		
		return view;
	}
	 public  interface OnAdapterListaSelectRespuestasot  {
		    // you can define any parameter as per your requirement
			void onClickList(int  er);
	}
	@Override
	public int getCount() {
		if (data == null){
			Log.d("w nullu","w nullu");
			return 0;
		}
		return data.size();
	}

	@Override
	public Respuestasot getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return data.get(position).get_id();
	}

	private class PlaceHolder {
		/*
		TextView domicilio;
		TextView descripcion;
		TextView place;
		ImageView image;
		*/
		CheckBox chk;

	}
	
	public  OnAdapterListaSelectRespuestasot getOnAdapterListaSelectRespuestasot() {
		return onAdapterListaSelectRespuestasot;
	}



	public  void setOnAdapterListaSelectRespuestasot(
			OnAdapterListaSelectRespuestasot onAdapterListaSelectRespuestasot) {
		RespuestasotListAdapter.onAdapterListaSelectRespuestasot = onAdapterListaSelectRespuestasot;
	}

	public ArrayList<Respuestasot> getSeleccionados() {
		return seleccionados;
	}

				// Filter Class
				public void filter(String charText) {
					charText = charText.toLowerCase(Locale.getDefault());
					this.data = new ArrayList<Respuestasot>();
					if (charText.length() == 0) {
						data.addAll(tipoarray);
					} else {
						for (Respuestasot wp : tipoarray) {
							if (wp.getDesc_res().toLowerCase(Locale.getDefault())
									.contains(charText)) {
								data.add(wp);
							}
						}
					}
					notifyDataSetChanged();
				}
				 public void swapCursor(Cursor newCursor) {
				        cursor = newCursor;
				        notifyDataSetChanged();
				    }
}