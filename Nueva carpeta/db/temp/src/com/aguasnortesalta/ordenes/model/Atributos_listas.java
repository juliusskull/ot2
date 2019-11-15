package  com.aguasnortesalta.ordenes.model;

public class Atributos_listas extends Base{
     int id_tipo_atributo;
  String id_lista;
  String desc_lista;

	public Atributos_listas() {
		super();

	}
	public Atributos_listas( int id_tipo_atributo, String id_lista, String desc_lista) {
		super();
	this.id_tipo_atributo = id_tipo_atributo;
this.id_lista = id_lista;
this.desc_lista = desc_lista;

	}
	
            public int getId_tipo_atributo() {
		        return id_tipo_atributo;
	        }
	        public void setId_tipo_atributo(int id_tipo_atributo) {
                this.id_tipo_atributo = id_tipo_atributo;
            }
            public String getId_lista() {
		        return id_lista;
	        }
	        public void setId_lista(String id_lista) {
                this.id_lista = id_lista;
            }
            public String getDesc_lista() {
		        return desc_lista;
	        }
	        public void setDesc_lista(String desc_lista) {
                this.desc_lista = desc_lista;
            }
			public String getAtributos_listas() {
				// TODO Auto-generated method stub
				return this.desc_lista;
			}

}