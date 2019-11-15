package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.SincronizarTable;
import com.aguasnortesalta.ordenes.db.tables.SincronizarTable.SincronizarColumns;
import com.aguasnortesalta.ordenes.model.Sincronizar;
import com.aguasnortesalta.ordenes.utils.Util;


public class SincronizarDAO extends DAOBase implements DAO<Sincronizar>{

	private static final String INSERT = "insert into "
				+ SincronizarTable.TABLE_NAME + "(" + SincronizarColumns._ID
			    + ", "+ SincronizarColumns.ID
				+ ", "+ SincronizarColumns.TIPO
				+ ", "+ SincronizarColumns.VALOR
				+ ", "+ SincronizarColumns.LAT
				+ ", "+ SincronizarColumns.LNG
				+ ", "+ SincronizarColumns.ENVIADO
			+ ") values (?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public SincronizarDAO(SQLiteDatabase db) {
		this.db = db;
		//db.execSQL("DROP TABLE IF EXISTS " + SincronizarTable.TABLE_NAME);
		try {
		    insertStatement = db.compileStatement(SincronizarDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			SincronizarTable.onCreate(db, null);
			insertStatement = db.compileStatement(SincronizarDAO.INSERT);
			e.printStackTrace();
		}
	}
		public long insert2(Sincronizar obj) {
		//insertStatement.clearBindings();

		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Sincronizar.class.getDeclaredFields();
		String[] s= new String[fields.length+1];
		int i=0;
		for( Field field : fields ){
			try {
				 if(i>0){
					 INSERT2+=",";
					 INSERT2_P+=",";
				 }
				 INSERT2+=field.getName().toString();
				 INSERT2_P+="?";

				 i++;

			}catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//-----------------
		INSERT2="insert into "+ SincronizarTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
		SQLiteStatement insertStatement2 = db.compileStatement(INSERT2);
		insertStatement2.clearBindings();

		i=0;
		for( Field field : fields ){
			try {
				 field.setAccessible(true);
				 Util.Log("field:"+field.getName().toString()+":"+field.getType().getName());
				 Util.Log("field->:"+field.get(obj));
				 i++;
				 if(field.getType().getName().contains("int") || field.getType().getName().contains("long") ){

					 insertStatement2.bindLong(i,  Long.valueOf( String.valueOf(field.get(obj))));

				 } else{
					 insertStatement2.bindString(i,   String.valueOf( field.get(obj)));
				 }

				} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		Util.Log("INSERT2->:"+INSERT2);
		return insertStatement2.executeInsert();

	}


	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindString(2, data[1]);
		insertStatement.bindString(3, data[2]);
		insertStatement.bindString(4, data[3]);
		insertStatement.bindString(5, data[4]);
		insertStatement.bindString(6, data[5]);
		insertStatement.bindLong(7,  Long.valueOf(data[6]));

		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(SincronizarTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Sincronizar getSincronizar(long id) {
		Sincronizar  place = null;
		String[] columns = SincronizarColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(SincronizarTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			 place = new Sincronizar();
			 place.set_id(c.getLong(0));
             place.setId(c.getString(1));
			 place.setTipo(c.getString(2));
			 place.setValor(c.getString(3));
			 place.setLat(c.getString(4));
			 place.setLng(c.getString(5));
			 place.setEnviado((int)c.getLong(6));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Sincronizar[] get(String condition, String[] params) {
		Sincronizar[] Sincronizar = null;
		String[] columns = SincronizarColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(SincronizarTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Sincronizar = new Sincronizar[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Sincronizar[i] = new Sincronizar();
				Sincronizar place = new Sincronizar();
				place.set_id(c.getLong(0));
				place.setId(c.getString(1));
				place.setTipo(c.getString(2));
				place.setValor(c.getString(3));
				place.setLat(c.getString(4));
				place.setLng(c.getString(5));
				place.setEnviado((int)c.getLong(6));


				Sincronizar[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Sincronizar;
	}
	@Override
	public Sincronizar get(long id) {

		Sincronizar[] sincronizar = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (sincronizar == null)
			return null;

		return sincronizar[0];
	}


	@Override
	public Sincronizar[] getAll() {
		return null;
	}
	public void set_enviado(long get_id) {
		// TODO Auto-generated method stub
		ContentValues valores = new ContentValues();
		valores.put(SincronizarColumns.ENVIADO,"1");
		db.update(SincronizarTable.TABLE_NAME, valores, "_id="+get_id, null);
	}



}
