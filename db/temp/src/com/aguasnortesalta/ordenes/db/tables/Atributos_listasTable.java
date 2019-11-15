package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Atributos_listasTable {
public static final String TABLE_NAME = "atributos_listas";

public static class Atributos_listasColumns implements BaseColumns {
public static final String ID_TIPO_ATRIBUTO="id_tipo_atributo";
public static final String ID_LISTA="id_lista";
public static final String DESC_LISTA="desc_lista";

public static  String[] getColumns(){
return new String[] {  _ID,ID_TIPO_ATRIBUTO,ID_LISTA,DESC_LISTA};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(Atributos_listasColumns.ID_TIPO_ATRIBUTO + " INTEGER , ");
sb.append(Atributos_listasColumns.ID_LISTA + " TEXT , ");
sb.append(Atributos_listasColumns.DESC_LISTA + " TEXT  ");


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