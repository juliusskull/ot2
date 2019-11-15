package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.Categorias_tramoTable;
import com.aguasnortesalta.ordenes.db.tables.Categorias_tramoTable.Categorias_tramoColumns;
import com.aguasnortesalta.ordenes.model.Categorias_tramo;
import com.aguasnortesalta.ordenes.utils.Util;


public class Categorias_tramoDAO extends DAOBase implements DAO<Categorias_tramo>{

	private static final String INSERT = "insert into "
			+ Categorias_tramoTable.TABLE_NAME + "(" 
			+ Categorias_tramoColumns._ID
		    + ", "+ Categorias_tramoColumns.ID_CATEGORIA
			+ ", "+ Categorias_tramoColumns.DESC_CATEGORIA
			+ ", "+ Categorias_tramoColumns.ID_TIPO_SERVICIO

			+ ") values (?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public Categorias_tramoDAO(SQLiteDatabase db) {
		this.db = db;
		try {
		    insertStatement = db.compileStatement(Categorias_tramoDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Categorias_tramoTable.onCreate(db, null);
			insertStatement = db.compileStatement(Categorias_tramoDAO.INSERT);
			e.printStackTrace();
		}
	}
public long insert2(Categorias_tramo obj) {
		//insertStatement.clearBindings();

		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Categorias_tramo.class.getDeclaredFields();
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
		INSERT2="insert into "+ Categorias_tramoTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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
insertStatement.bindLong(3,  Long.valueOf(data[2]));
insertStatement.bindLong(4,  Long.valueOf(data[3]));


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(Categorias_tramoTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Categorias_tramo getCategorias_tramo(long id) {
		Categorias_tramo  place = null;
		String[] columns = Categorias_tramoColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(Categorias_tramoTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Categorias_tramo();
			place.set_id(c.getLong(0));
             place.setId_categoria((int)c.getLong(1));
             place.setDesc_categoria(c.getString(2));
			 place.setId_tipo_servicio((int)c.getLong(3));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Categorias_tramo[] get(String condition, String[] params) {
		Categorias_tramo[] Categorias_tramo = null;
		String[] columns = Categorias_tramoColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(Categorias_tramoTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Categorias_tramo = new Categorias_tramo[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Categorias_tramo[i] = new Categorias_tramo();
				Categorias_tramo place = new Categorias_tramo();
				place.set_id(c.getLong(0));
				place.setId_categoria((int)c.getLong(1));
				place.setDesc_categoria(c.getString(2));
				place.setId_tipo_servicio((int)c.getLong(3));
				Categorias_tramo[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Categorias_tramo;
	}
	@Override
	public Categorias_tramo get(long id) {

		Categorias_tramo[] categorias_tramo = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (categorias_tramo == null)
			return null;

		return categorias_tramo[0];
	}




	@Override
	public Categorias_tramo[] getAll() {
		return null;
	}



}
