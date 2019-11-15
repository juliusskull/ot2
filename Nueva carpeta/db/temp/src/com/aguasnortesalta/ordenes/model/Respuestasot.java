package  com.aguasnortesalta.ordenes.model;

public class Respuestasot extends Base{
     long id_res;
  String desc_res;
  String icon;
  long id_rubro;
  String geren;
  long id_txc;

	public Respuestasot() {
		super();

	}
	public Respuestasot( int id_res, String desc_res, String icon, int id_rubro, String geren, int id_txc) {
		super();
	this.id_res = id_res;
this.desc_res = desc_res;
this.icon = icon;
this.id_rubro = id_rubro;
this.geren = geren;
this.id_txc = id_txc;

	}
	
            public long getId_res() {
		        return id_res;
	        }
	        public void setId_res(long id_res) {
                this.id_res = id_res;
            }
            public String getDesc_res() {
		        return desc_res;
	        }
	        public void setDesc_res(String desc_res) {
                this.desc_res = desc_res;
            }
            public String getIcon() {
		        return icon;
	        }
	        public void setIcon(String icon) {
                this.icon = icon;
            }
            public long getId_rubro() {
		        return id_rubro;
	        }
	        public void setId_rubro(long id_rubro) {
                this.id_rubro = id_rubro;
            }
            public String getGeren() {
		        return geren;
	        }
	        public void setGeren(String geren) {
                this.geren = geren;
            }
            public long getId_txc() {
		        return id_txc;
	        }
	        public void setId_txc(long id_txc) {
                this.id_txc = id_txc;
            }
			@Override
			public String toString() {
				return "Respuestasot [id_res=" + id_res + ", desc_res="
						+ desc_res + ", icon=" + icon + ", id_rubro="
						+ id_rubro + ", geren=" + geren + ", id_txc=" + id_txc
						+ "]";
			}

}