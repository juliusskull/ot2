package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class EquipoTable {
public static final String TABLE_NAME = "equipo";

public static class EquipoColumns implements BaseColumns {
public static final String ID="id";
public static final String DESCRIPCION="descripcion";
public static final String TIPOID="tipoid";
public static final String FCHALTA="fchalta";
public static final String GEREN="geren";

public static  String[] getColumns(){
return new String[] { _ID,ID,DESCRIPCION,TIPOID,FCHALTA,GEREN};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(EquipoColumns.ID + " INTEGER , ");
sb.append(EquipoColumns.DESCRIPCION + " TEXT , ");
sb.append(EquipoColumns.TIPOID + " INTEGER , ");
sb.append(EquipoColumns.FCHALTA + " TEXT , ");
sb.append(EquipoColumns.GEREN + " TEXT  ");


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