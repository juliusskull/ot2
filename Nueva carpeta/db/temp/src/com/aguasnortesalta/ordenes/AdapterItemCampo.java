package com.aguasnortesalta.ordenes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.aguasnortesalta.ordenes.utils.Util;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterItemCampo extends BaseAdapter {
	
	Context mContext;
	LayoutInflater inflater;
	private List<ObjectView> tipolist = null;
	private ArrayList<ObjectView> tipoarray;
	//private ListViewAdapterCosaClick listener;
	private Cursor cursor;
	//ImageLoader imageLoader;
	public AdapterItemCampo(Context context,
			List<ObjectView> worldpopulationlist) {
		mContext = context;
		this.tipolist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.tipoarray = new ArrayList<ObjectView>();
		this.tipoarray.addAll(worldpopulationlist);		
		Util.Log("datos2:"+tipolist.size());
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tipolist.size();
	}
	public class ViewHolder {
		TextView lblTitulo;		
		TextView lblDescripcion;	
		LinearLayout lyraiting;
		
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return tipolist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.agenda_item_campos, null);
			holder.lblTitulo = (TextView)view.findViewById(R.id.txtTitulo);						
			holder.lblDescripcion = (TextView)view.findViewById(R.id.txtDescripcion);	
			view.setTag(holder);
		} else {
			
			
			holder = (ViewHolder) view.getTag();
		}	
		Util.Log("tt:"+tipolist.get(position).getTitulo());
	      
		holder.lblTitulo.setText(tipolist.get(position).getTitulo());	
		holder.lblDescripcion.setText(tipolist.get(position).getDescripcion());
		
		return view	;
	}	 

}

