package  com.aguasnortesalta.ordenes.model;

public class Materialesot extends Base{
     long id;
  String produc;
  String desc_larga;
  String desc_corta;
  String agru_produc;
  String agru_descrip;
  String geren;
  String fchalta;
  String cantidad="0";

	public Materialesot() {
		super();

	}
	public Materialesot( int id, String produc, String desc_larga, String desc_corta, String agru_produc, String agru_descrip, String geren, String fchalta) {
		super();
	this.id = id;
this.produc = produc;
this.desc_larga = desc_larga;
this.desc_corta = desc_corta;
this.agru_produc = agru_produc;
this.agru_descrip = agru_descrip;
this.geren = geren;
this.fchalta = fchalta;

	}
	
            public long getId() {
		        return id;
	        }
	        public void setId(long id) {
                this.id = id;
            }
            public String getProduc() {
		        return produc;
	        }
	        public void setProduc(String produc) {
                this.produc = produc;
            }
            public String getDesc_larga() {
		        return desc_larga;
	        }
	        public void setDesc_larga(String desc_larga) {
                this.desc_larga = desc_larga;
            }
            public String getDesc_corta() {
		        return desc_corta;
	        }
	        public void setDesc_corta(String desc_corta) {
                this.desc_corta = desc_corta;
            }
            public String getAgru_produc() {
		        return agru_produc;
	        }
	        public void setAgru_produc(String agru_produc) {
                this.agru_produc = agru_produc;
            }
            public String getAgru_descrip() {
		        return agru_descrip;
	        }
	        public void setAgru_descrip(String agru_descrip) {
                this.agru_descrip = agru_descrip;
            }
            public String getGeren() {
		        return geren;
	        }
	        public void setGeren(String geren) {
                this.geren = geren;
            }
            public String getFchalta() {
		        return fchalta;
	        }
	        public void setFchalta(String fchalta) {
                this.fchalta = fchalta;
            }
			public String getMaterialesot() {
				// TODO Auto-generated method stub
				return desc_larga;
			}
			public String getCantidad() {
				return cantidad;
			}
			public void setCantidad(String cantidad) {
				this.cantidad = cantidad;
			}

}