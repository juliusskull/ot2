package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.EquipoTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_aperturasTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_finalizadaTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_aperturasTable.Ot_aperturasColumns;
import com.aguasnortesalta.ordenes.model.Ot_aperturas;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.utils.Util;


public class Ot_aperturasDAO extends DAOBase implements DAO<Ot_aperturas>{

	private static final String INSERT = "insert into "
			+ Ot_aperturasTable.TABLE_NAME + "(" + Ot_aperturasColumns._ID 
		    + ", "+ Ot_aperturasColumns.OT
			+ ", "+ Ot_aperturasColumns.TIPO_APERTURA
			+ ", "+ Ot_aperturasColumns.ID_TIPO_MATERIAL
			+ ", "+ Ot_aperturasColumns.ANCHO
			+ ", "+ Ot_aperturasColumns.LARGO
			+ ", "+ Ot_aperturasColumns.PROFUNDIDAD
			+ ", "+ Ot_aperturasColumns.ID_TIPO_SENIAL
			+ ", "+ Ot_aperturasColumns.ESTADO_APERTURA
			+ ", "+ Ot_aperturasColumns.FCHALTA
			+ ", "+ Ot_aperturasColumns.FCHCAD

			+ ") values (?,?,?,?,?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public Ot_aperturasDAO(SQLiteDatabase db) {
		this.db = db;
		
		try {
			insertStatement = db.compileStatement(Ot_aperturasDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Util.Log("Error=>no existe "+Ot_aperturasTable.TABLE_NAME);
			Ot_aperturasTable.onCreate(db, null);
			e.printStackTrace();
		}
	}
	public long insert3(Ot_aperturas obj) {
		try {
			return this.do_insert(obj, Ot_aperturas.class, db, Ot_aperturasTable.TABLE_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return 0;
			
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
		insertStatement.bindString(7, data[6]);
		insertStatement.bindLong(8,  Long.valueOf(data[7]));
		insertStatement.bindString(9, data[8]);
		insertStatement.bindString(10, data[9]);
		insertStatement.bindString(11, data[10]);

		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(Ot_aperturasTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Ot_aperturas getOt_aperturas(long id) {
		Ot_aperturas  place = null;
		String[] columns = Ot_aperturasColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(Ot_aperturasTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Ot_aperturas();
			place.set_id(c.getLong(0));
             place.setOt(c.getLong(1));
			 place.setTipo_apertura(c.getString(2));
			 place.setId_tipo_material(c.getLong(3));
			 place.setAncho(c.getString(4));
			 place.setLargo(c.getString(5));
			 place.setProfundidad(c.getString(6));
			 place.setId_tipo_senial((int)c.getLong(7));
			 place.setEstado_apertura(c.getString(8));
			 place.setFchalta(c.getString(9));
			 place.setFchcad(c.getString(10));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Ot_aperturas[] get(String condition, String[] params) {
		Ot_aperturas[] Ot_aperturas = null;
		String[] columns = Ot_aperturasColumns.getColumns();
		
		Cursor c = db.query(Ot_aperturasTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		Ot_aperturas = new Ot_aperturas[c.getCount()];
		if (c.moveToFirst()) {
			
			for (int i = 0; i < c.getCount(); i++) {
				Ot_aperturas[i] = new Ot_aperturas();
				Ot_aperturas place = new Ot_aperturas();
				place.set_id(c.getLong(0));
				place.setOt(c.getLong(1));
				 place.setTipo_apertura(c.getString(2));
				 place.setId_tipo_material(c.getLong(3));
				 place.setAncho(c.getString(4));
				 place.setLargo(c.getString(5));
				 place.setProfundidad(c.getString(6));
				 place.setId_tipo_senial((int)c.getLong(7));
				 place.setEstado_apertura(c.getString(8));
				 place.setFchalta(c.getString(9));
				 place.setFchcad(c.getString(10));

				Ot_aperturas[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Ot_aperturas;
	}
	@Override
	public Ot_aperturas get(long id) {

		Ot_aperturas[] ot_aperturas = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (ot_aperturas == null)
			return null;

		return ot_aperturas[0];
	}




	@Override
	public Ot_aperturas[] getAll() {
		return null;
	}

	public  void delete() {
	    db.execSQL("delete from  " + Ot_aperturasTable.TABLE_NAME);
	    
	}

}
