package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Ot_aperturasTable {
public static final String TABLE_NAME = "ot_aperturas";

public static class Ot_aperturasColumns implements BaseColumns {
public static final String OT="ot";
public static final String TIPO_APERTURA="tipo_apertura";
public static final String ID_TIPO_MATERIAL="id_tipo_material";
public static final String ANCHO="ancho";
public static final String LARGO="largo";
public static final String PROFUNDIDAD="profundidad";
public static final String ID_TIPO_SENIAL="id_tipo_senial";
public static final String ESTADO_APERTURA="estado_apertura";
public static final String FCHALTA="fchalta";
public static final String FCHCAD="fchcad";

public static  String[] getColumns(){
return new String[] {_ID, OT,TIPO_APERTURA,ID_TIPO_MATERIAL,ANCHO,LARGO,PROFUNDIDAD,ID_TIPO_SENIAL,ESTADO_APERTURA,FCHALTA,FCHCAD};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(Ot_aperturasColumns.OT + " INTEGER , ");
		sb.append(Ot_aperturasColumns.TIPO_APERTURA + " TEXT , ");
		sb.append(Ot_aperturasColumns.ID_TIPO_MATERIAL + " INTEGER , ");
		sb.append(Ot_aperturasColumns.ANCHO + " TEXT , ");
		sb.append(Ot_aperturasColumns.LARGO + " TEXT , ");
		sb.append(Ot_aperturasColumns.PROFUNDIDAD + " TEXT , ");
		sb.append(Ot_aperturasColumns.ID_TIPO_SENIAL + " INTEGER , ");
		sb.append(Ot_aperturasColumns.ESTADO_APERTURA + " TEXT , ");
		sb.append(Ot_aperturasColumns.FCHALTA + " TEXT , ");
		sb.append(Ot_aperturasColumns.FCHCAD + " TEXT  ");


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