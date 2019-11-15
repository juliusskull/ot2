package  com.aguasnortesalta.ordenes.model;

public class Serviciosxmotivo extends Base{
	long id_motivo;
  String desc_motivo;
  long id_servicio;
  String desc_servicio;
  long id_rubro;

	public Serviciosxmotivo() {
		super();

	}
	public Serviciosxmotivo( int id_motivo, String desc_motivo, int id_servicio, String desc_servicio, int id_rubro) {
		super();
	this.id_motivo = id_motivo;
this.desc_motivo = desc_motivo;
this.id_servicio = id_servicio;
this.desc_servicio = desc_servicio;
this.id_rubro = id_rubro;

	}
	
            public long getId_motivo() {
		        return id_motivo;
	        }
	        public void setId_motivo(long id_motivo) {
                this.id_motivo = id_motivo;
            }
            public String getDesc_motivo() {
		        return desc_motivo;
	        }
	        public void setDesc_motivo(String desc_motivo) {
                this.desc_motivo = desc_motivo;
            }
            public long getId_servicio() {
		        return id_servicio;
	        }
	        public void setId_servicio(long id_servicio) {
                this.id_servicio = id_servicio;
            }
            public String getDesc_servicio() {
		        return desc_servicio;
	        }
	        public void setDesc_servicio(String desc_servicio) {
                this.desc_servicio = desc_servicio;
            }
            public long getId_rubro() {
		        return id_rubro;
	        }
	        public void setId_rubro(long id_rubro) {
                this.id_rubro = id_rubro;
            }

}