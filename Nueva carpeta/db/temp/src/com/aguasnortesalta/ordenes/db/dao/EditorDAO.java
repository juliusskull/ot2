package com.aguasnortesalta.ordenes.db.dao;

import java.util.Locale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.aguasnortesalta.ordenes.db.tables.EditorTable;
import com.aguasnortesalta.ordenes.db.tables.EditorTable.EditorColumns;
import com.aguasnortesalta.ordenes.model.Editor;


public class EditorDAO implements DAO<Editor>{

	private static final String INSERT = "insert into "
			+ EditorTable.TABLE_NAME + "(" + EditorColumns._ID + ", "
		    + ", "+ EditorColumns.ID
			+ ", "+ EditorColumns.TITULO
			+ ", "+ EditorColumns.CONTENT
			+ ", "+ EditorColumns.TIPO_CONTENT
			+ ", "+ EditorColumns.FCH_ALTA
			+ ", "+ EditorColumns.ACTIVO

			+ ") values (?,?,?,?,?,?,?)";

	private SQLiteDatabase db;
	private SQLiteStatement insertStatement;

	public EditorDAO(SQLiteDatabase db) {
		this.db = db;
		insertStatement = db.compileStatement(EditorDAO.INSERT);
	}

	@Override
	public long insert(String[] data) {
		insertStatement.clearBindings();
		insertStatement.bindLong(1, Long.valueOf(data[0]));
		insertStatement.bindLong(2,  Long.valueOf(data[1]));
insertStatement.bindString(3, data[2]);
insertStatement.bindString(4, data[3]);
insertStatement.bindString(5, data[4]);
insertStatement.bindString(6, data[5]);
insertStatement.bindLong(7,  Long.valueOf(data[6]));


		return insertStatement.executeInsert();
	}

	@Override
	public void remove(long id) {
		db.delete(EditorTable.TABLE_NAME, BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });

	}

	public Editor getEditor(long id) {
		Editor  place = null;
		String[] columns = EditorColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db
				.query(EditorTable.TABLE_NAME, columns, BaseColumns._ID
						+ " = ?", new String[] { String.valueOf(id) }, null,
						null, null);
		if (c.moveToFirst()) {
			place = new Editor();
			place.set_id(c.getLong(0));
             place.setId((int)c.getLong(1));
			 place.setTitulo(c.getString(2));
			 place.setContent(c.getString(3));
			 place.setTipo_content(c.getString(4));
			 place.setFch_alta(c.getString(5));
			 place.setActivo((int)c.getLong(6));


		}
		if (!c.isClosed()) {
			c.close();
		}
		return place;
	}


	private Editor[] get(String condition, String[] params) {
		Editor[] Editor = null;
		String[] columns = EditorColumns.getColumns();
		String language = Locale.getDefault().getLanguage();

		Cursor c = db.query(EditorTable.TABLE_NAME, columns, condition,
				params, null, null, null);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		if (c.moveToFirst()) {
			Editor = new Editor[c.getCount()];
			for (int i = 0; i < c.getCount(); i++) {
				Editor[i] = new Editor();
				Editor place = new Editor();
				 place.set_id(c.getLong(0));
				 place.setId((int)c.getLong(1));
				 place.setTitulo(c.getString(2));
				 place.setContent(c.getString(3));
				 place.setTipo_content(c.getString(4));
				 place.setFch_alta(c.getString(5));
				 place.setActivo((int)c.getLong(6));


				Editor[i] = place;

				c.moveToNext();
			}
		}
		if (!c.isClosed()) {
			c.close();
		}
		return Editor;
	}
	@Override
	public Editor get(long id) {

		Editor[] editor = get(BaseColumns._ID + " = ?",
				new String[] { String.valueOf(id) });
		if (editor == null)
			return null;

		return editor[0];
	}




	@Override
	public Editor[] getAll() {
		return null;
	}



}
