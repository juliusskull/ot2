package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class MotivosotTable {
public static final String TABLE_NAME = "motivosot";

public static class MotivosotColumns implements BaseColumns {
public static final String ID_MOTIVO="id_motivo";
public static final String DESC_MOTIVO="desc_motivo";
public static final String ID_RUBRO="id_rubro";
public static final String REG_APERTURA="reg_apertura";
public static final String ID_TXC="id_txc";
public static final String GEREN="geren";

public static  String[] getColumns(){
		return new String[] { _ID,ID_MOTIVO,DESC_MOTIVO,ID_RUBRO,REG_APERTURA,ID_TXC,GEREN};

		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(MotivosotColumns.ID_MOTIVO + " INTEGER , ");
		sb.append(MotivosotColumns.DESC_MOTIVO + " TEXT , ");
		sb.append(MotivosotColumns.ID_RUBRO + " INTEGER , ");
		sb.append(MotivosotColumns.REG_APERTURA + " TEXT , ");
		sb.append(MotivosotColumns.ID_TXC + " INTEGER , ");
		sb.append(MotivosotColumns.GEREN + " TEXT  ");

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