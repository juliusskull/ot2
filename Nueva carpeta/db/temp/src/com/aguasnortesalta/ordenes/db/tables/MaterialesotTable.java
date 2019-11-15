package com.aguasnortesalta.ordenes.db.tables;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class MaterialesotTable {
public static final String TABLE_NAME = "materialesot";

public static class MaterialesotColumns implements BaseColumns {
public static final String ID="id";
public static final String PRODUC="produc";
public static final String DESC_LARGA="desc_larga";
public static final String DESC_CORTA="desc_corta";
public static final String AGRU_PRODUC="agru_produc";
public static final String AGRU_DESCRIP="agru_descrip";
public static final String GEREN="geren";
public static final String FCHALTA="fchalta";

public static  String[] getColumns(){
return new String[] { _ID,ID,PRODUC,DESC_LARGA,DESC_CORTA,AGRU_PRODUC,AGRU_DESCRIP,GEREN,FCHALTA};


		}

	}


	public static void onCreate(SQLiteDatabase db, Context context) {
    StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(MaterialesotColumns.ID + " INTEGER , ");
		sb.append(MaterialesotColumns.PRODUC + " TEXT , ");
		sb.append(MaterialesotColumns.DESC_LARGA + " TEXT , ");
		sb.append(MaterialesotColumns.DESC_CORTA + " TEXT , ");
		sb.append(MaterialesotColumns.AGRU_PRODUC + " TEXT , ");
		sb.append(MaterialesotColumns.AGRU_DESCRIP + " TEXT , ");
		sb.append(MaterialesotColumns.GEREN + " TEXT , ");
		sb.append(MaterialesotColumns.FCHALTA + " TEXT  ");


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