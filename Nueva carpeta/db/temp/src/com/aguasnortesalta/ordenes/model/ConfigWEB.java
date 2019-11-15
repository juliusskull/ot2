package com.aguasnortesalta.ordenes.model;

public class ConfigWEB extends Config{	
	
	 public ConfigWEB(long version_db, String version_fch, int fotos_ini,
			int fotos_fin, int metros_ini, int metros_fin) {
		super(version_db, version_fch);
		this.fotos_ini = fotos_ini;
		this.fotos_fin = fotos_fin;
		this.metros_ini = metros_ini;
		this.metros_fin = metros_fin;
	}
	 public int fotos_ini;
	 public int fotos_fin;
	 public int metros_ini;
	 public int metros_fin;
	 public int modo_seguro;
	 public int intervalo;
}
