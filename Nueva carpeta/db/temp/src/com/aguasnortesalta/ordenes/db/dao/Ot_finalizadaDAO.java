package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.OtTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_finalizadaTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_finalizadaTable.Ot_finalizadaColumns;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.utils.Util;


public class Ot_finalizadaDAO extends DAOBase implements DAO<Ot_finalizada>{

	private static final String INSERT = "insert into "
			+ Ot_finalizadaTable.TABLE_NAME + "(" 
			/*+ Ot_finalizadaColumns._ID 
		    + ", "*/+ Ot_finalizadaColumns.ID
			+ ", "+ Ot_finalizadaColumns.OT
			+ ", "+ Ot_finalizadaColumns.FECHAINICIO
			+ ", "+ Ot_finalizadaColumns.FECHAFINALIZO
			+ ", "+ Ot_finalizadaColumns.IDMOTIVOFINALIZA
			+ ", "+ Ot_finalizadaColumns.LAT
			+ ", "+ Ot_finalizadaColumns.LNG
			+ ", "+ Ot_finalizadaColumns.ALTURA
			+ ", "+ Ot_finalizadaColumns.ESTADO
			+ ", "+ Ot_finalizadaColumns.T
			+ ", "+ Ot_finalizadaColumns.FCH
			+ ", "+ Ot_finalizadaColumns.FCHALTA
			+ ", "+ Ot_finalizadaColumns.LEGAJO
			+ ", "+ Ot_finalizadaColumns.CODIGO_CUADRILLA
			+ ", "+ Ot_finalizadaColumns.OBSERVACION
			+ ", "+ Ot_finalizadaColumns.ID_SEG
			+ ", "+ Ot_finalizadaColumns.NRO_SEC
			+ ", "+ Ot_finalizadaColumns.ID_TXC
			+ ", "+ Ot_finalizadaColumns.ID_INM
			+ ", "+ Ot_finalizadaColumns.NRO_FORM

			+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public Ot_finalizadaDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(Ot_finalizadaDAO.INSERT);
	}
	
	public long insert3(Ot_finalizada obj) {
		try {
			return this.do_insert(obj, Ot_finalizada.class, db, Ot_finalizadaTable.TABLE_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return 0;
			
		}
	}
	public long update(Ot_finalizada obj){
		
		 ContentValues contentValues = new ContentValues();
		 contentValues.put(Ot_finalizadaTable.Ot_finalizadaColumns.ESTADO, obj.getEstado());
		 contentValues.put(Ot_finalizadaTable.Ot_finalizadaColumns.FECHAFINALIZO, obj.getFechafinalizo());
		 contentValues.put(Ot_finalizadaTable.Ot_finalizadaColumns.IDMOTIVOFINALIZA, obj.getIdmotivofinaliza());
		 contentValues.put(Ot_finalizadaTable.Ot_finalizadaColumns.LAT, obj.getLat());
		 contentValues.put(Ot_finalizadaTable.Ot_finalizadaColumns.LNG, obj.getLng());
		 
		return db.update(Ot_finalizadaTable.TABLE_NAME, contentValues, "OT=?", new String[]{String.valueOf(obj.getOt())});
						
		
	}
	public long insert2(Ot_finalizada obj) {
		//insertStatement.clearBindings();		
		
		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Ot_finalizada.class.getDeclaredFields();		
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
		INSERT2="insert into "+ Ot_finalizadaTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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
		//insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(1,  Long.valueOf(data[1]));
		insertStatement.bindLong(2,  Long.valueOf(data[2]));
		insertStatement.bindString(3, data[3]);
		insertStatement.bindString(4, data[4]);
		insertStatement.bindLong(5,  Long.valueOf(data[5]));
		insertStatement.bindString(6, data[6]);
		insertStatement.bindString(7, data[7]);
		insertStatement.bindString(8, data[8]);
		insertStatement.bindString(9, data[9]);
		insertStatement.bindString(10, data[10]);
		insertStatement.bindString(11, data[11]);
		insertStatement.bindString(12, data[12]);
		insertStatement.bindLong(13,  Long.valueOf(data[13]));
		insertStatement.bindLong(14,  Long.valueOf(data[14]));
		insertStatement.bindString(15, data[15]);
		insertStatement.bindLong(16,  Long.valueOf(data[16]));
		insertStatement.bindLong(17,  Long.valueOf(data[17]));
		insertStatement.bindLong(18,  Long.valueOf(data[18]));
		insertStatement.bindLong(19,  Long.valueOf(data[19]));
		insertStatement.bindLong(20,  Long.valueOf(data[20]));

		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(Ot_finalizadaTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Ot_finalizada getOt_finalizada(long id) {
		Ot_finalizada  place = null;
		String[] columns = Ot_finalizadaColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(Ot_finalizadaTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Ot_finalizada();
			place.set_id(c.getLong(0));
            place.setId(c.getLong(1));
             place.setOt(c.getLong(2));
			 place.setFechainicio(c.getString(3));
			 place.setFechafinalizo(c.getString(4));
			 place.setIdmotivofinaliza((int)c.getLong(5));
			 place.setLat(c.getString(6));
			 place.setLng(c.getString(7));
			 place.setAltura(c.getString(8));
			 place.setEstado(c.getString(9));
			 place.setT(c.getString(10));
			 place.setFch(c.getString(11));
			 place.setFchalta(c.getString(12));
			 place.setLegajo((int)c.getLong(13));
			 place.setCodigo_cuadrilla((int)c.getLong(14));
			 place.setObservacion(c.getString(15));
			 place.setId_seg((int)c.getLong(16));
			 place.setNro_sec((int)c.getLong(17));
			 place.setId_txc((int)c.getLong(18));
			 place.setId_inm((int)c.getLong(19));
			 place.setNro_form((int)c.getLong(20));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Ot_finalizada[] get(String condition, String[] params) {
		Ot_finalizada[] Ot_finalizada = null;
		String[] columns = Ot_finalizadaColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(Ot_finalizadaTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return Ot_finalizada;
		}
		if (c.moveToFirst()) {
			Ot_finalizada = new Ot_finalizada[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Ot_finalizada[i] = new Ot_finalizada();
				Ot_finalizada place = new Ot_finalizada();
				place.set_id(c.getLong(0));
				  place.setId(c.getLong(1));
				  place.setOt(c.getLong(2));
					 place.setFechainicio(c.getString(3));
					 place.setFechafinalizo(c.getString(4));
					 place.setIdmotivofinaliza((int)c.getLong(5));
					 place.setLat(c.getString(6));
					 place.setLng(c.getString(7));
					 place.setAltura(c.getString(8));
					 place.setEstado(c.getString(9));
					 place.setT(c.getString(10));
					 place.setFch(c.getString(11));
					 place.setFchalta(c.getString(12));
					 place.setLegajo((int)c.getLong(13));
					 place.setCodigo_cuadrilla((int)c.getLong(14));
					 place.setObservacion(c.getString(15));
					 place.setId_seg((int)c.getLong(16));
					 place.setNro_sec((int)c.getLong(17));
					 place.setId_txc((int)c.getLong(18));
					 place.setId_inm((int)c.getLong(19));
					 place.setNro_form((int)c.getLong(20));


				Ot_finalizada[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Ot_finalizada;
	}
	@Override
	public Ot_finalizada get(long id) {

		Ot_finalizada[] ot_finalizada = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (ot_finalizada == null)
			return null;

		return ot_finalizada[0];
	}




	@Override
	public Ot_finalizada[] getAll() {
		return null;
	}



}
