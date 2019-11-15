package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class UsuarioTable {
public static final String TABLE_NAME = "usuario";

public static class UsuarioColumns implements BaseColumns {
public static final String ID_USUARIO="id_usuario";
public static final String USUARIO="usuario";
public static final String EMAIL="email";
public static final String TIPO="tipo";

public static  String[] getColumns(){
return new String[] { ID_USUARIO,USUARIO,EMAIL,TIPO};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(UsuarioColumns.ID_USUARIO + " INTEGER , ");
		sb.append(UsuarioColumns.USUARIO + " TEXT , ");
		sb.append(UsuarioColumns.EMAIL + " TEXT , ");
		sb.append(UsuarioColumns.TIPO + " INTEGER ");


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