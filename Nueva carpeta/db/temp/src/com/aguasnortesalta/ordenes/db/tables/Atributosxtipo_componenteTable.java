package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Atributosxtipo_componenteTable {
public static final String TABLE_NAME = "atributosxtipo_componente";

public static class Atributosxtipo_componenteColumns implements BaseColumns {
public static final String ID_TIPO_COMPONENTE="id_tipo_componente";
public static final String ID_TIPO_ATRIBUTO="id_tipo_atributo";
public static final String DESC_ATRIBUTO="desc_atributo";
public static final String TIPO_DATO="tipo_dato";
public static final String ID_UNIDAD_MEDIDA="ID_UNIDAD_MEDIDA";

public static  String[] getColumns(){
return new String[] {  _ID,ID_TIPO_COMPONENTE,ID_TIPO_ATRIBUTO,DESC_ATRIBUTO,TIPO_DATO,ID_UNIDAD_MEDIDA};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(Atributosxtipo_componenteColumns.ID_TIPO_COMPONENTE + " INTEGER , ");
sb.append(Atributosxtipo_componenteColumns.ID_TIPO_ATRIBUTO + " INTEGER , ");
sb.append(Atributosxtipo_componenteColumns.DESC_ATRIBUTO + " TEXT , ");
sb.append(Atributosxtipo_componenteColumns.TIPO_DATO + " TEXT , ");
sb.append(Atributosxtipo_componenteColumns.ID_UNIDAD_MEDIDA + " TEXT  ");


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