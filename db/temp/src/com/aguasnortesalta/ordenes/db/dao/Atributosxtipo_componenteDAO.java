package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.Atributosxtipo_componenteTable;
import com.aguasnortesalta.ordenes.db.tables.Atributosxtipo_componenteTable.Atributosxtipo_componenteColumns;
import com.aguasnortesalta.ordenes.model.Atributosxtipo_componente;
import com.aguasnortesalta.ordenes.utils.Util;


public class Atributosxtipo_componenteDAO extends DAOBase implements DAO<Atributosxtipo_componente>{

	private static final String INSERT = "insert into "
			+ Atributosxtipo_componenteTable.TABLE_NAME + "(" + Atributosxtipo_componenteColumns._ID
		    + ", "+ Atributosxtipo_componenteColumns.ID_TIPO_COMPONENTE
			+ ", "+ Atributosxtipo_componenteColumns.ID_TIPO_ATRIBUTO
			+ ", "+ Atributosxtipo_componenteColumns.DESC_ATRIBUTO
			+ ", "+ Atributosxtipo_componenteColumns.TIPO_DATO
			+ ", "+ Atributosxtipo_componenteColumns.ID_UNIDAD_MEDIDA

			+ ") values (?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public Atributosxtipo_componenteDAO(SQLiteDatabase db) {
		this.db = db;
		try {
		    insertStatement = db.compileStatement(Atributosxtipo_componenteDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Atributosxtipo_componenteTable.onCreate(db, null);
			insertStatement = db.compileStatement(Atributosxtipo_componenteDAO.INSERT);
			e.printStackTrace();
		}
	}
public long insert2(Atributosxtipo_componente obj) {
		//insertStatement.clearBindings();

		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Atributosxtipo_componente.class.getDeclaredFields();
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
		INSERT2="insert into "+ Atributosxtipo_componenteTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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
insertStatement.bindString(4, data[3]);
insertStatement.bindString(5, data[4]);
insertStatement.bindString(6, data[5]);


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(Atributosxtipo_componenteTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Atributosxtipo_componente getAtributosxtipo_componente(long id) {
		Atributosxtipo_componente  place = null;
		String[] columns = Atributosxtipo_componenteColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(Atributosxtipo_componenteTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Atributosxtipo_componente();
			place.set_id(c.getLong(0));
             place.setId_tipo_componente((int)c.getLong(1));
			 place.setId_tipo_atributo((int)c.getLong(2));
			 place.setDesc_atributo(c.getString(3));
			 place.setTipo_dato(c.getString(4));
			 place.setID_UNIDAD_MEDIDA(c.getString(5));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Atributosxtipo_componente[] get(String condition, String[] params) {
		Atributosxtipo_componente[] Atributosxtipo_componente = null;
		String[] columns = Atributosxtipo_componenteColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(Atributosxtipo_componenteTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Atributosxtipo_componente = new Atributosxtipo_componente[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Atributosxtipo_componente[i] = new Atributosxtipo_componente();
				Atributosxtipo_componente place = new Atributosxtipo_componente();
				place.set_id(c.getLong(0));
				  place.setId_tipo_componente((int)c.getLong(1));
				 place.setId_tipo_atributo((int)c.getLong(2));
				 place.setDesc_atributo(c.getString(3));
				 place.setTipo_dato(c.getString(4));
				 place.setID_UNIDAD_MEDIDA(c.getString(5));


				Atributosxtipo_componente[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Atributosxtipo_componente;
	}
	@Override
	public Atributosxtipo_componente get(long id) {

		Atributosxtipo_componente[] atributosxtipo_componente = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (atributosxtipo_componente == null)
			return null;

		return atributosxtipo_componente[0];
	}




	@Override
	public Atributosxtipo_componente[] getAll() {
		return null;
	}



}
