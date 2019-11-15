package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.DerivacionesotTable;
import com.aguasnortesalta.ordenes.db.tables.EquipoTable;
import com.aguasnortesalta.ordenes.db.tables.EquipoTable.EquipoColumns;
import com.aguasnortesalta.ordenes.model.Equipo;
import com.aguasnortesalta.ordenes.utils.Util;


public class EquipoDAO implements DAO<Equipo>{

	private static final String INSERT = "insert into "
			+ EquipoTable.TABLE_NAME 
			+ "(" + EquipoColumns._ID 
		    + ", "+ EquipoColumns.ID
			+ ", "+ EquipoColumns.DESCRIPCION
			+ ", "+ EquipoColumns.TIPOID
			+ ", "+ EquipoColumns.FCHALTA
			+ ", "+ EquipoColumns.GEREN

			+ ") values (?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public EquipoDAO(SQLiteDatabase db) {
		this.db = db;
		
		try {
			insertStatement = db.compileStatement(EquipoDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Util.Log("Error=>no existe "+EquipoTable.TABLE_NAME);
			EquipoTable.onCreate(db, null);
			e.printStackTrace();
		}
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
insertStatement.bindString(3, data[2]);
insertStatement.bindLong(4,  Long.valueOf(data[3]));
insertStatement.bindString(5, data[4]);
insertStatement.bindString(6, data[5]);


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(EquipoTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Equipo getEquipo(long id) {
		Equipo  place = null;
		String[] columns = EquipoColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(EquipoTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Equipo();
			place.set_id(c.getLong(0));
             place.setId((int)c.getLong(1));
			 place.setDescripcion(c.getString(2));
			 place.setTipoid((int)c.getLong(3));
			 place.setFchalta(c.getString(4));
			 place.setGeren(c.getString(5));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Equipo[] get(String condition, String[] params) {
		Equipo[] Equipo = null;
		String[] columns = EquipoColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(EquipoTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Equipo = new Equipo[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Equipo[i] = new Equipo();
				Equipo place = new Equipo();
				place.set_id(c.getLong(0));
				place.setId((int)c.getLong(1));
				place.setDescripcion(c.getString(2));
				place.setTipoid((int)c.getLong(3));
				place.setFchalta(c.getString(4));
				place.setGeren(c.getString(5));


				Equipo[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Equipo;
	}
	@Override
	public Equipo get(long id) {

		Equipo[] equipo = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (equipo == null)
			return null;

		return equipo[0];
	}




	@Override
	public Equipo[] getAll() {
		return null;
	}



}
