package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.ServiciosxmotivoTable;
import com.aguasnortesalta.ordenes.db.tables.ServiciosxmotivoTable.ServiciosxmotivoColumns;
import com.aguasnortesalta.ordenes.model.Serviciosxmotivo;


public class ServiciosxmotivoDAO implements DAO<Serviciosxmotivo>{

	private static final String INSERT = "insert into "
			+ ServiciosxmotivoTable.TABLE_NAME + "(" + ServiciosxmotivoColumns._ID 
		    + ", "+ ServiciosxmotivoColumns.ID_MOTIVO
			+ ", "+ ServiciosxmotivoColumns.DESC_MOTIVO
			+ ", "+ ServiciosxmotivoColumns.ID_SERVICIO
			+ ", "+ ServiciosxmotivoColumns.DESC_SERVICIO
			+ ", "+ ServiciosxmotivoColumns.ID_RUBRO

			+ ") values (?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public ServiciosxmotivoDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(ServiciosxmotivoDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
		insertStatement.bindString(3, data[2]);
		insertStatement.bindLong(4,  Long.valueOf(data[3]));
		insertStatement.bindString(5, data[4]);
		insertStatement.bindLong(6,  Long.valueOf(data[5]));


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(ServiciosxmotivoTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Serviciosxmotivo getServiciosxmotivo(long id) {
		Serviciosxmotivo  place = null;
		String[] columns = ServiciosxmotivoColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(ServiciosxmotivoTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Serviciosxmotivo();
			place.set_id(c.getLong(0));
             place.setId_motivo(c.getLong(1));
			 place.setDesc_motivo(c.getString(2));
			 place.setId_servicio(c.getLong(3));
			 place.setDesc_servicio(c.getString(4));
			 place.setId_rubro(c.getLong(5));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	public Serviciosxmotivo[] get(String condition, String[] params) {
		Serviciosxmotivo[] Serviciosxmotivo = null;
		String[] columns = ServiciosxmotivoColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(ServiciosxmotivoTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Serviciosxmotivo = new Serviciosxmotivo[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Serviciosxmotivo[i] = new Serviciosxmotivo();
				Serviciosxmotivo place = new Serviciosxmotivo();
				place.set_id(c.getLong(0));
				  place.setId_motivo(c.getLong(1));
 place.setDesc_motivo(c.getString(2));
 place.setId_servicio(c.getLong(3));
 place.setDesc_servicio(c.getString(4));
 place.setId_rubro(c.getLong(5));


				Serviciosxmotivo[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Serviciosxmotivo;
	}
	@Override
	public Serviciosxmotivo get(long id) {

		Serviciosxmotivo[] serviciosxmotivo = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (serviciosxmotivo == null)
			return null;

		return serviciosxmotivo[0];
	}




	@Override
	public Serviciosxmotivo[] getAll() {
		return null;
	}



}
