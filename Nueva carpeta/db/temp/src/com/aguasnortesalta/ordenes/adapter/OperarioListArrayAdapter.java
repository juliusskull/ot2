package com.aguasnortesalta.ordenes.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



import com.aguasnortesalta.ordenes.model.Operario;
import com.aguasnortesalta.ordenes.utils.Util;

import com.aguasnortesalta.ordenes.R;


import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OperarioListArrayAdapter extends ArrayAdapter<Operario>{
	private Context mContext;
	private List<Operario> data;
	private ArrayList<Operario> tipoarray;
	int layoutResourceId;
	private Cursor cursor;
	public OperarioListArrayAdapter(Context context, int resource,List<Operario> data) {
		super(context, resource);
		// TODO Auto-generated constructor stub
		this.layoutResourceId=resource;
		mContext = context;
		this.tipoarray = new ArrayList<Operario>();	
		this.data = data;
		this.tipoarray.addAll(this.data);
		Util.Log("data=>"+this.data.size());
	}
	
	/*
	    @Override
	    public View getView(int position,  View convertView,  ViewGroup parent) {
		View listItem = convertView;
		View view = null;
		PlaceHolder holder = null;

		if (listItem == null) {
			listItem = LayoutInflater.from(mContext).inflate(layoutResourceId,
					parent, false);
			holder = new PlaceHolder();
			holder.place = (TextView) view.findViewById(R.id.tv_name);
			view.setTag(holder);

		} else {
			view = convertView;
			holder = (PlaceHolder) view.getTag();
		}

		Operario currentMovie = data.get(position);
		Util.Log("Op=>"+currentMovie.toString());
		holder.place.setText(currentMovie.getNombre());
		return listItem;
	    }
	    */
	 @NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;

		PlaceHolder holder = null;
		final int pos= position;

		if (convertView == null) {
			/*
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			view = inflater.inflate(layoutResourceId, parent, false);
			*/
			view=LayoutInflater.from(mContext).inflate(layoutResourceId,parent, false);
			
			
			holder = new PlaceHolder();
			holder.place = (TextView) view.findViewById(R.id.tv_name);
			holder.descripcion = (TextView) view.findViewById(R.id.tv_descripcion);
			holder.domicilio = (TextView) view.findViewById(R.id.tv_domicilio);
			view.setTag(holder);

		} else {
			view = convertView;
			holder = (PlaceHolder) view.getTag();
		}
		Util.Log("data=>"+data.get(position).getNombre());
		holder.place.setText(data.get(position).getNombre());
		//holder.descripcion.setText(data.get(position).getDomicilio());
		holder.domicilio.setText("asdasdasd");

		return view;
	}
	 @Override
	 public int getCount(){
	     return data.size();
	 }
		private class PlaceHolder {
			TextView domicilio;
			TextView descripcion;
			TextView place;
			ImageView image;

		}
		public void filter(String charText) {
			charText = charText.toLowerCase(Locale.getDefault());
			this.data = new ArrayList<Operario>();
			if (charText.length() == 0) {
				data.addAll(tipoarray);
			} else {
				for (Operario wp : tipoarray) {
					if (wp.getNombre().toLowerCase(Locale.getDefault())
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
