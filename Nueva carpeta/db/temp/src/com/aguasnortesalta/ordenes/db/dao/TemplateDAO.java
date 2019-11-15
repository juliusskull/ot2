package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.ArchivoTable;
import com.aguasnortesalta.ordenes.db.tables.ArchivoTable.ArchivoColumns;
import com.aguasnortesalta.ordenes.db.tables.TemplateTable;
import com.aguasnortesalta.ordenes.db.tables.TemplateTable.TemplateColumns;
import com.aguasnortesalta.ordenes.model.Archivo;
import com.aguasnortesalta.ordenes.model.Template;

public class TemplateDAO extends DAOBase implements DAO<Template>{
	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;
	
	public TemplateDAO(SQLiteDatabase db2) {
		// TODO Auto-generated constructor stub
		this.db=db2;
	}
	public Template getTemplate(long id) {
		Template  place = null;
		String[] columns = TemplateColumns.getColumns();
		String language = Locale.getDefault().getLanguage();
//_ID,ID_TEMPLATE,DESC_TEMPLATE,OBSERVACION,TITULO
		Cursor c = db
				.query(TemplateTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			 place = new Template();
			 place.set_id(c.getLong(0));
			 place.setId_template((int)c.getLong(1));
             place.setDesc_template(c.getString(2));
			 place.setObservacion(c.getString(3));
			 place.setTitulo(c.getString(4));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}
	@Override
	public long insert(String[] data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Template get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Template[] getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
