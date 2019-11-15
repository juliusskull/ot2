package com.aguasnortesalta.ordenes.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.aguasnortesalta.ordenes.R;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.aguasnortesalta.ordenes.adapter.OperarioListAdapter.OnAdapterListaSelectOperario;
import com.aguasnortesalta.ordenes.model.Archivo;
import com.aguasnortesalta.ordenes.model.Usuario;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
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

public class ArchivoListAdapter extends BaseAdapter {

	private final Context context;
	int layoutResourceId;
	private List<Archivo>data;
	private ArrayList<Archivo> tipoarray;
	private Cursor cursor;
	private static OnAdapterListaSelectArchivo onAdapterListaSelectArchivo;

	public ArchivoListAdapter(Context context, int layoutResourceId,
			List<Archivo> data) {
		this.layoutResourceId=layoutResourceId;
		this.context = context;
		this.tipoarray = new ArrayList<Archivo>();
		//this.data = new ArrayList<Archivo>();
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
			holder.image = (ImageView) view.findViewById(R.id.img_foto);
			holder.btn=(Button)view.findViewById(R.id.btn_enviar_imagen);

			view.setTag(holder);

		} else {
			view = convertView;
			holder = (PlaceHolder) view.getTag();
		}

		holder.place.setText(data.get(position).getArchivo());
		//holder.descripcion.setText(data.get(position).getDomicilio());
		holder.domicilio.setText("");
		
		if(data.get(position).getEnviado()>0){
			holder.btn.setEnabled(false);
		}
		final long _id=data.get(position).get_id();
		holder.btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getOnAdapterListaSelectArchivo().onClickList(_id);
			}
		});
		
		
		try {
			String root = Environment.getExternalStorageDirectory().getAbsolutePath();
			String imageFolderPath = root + "/ot_img";
			
			File image = new File(imageFolderPath, data.get(position).getArchivo()); 
			
			Picasso.with(context).load(image).into(holder.image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Util.Log("error imagen "+ data.get(position).getArchivo());
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
	public Archivo getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return data.get(position).get_id();
	}
	
	 public  interface OnAdapterListaSelectArchivo  {
		    // you can define any parameter as per your requirement
			void onClickList(long  er);
	}

	private class PlaceHolder {
		TextView domicilio;
		TextView descripcion;
		TextView place;
		ImageView image;
		Button btn;

	}
	
	public  OnAdapterListaSelectArchivo getOnAdapterListaSelectArchivo() {
		return onAdapterListaSelectArchivo;
	}



	public  void setOnAdapterListaSelectArchivo(
			OnAdapterListaSelectArchivo onAdapterListaSelectArchivo) {
		ArchivoListAdapter.onAdapterListaSelectArchivo = onAdapterListaSelectArchivo;
	}



				// Filter Class
				public void filter(String charText) {
					charText = charText.toLowerCase(Locale.getDefault());
					this.data = new ArrayList<Archivo>();
					if (charText.length() == 0) {
						data.addAll(tipoarray);
					} else {
						for (Archivo wp : tipoarray) {
							if (wp.getArchivo().toLowerCase(Locale.getDefault())
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