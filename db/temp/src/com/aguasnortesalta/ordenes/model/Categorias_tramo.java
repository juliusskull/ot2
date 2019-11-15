package  com.aguasnortesalta.ordenes.model;

public class Categorias_tramo extends Base{
     int id_categoria;
  String desc_categoria;
  int id_tipo_servicio;

	public Categorias_tramo() {
		super();

	}
	public Categorias_tramo( int id_categoria, String desc_categoria, int id_tipo_servicio) {
		super();
	this.id_categoria = id_categoria;
this.desc_categoria = desc_categoria;
this.id_tipo_servicio = id_tipo_servicio;

	}
	
            public int getId_categoria() {
		        return id_categoria;
	        }
	        public void setId_categoria(int id_categoria) {
                this.id_categoria = id_categoria;
            }
            public String getDesc_categoria() {
		        return desc_categoria;
	        }
	        public void setDesc_categoria(String desc_categoria) {
                this.desc_categoria = desc_categoria;
            }
            public int getId_tipo_servicio() {
		        return id_tipo_servicio;
	        }
	        public void setId_tipo_servicio(int id_tipo_servicio) {
                this.id_tipo_servicio = id_tipo_servicio;
            }
			public String getCategorias_tramo() {
				// TODO Auto-generated method stub
				return this.desc_categoria;
			}

}