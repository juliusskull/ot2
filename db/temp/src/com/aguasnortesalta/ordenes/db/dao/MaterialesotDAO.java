package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.MaterialesotTable;
import com.aguasnortesalta.ordenes.db.tables.MaterialesotTable.MaterialesotColumns;
import com.aguasnortesalta.ordenes.model.Materialesot;


public class MaterialesotDAO implements DAO<Materialesot>{

	private static final String INSERT = "insert into "
			+ MaterialesotTable.TABLE_NAME + "(" 
			+ MaterialesotColumns._ID 
		    + ", "+ MaterialesotColumns.ID
			+ ", "+ MaterialesotColumns.PRODUC
			+ ", "+ MaterialesotColumns.DESC_LARGA
			+ ", "+ MaterialesotColumns.DESC_CORTA
			+ ", "+ MaterialesotColumns.AGRU_PRODUC
			+ ", "+ MaterialesotColumns.AGRU_DESCRIP
			+ ", "+ MaterialesotColumns.GEREN
			+ ", "+ MaterialesotColumns.FCHALTA
			+ ") values (?,?,?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public MaterialesotDAO(SQLiteDatabase db) {
		this.db = db;
		//insertStatement = db.compileStatement(MaterialesotDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
		insertStatement.bindString(3, data[2]);
		insertStatement.bindString(4, data[3]);
		insertStatement.bindString(5, data[4]);
		insertStatement.bindString(6, data[5]);
		insertStatement.bindString(7, data[6]);
		insertStatement.bindString(8, data[7]);
		insertStatement.bindString(9, data[8]);


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(MaterialesotTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Materialesot getMaterialesot(long id) {
		Materialesot  place = null;
		String[] columns = MaterialesotColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(MaterialesotTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Materialesot();
			place.set_id(c.getLong(0));
             place.setId(c.getLong(1));
			 place.setProduc(c.getString(2));
			 place.setDesc_larga(c.getString(3));
			 place.setDesc_corta(c.getString(4));
			 place.setAgru_produc(c.getString(5));
			 place.setAgru_descrip(c.getString(6));
			 place.setGeren(c.getString(7));
			 place.setFchalta(c.getString(8));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Materialesot[] get(String condition, String[] params) {
		Materialesot[] Materialesot = null;
		String[] columns = MaterialesotColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(MaterialesotTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Materialesot = new Materialesot[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Materialesot[i] = new Materialesot();
				Materialesot place = new Materialesot();
				 place.set_id(c.getLong(0));
				 place.setId(c.getLong(1));
				 place.setProduc(c.getString(2));
				 place.setDesc_larga(c.getString(3));
				 place.setDesc_corta(c.getString(4));
				 place.setAgru_produc(c.getString(5));
				 place.setAgru_descrip(c.getString(6));
				 place.setGeren(c.getString(7));
				 place.setFchalta(c.getString(8));

				Materialesot[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Materialesot;
	}
	@Override
	public Materialesot get(long id) {

		Materialesot[] materialesot = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (materialesot == null)
			return null;

		return materialesot[0];
	}




	@Override
	public Materialesot[] getAll() {
		return null;
	}



}
