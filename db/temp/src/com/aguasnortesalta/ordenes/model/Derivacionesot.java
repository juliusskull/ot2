package  com.aguasnortesalta.ordenes.model;

public class Derivacionesot extends Base{
     long id_res;
	  String geren;
	  long id_mot_prox;
	  String desc_motivo;

	public Derivacionesot() {
		super();

	}
	public Derivacionesot( int id_res, String geren, int id_mot_prox, String desc_motivo) {
		super();
	this.id_res = id_res;
this.geren = geren;
this.id_mot_prox = id_mot_prox;
this.desc_motivo = desc_motivo;

	}
	
            public long getId_res() {
		        return id_res;
	        }
	        public void setId_res(long id_res) {
                this.id_res = id_res;
            }
            public String getGeren() {
		        return geren;
	        }
	        public void setGeren(String geren) {
                this.geren = geren;
            }
            public long getId_mot_prox() {
		        return id_mot_prox;
	        }
	        public void setId_mot_prox(long id_mot_prox) {
                this.id_mot_prox = id_mot_prox;
            }
            public String getDesc_motivo() {
		        return desc_motivo;
	        }
	        public void setDesc_motivo(String desc_motivo) {
                this.desc_motivo = desc_motivo;
            }
			public String getDerivacionesot() {
				// TODO Auto-generated method stub
				return getDesc_motivo();
			}

}