package com.aguasnortesalta.ordenes.db.tables;


import com.aguasnortesalta.ordenes.utils.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class OtTable {
public static final String TABLE_NAME = "ot";

public static class OtColumns implements BaseColumns {
public static final String ID="id";
public static final String NRO_OT="nro_ot";
public static final String ID_LOC="id_loc";
public static final String ID_BAR="id_bar";
public static final String BARRIO="barrio";
public static final String ID_CAL="id_cal";
public static final String CALLE="calle";
public static final String ALTURA="altura";
public static final String ID_MOTIVO="id_motivo";
public static final String MOTIVO="motivo";
public static final String COD_EMPLEADO_ASIG="cod_empleado_asig";
public static final String NOMBRE_EMPLEADO_ASIG="nombre_empleado_asig";
public static final String COD_CUADRILLA_ASIG="cod_cuadrilla_asig";
public static final String FCHALTA="fchalta";
public static final String LAT="lat";
public static final String LNG="lng";
public static final String PRIORIDAD="prioridad";
public static final String ID_SEG="id_seg";
public static final String NRO_SEC="nro_sec";
public static final String ID_TXC="id_txc";
public static final String ID_INM="id_inm";
public static final String NRO_FORM="nro_form";
public static final String FCHREGISTRACION="fchregistracion";
public static final String OBSERVACION="observacion";
public static final String GEREN="geren";

public static  String[] getColumns(){
return new String[] { _ID,ID,NRO_OT,ID_LOC,ID_BAR,BARRIO,ID_CAL,CALLE,ALTURA,ID_MOTIVO,MOTIVO,COD_EMPLEADO_ASIG,NOMBRE_EMPLEADO_ASIG,COD_CUADRILLA_ASIG,FCHALTA,LAT,LNG,PRIORIDAD,ID_SEG,NRO_SEC,ID_TXC,ID_INM,NRO_FORM,FCHREGISTRACION,OBSERVACION,GEREN};

		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sb.append(OtColumns.ID + " INTEGER , ");
		sb.append(OtColumns.NRO_OT + " INTEGER , ");
		sb.append(OtColumns.ID_LOC + " INTEGER , ");
		sb.append(OtColumns.ID_BAR + " INTEGER , ");
		sb.append(OtColumns.BARRIO + " TEXT , ");
		sb.append(OtColumns.ID_CAL + " INTEGER , ");
		sb.append(OtColumns.CALLE + " TEXT , ");
		sb.append(OtColumns.ALTURA + " TEXT , ");
		sb.append(OtColumns.ID_MOTIVO + " INTEGER , ");
		sb.append(OtColumns.MOTIVO + " TEXT , ");
		sb.append(OtColumns.COD_EMPLEADO_ASIG + " INTEGER , ");
		sb.append(OtColumns.NOMBRE_EMPLEADO_ASIG + " TEXT , ");
		sb.append(OtColumns.COD_CUADRILLA_ASIG + " INTEGER , ");
		sb.append(OtColumns.FCHALTA + " TEXT , ");
		sb.append(OtColumns.LAT + " TEXT , ");
		sb.append(OtColumns.LNG + " TEXT , ");
		sb.append(OtColumns.PRIORIDAD + " INTEGER , ");
		sb.append(OtColumns.ID_SEG + " INTEGER , ");
		sb.append(OtColumns.NRO_SEC + " INTEGER , ");
		sb.append(OtColumns.ID_TXC + " INTEGER , ");
		sb.append(OtColumns.ID_INM + " INTEGER , ");
		sb.append(OtColumns.NRO_FORM + " INTEGER , ");
		sb.append(OtColumns.FCHREGISTRACION + " TEXT , ");
		sb.append(OtColumns.OBSERVACION + " TEXT , ");
		sb.append(OtColumns.GEREN + " TEXT  ");


		sb.append(");");
		Util.Log(sb.toString());
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