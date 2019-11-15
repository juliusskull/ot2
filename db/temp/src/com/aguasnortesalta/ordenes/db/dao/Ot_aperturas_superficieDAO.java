package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.Ot_aperturas_superficieTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_aperturas_superficieTable.Ot_aperturas_superficieColumns;
import com.aguasnortesalta.ordenes.model.Ot_aperturas_superficie;


public class Ot_aperturas_superficieDAO implements DAO<Ot_aperturas_superficie>{

	private static final String INSERT = "insert into "
			+ Ot_aperturas_superficieTable.TABLE_NAME + "(" 
			+ Ot_aperturas_superficieColumns._ID 
		    + ", "+ Ot_aperturas_superficieColumns.ID_SUPERFICIE
			+ ", "+ Ot_aperturas_superficieColumns.DESCRIPCION
			+ ", "+ Ot_aperturas_superficieColumns.TIPO_APERTURA

			+ ") values (?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public Ot_aperturas_superficieDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(Ot_aperturas_superficieDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
		insertStatement.bindString(3, data[2]);
		insertStatement.bindString(4, data[3]);

		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(Ot_aperturas_superficieTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Ot_aperturas_superficie getOt_aperturas_superficie(long id) {
		Ot_aperturas_superficie  place = null;
		String[] columns = Ot_aperturas_superficieColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(Ot_aperturas_superficieTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Ot_aperturas_superficie();
			place.set_id(c.getLong(0));
             place.setId_superficie((int)c.getLong(1));
 place.setDescripcion(c.getString(2));
 place.setTipo_apertura(c.getString(3));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Ot_aperturas_superficie[] get(String condition, String[] params) {
		Ot_aperturas_superficie[] Ot_aperturas_superficie = null;
		String[] columns = Ot_aperturas_superficieColumns.getColumns();
		
		Cursor c = db.query(Ot_aperturas_superficieTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Ot_aperturas_superficie = new Ot_aperturas_superficie[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Ot_aperturas_superficie[i] = new Ot_aperturas_superficie();
				Ot_aperturas_superficie place = new Ot_aperturas_superficie();
				place.set_id(c.getLong(0));
				place.setId_superficie((int)c.getLong(1));
				place.setDescripcion(c.getString(2));
				place.setTipo_apertura(c.getString(3));
				Ot_aperturas_superficie[i] = place;
				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Ot_aperturas_superficie;
	}
	@Override
	public Ot_aperturas_superficie get(long id) {

		Ot_aperturas_superficie[] ot_aperturas_superficie = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (ot_aperturas_superficie == null)
			return null;

		return ot_aperturas_superficie[0];
	}




	@Override
	public Ot_aperturas_superficie[] getAll() {
		return null;
	}



}
