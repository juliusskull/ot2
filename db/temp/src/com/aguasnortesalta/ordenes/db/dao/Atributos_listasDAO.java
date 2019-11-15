package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.Atributos_listasTable;
import com.aguasnortesalta.ordenes.db.tables.Atributos_listasTable.Atributos_listasColumns;
import com.aguasnortesalta.ordenes.model.Atributos_listas;
import com.aguasnortesalta.ordenes.utils.Util;


public class Atributos_listasDAO extends DAOBase implements DAO<Atributos_listas>{

	private static final String INSERT = "insert into "
			+ Atributos_listasTable.TABLE_NAME + "(" + Atributos_listasColumns._ID
		    + ", "+ Atributos_listasColumns.ID_TIPO_ATRIBUTO
+ ", "+ Atributos_listasColumns.ID_LISTA
+ ", "+ Atributos_listasColumns.DESC_LISTA

			+ ") values (?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public Atributos_listasDAO(SQLiteDatabase db) {
		this.db = db;
		try {
		    insertStatement = db.compileStatement(Atributos_listasDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Atributos_listasTable.onCreate(db, null);
			insertStatement = db.compileStatement(Atributos_listasDAO.INSERT);
			e.printStackTrace();
		}
	}
public long insert2(Atributos_listas obj) {
		//insertStatement.clearBindings();

		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Atributos_listas.class.getDeclaredFields();
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
		INSERT2="insert into "+ Atributos_listasTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
insertStatement.bindString(3, data[2]);
insertStatement.bindString(4, data[3]);


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(Atributos_listasTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Atributos_listas getAtributos_listas(long id) {
		Atributos_listas  place = null;
		String[] columns = Atributos_listasColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(Atributos_listasTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Atributos_listas();
			place.set_id(c.getLong(0));
            place.setId_tipo_atributo((int)c.getLong(1));
			place.setId_lista(c.getString(2));
			place.setDesc_lista(c.getString(3));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Atributos_listas[] get(String condition, String[] params) {
		Atributos_listas[] Atributos_listas = null;
		String[] columns = Atributos_listasColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(Atributos_listasTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Atributos_listas = new Atributos_listas[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Atributos_listas[i] = new Atributos_listas();
				Atributos_listas place = new Atributos_listas();
				place.set_id(c.getLong(0));
				  place.setId_tipo_atributo((int)c.getLong(1));
 place.setId_lista(c.getString(2));
 place.setDesc_lista(c.getString(3));


				Atributos_listas[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Atributos_listas;
	}
	@Override
	public Atributos_listas get(long id) {

		Atributos_listas[] atributos_listas = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (atributos_listas == null)
			return null;

		return atributos_listas[0];
	}




	@Override
	public Atributos_listas[] getAll() {
		return null;
	}



}
