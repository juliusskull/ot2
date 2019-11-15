package  com.aguasnortesalta.ordenes.model;

public class Componentesxatributos extends Base{
     int id_componente;
  String featid;
  int id_tipo_componente;
  int id_tipo_atributo;
  String valor;
  /*no forman parte de la tabla*/
  public String desc_atributo="";
  public String tipo_dato="";
  
  public String unidad_medida="";
 /*----------------------------*/
	public Componentesxatributos() {
		super();

	}
	public Componentesxatributos( int id_componente, String featid, int id_tipo_componente, int id_tipo_atributo, String valor) {
		super();
	this.id_componente = id_componente;
this.featid = featid;
this.id_tipo_componente = id_tipo_componente;
this.id_tipo_atributo = id_tipo_atributo;
this.valor = valor;

	}
	
            public int getId_componente() {
		        return id_componente;
	        }
	        public void setId_componente(int id_componente) {
                this.id_componente = id_componente;
            }
            public String getFeatid() {
		        return featid;
	        }
	        public void setFeatid(String featid) {
                this.featid = featid;
            }
            public int getId_tipo_componente() {
		        return id_tipo_componente;
	        }
	        public void setId_tipo_componente(int id_tipo_componente) {
                this.id_tipo_componente = id_tipo_componente;
            }
            public int getId_tipo_atributo() {
		        return id_tipo_atributo;
	        }
	        public void setId_tipo_atributo(int id_tipo_atributo) {
                this.id_tipo_atributo = id_tipo_atributo;
            }
            public String getValor() {
		        return valor;
	        }
	        public void setValor(String valor) {
                this.valor = valor;
            }
			public String getComponentesxatributos() {
				// TODO Auto-generated method stub
				return valor;
			}

}