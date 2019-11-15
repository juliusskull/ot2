package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;
import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.OtTable;
import com.aguasnortesalta.ordenes.db.tables.OtTable.OtColumns;
import com.aguasnortesalta.ordenes.model.Ot;
import com.aguasnortesalta.ordenes.utils.Util;


public class OtDAO implements DAO<Ot>{

	private static final String INSERT = "insert into "
			+ OtTable.TABLE_NAME + "(" 
			/*+ OtColumns._ID 
		    + ", "*/+ OtColumns.ID
			+ ", "+ OtColumns.NRO_OT
			+ ", "+ OtColumns.ID_LOC
			+ ", "+ OtColumns.ID_BAR
			+ ", "+ OtColumns.BARRIO
			+ ", "+ OtColumns.ID_CAL
			+ ", "+ OtColumns.CALLE
			+ ", "+ OtColumns.ALTURA
			+ ", "+ OtColumns.ID_MOTIVO
			+ ", "+ OtColumns.MOTIVO
			+ ", "+ OtColumns.COD_EMPLEADO_ASIG
			+ ", "+ OtColumns.NOMBRE_EMPLEADO_ASIG
			+ ", "+ OtColumns.COD_CUADRILLA_ASIG
			+ ", "+ OtColumns.FCHALTA
			+ ", "+ OtColumns.LAT
			+ ", "+ OtColumns.LNG
			+ ", "+ OtColumns.PRIORIDAD
			+ ", "+ OtColumns.ID_SEG
			+ ", "+ OtColumns.NRO_SEC
			+ ", "+ OtColumns.ID_TXC
			+ ", "+ OtColumns.ID_INM
			+ ", "+ OtColumns.NRO_FORM
			+ ", "+ OtColumns.FCHREGISTRACION
			+ ", "+ OtColumns.OBSERVACION
			+ ", "+ OtColumns.GEREN

			+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public OtDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(OtDAO.INSERT);
		
		
	}
	
	public long insert2(Ot obj) {
		//insertStatement.clearBindings();
		
		SQLiteStatement insertStatement2 = null;
		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = Ot.class.getDeclaredFields();
		
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
		INSERT2="insert into "+ OtTable.TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
		insertStatement2 = db.compileStatement(INSERT2);
		insertStatement.clearBindings();
		
		i=0;
		
		for( Field field : fields ){
			try {
				 field.setAccessible(true); 
				 s[i] =String.valueOf( field.get(this));				
				 i++;
				 if(field.getType().getName().contains("int") || field.getType().getName().contains("long") ){
					 insertStatement2.bindLong(1,  Long.valueOf( (String) field.get(obj)));
				 } else{
					 insertStatement2.bindString(1,   (String) field.get(obj));
				 }
				
				} catch (IllegalAccessException e) {
	
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		
		return insertStatement2.executeInsert();
		
	}

	@Override
	public long insert(String[] data) {
		
			insertStatement.clearBindings();
			try {
			//insertStatement.bindLong(1, Long.valueOf(data[0]));
			insertStatement.bindLong(1,  Long.valueOf(data[1]));
			insertStatement.bindLong(2,  Long.valueOf(data[2]));
			insertStatement.bindLong(3,  Long.valueOf(data[3]));
			insertStatement.bindLong(4,  Long.valueOf(data[4]));
			insertStatement.bindString(5, data[5]!=null?data[5]:"");
			insertStatement.bindLong(6,  Long.valueOf(data[6]));
			insertStatement.bindString(7, data[7]!=null?data[7]:"");
			insertStatement.bindString(8, data[8]!=null?data[8]:"");
			insertStatement.bindLong(9,  Long.valueOf(data[9]));
			insertStatement.bindString(10, data[10]!=null?data[10]:"");			
			insertStatement.bindLong(11,  Long.valueOf(data[11]));
			insertStatement.bindString(12, data[12]!=null?data[12]:"");
			insertStatement.bindLong(13,  Long.valueOf(data[13]));
			insertStatement.bindString(14, data[14]!=null?data[14]:"");
			insertStatement.bindString(15, data[15]!=null?data[15]:"");
			insertStatement.bindString(16, data[16]!=null?data[16]:"");
			
			try {
				insertStatement.bindLong(17,  Long.valueOf(data[17]));
				insertStatement.bindLong(18,  Long.valueOf(data[18]));
				insertStatement.bindLong(19,  Long.valueOf(data[19]));
				insertStatement.bindLong(20,  Long.valueOf(data[20]));
				insertStatement.bindLong(21,  Long.valueOf(data[21]));
				insertStatement.bindLong(22,  Long.valueOf(data[22]));
				insertStatement.bindString(23, data[23]!=null?data[23]:"");
				insertStatement.bindString(24, data[24]!=null?data[24]:"");
				insertStatement.bindString(25, data[25]!=null?data[25]:"");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Util.Log("Error=>ot:"+data[1]+":"+e.getMessage());
				//e.printStackTrace();
			}

			} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.Log("Error insert=>"+e.getMessage());
			e.printStackTrace();
		}
		
			return insertStatement.executeInsert();
		
	}

	@Override
	public void remove(long id) {
		db.delete(OtTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Ot getOt(long id) {
		Ot  place = null;
		String[] columns = OtColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(OtTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Ot();
			 place.set_id(c.getLong(0));
             place.setId((int)c.getLong(1));
			 place.setNro_ot((int)c.getLong(2));
			 place.setId_loc((int)c.getLong(3));
			 place.setId_bar((int)c.getLong(4));
			 place.setBarrio(c.getString(5));
			 place.setId_cal((int)c.getLong(6));
			 place.setCalle(c.getString(7));
			 place.setAltura(c.getString(8));
			 place.setId_motivo((int)c.getLong(9));
			 place.setMotivo(c.getString(10));
			 place.setCod_empleado_asig((int)c.getLong(11));
			 place.setNombre_empleado_asig(c.getString(12));
			 place.setCod_cuadrilla_asig((int)c.getLong(13));
			 place.setFchalta(c.getString(14));
			 place.setLat(c.getString(15));
			 place.setLng(c.getString(16));
			 place.setPrioridad((int)c.getLong(17));
			 place.setId_seg((int)c.getLong(18));
			 place.setNro_sec((int)c.getLong(19));
			 place.setId_txc((int)c.getLong(20));
			 place.setId_inm((int)c.getLong(21));
			 place.setNro_form((int)c.getLong(22));
			 place.setFchregistracion(c.getString(23));
			 place.setObservacion(c.getString(24));
			 place.setGeren(c.getString(25));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}

	public long get_cant(String condition, String[] params) {
		String[] columns = OtColumns.getColumns();
		Cursor c = db.query(OtTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		return c.getCount();
	}
	
	public Ot[] get_sin_finalizar() {
		Ot[] Ot = null;
		String[] columns = OtColumns.getColumns();
		String sql="select * from ot o where nro_ot not in (select ot  from  ot_finalizada) order by prioridad asc";
		Cursor c =db.rawQuery(sql, null)	;	
		Util.Log("ot cant()=>"+c.getCount());
		if (c.getCount() == 0) {
			c.close();
			return Ot;
		}
		if (c.moveToFirst()) {
			Ot = new Ot[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				 Ot[i] = new Ot();
				 Ot place = new Ot();
				 place.set_id(c.getLong(0));
	             place.setId((int)c.getLong(1));
				 place.setNro_ot((int)c.getLong(2));
				 place.setId_loc((int)c.getLong(3));
				 place.setId_bar((int)c.getLong(4));
				 place.setBarrio(c.getString(5));
				 place.setId_cal((int)c.getLong(6));
				 place.setCalle(c.getString(7));
				 place.setAltura(c.getString(8));
				 place.setId_motivo((int)c.getLong(9));
				 place.setMotivo(c.getString(10));
				 place.setCod_empleado_asig((int)c.getLong(11));
				 place.setNombre_empleado_asig(c.getString(12));
				 place.setCod_cuadrilla_asig((int)c.getLong(13));
				 place.setFchalta(c.getString(14));
				 place.setLat(c.getString(15));
				 place.setLng(c.getString(16));
				 place.setPrioridad((int)c.getLong(17));
				 place.setId_seg((int)c.getLong(18));
				 place.setNro_sec((int)c.getLong(19));
				 place.setId_txc((int)c.getLong(20));
				 place.setId_inm((int)c.getLong(21));
				 place.setNro_form((int)c.getLong(22));
				 place.setFchregistracion(c.getString(23));
				 place.setObservacion(c.getString(24));
				 place.setGeren(c.getString(25));

				Ot[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Ot;
	}
	public Ot[] get(String condition, String[] params) {
		Ot[] Ot = null;
		String[] columns = OtColumns.getColumns();
	
		Cursor c = db.query(OtTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		
		Util.Log("ot cant()=>"+c.getCount());
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Ot = new Ot[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Ot[i] = new Ot();
				Ot place = new Ot();
				place.set_id(c.getLong(0));
	             place.setId((int)c.getLong(1));
				 place.setNro_ot((int)c.getLong(2));
				 place.setId_loc((int)c.getLong(3));
				 place.setId_bar((int)c.getLong(4));
				 place.setBarrio(c.getString(5));
				 place.setId_cal((int)c.getLong(6));
				 place.setCalle(c.getString(7));
				 place.setAltura(c.getString(8));
				 place.setId_motivo((int)c.getLong(9));
				 place.setMotivo(c.getString(10));
				 place.setCod_empleado_asig((int)c.getLong(11));
				 place.setNombre_empleado_asig(c.getString(12));
				 place.setCod_cuadrilla_asig((int)c.getLong(13));
				 place.setFchalta(c.getString(14));
				 place.setLat(c.getString(15));
				 place.setLng(c.getString(16));
				 place.setPrioridad((int)c.getLong(17));
				 place.setId_seg((int)c.getLong(18));
				 place.setNro_sec((int)c.getLong(19));
				 place.setId_txc((int)c.getLong(20));
				 place.setId_inm((int)c.getLong(21));
				 place.setNro_form((int)c.getLong(22));
				 place.setFchregistracion(c.getString(23));
				 place.setObservacion(c.getString(24));
				 place.setGeren(c.getString(25));

				Ot[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Ot;
	}
	@Override
	public Ot get(long id) {

		Ot[] ot = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (ot == null)
			return null;

		return ot[0];
	}




	@Override
	public Ot[] getAll() {
		return null;
	}



}
