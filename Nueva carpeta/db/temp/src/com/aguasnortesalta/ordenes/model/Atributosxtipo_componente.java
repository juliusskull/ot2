package  com.aguasnortesalta.ordenes.model;

public class Atributosxtipo_componente extends Base{
     int id_tipo_componente;
  int id_tipo_atributo;
  String desc_atributo;
  String tipo_dato;
  String ID_UNIDAD_MEDIDA;

	public Atributosxtipo_componente() {
		super();

	}
	public Atributosxtipo_componente( int id_tipo_componente, int id_tipo_atributo, String desc_atributo, String tipo_dato, String ID_UNIDAD_MEDIDA) {
		super();
	this.id_tipo_componente = id_tipo_componente;
this.id_tipo_atributo = id_tipo_atributo;
this.desc_atributo = desc_atributo;
this.tipo_dato = tipo_dato;
this.ID_UNIDAD_MEDIDA = ID_UNIDAD_MEDIDA;

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
            public String getDesc_atributo() {
		        return desc_atributo;
	        }
	        public void setDesc_atributo(String desc_atributo) {
                this.desc_atributo = desc_atributo;
            }
            public String getTipo_dato() {
		        return tipo_dato;
	        }
	        public void setTipo_dato(String tipo_dato) {
                this.tipo_dato = tipo_dato;
            }
            public String getID_UNIDAD_MEDIDA() {
		        return ID_UNIDAD_MEDIDA;
	        }
	        public void setID_UNIDAD_MEDIDA(String ID_UNIDAD_MEDIDA) {
                this.ID_UNIDAD_MEDIDA = ID_UNIDAD_MEDIDA;
            }
			public String getAtributosxtipo_componente() {
				// TODO Auto-generated method stub
				return desc_atributo;
			}

}