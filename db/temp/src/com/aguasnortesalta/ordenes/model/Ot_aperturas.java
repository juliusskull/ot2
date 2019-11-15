package  com.aguasnortesalta.ordenes.model;

public class Ot_aperturas extends Base{
     long ot;
  String tipo_apertura;
  long id_tipo_material;
  String ancho;
  String largo;
  String profundidad;
  int id_tipo_senial;
  String estado_apertura;
  String fchalta;
  String fchcad;

	public Ot_aperturas() {		
		super();
		tipo_apertura="C";
		

	}
	public Ot_aperturas( int ot, String tipo_apertura, int id_tipo_material, String ancho, String largo, String profundidad, int id_tipo_senial, String estado_apertura, String fchalta, String fchcad) {
		super();
		this.ot = ot;
		this.tipo_apertura = tipo_apertura;
		this.id_tipo_material = id_tipo_material;
		this.ancho = ancho;
		this.largo = largo;
		this.profundidad = profundidad;
		this.id_tipo_senial = id_tipo_senial;
		this.estado_apertura = estado_apertura;
		this.fchalta = fchalta;
		this.fchcad = fchcad;

	}
	
            public long getOt() {
		        return ot;
	        }
	        public void setOt(long ot) {
                this.ot = ot;
            }
            public String getTipo_apertura() {
		        return tipo_apertura;
	        }
	        public void setTipo_apertura(String tipo_apertura) {
                this.tipo_apertura = tipo_apertura;
            }
            public long getId_tipo_material() {
		        return id_tipo_material;
	        }
	        public void setId_tipo_material(long id_tipo_material) {
                this.id_tipo_material = id_tipo_material;
            }
            public String getAncho() {
		        return ancho;
	        }
	        public void setAncho(String ancho) {
                this.ancho = ancho;
            }
            public String getLargo() {
		        return largo;
	        }
	        public void setLargo(String largo) {
                this.largo = largo;
            }
            public String getProfundidad() {
		        return profundidad;
	        }
	        public void setProfundidad(String profundidad) {
                this.profundidad = profundidad;
            }
            public int getId_tipo_senial() {
		        return id_tipo_senial;
	        }
	        public void setId_tipo_senial(int id_tipo_senial) {
                this.id_tipo_senial = id_tipo_senial;
            }
            public String getEstado_apertura() {
		        return estado_apertura;
	        }
	        public void setEstado_apertura(String estado_apertura) {
                this.estado_apertura = estado_apertura;
            }
            public String getFchalta() {
		        return fchalta;
	        }
	        public void setFchalta(String fchalta) {
                this.fchalta = fchalta;
            }
            public String getFchcad() {
		        return fchcad;
	        }
	        public void setFchcad(String fchcad) {
                this.fchcad = fchcad;
            }
			public String getOt_aperturas() {
				// TODO Auto-generated method stub
				return getOt()+"-"+getTipo_apertura();
			}
			public CharSequence getTitulo() {
				// TODO Auto-generated method stub
				return null;
			}

}