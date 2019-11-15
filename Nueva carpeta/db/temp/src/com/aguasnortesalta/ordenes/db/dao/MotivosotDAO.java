package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.MotivosotTable;
import com.aguasnortesalta.ordenes.db.tables.MotivosotTable.MotivosotColumns;
import com.aguasnortesalta.ordenes.model.Motivosot;


public class MotivosotDAO implements DAO<Motivosot>{

	private static final String INSERT = "insert into "
			+ MotivosotTable.TABLE_NAME + "(" + MotivosotColumns._ID + ", "
		    + ", "+ MotivosotColumns.ID_MOTIVO
+ ", "+ MotivosotColumns.DESC_MOTIVO
+ ", "+ MotivosotColumns.ID_RUBRO
+ ", "+ MotivosotColumns.REG_APERTURA
+ ", "+ MotivosotColumns.ID_TXC
+ ", "+ MotivosotColumns.GEREN

			+ ") values (?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public MotivosotDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(MotivosotDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
insertStatement.bindString(3, data[2]);
insertStatement.bindLong(4,  Long.valueOf(data[3]));
insertStatement.bindString(5, data[4]);
insertStatement.bindLong(6,  Long.valueOf(data[5]));
insertStatement.bindString(7, data[6]);


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(MotivosotTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Motivosot getMotivosot(long id) {
		Motivosot  place = null;
		String[] columns = MotivosotColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(MotivosotTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Motivosot();
			place.set_id(c.getLong(0));
             place.setId_motivo((int)c.getLong(1));
			 place.setDesc_motivo(c.getString(2));
			 place.setId_rubro((int)c.getLong(3));
			 place.setReg_apertura(c.getString(4));
			 place.setId_txc((int)c.getLong(5));
			 place.setGeren(c.getString(6));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	private Motivosot[] get(String condition, String[] params) {
		Motivosot[] Motivosot = null;
		String[] columns = MotivosotColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(MotivosotTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Motivosot = new Motivosot[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Motivosot[i] = new Motivosot();
				Motivosot place = new Motivosot();
				place.set_id(c.getLong(0));
				  place.setId_motivo((int)c.getLong(1));
				 place.setDesc_motivo(c.getString(2));
				 place.setId_rubro((int)c.getLong(3));
				 place.setReg_apertura(c.getString(4));
				 place.setId_txc((int)c.getLong(5));
				 place.setGeren(c.getString(6));


				Motivosot[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Motivosot;
	}
	@Override
	public Motivosot get(long id) {

		Motivosot[] motivosot = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (motivosot == null)
			return null;

		return motivosot[0];
	}




	@Override
	public Motivosot[] getAll() {
		return null;
	}



}
