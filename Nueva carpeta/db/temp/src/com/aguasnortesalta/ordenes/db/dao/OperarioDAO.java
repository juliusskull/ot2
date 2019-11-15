package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.OperarioTable;
import com.aguasnortesalta.ordenes.db.tables.OperarioTable.OperarioColumns;
import com.aguasnortesalta.ordenes.model.Operario;


public class OperarioDAO implements DAO<Operario>{

	private static final String INSERT = "insert into "
			+ OperarioTable.TABLE_NAME + "(" 
			+ OperarioColumns._ID 
		    + ", "+ OperarioColumns.ID
			+ ", "+ OperarioColumns.NOMBRE
			+ ", "+ OperarioColumns.FCHALTA
			+ ", "+ OperarioColumns.GEREN
			+ ", "+ OperarioColumns.CUADRILLA
			+ ", "+ OperarioColumns.CAPATAZ
			+ ", "+ OperarioColumns.PASSWORD

			+ ") values (?,?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public OperarioDAO(SQLiteDatabase db) {
		this.db = db;
		//insertStatement = db.compileStatement(OperarioDAO.INSERT);
	}
	

	@Override
	public long insert(String[] data) {
		/*
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
		insertStatement.bindString(3, data[2]);
		insertStatement.bindString(4, data[3]);
		insertStatement.bindString(5, data[4]);
		insertStatement.bindLong(6,  Long.valueOf(data[5]));
		insertStatement.bindLong(7,  Long.valueOf(data[6]));
		insertStatement.bindLong(8,  Long.valueOf(data[7]));


		return insertStatement.executeInsert();
		*/
		return 1;
	}

	@Override
	public void remove(long id) {
		db.delete(OperarioTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Operario getOperario(long id) {
		Operario  place = null;
		String[] columns = OperarioColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(OperarioTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Operario();
			place.set_id(c.getLong(0));
             place.setId((int)c.getLong(1));
			 place.setNombre(c.getString(2));
			 place.setFchalta(c.getString(3));
			 place.setGeren(c.getString(4));
			 place.setCuadrilla((int)c.getLong(5));
			 place.setCapataz((int)c.getLong(6));
			 place.setPassword((int)c.getLong(7));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Operario[] get(String condition, String[] params) {
		Operario[] Operario = null;
		String[] columns = OperarioColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(OperarioTable.TABLE_NAME, columns, condition,
				params, null, null, OperarioTable.OperarioColumns.NOMBRE + " ASC");
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Operario = new Operario[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Operario[i] = new Operario();
				Operario place = new Operario();
				 place.set_id(c.getLong(0));
				 place.setId((int)c.getLong(1));
				 place.setNombre(c.getString(2));
				 place.setFchalta(c.getString(3));
				 place.setGeren(c.getString(4));
				 place.setCuadrilla((int)c.getLong(5));
				 place.setCapataz((int)c.getLong(6));
				 place.setPassword((int)c.getLong(7));


				Operario[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Operario;
	}
	@Override
	public Operario get(long id) {

		Operario[] operario = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (operario == null)
			return null;

		return operario[0];
	}




	@Override
	public Operario[] getAll() {
		return null;
	}



}
