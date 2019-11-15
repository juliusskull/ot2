package com.aguasnortesalta.ordenes.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.aguasnortesalta.ordenes.db.dao.ArchivoDAO;
import com.aguasnortesalta.ordenes.db.dao.DAO;
import com.aguasnortesalta.ordenes.db.dao.UsuarioDAO;
import com.aguasnortesalta.ordenes.db.tables.ArchivoTable;
import com.aguasnortesalta.ordenes.db.tables.Categorias_tramoTable;
import com.aguasnortesalta.ordenes.db.tables.DerivacionesotTable;
import com.aguasnortesalta.ordenes.db.tables.EquipoTable;
import com.aguasnortesalta.ordenes.db.tables.GeometriaTable;
import com.aguasnortesalta.ordenes.db.tables.MaterialesotTable;
import com.aguasnortesalta.ordenes.db.tables.MotivosotTable;
import com.aguasnortesalta.ordenes.db.tables.OperarioTable;
import com.aguasnortesalta.ordenes.db.tables.OtTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_aperturasTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_aperturas_superficieTable;
import com.aguasnortesalta.ordenes.db.tables.Ot_finalizadaTable;
import com.aguasnortesalta.ordenes.db.tables.RespuestasotTable;
import com.aguasnortesalta.ordenes.db.tables.ServiciosxmotivoTable;
import com.aguasnortesalta.ordenes.db.tables.UsuarioTable;
import com.aguasnortesalta.ordenes.model.Categorias_tramo;
import com.aguasnortesalta.ordenes.model.Derivacionesot;
import com.aguasnortesalta.ordenes.model.Geometria;
import com.aguasnortesalta.ordenes.model.Materialesot;
import com.aguasnortesalta.ordenes.model.Motivosot;
import com.aguasnortesalta.ordenes.model.Operario;
import com.aguasnortesalta.ordenes.model.Ot_aperturas_superficie;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.model.Respuestasot;
import com.aguasnortesalta.ordenes.model.Serviciosxmotivo;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class OpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 59;
	private Context context;

	public OpenHelper(final Context context) {
		super(context, "ordenes.db", null, DATABASE_VERSION);
		this.context = context;
	}
	public void onOpen(final SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			db.execSQL("PRAGMA foreign_keys=ON;");
			Cursor c = db.rawQuery("PRAGMA foreign_keys", null);
			if (c.moveToFirst()) {
				c.getInt(0);
			} else {
			}
			if (!c.isClosed()) {
				c.close();
			}
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v("(SQL)", "ini script");
		UsuarioTable.onCreate(db, context);
		GeometriaTable.onCreate(db, context);
		EquipoTable.onCreate(db, context);
		UsuarioTable.onCreate(db, context);
		ServiciosxmotivoTable.onCreate(db, context);
		RespuestasotTable.onCreate(db, context);
		OtTable.onCreate(db, context);
		Ot_finalizadaTable.onCreate(db, context);
		MotivosotTable.onCreate(db, context);
		Ot_aperturasTable.onCreate(db, context);
		Ot_aperturas_superficieTable.onCreate(db, context);
		OperarioTable.onCreate(db, context);
		MaterialesotTable.onCreate(db, context);
		DerivacionesotTable.onCreate(db, context);
		Categorias_tramoTable.onCreate(db, context);
		ArchivoTable.onCreate(db, context);

	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion) {
		if(newVersion>oldVersion){
			Log.v("(SQL)", "re-ini script");
			UsuarioTable.onUpgrade(db, oldVersion, newVersion, context);
			GeometriaTable.onUpgrade(db, oldVersion, newVersion, context);
			EquipoTable.onUpgrade(db,  oldVersion,newVersion, context);
			UsuarioTable.onUpgrade(db,  oldVersion,newVersion, context);
			ServiciosxmotivoTable.onUpgrade(db,  oldVersion,newVersion, context);
			RespuestasotTable.onUpgrade(db,  oldVersion,newVersion, context);
			OtTable.onUpgrade(db,  oldVersion,newVersion, context);
			Ot_finalizadaTable.onUpgrade(db,  oldVersion,newVersion, context);
			MotivosotTable.onUpgrade(db,  oldVersion,newVersion, context);
			Ot_aperturasTable.onUpgrade(db,  oldVersion,newVersion, context);
			Ot_aperturas_superficieTable.onUpgrade(db,  oldVersion,newVersion, context);
			OperarioTable.onUpgrade(db,  oldVersion,newVersion, context);
			MaterialesotTable.onUpgrade(db,  oldVersion,newVersion, context);
			DerivacionesotTable.onUpgrade(db,  oldVersion,newVersion, context);
			Categorias_tramoTable.onUpgrade(db,  oldVersion,newVersion, context);
			ArchivoTable.onUpgrade(db,  oldVersion,newVersion, context);
		}
	
	}




}