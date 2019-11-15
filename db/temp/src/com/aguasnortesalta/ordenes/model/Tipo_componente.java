package  com.aguasnortesalta.ordenes.model;

public class Tipo_componente extends Base{
     int id_tipo_componente;
  String desc_tipo_componente;
  int id_tipo_servicio;

	public Tipo_componente() {
		super();

	}
	public Tipo_componente( int id_tipo_componente, String desc_tipo_componente, int id_tipo_servicio) {
		super();
	this.id_tipo_componente = id_tipo_componente;
this.desc_tipo_componente = desc_tipo_componente;
this.id_tipo_servicio = id_tipo_servicio;

	}
	
            public int getId_tipo_componente() {
		        return id_tipo_componente;
	        }
	        public void setId_tipo_componente(int id_tipo_componente) {
                this.id_tipo_componente = id_tipo_componente;
            }
            public String getDesc_tipo_componente() {
		        return desc_tipo_componente;
	        }
	        public void setDesc_tipo_componente(String desc_tipo_componente) {
                this.desc_tipo_componente = desc_tipo_componente;
            }
            public int getId_tipo_servicio() {
		        return id_tipo_servicio;
	        }
	        public void setId_tipo_servicio(int id_tipo_servicio) {
                this.id_tipo_servicio = id_tipo_servicio;
            }
			public String getTipo_componente() {
				// TODO Auto-generated method stub
				return desc_tipo_componente;
			}

}