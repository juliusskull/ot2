package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ConfirmacionTable {
public static final String TABLE_NAME = "confirmacion";

public static class ConfirmacionColumns implements BaseColumns {
public static final String NRO_OT="nro_ot";
public static final String VALOR="valor";
public static final String ENVIADO="enviado";

public static  String[] getColumns(){
return new String[] {  _ID,NRO_OT,VALOR,ENVIADO};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(ConfirmacionColumns.NRO_OT + " TEXT , ");
		sb.append(ConfirmacionColumns.VALOR + " TEXT , ");
		sb.append(ConfirmacionColumns.ENVIADO + " INTEGER  ");
		
		
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