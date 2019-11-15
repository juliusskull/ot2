package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import com.aguasnortesalta.ordenes.db.tables.ArchivoTable;
import com.aguasnortesalta.ordenes.db.tables.ConfiguracionTable;
import com.aguasnortesalta.ordenes.db.tables.ArchivoTable.ArchivoColumns;
import com.aguasnortesalta.ordenes.db.tables.ConfiguracionTable.ConfiguracionColumns;
import com.aguasnortesalta.ordenes.model.Archivo;
import com.aguasnortesalta.ordenes.model.Config;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ConfiguracionDAO {

	private SQLiteDatabase db;
	public ConfiguracionDAO(SQLiteDatabase db) {
		// TODO Auto-generated constructor stub
		this.db = db;
		try {
		   // insertStatement = db.compileStatement(ArchivoDAO.INSERT);
			getConfiguracion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//db.execSQL("DROP TABLE IF EXISTS " + ArchivoTable.TABLE_NAME);
			ConfiguracionTable.onCreate(db, null);
			//insertStatement = db.compileStatement(ArchivoDAO.INSERT);
			e.printStackTrace();
		}
	}
	public Config getConfiguracion() {
		Config  place = null;
		String[] columns = ConfiguracionColumns.getColumns();
		String language = Locale.getDefault().getLanguage();
		Cursor c = db
				.query(ConfiguracionTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { "1" }, null,
						null, null);
		if (c.moveToFirst()) {
			place= new Config();
			place.set_id(1);
			place.setVersion_db(c.getLong(1));
			place.setVersion_fch(c.getString(2));
		}
		
		return place;
	}
	
	

}
