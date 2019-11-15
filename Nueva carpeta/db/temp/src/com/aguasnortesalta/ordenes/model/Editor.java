package  com.aguasnortesalta.ordenes.model;

public class Editor extends Base{
     int id;
  String titulo;
  String content;
  String tipo_content;
  String fch_alta;
  int activo;

	public Editor() {
		super();

	}
	public Editor( int id, String titulo, String content, String tipo_content, String fch_alta, int activo) {
		super();
	this.id = id;
this.titulo = titulo;
this.content = content;
this.tipo_content = tipo_content;
this.fch_alta = fch_alta;
this.activo = activo;

	}
	
            public int getId() {
		        return id;
	        }
	        public void setId(int id) {
                this.id = id;
            }
            public String getTitulo() {
		        return titulo;
	        }
	        public void setTitulo(String titulo) {
                this.titulo = titulo;
            }
            public String getContent() {
		        return content;
	        }
	        public void setContent(String content) {
                this.content = content;
            }
            public String getTipo_content() {
		        return tipo_content;
	        }
	        public void setTipo_content(String tipo_content) {
                this.tipo_content = tipo_content;
            }
            public String getFch_alta() {
		        return fch_alta;
	        }
	        public void setFch_alta(String fch_alta) {
                this.fch_alta = fch_alta;
            }
            public int getActivo() {
		        return activo;
	        }
	        public void setActivo(int activo) {
                this.activo = activo;
            }

}