package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class OperarioTable {
public static final String TABLE_NAME = "operario";

public static class OperarioColumns implements BaseColumns {
public static final String ID="id";
public static final String NOMBRE="nombre";
public static final String FCHALTA="fchalta";
public static final String GEREN="geren";
public static final String CUADRILLA="cuadrilla";
public static final String CAPATAZ="capataz";
public static final String PASSWORD="password";

public static  String[] getColumns(){
return new String[] { _ID,ID,NOMBRE,FCHALTA,GEREN,CUADRILLA,CAPATAZ,PASSWORD};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(OperarioColumns.ID + " INTEGER , ");
sb.append(OperarioColumns.NOMBRE + " TEXT , ");
sb.append(OperarioColumns.FCHALTA + " TEXT , ");
sb.append(OperarioColumns.GEREN + " TEXT , ");
sb.append(OperarioColumns.CUADRILLA + " INTEGER , ");
sb.append(OperarioColumns.CAPATAZ + " INTEGER , ");
sb.append(OperarioColumns.PASSWORD + " INTEGER  ");


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