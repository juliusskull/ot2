package com.aguasnortesalta.ordenes.adapter;

import java.util.List;

import com.aguasnortesalta.ordenes.model.Atributos_listas;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Atributis_listasListSpinAdapter extends ArrayAdapter<Atributos_listas> {
	private Context context;
	private List<Atributos_listas> values;
	
	public Atributis_listasListSpinAdapter(Context context, int textViewResourceId, List<Atributos_listas> values) {
	    super(context, textViewResourceId, values);
	    this.context = context;
	    this.values = values;
	}

	public int getCount() {
	    return values.size();
	}

	public Atributos_listas getItem(int position) {
	    return values.get(position);
	}

	public long getItemId(int position) {
	    return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    TextView label = new TextView(context);
	    label.setTextColor(Color.BLACK);
	    label.setText(values.get(position).getAtributos_listas());
	            
	    return label;
	}
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
	    TextView label = new TextView(context);
	    label.setTextColor(Color.BLACK);
	    label.setText(values.get(position).getAtributos_listas());

	    return label;
	}
}
