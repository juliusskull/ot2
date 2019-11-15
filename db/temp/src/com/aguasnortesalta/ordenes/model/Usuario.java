package  com.aguasnortesalta.ordenes.model;

public class Usuario extends Base{
  int id_usuario;
  String usuario;
  String email;
  int tipo;

	public Usuario() {
		super();

	}
	public Usuario( int id_usuario, String usuario, String email, int tipo) {
		super();
		this.id_usuario = id_usuario;
		this.usuario = usuario;
		this.email = email;
		this.tipo = tipo;

	}
	
            public int getId_usuario() {
		        return id_usuario;
	        }
	        public void setId_usuario(int id_usuario) {
                this.id_usuario = id_usuario;
            }
            public String getUsuario() {
		        return usuario;
	        }
	        public void setUsuario(String usuario) {
                this.usuario = usuario;
            }
            public String getEmail() {
		        return email;
	        }
	        public void setEmail(String email) {
                this.email = email;
            }
            public int getTipo() {
		        return tipo;
	        }
	        public void setTipo(int tipo) {
                this.tipo = tipo;
            }

}