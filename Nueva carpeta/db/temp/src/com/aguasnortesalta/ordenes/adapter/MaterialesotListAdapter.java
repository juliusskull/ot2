package com.aguasnortesalta.ordenes.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.aguasnortesalta.ordenes.R;
import com.aguasnortesalta.ordenes.utils.Util;
import com.aguasnortesalta.ordenes.utils.Utils;
import com.aguasnortesalta.ordenes.adapter.EquipoListAdapter.OnAdapterListaSelectEquipo;
import com.aguasnortesalta.ordenes.constants.Configuracion;
import com.aguasnortesalta.ordenes.model.Equipo;
import com.aguasnortesalta.ordenes.model.Materialesot;
import com.aguasnortesalta.ordenes.model.Usuario;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MaterialesotListAdapter extends BaseAdapter {
	public static final int ERROR_MAX_SELECCIONADOS = 1;
	private final Context context;
	int layoutResourceId;
	private List<Materialesot>data;
	private ArrayList<Materialesot> tipoarray;
	private ArrayList<Materialesot> seleccionados = new ArrayList<Materialesot>();
	private Cursor cursor;
	private int TOTAL_SELECCIONADOS_POSIBLES= Configuracion.OPERARIOS_TOTAL_SELECCIONADOS_POSIBLES;
	private int TOTOTAL_SELECCIONADOS=0;
	private static OnAdapterListaSelectMaterialesot onAdapterListaSelectMaterialesot;

	public MaterialesotListAdapter(Context context, int layoutResourceId,
			List<Materialesot> data) {
		this.layoutResourceId=layoutResourceId;
		this.context = context;
		this.tipoarray = new ArrayList<Materialesot>();
		//this.data = new ArrayList<Materialesot>();
		this.data = data;//Arrays.asList(data);
		this.tipoarray.addAll(this.data);
		Util.Log("data=>"+this.data.size());
		//this.app = (TouristGuideApp) ((Activity) context).getApplication();
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final View view;

		PlaceHolder holder = null;
		final int pos= position;

		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(layoutResourceId, parent, false);
			holder = new PlaceHolder();
			/*
			holder.place = (TextView) view.findViewById(R.id.tv_name);
			holder.descripcion = (TextView) view.findViewById(R.id.tv_descripcion);
			holder.domicilio = (TextView) view.findViewById(R.id.tv_domicilio);
			 	*/
			holder.chk =(CheckBox)view.findViewById(R.id.checkBox1);
			view.setTag(holder);

		} else {
			view = convertView;
			holder = (PlaceHolder) view.getTag();
		}

		view.setClickable(true);		
		holder.chk.setText(data.get(position).getMaterialesot());
		final PlaceHolder holder2 =holder;
		final int position_actual=position;
view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Util.Log("=>clik view");
				if(TOTOTAL_SELECCIONADOS< TOTAL_SELECCIONADOS_POSIBLES){
					if(holder2.chk.isChecked()){
						holder2.chk.setChecked(false);
						view.setBackgroundColor(Color.WHITE);
						TOTOTAL_SELECCIONADOS--;
						seleccionados.remove(data.get(position_actual));
					}else{
						holder2.chk.setChecked(true);
						view.setBackgroundColor(Color.RED);
						add_cant_material(position_actual);
						TOTOTAL_SELECCIONADOS++;
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
						onAdapterListaSelectMaterialesot.onClickList(OperarioListAdapter.ERROR_MAX_SELECCIONADOS);
					}
					
				}
			}
		});

		return view;
	}
    public void add_cant_material(int position_actual){
    	final int _position_actual=position_actual;
    	final EditText txt_cant = new EditText(context);
    	txt_cant.setText("");
    	txt_cant.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    	new AlertDialog.Builder(context)
    	  .setTitle("Materiales")
    	  .setMessage("Producto:"+data.get(position_actual).getAgru_produc() +" cantidad")
    	  .setView(txt_cant)
    	  .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int whichButton) {
    	      String cant = txt_cant.getText().toString();
    	      //moustachify(null, url);
    	      if(Util.isNumeric(cant)){
    	    	  data.get(_position_actual).setCantidad(cant);
    	      }else{
    	    	  Utils.showDialog(context, "Debe ingresar un valor numerico");
    	      }
    	      
    	      
    	    }
    	  })
    	  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int whichButton) {
    	    }
    	  })
    	  .show();
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
	public Materialesot getItem(int position) {
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
		CheckBox chk;
	}
	// Filter Class
				public void filter(String charText) {
					charText = charText.toLowerCase(Locale.getDefault());
					this.data = new ArrayList<Materialesot>();
					if (charText.length() == 0) {
						data.addAll(tipoarray);
					} else {
						for (Materialesot wp : tipoarray) {
							if (wp.getMaterialesot().toLowerCase(Locale.getDefault())
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
				 
				 public  interface OnAdapterListaSelectMaterialesot  {
					    // you can define any parameter as per your requirement
						void onClickList(int  er);
				}
				 public ArrayList<Materialesot> getSeleccionados() {
						return seleccionados;
					}
				public  OnAdapterListaSelectMaterialesot getOnAdapterListaSelectMaterialesot() {
					return onAdapterListaSelectMaterialesot;
				}
				public  void setOnAdapterListaSelectMaterialesot(
						OnAdapterListaSelectMaterialesot onAdapterListaSelectMaterialesot) {
					MaterialesotListAdapter.onAdapterListaSelectMaterialesot = onAdapterListaSelectMaterialesot;
				}
				 
}