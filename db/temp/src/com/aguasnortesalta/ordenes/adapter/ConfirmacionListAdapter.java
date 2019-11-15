package com.aguasnortesalta.ordenes.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.aguasnortesalta.ordenes.R;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.aguasnortesalta.ordenes.model.Confirmacion;
import com.aguasnortesalta.ordenes.model.Usuario;

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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmacionListAdapter extends BaseAdapter {

	private final Context context;
	int layoutResourceId;
	private List<Confirmacion>data;
	private ArrayList<Confirmacion> tipoarray;
	private Cursor cursor;


	public ConfirmacionListAdapter(Context context, int layoutResourceId,
			List<Confirmacion> data) {
		this.layoutResourceId=layoutResourceId;
		this.context = context;
		this.tipoarray = new ArrayList<Confirmacion>();
		//this.data = new ArrayList<Confirmacion>();
		this.data = data;//Arrays.asList(data);
		this.tipoarray.addAll(this.data);
		Util.Log("data=>"+this.data.size());
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

			holder.place = (TextView) view.findViewById(R.id.tv_name);
			holder.descripcion = (TextView) view.findViewById(R.id.tv_descripcion);
			holder.domicilio = (TextView) view.findViewById(R.id.tv_domicilio);

			view.setTag(holder);

		} else {
			view = convertView;
			holder = (PlaceHolder) view.getTag();
		}

		holder.place.setText(data.get(position).nro_ot);
		//holder.descripcion.setText(data.get(position).getDomicilio());
		holder.domicilio.setText("");

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
	public Confirmacion getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return data.get(position).get_id();
	}

	private class PlaceHolder {
		TextView domicilio;
		TextView descripcion;
		TextView place;
		ImageView image;

	}
	// Filter Class
				public void filter(String charText) {
					charText = charText.toLowerCase(Locale.getDefault());
					this.data = new ArrayList<Confirmacion>();
					if (charText.length() == 0) {
						data.addAll(tipoarray);
					} else {
						for (Confirmacion wp : tipoarray) {
							if (wp.nro_ot.toLowerCase(Locale.getDefault())
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