package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ArchivoTable {
public static final String TABLE_NAME = "archivo";

public static class ArchivoColumns implements BaseColumns {
public static final String NOMBRE="nombre";
public static final String TIPO="tipo";
public static final String OT="ot";
public static final String ENVIADO="enviado";
public static  String[] getColumns(){
			return new String[] {  _ID,NOMBRE,TIPO,OT,ENVIADO};
		}
}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(ArchivoColumns.NOMBRE + " TEXT , ");
		sb.append(ArchivoColumns.TIPO + " TEXT , ");
		sb.append(ArchivoColumns.OT + " TEXT,  ");
		sb.append(ArchivoColumns.ENVIADO + " INTEGER  ");

		sb.append(");");
		db.execSQL(sb.toString());
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