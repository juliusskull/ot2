package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class EditorTable {
public static final String TABLE_NAME = "editor";

public static class EditorColumns implements BaseColumns {
public static final String ID="id";
public static final String TITULO="titulo";
public static final String CONTENT="content";
public static final String TIPO_CONTENT="tipo_content";
public static final String FCH_ALTA="fch_alta";
public static final String ACTIVO="activo";

public static  String[] getColumns(){
return new String[] { ID,TITULO,CONTENT,TIPO_CONTENT,FCH_ALTA,ACTIVO};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(EditorColumns.ID + " INTEGER , ");
sb.append(EditorColumns.TITULO + " TEXT , ");
sb.append(EditorColumns.CONTENT + " TEXT , ");
sb.append(EditorColumns.TIPO_CONTENT + " TEXT , ");
sb.append(EditorColumns.FCH_ALTA + " TEXT , ");
sb.append(EditorColumns.ACTIVO + " INTEGER ");


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