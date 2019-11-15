package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class SincronizarTable {
public static final String TABLE_NAME = "sincronizar";

public static class SincronizarColumns implements BaseColumns {
public static final String ID="id";
public static final String TIPO="tipo";
public static final String VALOR="valor";
public static final String LAT="lat";
public static final String LNG="lng";
public static final String ENVIADO="enviado";
public static final String PRESICION="precision";
public static final String GPS="gps";
public static final String RED="red";

public static  String[] getColumns(){
	return new String[] {  _ID,ID,TIPO,VALOR,LAT,LNG,ENVIADO,PRESICION,GPS,RED};

	}

}


	public static void onCreate(SQLiteDatabase db, Context context) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sb.append(SincronizarColumns.ID + " TEXT , ");
		sb.append(SincronizarColumns.TIPO + " TEXT , ");
		sb.append(SincronizarColumns.VALOR + " TEXT , ");
		sb.append(SincronizarColumns.LAT + " TEXT , ");
		sb.append(SincronizarColumns.LNG + " TEXT , ");
		sb.append(SincronizarColumns.ENVIADO + " INTEGER, ");
		sb.append(SincronizarColumns.PRESICION + " TEXT, ");
		sb.append(SincronizarColumns.GPS + " INTEGER, ");
		sb.append(SincronizarColumns.RED + " INTEGER ");

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