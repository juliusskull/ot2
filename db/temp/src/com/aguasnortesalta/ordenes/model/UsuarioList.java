package com.aguasnortesalta.ordenes.model;

import java.util.ArrayList;
import java.util.List;

import com.aguasnortesalta.ordenes.db.DatabaseManager;

public class UsuarioList extends BaseList {
	public static class UsuarioWEB {
		public int id;
		public String usuario;
		public String email;
		public String password;
		public String fchalta;
		public String fchultimoacceso;
		public String fchcad;
		public String rol;
		public String geren;
		public Usuario getUsuario(){
			return new Usuario(this.id, this.usuario, this.email, Integer.parseInt(this.rol));
		}
	}

	public List<UsuarioWEB> data= null;
	public List<Usuario> datos=  new ArrayList<Usuario>();
	public void actualizar( ) {
		// TODO Auto-generated method stub
		for(int i=0;i<data.size();i++){
			datos.add(data.get(i).getUsuario());
			
		}
		
	} ;
	

	
	
}
