package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Ot_finalizadaTable {
public static final String TABLE_NAME = "ot_finalizada";

public static class Ot_finalizadaColumns implements BaseColumns {
public static final String ID="id";
public static final String OT="ot";
public static final String FECHAINICIO="fechainicio";
public static final String FECHAFINALIZO="fechafinalizo";
public static final String IDMOTIVOFINALIZA="idmotivofinaliza";
public static final String LAT="lat";
public static final String LNG="lng";
public static final String ALTURA="altura";
public static final String ESTADO="estado";
public static final String T="t";
public static final String FCH="fch";
public static final String FCHALTA="fchalta";
public static final String LEGAJO="legajo";
public static final String CODIGO_CUADRILLA="codigo_cuadrilla";
public static final String OBSERVACION="observacion";
public static final String ID_SEG="id_seg";
public static final String NRO_SEC="nro_sec";
public static final String ID_TXC="id_txc";
public static final String ID_INM="id_inm";
public static final String NRO_FORM="nro_form";

public static  String[] getColumns(){
return new String[] { _ID,ID,OT,FECHAINICIO,FECHAFINALIZO,IDMOTIVOFINALIZA,LAT,LNG,ALTURA,ESTADO,T,FCH,FCHALTA,LEGAJO,CODIGO_CUADRILLA,OBSERVACION,ID_SEG,NRO_SEC,ID_TXC,ID_INM,NRO_FORM};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(Ot_finalizadaColumns.ID + " INTEGER , ");
sb.append(Ot_finalizadaColumns.OT + " INTEGER , ");
sb.append(Ot_finalizadaColumns.FECHAINICIO + " TEXT , ");
sb.append(Ot_finalizadaColumns.FECHAFINALIZO + " TEXT , ");
sb.append(Ot_finalizadaColumns.IDMOTIVOFINALIZA + " INTEGER , ");
sb.append(Ot_finalizadaColumns.LAT + " TEXT , ");
sb.append(Ot_finalizadaColumns.LNG + " TEXT , ");
sb.append(Ot_finalizadaColumns.ALTURA + " TEXT , ");
sb.append(Ot_finalizadaColumns.ESTADO + " TEXT , ");
sb.append(Ot_finalizadaColumns.T + " TEXT , ");
sb.append(Ot_finalizadaColumns.FCH + " TEXT , ");
sb.append(Ot_finalizadaColumns.FCHALTA + " TEXT , ");
sb.append(Ot_finalizadaColumns.LEGAJO + " INTEGER , ");
sb.append(Ot_finalizadaColumns.CODIGO_CUADRILLA + " INTEGER , ");
sb.append(Ot_finalizadaColumns.OBSERVACION + " TEXT , ");
sb.append(Ot_finalizadaColumns.ID_SEG + " INTEGER , ");
sb.append(Ot_finalizadaColumns.NRO_SEC + " INTEGER , ");
sb.append(Ot_finalizadaColumns.ID_TXC + " INTEGER , ");
sb.append(Ot_finalizadaColumns.ID_INM + " INTEGER , ");
sb.append(Ot_finalizadaColumns.NRO_FORM + " INTEGER  ");


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