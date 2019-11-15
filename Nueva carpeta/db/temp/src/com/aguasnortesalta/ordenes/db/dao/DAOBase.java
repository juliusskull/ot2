package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.aguasnortesalta.ordenes.db.DatabaseManager;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.utils.Util;
//import com.google.android.gms.internal.db;

public class DAOBase {
	protected long do_insert(Object obj,Class cls , SQLiteDatabase db, String TABLE_NAME) throws ClassNotFoundException {
		//insertStatement.clearBindings();		
		//Class cls = Class.forName(object_name);
		
		String INSERT2="";
		String INSERT2_P="";
		Field[] fields = cls.getDeclaredFields();		
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
		INSERT2="insert into "+ TABLE_NAME +"("+INSERT2+")VALUES("+INSERT2_P+")";
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
}
