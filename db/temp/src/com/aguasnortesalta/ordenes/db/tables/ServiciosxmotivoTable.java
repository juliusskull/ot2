package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ServiciosxmotivoTable {
public static final String TABLE_NAME = "serviciosxmotivo";

public static class ServiciosxmotivoColumns implements BaseColumns {
public static final String ID_MOTIVO="id_motivo";
public static final String DESC_MOTIVO="desc_motivo";
public static final String ID_SERVICIO="id_servicio";
public static final String DESC_SERVICIO="desc_servicio";
public static final String ID_RUBRO="id_rubro";

public static  String[] getColumns(){
return new String[] {_ID, ID_MOTIVO,DESC_MOTIVO,ID_SERVICIO,DESC_SERVICIO,ID_RUBRO};
		}
	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(ServiciosxmotivoColumns.ID_MOTIVO + " INTEGER , ");
		sb.append(ServiciosxmotivoColumns.DESC_MOTIVO + " TEXT , ");
		sb.append(ServiciosxmotivoColumns.ID_SERVICIO + " INTEGER , ");
		sb.append(ServiciosxmotivoColumns.DESC_SERVICIO + " TEXT , ");
		sb.append(ServiciosxmotivoColumns.ID_RUBRO + " INTEGER  ");


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