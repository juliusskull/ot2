package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Ot_aperturas_superficieTable {
public static final String TABLE_NAME = "ot_aperturas_superficie";

public static class Ot_aperturas_superficieColumns implements BaseColumns {
public static final String ID_SUPERFICIE="id_superficie";
public static final String DESCRIPCION="descripcion";
public static final String TIPO_APERTURA="tipo_apertura";

public static  String[] getColumns(){
	return new String[] {_ID, ID_SUPERFICIE,DESCRIPCION,TIPO_APERTURA};

}


}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(Ot_aperturas_superficieColumns.ID_SUPERFICIE + " INTEGER , ");
		sb.append(Ot_aperturas_superficieColumns.DESCRIPCION + " TEXT , ");
		sb.append(Ot_aperturas_superficieColumns.TIPO_APERTURA + " TEXT  ");
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