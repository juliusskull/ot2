package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.DerivacionesotTable;
import com.aguasnortesalta.ordenes.db.tables.DerivacionesotTable.DerivacionesotColumns;
import com.aguasnortesalta.ordenes.model.Derivacionesot;
import com.aguasnortesalta.ordenes.utils.Util;


public class DerivacionesotDAO implements DAO<Derivacionesot>{

	private static final String INSERT = "insert into "
			+ DerivacionesotTable.TABLE_NAME + "(" + DerivacionesotColumns._ID
		    + ", "+ DerivacionesotColumns.ID_RES
				+ ", "+ DerivacionesotColumns.GEREN
				+ ", "+ DerivacionesotColumns.ID_MOT_PROX
				+ ", "+ DerivacionesotColumns.DESC_MOTIVO

			+ ") values (?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public DerivacionesotDAO(SQLiteDatabase db) {
		this.db = db;
		
		try {
			insertStatement = db.compileStatement(DerivacionesotDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Util.Log("Error=>no existe "+DerivacionesotTable.TABLE_NAME);
			DerivacionesotTable.onCreate(db, null);
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


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(DerivacionesotTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Derivacionesot getDerivacionesot(long id) {
		Derivacionesot  place = null;
		String[] columns = DerivacionesotColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(DerivacionesotTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Derivacionesot();
			place.set_id(c.getLong(0));
             place.setId_res(c.getLong(1));
				 place.setGeren(c.getString(2));
				 place.setId_mot_prox(c.getLong(3));
				 place.setDesc_motivo(c.getString(4));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Derivacionesot[] get(String condition, String[] params) {
		Util.Log("=>(Derivacionesot):---------------------------");
		Derivacionesot[] Derivacionesot = null;
		
		String[] columns = DerivacionesotColumns.getColumns();
		Util.Log("=>(Derivacionesot):"+condition+ ":"+params[0]);
		Cursor c = db.query(DerivacionesotTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Derivacionesot = new Derivacionesot[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Derivacionesot[i] = new Derivacionesot();
				Derivacionesot place = new Derivacionesot();
				place.set_id(c.getLong(0));
				place.setId_res(c.getLong(1));
				place.setGeren(c.getString(2));
				place.setId_mot_prox(c.getLong(3));
				place.setDesc_motivo(c.getString(4));
				Derivacionesot[i] = place;
				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Derivacionesot;
	}
	@Override
	public Derivacionesot get(long id) {

		Derivacionesot[] derivacionesot = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (derivacionesot == null)
			return null;

		return derivacionesot[0];
	}




	@Override
	public Derivacionesot[] getAll() {
		return null;
	}



}
