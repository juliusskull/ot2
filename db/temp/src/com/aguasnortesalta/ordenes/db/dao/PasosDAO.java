package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.PasosTable;
import com.aguasnortesalta.ordenes.db.tables.TemplateTable;
import com.aguasnortesalta.ordenes.db.tables.PasosTable.PasosColumns;
import com.aguasnortesalta.ordenes.db.tables.TemplateTable.TemplateColumns;
import com.aguasnortesalta.ordenes.model.Materialesot;
import com.aguasnortesalta.ordenes.model.Ot_aperturas;
import com.aguasnortesalta.ordenes.model.Pasos;
import com.aguasnortesalta.ordenes.model.Template;

public class PasosDAO extends DAOBase implements DAO<Pasos>{
	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;
	@Override
	public long insert(String[] data) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public PasosDAO(SQLiteDatabase db2) {
		super();
		// TODO Auto-generated constructor stub
		this.db=db2;
	}

	public Pasos getPasos(long id) {
		Pasos  place = null;
		String[] columns = PasosColumns.getColumns();
		String language = Locale.getDefault().getLanguage();
// _ID,ID_PASO,DESC_PASO,TIPO,FOTO,OBLIGATORIO
		Cursor c = db
				.query(PasosTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			 place = new Pasos();
			 place.set_id(c.getLong(0));
			 place.setId_paso((int)c.getLong(1));
             place.setDesc_paso(c.getString(2));
			 place.setTipo(c.getString(3));
			 place.setFoto((int)c.getLong(4));
			 place.setObligatorio((int)c.getLong(5));

		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}
	public  Pasos[] getPasosXTemplate(long template_id) {
		Pasos  place = null;
		Pasos[] Pasos = null;
		String[] columns = PasosColumns.getColumns();
		String language = Locale.getDefault().getLanguage();
// _ID,ID_PASO,DESC_PASO,TIPO,FOTO,OBLIGATORIO
		Cursor c = db
				.query(PasosTable.VIEW_NAME, columns, "id_template"
						+ " = ?", new String[] { String.valueOf(template_id) }, null,
						null, null);
		if (c.moveToFirst()) {
			Pasos = new Pasos[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
			 place = new Pasos();
			 Pasos[i] = new Pasos();
			 place.set_id(c.getLong(0));
			 place.setId_paso((int)c.getLong(1));
             place.setDesc_paso(c.getString(2));
			 place.setTipo(c.getString(3));
			 place.setFoto((int)c.getLong(4));
			 place.setObligatorio((int)c.getLong(5));
			 Pasos[i] = place;
			 c.moveToNext();
			}

		}
		if (!c.isClosed()) {
			c.close();
		}
		return Pasos;
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pasos get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pasos[] getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
