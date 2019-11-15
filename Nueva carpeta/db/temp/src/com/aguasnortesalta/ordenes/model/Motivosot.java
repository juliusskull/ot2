package  com.aguasnortesalta.ordenes.model;

public class Motivosot extends Base{
     int id_motivo;
  String desc_motivo;
  int id_rubro;
  String reg_apertura;
  int id_txc;
  String geren;

	public Motivosot() {
		super();

	}
	public Motivosot( int id_motivo, String desc_motivo, int id_rubro, String reg_apertura, int id_txc, String geren) {
		super();
	this.id_motivo = id_motivo;
this.desc_motivo = desc_motivo;
this.id_rubro = id_rubro;
this.reg_apertura = reg_apertura;
this.id_txc = id_txc;
this.geren = geren;

	}
	
            public int getId_motivo() {
		        return id_motivo;
	        }
	        public void setId_motivo(int id_motivo) {
                this.id_motivo = id_motivo;
            }
            public String getDesc_motivo() {
		        return desc_motivo;
	        }
	        public void setDesc_motivo(String desc_motivo) {
                this.desc_motivo = desc_motivo;
            }
            public int getId_rubro() {
		        return id_rubro;
	        }
	        public void setId_rubro(int id_rubro) {
                this.id_rubro = id_rubro;
            }
            public String getReg_apertura() {
		        return reg_apertura;
	        }
	        public void setReg_apertura(String reg_apertura) {
                this.reg_apertura = reg_apertura;
            }
            public int getId_txc() {
		        return id_txc;
	        }
	        public void setId_txc(int id_txc) {
                this.id_txc = id_txc;
            }
            public String getGeren() {
		        return geren;
	        }
	        public void setGeren(String geren) {
                this.geren = geren;
            }

}