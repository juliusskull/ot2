package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DerivacionesotTable {
public static final String TABLE_NAME = "derivacionesot";

public static class DerivacionesotColumns implements BaseColumns {
public static final String ID_RES="id_res";
public static final String GEREN="geren";
public static final String ID_MOT_PROX="id_mot_prox";
public static final String DESC_MOTIVO="desc_motivo";

public static  String[] getColumns(){
	return new String[] { _ID,ID_RES,GEREN,ID_MOT_PROX,DESC_MOTIVO};

		}

}
	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(DerivacionesotColumns.ID_RES + " INTEGER , ");
		sb.append(DerivacionesotColumns.GEREN + " TEXT , ");
		sb.append(DerivacionesotColumns.ID_MOT_PROX + " INTEGER , ");
		sb.append(DerivacionesotColumns.DESC_MOTIVO + " TEXT  ");
		
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