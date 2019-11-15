package com.aguasnortesalta.ordenes.db.tables;

import android.provider.BaseColumns;

public class TemplateTable {
	public static final String TABLE_NAME = "template";
	public static class TemplateColumns implements BaseColumns {
		public static final String ID_TEMPLATE="id_template";
		public static final String DESC_TEMPLATE="desc_template";
		public static final String OBSERVACION="observacion";
		public static final String TITULO="titulo";
		public static  String[] getColumns(){
					return new String[] {  _ID,ID_TEMPLATE,DESC_TEMPLATE,OBSERVACION,TITULO};
				}
		}
}
