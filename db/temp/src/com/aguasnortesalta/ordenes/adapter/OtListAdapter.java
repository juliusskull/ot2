package com.aguasnortesalta.ordenes.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.aguasnortesalta.ordenes.R;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.Usuario;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OtListAdapter extends BaseAdapter {

	private final Context context;
	int layoutResourceId;
	private List<Ot>data;
	private ArrayList<Ot> tipoarray;
	private Cursor cursor;
	private LatLng pto_actual = null;


	public OtListAdapter(Context context, int layoutResourceId,
			List<Ot> data) {
		this.layoutResourceId=layoutResourceId;
		this.context = context;
		this.tipoarray = new ArrayList<Ot>();
		//this.data = new ArrayList<Ot>();
		this.data = data;//Arrays.asList(data);
		this.tipoarray.addAll(this.data);
		Util.Log("data=>"+this.data.size());
		//this.app = (TouristGuideApp) ((Activity) context).getApplication();
	}
	public OtListAdapter(Context context, int layoutResourceId,
			List<Ot> data, LatLng pto_actual) {
		this.layoutResourceId=layoutResourceId;
		this.context = context;
		this.tipoarray = new ArrayList<Ot>();
		//this.data = new ArrayList<Ot>();
		this.data = data;//Arrays.asList(data);
		this.tipoarray.addAll(this.data);
		Util.Log("data=>"+this.data.size());
		this.pto_actual=pto_actual;
		//this.app = (TouristGuideApp) ((Activity) context).getApplication();
	}




	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;

		PlaceHolder holder = null;
		final int pos= position;

		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(layoutResourceId, parent, false);
			holder = new PlaceHolder();
			
			holder.titulo = (TextView) view.findViewById(R.id.txt_ot_titulo);
			holder.descripcion = (TextView) view.findViewById(R.id.txt_ot_subtitulo);
			holder.distancia = (TextView) view.findViewById(R.id.txt_distancia);
			view.setTag(holder);

		} else {
			view = convertView;
			holder = (PlaceHolder) view.getTag();
		}
	
		
		holder.titulo.setText(data.get(position).getTitulo());
		holder.descripcion.setText(data.get(position).getSubTitulo());
		
		LatLng ot_pto= new LatLng(Double.parseDouble(data.get(position).getLat()),Double.parseDouble(data.get(position).getLng()));
		if(pto_actual!=null){
			holder.distancia.setText(Configuracion.get_distancia(pto_actual, ot_pto));
		}
		
		
		
		return view;
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
	public Ot getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return data.get(position).get_id();
	}

	private class PlaceHolder {
	
		TextView titulo;
		TextView descripcion;
		TextView distancia;

	}
	// Filter Class
				public void filter(String charText) {
					charText = charText.toLowerCase(Locale.getDefault());
					this.data = new ArrayList<Ot>();
					if (charText.length() == 0) {
						data.addAll(tipoarray);
					} else {
						for (Ot wp : tipoarray) {
							if (wp.getTitulo().toLowerCase(Locale.getDefault())
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