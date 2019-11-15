package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.GeometriaTable;
import com.aguasnortesalta.ordenes.db.tables.GeometriaTable.GeometriaColumns;
import com.aguasnortesalta.ordenes.model.Geometria;
import com.aguasnortesalta.ordenes.utils.Util;


public class GeometriaDAO implements DAO<Geometria>{

	private static final String INSERT = "insert into "
			+ GeometriaTable.TABLE_NAME + "(" 
				  + GeometriaColumns._ID 
		    + ", "+ GeometriaColumns.FEATID
			+ ", "+ GeometriaColumns.TYPE
			+ ", "+ GeometriaColumns.PROPERTIES
			+ ", "+ GeometriaColumns.GEOMETRY
			+ ", "+ GeometriaColumns.ACTUALIZAR
			+ ", "+ GeometriaColumns.LAT
			+ ", "+ GeometriaColumns.LNG
			+ ") values (?,?,?,?,?,?,?,?)";
	/*insert con el incremento*/
	private static final String INSERT2 = "insert into "
			+ GeometriaTable.TABLE_NAME + "(" 
		    +       GeometriaColumns.FEATID
			+ ", "+ GeometriaColumns.TYPE
			+ ", "+ GeometriaColumns.PROPERTIES
			+ ", "+ GeometriaColumns.GEOMETRY
			+ ", "+ GeometriaColumns.ACTUALIZAR
			+ ") values (?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public GeometriaDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(GeometriaDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
		insertStatement.bindString(3, data[2]);
		insertStatement.bindString(4, data[3]);
		insertStatement.bindString(5, data[4]);
		insertStatement.bindLong(6,  Long.valueOf(data[5]));
		insertStatement.bindString(7, data[6]);
		insertStatement.bindString(8, data[7]);


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(GeometriaTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	
	public Geometria getGeometria(long id) {
		Geometria  place = null;
		String[] columns = GeometriaColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(GeometriaTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Geometria();
			place.set_id(c.getLong(0));
             place.setFeatid(c.getLong(1));
 place.setType(c.getString(2));
 place.setProperties(c.getString(3));
 place.setGeometry(c.getString(4));
 place.setActualizar((int)c.getLong(5));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}

	public Geometria[] get(double lat, double lng, long tipo) {
		Geometria[] Geometria = null;
		String[] columns = GeometriaColumns.getColumns();
		
		String s_lat= String.valueOf(lat);
		s_lat=s_lat.substring(0, 6);
		
		String s_lng= String.valueOf(lng);
		s_lng=s_lng.substring(0,6);
		Cursor c =null;
		if(tipo==-1){
			 c = db.query(GeometriaTable.TABLE_NAME, columns, " lat like '"+s_lat+"%' and lng like '"+s_lng+"%'",
					null, null, null, null);	
		}else{
			 c = db.query(GeometriaTable.TABLE_NAME, columns, "tipo_geometria="+tipo+" and "+" lat like '"+s_lat+"%' and lng like '"+s_lng+"%'",
					null, null, null, null);
		}
		
		if (c.getCount() == 0) {
			c.close();
			return Geometria;
		}
		if (c.moveToFirst()) {
			Geometria = new Geometria[c.getCount()];
			 Util.Log("json=>"+c.getCount());
			for (int i = 0; i < c.getCount(); i++) {
				Geometria[i] = new Geometria();
				Geometria place = new Geometria();
				place.set_id(c.getLong(0));
				place.setFeatid(c.getLong(1));
				place.setType(c.getString(2));
				place.setProperties(c.getString(3));
				place.setGeometry(c.getString(4));
				place.setActualizar((int)c.getInt(5));
				place.setActualizar(0);
				place.setLat(c.getString(6));
				place.setLng(c.getString(7));
				
				Geometria[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Geometria;
	}
	public Geometria[] get(String condition, String[] params) {
		Geometria[] Geometria = null;
		String[] columns = GeometriaColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(GeometriaTable.TABLE_NAME, columns, condition,params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Geometria = new Geometria[c.getCount()];
			 Util.Log("json=>"+c.getCount());
			for (int i = 0; i < c.getCount(); i++) {
				Geometria[i] = new Geometria();
				Geometria place = new Geometria();
				 place.set_id(c.getLong(0));
				 place.setFeatid(c.getLong(1));
				 place.setType(c.getString(2));
				 place.setProperties(c.getString(3));
				 place.setGeometry(c.getString(4));
				 place.setActualizar((int)c.getInt(5));
				 place.setActualizar(0);
				 place.setLat(c.getString(6));
				 place.setLng(c.getString(7));
				 Util.Log("json=>"+place.toString());

				Geometria[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Geometria;
	}
	@Override
	public Geometria get(long id) {

		Geometria[] geometria = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (geometria == null)
			return null;

		return geometria[0];
	}

	@Override
	public Geometria[] getAll() {
		return null;
	}

	public long cant_total() {
		// TODO Auto-generated method stub
		try {
			String[] columns = GeometriaColumns.getColumns();
			Cursor c = db.query(GeometriaTable.TABLE_NAME,columns, "", new String[]{}, null, null, null);
			return c.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	public long cant_total(long tipo) {
		// TODO Auto-generated method stub
		try {
			String[] columns = GeometriaColumns.getColumns();
			Cursor c = db.query(GeometriaTable.TABLE_NAME,columns, "tipo_geometria=?", new String[]{String.valueOf(tipo)}, null, null, null);
			return c.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	



}
