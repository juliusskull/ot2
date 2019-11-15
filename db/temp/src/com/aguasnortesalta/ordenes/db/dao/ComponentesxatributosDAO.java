package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.ComponentesxatributosTable;
import com.aguasnortesalta.ordenes.db.tables.ComponentesxatributosTable.ComponentesxatributosColumns;
import com.aguasnortesalta.ordenes.model.Componentesxatributos;
import com.aguasnortesalta.ordenes.utils.Util;


public class ComponentesxatributosDAO extends DAOBase implements DAO<Componentesxatributos>{

	private static final String INSERT = "insert into "
			+ ComponentesxatributosTable.TABLE_NAME + "(" + ComponentesxatributosColumns._ID
				+ ", "+ ComponentesxatributosColumns.ID_COMPONENTE
				+ ", "+ ComponentesxatributosColumns.FEATID
				+ ", "+ ComponentesxatributosColumns.ID_TIPO_COMPONENTE
				+ ", "+ ComponentesxatributosColumns.ID_TIPO_ATRIBUTO
				+ ", "+ ComponentesxatributosColumns.VALOR

			+ ") values (?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public ComponentesxatributosDAO(SQLiteDatabase db) {
		this.db = db;
		try {
		    insertStatement = db.compileStatement(ComponentesxatributosDAO.INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ComponentesxatributosTable.onCreate(db, null);
			insertStatement = db.compileStatement(ComponentesxatributosDAO.INSERT);
			e.printStackTrace();
		}
	}
public long insert2(Componentesxatributos obj) {
		//insertStatement.clearBindings();

		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Componentesxatributos.class.getDeclaredFields();
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
		INSERT2="insert into "+ ComponentesxatributosTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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
		insertStatement.bindLong(5,  Long.valueOf(data[4]));
		insertStatement.bindString(6, data[5]);


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(ComponentesxatributosTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Componentesxatributos getComponentesxatributos(long id) {
		Componentesxatributos  place = null;
		String[] columns = ComponentesxatributosColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(ComponentesxatributosTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Componentesxatributos();
			place.set_id(c.getLong(0));
             place.setId_componente((int)c.getLong(1));
			 place.setFeatid(c.getString(2));
			 place.setId_tipo_componente((int)c.getLong(3));
			 place.setId_tipo_atributo((int)c.getLong(4));
			 place.setValor(c.getString(5));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Componentesxatributos[] get(String condition, String[] params) {
		Componentesxatributos[] Componentesxatributos = null;
		String[] columns = ComponentesxatributosColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(ComponentesxatributosTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Componentesxatributos = new Componentesxatributos[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Componentesxatributos[i] = new Componentesxatributos();
				Componentesxatributos place = new Componentesxatributos();
				 place.set_id(c.getLong(0));
				 place.setId_componente((int)c.getLong(1));
				 place.setFeatid(c.getString(2));
				 place.setId_tipo_componente((int)c.getLong(3));
				 place.setId_tipo_atributo((int)c.getLong(4));
				 place.setValor(c.getString(5));


				Componentesxatributos[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Componentesxatributos;
	}
	
public Componentesxatributos[] get_view(String condition, String[] params) {
		Componentesxatributos[] Componentesxatributos = null;
		String[] columns = ComponentesxatributosColumns.getColumns_view();
	
		try {
		
			Cursor c = db.query(ComponentesxatributosTable.VIEW_NAME, columns, condition,params, null, null, null);
			
			if (c.getCount() == 0) {
				c.close();
				return Componentesxatributos;
			}
			if (c.moveToFirst()) {
				Componentesxatributos = new Componentesxatributos[c.getCount()];
				for (int i = 0; i < c.getCount(); i++) {
					Componentesxatributos[i] = new Componentesxatributos();
					Componentesxatributos place = new Componentesxatributos();
					 place.set_id(c.getLong(0));
					 place.setId_componente((int)c.getLong(1));
					 place.setFeatid(c.getString(2));
					 place.setId_tipo_componente((int)c.getLong(3));
					 place.setId_tipo_atributo((int)c.getLong(4));
					 place.setValor(c.getString(5));
					 
					 place.desc_atributo=c.getString(6);
					 place.tipo_dato=c.getString(7);
					 
					 place.unidad_medida=c.getString(8);

					 Componentesxatributos[i] = place;

					 c.moveToNext();
				}
			}
			if (!c.isClosed()) {
				c.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("error table "+ComponentesxatributosTable.VIEW_NAME+"->"+e.getMessage());
			
		}
		return Componentesxatributos;
	}
	@Override
	public Componentesxatributos get(long id) {

		Componentesxatributos[] componentesxatributos = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (componentesxatributos == null)
			return null;

		return componentesxatributos[0];
	}




	@Override
	public Componentesxatributos[] getAll() {
		return null;
	}
	public long get_view_cant(String condition, String[] params) {
		// TODO Auto-generated method stub
		String[] columns = ComponentesxatributosColumns.getColumns_view();
		Cursor c = db.query(ComponentesxatributosTable.VIEW_NAME, columns, condition,params, null, null, null);
		
		return c.getCount();
	}
	public void delete(Componentesxatributos a) {
		// TODO Auto-generated method stub
			
		db.delete("componentesxatributos", "featid=? and id_tipo_componente=? and id_tipo_atributo=?"
				,new String[]{a.getFeatid(),String.valueOf(a.getId_tipo_componente()),String.valueOf(a.getId_tipo_atributo())});
	}
	public long insert_componentesxatributos(Componentesxatributos a) {
		/*es por que es una vista*/
		
		try {
			ContentValues insertValues = new ContentValues();
			insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns._ID, a.get_id());
			insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns.FEATID, a.getFeatid());
			insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns.ID_TIPO_COMPONENTE, a.getId_tipo_componente());
			insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns.ID_TIPO_ATRIBUTO, a.getId_tipo_atributo());
			insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns.VALOR, a.getValor());
			
			return	db.insert("componentesxatributos", "_id=?,featid=?,id_tipo_componente=?,id_tipo_atributo=?,valor=?"
					,insertValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log(" Error => (insert)"+e.getMessage());
			return -1;
		}
	}
	
	public void update(Componentesxatributos a) {
		// TODO Auto-generated method stub
		/*es por que es una vista*/
		/*
		ContentValues insertValues = new ContentValues();
		insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns._ID, a.get_id());
		insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns.FEATID, a.getFeatid());
		insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns.ID_TIPO_COMPONENTE, a.getId_tipo_componente());
		insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns.ID_TIPO_ATRIBUTO, a.getId_tipo_atributo());
		insertValues.put(ComponentesxatributosTable.ComponentesxatributosColumns.VALOR, a.getValor());

		db.update("componentesxatributos", "featid=? and id_tipo_componente=? and id_tipo_atributo=?",insertValues);
	*/
	}


}
