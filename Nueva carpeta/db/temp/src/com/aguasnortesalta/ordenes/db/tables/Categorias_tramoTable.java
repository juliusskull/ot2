package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Categorias_tramoTable {
public static final String TABLE_NAME = "categorias_tramo";

public static class Categorias_tramoColumns implements BaseColumns {
public static final String ID_CATEGORIA="id_categoria";
public static final String DESC_CATEGORIA="desc_categoria";
public static final String ID_TIPO_SERVICIO="id_tipo_servicio";

public static  String[] getColumns(){
return new String[] {  _ID,ID_CATEGORIA,DESC_CATEGORIA,ID_TIPO_SERVICIO};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(Categorias_tramoColumns.ID_CATEGORIA + " INTEGER , ");
		sb.append(Categorias_tramoColumns.DESC_CATEGORIA + " TEXT , ");
		sb.append(Categorias_tramoColumns.ID_TIPO_SERVICIO + " INTEGER  ");


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