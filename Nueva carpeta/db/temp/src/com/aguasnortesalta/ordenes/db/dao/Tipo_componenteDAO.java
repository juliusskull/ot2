package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.Tipo_componenteTable;
import com.aguasnortesalta.ordenes.db.tables.Tipo_componenteTable.Tipo_componenteColumns;
import com.aguasnortesalta.ordenes.model.Tipo_componente;
import com.aguasnortesalta.ordenes.utils.Util;


public class Tipo_componenteDAO extends DAOBase implements DAO<Tipo_componente>{

	private static final String INSERT = "insert into "
			+ Tipo_componenteTable.TABLE_NAME + "(" + Tipo_componenteColumns._ID
		    + ", "+ Tipo_componenteColumns.ID_TIPO_COMPONENTE
+ ", "+ Tipo_componenteColumns.DESC_TIPO_COMPONENTE
+ ", "+ Tipo_componenteColumns.ID_TIPO_SERVICIO

			+ ") values (?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public Tipo_componenteDAO(SQLiteDatabase db) {
		this.db = db;
		try {
		    insertStatement = db.compileStatement(Tipo_componenteDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Tipo_componenteTable.onCreate(db, null);
			insertStatement = db.compileStatement(Tipo_componenteDAO.INSERT);
			e.printStackTrace();
		}
	}
public long insert2(Tipo_componente obj) {
		//insertStatement.clearBindings();

		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Tipo_componente.class.getDeclaredFields();
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
		INSERT2="insert into "+ Tipo_componenteTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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
insertStatement.bindLong(4,  Long.valueOf(data[3]));


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(Tipo_componenteTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Tipo_componente getTipo_componente(long id) {
		Tipo_componente  place = null;
		String[] columns = Tipo_componenteColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(Tipo_componenteTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			 place = new Tipo_componente();
			 place.set_id(c.getLong(0));
             place.setId_tipo_componente((int)c.getLong(1));
			 place.setDesc_tipo_componente(c.getString(2));
			 place.setId_tipo_servicio((int)c.getLong(3));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Tipo_componente[] get(String condition, String[] params) {
		Tipo_componente[] Tipo_componente = null;
		String[] columns = Tipo_componenteColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(Tipo_componenteTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Tipo_componente = new Tipo_componente[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Tipo_componente[i] = new Tipo_componente();
				Tipo_componente place = new Tipo_componente();
				place.set_id(c.getLong(0));
				place.setId_tipo_componente((int)c.getLong(1));
				place.setDesc_tipo_componente(c.getString(2));
				place.setId_tipo_servicio((int)c.getLong(3));


				Tipo_componente[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Tipo_componente;
	}
	@Override
	public Tipo_componente get(long id) {

		Tipo_componente[] tipo_componente = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (tipo_componente == null)
			return null;

		return tipo_componente[0];
	}




	@Override
	public Tipo_componente[] getAll() {
		return null;
	}



}
