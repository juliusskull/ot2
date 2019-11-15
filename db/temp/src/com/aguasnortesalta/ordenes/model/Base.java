package com.aguasnortesalta.ordenes.model;

import java.lang.reflect.Field;

public class Base {
 long _id;

public long get_id() {
	return _id;
}

public void set_id(long _id) {
	this._id = _id;
}
public String[] get_base_all_fiel(){
	Field[] fields = Base.class.getDeclaredFields();
	
	return null;
}

}
