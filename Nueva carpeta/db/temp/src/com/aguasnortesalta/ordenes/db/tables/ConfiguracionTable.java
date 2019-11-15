package com.aguasnortesalta.ordenes.db.tables;

import com.aguasnortesalta.ordenes.db.tables.ArchivoTable.ArchivoColumns;
import com.aguasnortesalta.ordenes.utils.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ConfiguracionTable {
	public static final String TABLE_NAME = "configuracion";
	public static class ConfiguracionColumns implements BaseColumns {
		public static final String VERSION_DB="version_db";	
		public static final String VERSION_FECH="version_fch_actualizacion";	
		
		public static  String[] getColumns(){
		return new String[] {  _ID,VERSION_DB,VERSION_FECH};


				}
}
	public static void onCreate(SQLiteDatabase db, Context context) {
	    StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE " + TABLE_NAME + " (");
			sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
			sb.append(ConfiguracionColumns.VERSION_DB + " INTEGER,  ");
			sb.append(ConfiguracionColumns.VERSION_FECH + " TEXT  ");
			sb.append(");");
			db.execSQL(sb.toString());
			db.execSQL("insert into "+TABLE_NAME + "("+BaseColumns._ID+","+ConfiguracionColumns.VERSION_DB+", "+ConfiguracionColumns.VERSION_FECH+")values(1,1,'"+Util.getDateNowFormat()+  "')");
		}

		public static void onUpgrade(SQLiteDatabase db, int oldVersion,
				int newVersion, Context context) {
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    onCreate(db, context);
	}
		public static void onUpgrade2(SQLiteDatabase db, Context context) {
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    onCreate(db, context);
	}
}
