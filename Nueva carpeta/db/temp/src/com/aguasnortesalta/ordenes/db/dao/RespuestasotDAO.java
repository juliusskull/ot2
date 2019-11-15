package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.RespuestasotTable;
import com.aguasnortesalta.ordenes.db.tables.RespuestasotTable.RespuestasotColumns;
import com.aguasnortesalta.ordenes.model.Respuestasot;
import com.aguasnortesalta.ordenes.utils.Util;


public class RespuestasotDAO implements DAO<Respuestasot>{

	private static final String INSERT = "insert into "
			+ RespuestasotTable.TABLE_NAME + "(" 
			+ RespuestasotColumns._ID 
		    + ", "+ RespuestasotColumns.ID_RES
			+ ", "+ RespuestasotColumns.DESC_RES
			+ ", "+ RespuestasotColumns.ICON
			+ ", "+ RespuestasotColumns.ID_RUBRO
			+ ", "+ RespuestasotColumns.GEREN
			+ ", "+ RespuestasotColumns.ID_TXC
			+ ") values (?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public RespuestasotDAO(SQLiteDatabase db) {
		this.db = db;
		try {
			insertStatement = db.compileStatement(RespuestasotDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			RespuestasotTable.onCreate(db, null);
			insertStatement = db.compileStatement(RespuestasotDAO.INSERT);
			e.printStackTrace();
		}
	}
	
	

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
		insertStatement.bindString(3, data[2]);
		insertStatement.bindString(4, data[3]);
		insertStatement.bindLong(5,  Long.valueOf(data[4]));
		insertStatement.bindString(6, data[5]);
		insertStatement.bindLong(7,  Long.valueOf(data[6]));


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(RespuestasotTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Respuestasot getRespuestasot(long id) {
		Respuestasot  place = null;
		String[] columns = RespuestasotColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(RespuestasotTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Respuestasot();
			place.set_id(c.getLong(0));
             place.setId_res(c.getLong(1));
			 place.setDesc_res(c.getString(2));
			 place.setIcon(c.getString(3));
			 place.setId_rubro(c.getLong(4));
			 place.setGeren(c.getString(5));
			 place.setId_txc(c.getLong(6));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Respuestasot[] get(String condition, String[] params) {
		Respuestasot[] Respuestasot = null;
		String[] columns = RespuestasotColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(RespuestasotTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return Respuestasot;
		}
		if (c.moveToFirst()) {
			Respuestasot = new Respuestasot[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Respuestasot[i] = new Respuestasot();
				Respuestasot place = new Respuestasot();
				 place.set_id(c.getLong(0));
				 place.setId_res(c.getLong(1));
				 place.setDesc_res(c.getString(2));
				 place.setIcon(c.getString(3));
				 place.setId_rubro(c.getLong(4));
				 place.setGeren(c.getString(5));
				 place.setId_txc(c.getLong(6));
				 Util.Log("Respuestasot=>"+place.toString());


				Respuestasot[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Respuestasot;
	}
	@Override
	public Respuestasot get(long id) {

		Respuestasot[] respuestasot = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (respuestasot == null)
			return null;

		return respuestasot[0];
	}




	@Override
	public Respuestasot[] getAll() {
		return null;
	}



}
