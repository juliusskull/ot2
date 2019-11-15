package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ComponentesxatributosTable {
public static final String TABLE_NAME = "componentesxatributos";

public static final String VIEW_NAME = "v_componentesxatributos";

public static class ComponentesxatributosColumns implements BaseColumns {
public static final String ID_COMPONENTE="id_componente";
public static final String FEATID="featid";
public static final String ID_TIPO_COMPONENTE="id_tipo_componente";
public static final String ID_TIPO_ATRIBUTO="id_tipo_atributo";
public static final String VALOR="valor";


public static final String DESC_ATRIBUTO="desc_atributo";
public static final String TIPO_DATO="tipo_dato";
/**/
public static final String UNIDAD_MEDIDA="unidad_medida";

public static  String[] getColumns(){
	return new String[] {  _ID,ID_COMPONENTE,FEATID,ID_TIPO_COMPONENTE,ID_TIPO_ATRIBUTO,VALOR};

}

public static  String[] getColumns_view(){
	return new String[] {  _ID,ID_COMPONENTE,FEATID,ID_TIPO_COMPONENTE,ID_TIPO_ATRIBUTO,VALOR,DESC_ATRIBUTO,TIPO_DATO,UNIDAD_MEDIDA};

}

}


	

	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(ComponentesxatributosColumns.ID_COMPONENTE + " INTEGER , ");
		sb.append(ComponentesxatributosColumns.FEATID + " TEXT , ");
		sb.append(ComponentesxatributosColumns.ID_TIPO_COMPONENTE + " INTEGER , ");
		sb.append(ComponentesxatributosColumns.ID_TIPO_ATRIBUTO + " INTEGER , ");
		sb.append(ComponentesxatributosColumns.VALOR + " TEXT  ");


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