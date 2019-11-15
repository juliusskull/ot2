package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.ConfirmacionTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_finalizadaTable;
import com.aguasnortesalta.ordenes.db.tables.ConfirmacionTable.ConfirmacionColumns;
import com.aguasnortesalta.ordenes.model.Confirmacion;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.utils.Util;


public class ConfirmacionDAO extends DAOBase implements DAO<Confirmacion>{

	private static final String INSERT = "insert into "
			+ ConfirmacionTable.TABLE_NAME + "(" +
					ConfirmacionColumns._ID 
		    + ", "+ ConfirmacionColumns.NRO_OT
			+ ", "+ ConfirmacionColumns.VALOR
			+ ", "+ ConfirmacionColumns.ENVIADO

			+ ") values (?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public ConfirmacionDAO(SQLiteDatabase db) {
		this.db = db;
		try {
		    insertStatement = db.compileStatement(ConfirmacionDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    db.execSQL("DROP TABLE IF EXISTS " + ConfirmacionTable.TABLE_NAME);
			ConfirmacionTable.onCreate(db, null);
			insertStatement = db.compileStatement(ConfirmacionDAO.INSERT);
			e.printStackTrace();
		}
	}
public long insert2(Confirmacion obj) {
		//insertStatement.clearBindings();

		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Confirmacion.class.getDeclaredFields();
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
		INSERT2="insert into "+ ConfirmacionTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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
		int i = 1;
		Cursor c = db.rawQuery("select max(_id) id from "+ConfirmacionTable.TABLE_NAME, null);
		if (c.getCount() > 0) {
			 c.moveToFirst();
			 i=c.getInt(c.getColumnIndex("id"));
		}
		Util.Log("index=>"+i);
		insertStatement.bindLong(1, i);
		insertStatement.bindString(2, data[0]);
		insertStatement.bindString(3, data[1]);
		insertStatement.bindLong(4,  Long.valueOf(data[2]));


		return insertStatement.executeInsert();
	}


	@Override
	public void remove(long id) {
		db.delete(ConfirmacionTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Confirmacion getConfirmacion(long id) {
		Confirmacion  place = null;
		String[] columns = ConfirmacionColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(ConfirmacionTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Confirmacion();
			place.set_id(c.getLong(0));
             place.setNro_ot(c.getString(1));
			 place.setValor(c.getString(2));
			 place.setEnviado((int)c.getLong(3));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Confirmacion[] get(String condition, String[] params) {
		Confirmacion[] Confirmacion = null;
		String[] columns = ConfirmacionColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(ConfirmacionTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Confirmacion = new Confirmacion[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Confirmacion[i] = new Confirmacion();
				Confirmacion place = new Confirmacion();
				place.set_id(c.getLong(0));
				place.setNro_ot(c.getString(1));
				place.setValor(c.getString(2));
				place.setEnviado((int)c.getLong(3));


				Confirmacion[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Confirmacion;
	}
	@Override
	public Confirmacion get(long id) {

		Confirmacion[] confirmacion = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (confirmacion == null)
			return null;

		return confirmacion[0];
	}




	@Override
	public Confirmacion[] getAll() {
		return null;
	}

	public int update(String[] strings) {
		// TODO Auto-generated method stub
		 ContentValues contentValues = new ContentValues();
		// contentValues.put(ConfirmacionColumns.NRO_OT, strings[0]);
		 contentValues.put(ConfirmacionColumns.VALOR, strings[1]);
		 contentValues.put(ConfirmacionColumns.ENVIADO, strings[2]);
		 
		 return db.update(
				 ConfirmacionTable.TABLE_NAME
				 , contentValues
				 , ConfirmacionColumns.NRO_OT+"=?"
				 , new String[]{strings[0]}
				 );
			

	}



}
