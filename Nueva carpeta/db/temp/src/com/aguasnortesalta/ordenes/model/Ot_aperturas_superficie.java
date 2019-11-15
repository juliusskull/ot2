package  com.aguasnortesalta.ordenes.model;

public class Ot_aperturas_superficie extends Base{
     int id_superficie;
  String descripcion;
  String tipo_apertura;

	public Ot_aperturas_superficie() {
		super();

	}
	public Ot_aperturas_superficie( int id_superficie, String descripcion, String tipo_apertura) {
		super();
	this.id_superficie = id_superficie;
this.descripcion = descripcion;
this.tipo_apertura = tipo_apertura;

	}
	
            public int getId_superficie() {
		        return id_superficie;
	        }
	        public void setId_superficie(int id_superficie) {
                this.id_superficie = id_superficie;
            }
            public String getDescripcion() {
		        return descripcion;
	        }
	        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
            }
            public String getTipo_apertura() {
		        return tipo_apertura;
	        }
	        public void setTipo_apertura(String tipo_apertura) {
                this.tipo_apertura = tipo_apertura;
            }
			@Override
			public String toString() {
				return descripcion;
			}
	        

}