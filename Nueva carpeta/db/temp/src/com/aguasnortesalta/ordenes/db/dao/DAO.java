package com.aguasnortesalta.ordenes.db.dao;

import java.lang.reflect.Field;

import android.database.sqlite.SQLiteStatement;

import com.aguasnortesalta.ordenes.db.tables.Ot_finalizadaTable;
import com.aguasnortesalta.ordenes.model.Ot_finalizada;
import com.aguasnortesalta.ordenes.utils.Util;

public interface DAO<T> {
	long insert(String[] data);
	void remove(long id);
	T get(long id);
	T[] getAll();
	

}
