package com.aguasnortesalta.ordenes.db.tables;

import android.provider.BaseColumns;

public class PasosTable {
	public static final String TABLE_NAME = "pasos";
	public static final String VIEW_NAME = "PASOS_VIEW";
	public static class PasosColumns implements BaseColumns {
		public static final String ID_PASO="id_paso";
		public static final String DESC_CAMPO="desc_campo";
		public static final String TIPO="tipo";
		public static final String FOTO="foto";
		public static final String OBLIGATORIO="obligatorio";
		public static  String[] getColumns(){
					return new String[] {  _ID,ID_PASO,DESC_CAMPO,TIPO,FOTO,OBLIGATORIO};
				}
		
		}
	
}
	


