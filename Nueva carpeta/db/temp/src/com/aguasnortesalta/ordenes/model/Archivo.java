package  com.aguasnortesalta.ordenes.model;

public class Archivo extends Base{
    
	  String nombre;
	  String tipo;
	  String ot;
	  int enviado=0;

	public Archivo() {
		super();

	}
	public Archivo( String nombre, String tipo, String ot) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.ot = ot;

	}
	
            public String getNombre() {
		        return nombre;
	        }
	        public void setNombre(String nombre) {
                this.nombre = nombre;
            }
            public String getTipo() {
		        return tipo;
	        }
	        public void setTipo(String tipo) {
                this.tipo = tipo;
            }
            public String getOt() {
		        return ot;
	        }
	        public void setOt(String ot) {
                this.ot = ot;
            }
			public String getArchivo() {
				// TODO Auto-generated method stub
				return nombre;
			}
			public int getEnviado() {
				return enviado;
			}
			public void setEnviado(int enviado) {
				this.enviado = enviado;
			}
			

}