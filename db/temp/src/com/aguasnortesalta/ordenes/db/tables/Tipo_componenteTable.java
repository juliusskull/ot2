package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Tipo_componenteTable {
public static final String TABLE_NAME = "tipo_componente";

public static class Tipo_componenteColumns implements BaseColumns {
public static final String ID_TIPO_COMPONENTE="id_tipo_componente";
public static final String DESC_TIPO_COMPONENTE="desc_tipo_componente";
public static final String ID_TIPO_SERVICIO="id_tipo_servicio";

public static  String[] getColumns(){
return new String[] {  _ID,ID_TIPO_COMPONENTE,DESC_TIPO_COMPONENTE,ID_TIPO_SERVICIO};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(Tipo_componenteColumns.ID_TIPO_COMPONENTE + " INTEGER , ");
sb.append(Tipo_componenteColumns.DESC_TIPO_COMPONENTE + " TEXT , ");
sb.append(Tipo_componenteColumns.ID_TIPO_SERVICIO + " INTEGER  ");


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