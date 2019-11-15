package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.UsuarioTable;
import com.aguasnortesalta.ordenes.db.tables.UsuarioTable.UsuarioColumns;
import com.aguasnortesalta.ordenes.model.Usuario;


public class UsuarioDAO implements DAO<Usuario>{

	private static final String INSERT = "insert into "
			+ UsuarioTable.TABLE_NAME + "(" + UsuarioColumns._ID + ", "
		    + ", "+ UsuarioColumns.ID_USUARIO
+ ", "+ UsuarioColumns.USUARIO
+ ", "+ UsuarioColumns.EMAIL
+ ", "+ UsuarioColumns.TIPO

			+ ") values (?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public UsuarioDAO(SQLiteDatabase db) {
		this.db = db;
		
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
		insertStatement.bindString(3, data[2]);
		insertStatement.bindString(4, data[3]);
		insertStatement.bindLong(5,  Long.valueOf(data[4]));


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(UsuarioTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Usuario getUsuario(long id) {
		Usuario  place = null;
		String[] columns = UsuarioColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(UsuarioTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Usuario();
			place.set_id(c.getLong(0));
             place.setId_usuario((int)c.getLong(1));
			 place.setUsuario(c.getString(2));
			 place.setEmail(c.getString(3));
			 place.setTipo((int)c.getLong(4));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	private Usuario[] get(String condition, String[] params) {
		Usuario[] Usuario = null;
		String[] columns = UsuarioColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(UsuarioTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Usuario = new Usuario[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Usuario[i] = new Usuario();
				Usuario place = new Usuario();
				place.set_id(c.getLong(0));
				 place.setId_usuario((int)c.getLong(1));
				 place.setUsuario(c.getString(2));
				 place.setEmail(c.getString(3));
				 place.setTipo((int)c.getLong(4));


				Usuario[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Usuario;
	}
	@Override
	public Usuario get(long id) {

		Usuario[] usuario = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (usuario == null)
			return null;

		return usuario[0];
	}




	@Override
	public Usuario[] getAll() {
		return null;
	}



}
