package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.ArchivoTable;
import com.aguasnortesalta.ordenes.db.tables.SincronizarTable;
import com.aguasnortesalta.ordenes.db.tables.ArchivoTable.ArchivoColumns;
import com.aguasnortesalta.ordenes.db.tables.SincronizarTable.SincronizarColumns;
import com.aguasnortesalta.ordenes.model.Archivo;
import com.aguasnortesalta.ordenes.utils.Util;


public class ArchivoDAO extends DAOBase implements DAO<Archivo>{
	public static final String TIPO_IMAGEN = "imagen";
	private static final String INSERT = "insert into "
			+ ArchivoTable.TABLE_NAME + "(" + ArchivoColumns._ID
		    + ", "+ ArchivoColumns.NOMBRE
			+ ", "+ ArchivoColumns.TIPO
			+ ", "+ ArchivoColumns.OT
			+ ", "+ ArchivoColumns.ENVIADO
			+ ") values (?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public ArchivoDAO(SQLiteDatabase db) {
		this.db = db;
	
		try {
		    insertStatement = db.compileStatement(ArchivoDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			db.execSQL("DROP TABLE IF EXISTS " + ArchivoTable.TABLE_NAME);
			ArchivoTable.onCreate(db, null);
			insertStatement = db.compileStatement(ArchivoDAO.INSERT);
			e.printStackTrace();
		}
	}
	public long insert2(Archivo obj) {
			//insertStatement.clearBindings();
	
			String INSERT2="";
			String INSERT2_P="";
			Field[] fields = Archivo.class.getDeclaredFields();
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
			INSERT2="insert into "+ ArchivoTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(ArchivoTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Archivo getArchivo(long id) {
		Archivo  place = null;
		String[] columns = ArchivoColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(ArchivoTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			 place = new Archivo();
			 place.set_id(c.getLong(0));
             place.setNombre(c.getString(1));
			 place.setTipo(c.getString(2));
			 place.setOt(c.getString(3));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}

	public Archivo[] get(String condition, String[] params) {
		Archivo[] Archivo = null;
		String[] columns = ArchivoColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(ArchivoTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Archivo = new Archivo[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Archivo[i] = new Archivo();
				Archivo place = new Archivo();
				place.set_id(c.getLong(0));
				place.setNombre(c.getString(1));
				place.setTipo(c.getString(2));
				place.setOt(c.getString(3));
				Archivo[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Archivo;
	}
	@Override
	public Archivo get(long id) {

		Archivo[] archivo = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (archivo == null)
			return null;

		return archivo[0];
	}

	@Override
	public Archivo[] getAll() {
		return null;
	}
	public void set_enviado(long get_id) {
		// TODO Auto-generated method stub
		ContentValues valores = new ContentValues();
		valores.put(SincronizarColumns.ENVIADO,"1");
		db.update(SincronizarTable.TABLE_NAME, valores, "_id="+get_id, null);
	}


}
