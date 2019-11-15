package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class GeometriaTable {
public static final String TABLE_NAME = "geometria";

public static class GeometriaColumns implements BaseColumns {
public static final String FEATID="featid";
public static final String TYPE="type";
public static final String PROPERTIES="properties";
public static final String GEOMETRY="geometry";
public static final String ACTUALIZAR="actualizar";
public static final String LAT="lat";
public static final String LNG="lng";
public static  String[] getColumns(){
return new String[] { _ID,FEATID,TYPE,PROPERTIES,GEOMETRY,ACTUALIZAR,LAT,LNG};

		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sb.append(GeometriaColumns.FEATID + " INTEGER , ");
		sb.append(GeometriaColumns.TYPE + " TEXT , ");
		sb.append(GeometriaColumns.PROPERTIES + " TEXT , ");
		sb.append(GeometriaColumns.GEOMETRY + " TEXT , ");
		sb.append(GeometriaColumns.ACTUALIZAR + " INTEGER, ");
		sb.append(GeometriaColumns.LAT + " TEXT ,");
		sb.append(GeometriaColumns.LNG + " TEXT ");

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