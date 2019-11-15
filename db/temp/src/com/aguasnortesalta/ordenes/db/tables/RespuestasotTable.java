package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class RespuestasotTable {
public static final String TABLE_NAME = "respuestasot";

public static class RespuestasotColumns implements BaseColumns {
public static final String ID_RES="id_res";
public static final String DESC_RES="desc_res";
public static final String ICON="icon";
public static final String ID_RUBRO="id_rubro";
public static final String GEREN="geren";
public static final String ID_TXC="id_txc";

public static  String[] getColumns(){
	return new String[] {_ID, ID_RES,DESC_RES,ICON,ID_RUBRO,GEREN,ID_TXC};

	}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(RespuestasotColumns.ID_RES + " INTEGER , ");
		sb.append(RespuestasotColumns.DESC_RES + " TEXT , ");
		sb.append(RespuestasotColumns.ICON + " TEXT , ");
		sb.append(RespuestasotColumns.ID_RUBRO + " INTEGER , ");
		sb.append(RespuestasotColumns.GEREN + " TEXT , ");
		sb.append(RespuestasotColumns.ID_TXC + " INTEGER  ");

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